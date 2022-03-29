package nl.rijksoverheid.rdw.rde.serverdemo.components;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class DocumentEnrollmentIdGenerator {
    public String Next()
    {
        var bytes = new byte[1]; //TODO should be 16 (setting?) - Keep it short for manual entry in client
        new SecureRandom().nextBytes(bytes);
        return Hex.toHexString(bytes);
    }
}


