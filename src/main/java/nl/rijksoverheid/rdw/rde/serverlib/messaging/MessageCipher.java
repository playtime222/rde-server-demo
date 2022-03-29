package nl.rijksoverheid.rdw.rde.serverlib.messaging;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface MessageCipher {

    NamedParameter[] init(SecretKey secretKey, String algorithm, NamedParameter[] parameters)
            throws InvalidAlgorithmParameterException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException;

    byte[] decrypt(byte[] encryptedContent)
            throws BadPaddingException, IllegalBlockSizeException;

    //Parameters will be empty going in for aes
    byte[] encrypt(byte[] plainContent)
            throws BadPaddingException, IllegalBlockSizeException;
}
