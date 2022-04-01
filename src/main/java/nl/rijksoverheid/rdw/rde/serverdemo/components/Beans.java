package nl.rijksoverheid.rdw.rde.serverdemo.components;

import nl.rijksoverheid.rdw.rde.serverdemo.commands.obsolete.SendMessageCommand;
import nl.rijksoverheid.rdw.rde.serverdemo.repositories.MessageRepository;
import nl.rijksoverheid.rdw.rde.crypto.RbResponseToSecretKeyConverter;
import nl.rijksoverheid.rdw.rde.apdusimulator.RdeDocumentSimulator;
import nl.rijksoverheid.rdw.rde.messaging.zipV2.ZipMessageEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Beans {
//    @Bean
//    public CurrentAuthenticatedUserProvider currentAuthenticatedUserProvider() {return new CurrentAuthenticatedUserProvider(); }

    @Bean
    public RbResponseToSecretKeyConverter rbResponseToSecretKeyConverter () {return new RbResponseToSecretKeyConverter();}

    @Bean
    public SendMessageCommand sendMessageCommand(MessageRepository messageRepository)
    {
        return new SendMessageCommand(
                messageRepository,
                new ZipMessageEncoder(),
                new RbResponseToSecretKeyConverter(),
                new RdeDocumentSimulator());
    }
}
