package nl.rijksoverheid.rdw.rde.serverlib.messaging.zipV1;

import com.google.gson.Gson;
import nl.rijksoverheid.rdw.rde.serverlib.messaging.FileArgs;
import nl.rijksoverheid.rdw.rde.serverlib.messaging.Message;
import nl.rijksoverheid.rdw.rde.serverlib.messaging.MessageCipher;
import nl.rijksoverheid.rdw.rde.serverlib.messaging.MessageCryptoArgs;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.zip.ZipInputStream;

@Deprecated
//@Service
public class ZipMessageParser
{
    private final MessageCipher messageCipher;

    String note;
    MessageCryptoArgs messageCryptoArgs;

    //TODO Problem - this already has to be the correct cipher
    public ZipMessageParser(final MessageCipher messageCipher) {
        this.messageCipher = messageCipher;
    }

    public static MessageCryptoArgs deserializeMessageCryptoArgs(final byte[] message) throws IOException
    {
        final var json = new String(readEntry(message, ZipMessageFormatter.CryptoArgsEntryName), StandardCharsets.UTF_8);
        return new Gson().fromJson(json, MessageCryptoArgs.class);
    }

    public Message deserialize(final byte[] message, final SecretKey secretKey)
            throws IOException, BadPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, NoSuchPaddingException, InvalidAlgorithmParameterException
    {
        note = new String(readEntry(message, ZipMessageFormatter.NoteEntryName), StandardCharsets.UTF_8);

        final var json = new String(readEntry(message, ZipMessageFormatter.CryptoArgsEntryName), StandardCharsets.UTF_8);
        messageCryptoArgs = new Gson().fromJson(json, MessageCryptoArgs.class);
        messageCipher.init(secretKey, messageCryptoArgs.getCipher(), messageCryptoArgs.getNamedCipherParameters());

        final var filenames=getFilenames(message);

        final var files = new FileArgs[filenames.length];

        for (var i = 0; i < filenames.length; i++)
        {
            var enc = readEntry(message, ZipMessageFormatter.getEntryName(i));
            var dec = messageCipher.decrypt(enc);
            files[i] = new FileArgs(filenames[i], dec);
        }

        return new Message(note, messageCryptoArgs, files);
    }

    private static byte[] readEntry(final byte[] zipBytes,final String name) throws IOException
    {
        try(final var input = new ByteArrayInputStream(zipBytes))
        {
            try(final var stream = new ZipInputStream(input))
            {
                var zipEntry = stream.getNextEntry();
                while (zipEntry != null)
                {
                    if (zipEntry.getName().equals(name))
                    {
                        final var buffer = new byte[2048];
                        final var result = new ByteArrayOutputStream();
                        int len = stream.read(buffer);
                        while (len > 0)
                        {
                            result.write(buffer, 0, len);
                            len = stream.read(buffer);
                        }
                        return result.toByteArray();
                    }
                    zipEntry = stream.getNextEntry();
                }
                throw new IllegalStateException("Entry not found.");
            }
        }
    }

    private String[] getFilenames(final byte[] message) throws IOException, BadPaddingException, IllegalBlockSizeException
    {
        final var enc = readEntry(message, ZipMessageFormatter.ManifestEntryName);
        final var dec = messageCipher.decrypt(enc);
        final var str = new String(dec, StandardCharsets.UTF_8);
        return str.split(ZipMessageFormatter.Separator);
    }
}