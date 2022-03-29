package nl.rijksoverheid.rdw.rde.serverdemo.commands;

import net.sf.scuba.smartcards.CardServiceException;
import nl.rijksoverheid.rdw.rde.serverlib.apdusimulator.RdeDocumentSimulator;
import nl.rijksoverheid.rdw.rde.serverdemo.repositories.DocumentRepository;
import nl.rijksoverheid.rdw.rde.serverdemo.components.RbResponseToSecretKeyConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Base64;


@Component
public class GenerateKeyFromDocumentCommand {

    @Autowired
    DocumentRepository documentRepository;

    @Autowired
    RbResponseToSecretKeyConverter rbResponseToSecretKeyConverter;

    public GenerateKeyFromDocumentCommandResult execute(GenerateKeyFromDocumentCommandArgs args) throws GeneralSecurityException, CardServiceException, IOException {
        var document = documentRepository.getById(args.getDocument());
        var simulatedResponse = new RdeDocumentSimulator().getSimulatedAPDUResponse(document.getDataGroup14(), document.getPcdPrivateKey(), document.getFileContents(), document.getFileReadLength());
        var secretKey = rbResponseToSecretKeyConverter.convert(simulatedResponse);
        var result = new GenerateKeyFromDocumentCommandResult();
        result.setSecretKey(Base64.getEncoder().encodeToString(secretKey));
        return result;
    }
}
