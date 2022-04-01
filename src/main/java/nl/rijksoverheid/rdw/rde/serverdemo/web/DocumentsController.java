package nl.rijksoverheid.rdw.rde.serverdemo.web;

import nl.rijksoverheid.rdw.rde.serverdemo.commands.EnrollDocumentStartCommand;
import nl.rijksoverheid.rdw.rde.serverdemo.components.SecurityService;
import nl.rijksoverheid.rdw.rde.serverdemo.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//Avoid name DocumentController
@Controller
public class DocumentsController {

    @Autowired
    EnrollDocumentStartCommand enrollDocumentStartCommand;

    @Autowired
    DocumentRepository documentRepository;

    @Autowired
    SecurityService currentAuthenticatedUserProvider;

    //Set an enrollment id and display it
    //TODO QR code
    //NB This is only required for an enrollment WITHOUT logging in.
    @GetMapping("/documents/add")
    public String EnrollmentStart(Model model)
    {
        var newEnrollmentToken = enrollDocumentStartCommand.Execute();
        model.addAttribute("enrollmentToken", newEnrollmentToken);
        return "documents/add";
    }

    @GetMapping("/documents")
    public String index(Model model)
    {
        var emailAddress = currentAuthenticatedUserProvider.findLoggedInUsername();
        var items = documentRepository.findByUserEmail(emailAddress);
        model.addAttribute("documents", items);
        return "documents/index";
    }
}
