package nl.rijksoverheid.rdw.rde.serverdemo;

import nl.rijksoverheid.rdw.rde.messaging.FileArgs;
import nl.rijksoverheid.rdw.rde.messaging.MessageContentArgs;
import nl.rijksoverheid.rdw.rde.messaging.RdeSessionArgs;
import nl.rijksoverheid.rdw.rde.messaging.zipV2.ZipMessageDecoder;
import nl.rijksoverheid.rdw.rde.messaging.zipV2.ZipMessageEncoder;
import org.junit.jupiter.api.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageEncodingV2Tests {

    @Test
    public void RoundTrip() throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, IOException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        final var messageContentArgs = new MessageContentArgs();
        messageContentArgs.add(new FileArgs("argle", "argle...".getBytes(StandardCharsets.UTF_8)));
        messageContentArgs.setUnencryptedNote("note");
        final var rdeSessionArgs = new RdeSessionArgs();
        //TODO rdeSessionArgs.setCipher();

        final var encoder = new ZipMessageEncoder();

        var buffer = new byte[32];
        new SecureRandom().nextBytes(buffer);
        var sk = new SecretKeySpec(buffer, "AES");

        var encoded = encoder.encode(messageContentArgs, rdeSessionArgs, sk);
        
        var decoder = new ZipMessageDecoder();
        var actualRdeSessionArgs = decoder.decodeRdeSessionArgs(encoded);
        assertArrayEquals(rdeSessionArgs.getIv(), actualRdeSessionArgs.getIv());

        var message = decoder.decode(sk);
        assertEquals("note", message.getNote());
        assertEquals("argle", message.getFiles()[0].getFilename());
        assertEquals("argle...", new String(message.getFiles()[0].getContent()));
    }
}
