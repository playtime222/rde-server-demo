package nl.rijksoverheid.rdw.rde.serverlib.messaging;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface MessageFormatter {
    //TODO version marker entry
    //@RequiresApi(api = Build.VERSION_CODES.O)
    byte[] serialize(final MessageContentArgs messageArgs, final MessageCryptoArgs messageCryptoArgs, final SecretKey secretKey)
            throws IOException, NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException;
}
