package nl.rijksoverheid.rdw.rde.serverdemo.commands;

import nl.rijksoverheid.rdw.rde.serverdemo.components.DocumentEnrollmentIdGenerator;
import nl.rijksoverheid.rdw.rde.serverdemo.components.SecurityService;
import nl.rijksoverheid.rdw.rde.serverdemo.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class EnrollDocumentStartCommand {

    UserRepository userRepository;
    SecurityService userProvider;
    DocumentEnrollmentIdGenerator documentEnrollmentIdGenerator;

    public EnrollDocumentStartCommand(UserRepository userRepository, SecurityService userProvider, DocumentEnrollmentIdGenerator documentEnrollmentIdGenerator) {
        this.userRepository = userRepository;
        this.userProvider = userProvider;
        this.documentEnrollmentIdGenerator = documentEnrollmentIdGenerator;
    }

    public String Execute()
    {
        var emailAddress = userProvider.findLoggedInUsername();
        var user = userRepository.findByEmail(emailAddress);
        var result = documentEnrollmentIdGenerator.Next();
        user.setDocumentEnrollmentId(result);
        userRepository.save(user);
        return result;
    }
}
