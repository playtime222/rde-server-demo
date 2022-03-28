package nl.rijksoverheid.rdw.rde.serverdemo.components;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Beans {
    @Bean
    public CurrentAuthenticatedUserProvider currentAuthenticatedUserProvider() {return new CurrentAuthenticatedUserProvider(); }
}
