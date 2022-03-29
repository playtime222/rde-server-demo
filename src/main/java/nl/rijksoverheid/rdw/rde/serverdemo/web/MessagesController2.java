package nl.rijksoverheid.rdw.rde.serverdemo.web;

import net.sf.scuba.smartcards.CardServiceException;
import nl.rijksoverheid.rdw.rde.serverdemo.commands.obsolete.SendMessageCommand;
import nl.rijksoverheid.rdw.rde.serverdemo.components.SecurityService;
import nl.rijksoverheid.rdw.rde.serverdemo.repositories.DocumentRepository;
import nl.rijksoverheid.rdw.rde.serverdemo.repositories.MessageRepository;
import nl.rijksoverheid.rdw.rde.serverdemo.repositories.UserRepository;
import nl.rijksoverheid.rdw.rde.serverlib.messaging.FileArgs;
import nl.rijksoverheid.rdw.rde.serverlib.messaging.MessageContentArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;

@Deprecated(since="Encrypting on server - MITM")
@Controller
public class MessagesController2 {

    @Autowired
    DocumentRepository documentRepository;

    //TODO move inside send command?
    @Autowired
    MessageRepository messageRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SecurityService securityService;

    @Autowired
    SecurityService currentAuthenticatedUserProvider;

    @Autowired
    SendMessageCommand sendMessageCommand;

    @GetMapping("/messages2/send")
    public String send(Model model) {
        model.addAttribute("message", new SendMessageDto());
        var recipients = documentRepository.listRecipients(); //Drop down list
        model.addAttribute("recipients", recipients);
        return "/messages2/send";
    }

    @PostMapping("/messages2/send")
    public String messageSend(@ModelAttribute("message") SendMessageDto2 sendMessageDto)
        throws GeneralSecurityException, IOException, CardServiceException {

        var document = documentRepository.findById(sendMessageDto.getDocumentId()); //TODO error on cannot find
        var loggedInUsername = securityService.findLoggedInUsername();
        var sender = userRepository.findByEmail(loggedInUsername); //TODO error on cannot find

        //Map DTO to args >>>
        var file = new FileArgs();
        file.setName("File 1"); //TODO set in UI
        file.setContent(sendMessageDto.getContents().getBytes(StandardCharsets.UTF_8)); //TODO all files from UI in binary

        var messageContentArgs = new MessageContentArgs();
        messageContentArgs.setUnencryptedNote(sendMessageDto.getNote());
        messageContentArgs.Add(file);
        messageContentArgs.setUnencryptedNote(sendMessageDto.getNote()); //Handover here
        //<<<Map DTO to args

        sendMessageCommand.execute(messageContentArgs, document.get(), sender);
        return "redirect:/messages/sendsuccess";
    }
}
