package nl.rijksoverheid.rdw.rde.serverlib.messaging.zipV1;

import com.google.gson.Gson;
import nl.rijksoverheid.rdw.rde.serverlib.messaging.MessageCipher;
import nl.rijksoverheid.rdw.rde.serverlib.messaging.MessageContentArgs;
import nl.rijksoverheid.rdw.rde.serverlib.messaging.MessageCryptoArgs;
import nl.rijksoverheid.rdw.rde.serverlib.messaging.MessageFormatter;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


@Deprecated
//TODO add signatures and versions - zip in zip?
//@Service
public class ZipMessageFormatter implements MessageFormatter
{
    public static final String NoteEntryName = "unencryptedNote";
    public static final String CryptoArgsEntryName = "cryptoArgs";
    public static final String ManifestEntryName = "manifest";
    public static final String Separator = "<<<<";

    private final MessageCipher messageCipher;

    public ZipMessageFormatter(final MessageCipher messageCipher) {

        this.messageCipher = messageCipher;
    }

    //TODO version marker entry
    @Override
    public byte[] serialize(final MessageContentArgs messageArgs, final MessageCryptoArgs messageCryptoArgs, final SecretKey secretKey)
            throws IOException, NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException
    {
        messageCryptoArgs.setNamedCipherParameters(messageCipher.init(secretKey, messageCryptoArgs.getCipher(), messageCryptoArgs.getNamedCipherParameters()));

        try(var result = new ByteArrayOutputStream())
        {
            try(var zipStream = new ZipOutputStream(result))
            {
                write(zipStream,NoteEntryName, messageArgs.getUnencryptedNote().getBytes(StandardCharsets.UTF_8));

                var json = new Gson().toJson(messageCryptoArgs);
                write(zipStream, CryptoArgsEntryName, json.getBytes(StandardCharsets.UTF_8));

                var manifestItems = new ArrayList<String>();
                for (var i = 0; i < messageArgs.getFileArgs().length; i++)
                {
                    var item = messageArgs.getFileArgs()[i];
                    manifestItems.add(item.getName());

                    var encryptResult = messageCipher.encrypt(item.getContent());
                    write(zipStream, getEntryName(i), encryptResult);
                }

                write(zipStream, ManifestEntryName, messageCipher.encrypt(String.join(Separator, manifestItems).getBytes(StandardCharsets.UTF_8)));

                return result.toByteArray();
            }
        }
    }

    public static String getEntryName(final int i)
    {
        return String.format("%03d", i);
    }

    private void write(final ZipOutputStream zipStream, final String name, final byte[] content) throws IOException
    {
        var entry = new ZipEntry(name);
        zipStream.putNextEntry(entry);
        zipStream.write(content);
        zipStream.closeEntry();
    }
}
