package nl.rijksoverheid.rdw.rde.serverdemo.web;

//import nl.rijksoverheid.rdw.rde.serverdemo.api.*;
//import nl.rijksoverheid.rdw.rde.serverdemo.commands.*;
import nl.rijksoverheid.rdw.rde.serverdemo.components.SecurityService;
import nl.rijksoverheid.rdw.rde.serverdemo.entities.Message;
import nl.rijksoverheid.rdw.rde.serverdemo.repositories.DocumentRepository;
import nl.rijksoverheid.rdw.rde.serverdemo.repositories.MessageRepository;
import nl.rijksoverheid.rdw.rde.serverdemo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Base64;

@Controller
public class MessagesController {

//    //TODO move inside send command

    @Autowired
    DocumentRepository documentRepository;

    //TODO move inside send command?
    @Autowired
    MessageRepository messageRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SecurityService securityService;

//    @Autowired
//    SendMessageCommand sendMessageCommand;

    @Autowired
    SecurityService currentAuthenticatedUserProvider;

    @GetMapping("/messages")
    public String index(Model model) {
        var items = messageRepository.findByUser(currentAuthenticatedUserProvider.findLoggedInUsername());
        model.addAttribute("messages", items);
        return "/messages/index";
    }

    @GetMapping("/messages/send")
    public String send(Model model) {
        model.addAttribute("message", new SendMessageDto());
        var recipients = documentRepository.listRecipients(); //Drop down list
        model.addAttribute("recipients", recipients);
        return "/messages/send";
    }

    @PostMapping("/messages/send")
    public String messageSend(@ModelAttribute("message") SendMessageDto sendMessageDto)
    {
        //TODO move to command
        var message = new Message();
        message.setFromUser(userRepository.findByEmail(securityService.findLoggedInUsername()));
        message.setDocument(documentRepository.getById(sendMessageDto.getDocumentId()));
        message.setWhenSent(java.time.LocalDateTime.now()); //TODO utp date time provider
        message.setContent(Base64.getDecoder().decode(sendMessageDto.getResult()));
        message.setNote(sendMessageDto.getNote());
        messageRepository.save(message);

        return "/messages/sendsuccess";
    }
}
