package nl.rijksoverheid.rdw.rde.serverdemo.commands;

import nl.rijksoverheid.rdw.rde.serverdemo.components.SecurityService;
import nl.rijksoverheid.rdw.rde.serverdemo.entities.Document;
import nl.rijksoverheid.rdw.rde.serverdemo.repositories.DocumentRepository;
import nl.rijksoverheid.rdw.rde.serverdemo.repositories.UserRepository;
import nl.rijksoverheid.rdw.rde.serverlib.remoteapi.EnrollDocumentResult;
import org.springframework.stereotype.Service;

@Service
public class EnrollDocumentCommand {

    private final UserRepository userRepository;
    private final DocumentRepository documentRepository;
    private final SecurityService currentAuthenticatedUserProvider;

    public EnrollDocumentCommand(UserRepository userRepository, DocumentRepository documentRepository, SecurityService currentAuthenticatedUserProvider) {
        this.userRepository = userRepository;
        this.documentRepository = documentRepository;
        this.currentAuthenticatedUserProvider = currentAuthenticatedUserProvider;
    }

    public EnrollDocumentResult Execute(EnrollDocumentArgs args)
    {
        //TODO find user by enrollment id
        //var user = userRepository.findByDocumentEnrollmentId(args.getEnrollmentId());
        //if (user == null)
        //  return EnrollDocumentResult.EnrollmentIdInvalid;

        var user = userRepository.findByEmail(currentAuthenticatedUserProvider.findLoggedInUsername());

        //Map
        var doc = new Document();
        doc.setOwner(user);

        doc.setDisplayName(args.getDisplayName());
        doc.setShortFileId(args.getShortFileId());
        doc.setFileContents(args.getFileContents());

        doc.setFileReadLength(args.getFileReadLength());
        doc.setEncryptedCommand(args.getEncryptedCommand());

        doc.setRbResponse(args.getRbResponse()); //TODO remove cheat

        doc.setPcdPublicKey(args.getPcdPublicKey());
        doc.setPcdPrivateKey(args.getPcdPrivateKey());
        doc.setDataGroup14(args.getDataGroup14());

        try {
            documentRepository.save(doc);
            return EnrollDocumentResult.Success;
        }
        catch (Exception ex) //TODO unique index handling
        {
            ex.printStackTrace();
            //Assume dupe
            return EnrollDocumentResult.Duplicate;
        }
    }
}
