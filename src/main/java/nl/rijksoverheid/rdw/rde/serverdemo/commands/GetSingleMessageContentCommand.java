package nl.rijksoverheid.rdw.rde.serverdemo.commands;

import nl.rijksoverheid.rdw.rde.serverdemo.components.SecurityService;
import nl.rijksoverheid.rdw.rde.serverdemo.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetSingleMessageContentCommand
{

    @Autowired
    MessageRepository messageRepository;
    @Autowired
    SecurityService userProvider;

    public byte[] Execute(long id)
    {
        return messageRepository.tryGetContent(id, userProvider.findLoggedInUsername());
    }
}
