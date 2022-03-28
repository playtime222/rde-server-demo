package nl.rijksoverheid.rdw.rde.serverdemo.commands;

import nl.rijksoverheid.rdw.rde.serverdemo.components.CurrentAuthenticatedUserProvider;
import nl.rijksoverheid.rdw.rde.serverdemo.repositories.MessageMetadata;
import nl.rijksoverheid.rdw.rde.serverdemo.repositories.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListMessagesCommand
{
    MessageRepository messageRepository;
    CurrentAuthenticatedUserProvider userProvider;

    public ListMessagesCommand(MessageRepository messageRepository, CurrentAuthenticatedUserProvider userProvider) {
        this.messageRepository = messageRepository;
        this.userProvider = userProvider;
    }

    public List<MessageMetadata> Execute() {
        return messageRepository.findByUser(userProvider.getUserEmailAddress());
    }
}
