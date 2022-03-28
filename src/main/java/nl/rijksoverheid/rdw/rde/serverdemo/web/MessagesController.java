package nl.rijksoverheid.rdw.rde.serverdemo.web;

//import nl.rijksoverheid.rdw.rde.serverdemo.api.*;
//import nl.rijksoverheid.rdw.rde.serverdemo.commands.*;
import nl.rijksoverheid.rdw.rde.serverdemo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Controller
public class MessagesController {

//    //TODO move inside send command
//    @Autowired
//    DocumentRepository documentRepository;
//
//    //TODO move inside send command?
//    @Autowired
//    MessageRepository messageRepository;
//
//    @Autowired
//    UserRepository userRepository;
//
//    //TODO move inside send command
//    @Autowired
//    UsernameProvider usernameProvider;
//
////    @Autowired
////    SendMessageCommand sendMessageCommand;

//    @Autowired
//    CurrentAuthenticatedUserProvider currentAuthenticatedUserProvider;

    @GetMapping("/messages")
    public String index(Model model) {
//        var items = messageRepository.findByUser(currentAuthenticatedUserProvider.getUserEmailAddress());
//        model.addAttribute("messages", items);
        return "/messages/index";
    }

//    @GetMapping("/messages/send")
//    public String send(Model model) {
//        model.addAttribute("message", new SendMessageDto());
//        var recipients = documentRepository.listRecipients();
//        model.addAttribute("recipients", recipients);
//        return "/messages/send";
//    }
//
//    @PostMapping("/messages/send")
//    public String messageSend(@ModelAttribute("message") SendMessageDto sendMessageDto)
//        //TODO move to command
//            throws GeneralSecurityException, IOException {
//        //TODO Validation of selected document
//        //TODO error - cant find receiver user
//        //TODO error - cant find document
//        //var docs = documentRepository.findByUser(sendMessageArgs.getToUsername());
//        //if (docs.isEmpty())
//        //  return "No enrolled documents for user.";
//
//        var document = documentRepository.findById(sendMessageDto.getDocumentId()); //TODO error on cannot find
//        var sender = userRepository.findByEmail(usernameProvider.getUserEmailAddress()); //TODO error on cannot find
//
////        //Map DTO to args >>>
////        var file = new FileArgs();
////        file.setName("File 1"); //TODO set in UI
////        file.setContent(sendMessageDto.getContents().getBytes(StandardCharsets.UTF_8)); //TODO all files from UI in binary
////
////        var messageContentArgs = new MessageContentArgs();
////        messageContentArgs.setUnencryptedNote(sendMessageDto.getNote());
////        messageContentArgs.Add(file);
////        messageContentArgs.setUnencryptedNote(sendMessageDto.getNote()); //Handover here
////        //<<<Map DTO to args
////
////        sendMessageCommand.execute(messageContentArgs, document.get(), sender);
//
//        return "/messages/sendsuccess";
//    }
}
