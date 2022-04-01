package nl.rijksoverheid.rdw.rde.serverdemo.commands.obsolete;

import net.sf.scuba.smartcards.CardServiceException;
import nl.rijksoverheid.rdw.rde.serverdemo.components.Hex;
import nl.rijksoverheid.rdw.rde.serverdemo.entities.Document;
import nl.rijksoverheid.rdw.rde.serverdemo.entities.Message;
import nl.rijksoverheid.rdw.rde.serverdemo.entities.User;
import nl.rijksoverheid.rdw.rde.serverdemo.repositories.MessageRepository;
import nl.rijksoverheid.rdw.rde.messaging.RdeSessionArgs;
import nl.rijksoverheid.rdw.rde.mrtdfiles.Dg14Reader;
import nl.rijksoverheid.rdw.rde.apdusimulator.RdeDocumentSimulator;
import nl.rijksoverheid.rdw.rde.messaging.MessageContentArgs;
import nl.rijksoverheid.rdw.rde.messaging.MessageEncoder;
import nl.rijksoverheid.rdw.rde.crypto.RbResponseToSecretKeyConverter;

import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * Build message, encrypt contents, write to db
 */
@Deprecated(since = "Encrypted on server - MITM", forRemoval = true)
public class SendMessageCommand {

    MessageRepository messageRepository;
    MessageEncoder messageEncoder;
    RbResponseToSecretKeyConverter rbResponseToSecretKeyConverter;
    RdeDocumentSimulator rdeDocumentSimulator;

    public SendMessageCommand(MessageRepository messageRepository, MessageEncoder messageEncoder, RbResponseToSecretKeyConverter rbResponseToSecretKeyConverter, RdeDocumentSimulator rdeDocumentSimulator) {
        this.messageRepository = messageRepository;
        this.messageEncoder = messageEncoder;
        this.rbResponseToSecretKeyConverter = rbResponseToSecretKeyConverter;
        this.rdeDocumentSimulator = rdeDocumentSimulator;
    }

    public void execute(MessageContentArgs messageContentArgs, Document document, User sender)
            throws GeneralSecurityException, IOException, CardServiceException
    {
        if (messageContentArgs == null)
            throw new IllegalArgumentException();
        if (document == null)
            throw new IllegalArgumentException();
        if (sender == null)
            throw new IllegalArgumentException();

        var dg14 = new Dg14Reader(document.getDataGroup14());
        //var agreementAlg =  ChipAuthenticationInfo.toKeyAgreementAlgorithm(dg14.getCaSessionInfo().getCaInfo().getObjectIdentifier());

        //TODO check args are valid
        var messageCryptoArgs = new RdeSessionArgs();
        messageCryptoArgs.setDocumentDisplayName(document.getDisplayName());
        messageCryptoArgs.setCipher("AES/CBC/PKCS5Padding"); //N//IV - NameParameters - set in the formatter call
        messageCryptoArgs.setCaEncryptedCommand(document.getEncryptedCommand());
        messageCryptoArgs.setPcdPublicKey(document.getPcdPublicKey());
        messageCryptoArgs.setCaProtocolOid(dg14.getCaSessionInfo().getCaInfo().getObjectIdentifier());

        //var demoSimulatedResponse = new CheatRdeDocumentSimulator().getEncryptedResponse(document);
        var simulatedResponse = new RdeDocumentSimulator().getSimulatedAPDUResponse(document.getDataGroup14(), document.getPcdPrivateKey(), document.getFileContents(), document.getFileReadLength());
        //System.out.println("DEMO      RESPONSE:" + Hex.toHexString(demoSimulatedResponse));
        System.out.println("SIMULATED RESPONSE:" + Hex.toHexString(simulatedResponse));

        //if (!Arrays.equals(demoSimulatedResponse, simulatedResponse))
        //    throw new IllegalStateException("DEMO - simulation does not produce correct response.");

        var secretKey = rbResponseToSecretKeyConverter.convert(simulatedResponse);
        var content = messageEncoder.encode(messageContentArgs, messageCryptoArgs, new SecretKeySpec(secretKey, "AES"));
        System.out.println("SECRET KEY      RESPONSE:" + Hex.toHexString(secretKey));
        System.out.println("CONTENT:" + Hex.toHexString(content));

        //Build and save entity
        var message = new Message();
        message.setFromUser(sender);
        message.setDocument(document);
        message.setContent(content);
        message.setNote(messageContentArgs.getUnencryptedNote()); //Repeat metadata for UI
        message.setWhenSent(java.time.LocalDateTime.now()); //TODO utp date time provider
        messageRepository.save(message);
    }
}
