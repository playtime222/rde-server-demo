package nl.rijksoverheid.rdw.rde.serverdemo.components;

import nl.rijksoverheid.rdw.rde.serverdemo.commands.obsolete.SendMessageCommand;
import nl.rijksoverheid.rdw.rde.serverdemo.repositories.MessageRepository;
import nl.rijksoverheid.rdw.rde.serverlib.apdusimulator.RdeDocumentSimulator;
import nl.rijksoverheid.rdw.rde.serverlib.messaging.MessageContentArgs;
import nl.rijksoverheid.rdw.rde.serverlib.messaging.MessageCryptoArgs;
import nl.rijksoverheid.rdw.rde.serverlib.messaging.MessageFormatter;
import nl.rijksoverheid.rdw.rde.serverlib.messaging.ciphers.AesMessageCipher;
import nl.rijksoverheid.rdw.rde.serverlib.messaging.zipV1.ZipMessageFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Component
public class Beans {
    @Bean
    public CurrentAuthenticatedUserProvider currentAuthenticatedUserProvider() {return new CurrentAuthenticatedUserProvider(); }

    @Bean
    public SendMessageCommand sendMessageCommand(MessageRepository messageRepository)
    {
        return new SendMessageCommand(
                messageRepository,
                new ZipMessageFormatter(new AesMessageCipher()),
                new RbResponseToSecretKeyConverter(),
                new RdeDocumentSimulator());
    }
}
