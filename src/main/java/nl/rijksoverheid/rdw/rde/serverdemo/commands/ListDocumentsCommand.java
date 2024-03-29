package nl.rijksoverheid.rdw.rde.serverdemo.commands;

import nl.rijksoverheid.rdw.rde.serverdemo.components.SecurityService;
import nl.rijksoverheid.rdw.rde.serverdemo.repositories.DocumentMetadata;
import nl.rijksoverheid.rdw.rde.serverdemo.repositories.DocumentRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ListDocumentsCommand
{
    DocumentRepository documentRepository;
    SecurityService userProvider;

    public List<DocumentMetadata> Execute() {
        return documentRepository.findByUserEmail(userProvider.findLoggedInUsername());
    }
}
