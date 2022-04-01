package nl.rijksoverheid.rdw.rde.serverdemo.commands;

import nl.rijksoverheid.rdw.rde.serverdemo.components.SecurityService;
import nl.rijksoverheid.rdw.rde.serverdemo.repositories.MessageMetadata;
import nl.rijksoverheid.rdw.rde.serverdemo.repositories.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListMessagesCommand
{
    MessageRepository messageRepository;
    SecurityService userProvider;

    public ListMessagesCommand(MessageRepository messageRepository, SecurityService userProvider) {
        this.messageRepository = messageRepository;
        this.userProvider = userProvider;
    }

    public List<MessageMetadata> Execute() {
        return messageRepository.findByUser(userProvider.findLoggedInUsername());
    }
}
