package nl.rijksoverheid.rdw.rde.serverdemo.commands;


import nl.rijksoverheid.rdw.rde.serverdemo.components.CurrentAuthenticatedUserProvider;
import nl.rijksoverheid.rdw.rde.serverdemo.components.DocumentEnrollmentIdGenerator;
import nl.rijksoverheid.rdw.rde.serverdemo.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class EnrollDocumentStartCommand {

    UserRepository userRepository;
    CurrentAuthenticatedUserProvider userProvider;
    DocumentEnrollmentIdGenerator documentEnrollmentIdGenerator;

    public EnrollDocumentStartCommand(UserRepository userRepository, CurrentAuthenticatedUserProvider userProvider, DocumentEnrollmentIdGenerator documentEnrollmentIdGenerator) {
        this.userRepository = userRepository;
        this.userProvider = userProvider;
        this.documentEnrollmentIdGenerator = documentEnrollmentIdGenerator;
    }

    public String Execute()
    {
        var emailAddress = userProvider.getUserEmailAddress();
        var user = userRepository.findByEmail(emailAddress);
        var result = documentEnrollmentIdGenerator.Next();
        user.setDocumentEnrollmentId(result);
        userRepository.save(user);
        return result;
    }
}
