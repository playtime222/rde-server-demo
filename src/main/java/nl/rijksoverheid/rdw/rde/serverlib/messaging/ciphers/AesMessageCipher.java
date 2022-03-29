package nl.rijksoverheid.rdw.rde.serverlib.messaging.ciphers;


import nl.rijksoverheid.rdw.rde.serverlib.messaging.MessageCipher;
import nl.rijksoverheid.rdw.rde.serverlib.messaging.NamedParameter;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Deprecated(since="Only AES is to be supported. No requirement for NameParameter mechanism.", forRemoval = true)
public class AesMessageCipher implements MessageCipher
{
    private static final String IvParameterName = "IV";

    private Cipher cipher;

    //Send iv in named parameters if decrypting
    //If encrypting, iv will be added to parameters
    @Override
    public NamedParameter[] init(final SecretKey secretKey, final String algorithm, final NamedParameter[] parameters)
            throws InvalidAlgorithmParameterException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException
    {
        if (secretKey == null)
            throw new IllegalArgumentException();
        if (algorithm == null)
            throw new IllegalArgumentException();
        if (parameters == null)
            throw new IllegalArgumentException();

        if (cipher != null)
            throw new IllegalStateException("Already initialised");

        cipher = Cipher.getInstance(algorithm);

        //Decrypt mode.
        if (parameters.length == 1 && parameters[0].getName().equalsIgnoreCase(IvParameterName))
        {
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(parameters[0].getValue()));
            return parameters;
        }

        //Encrypt mode.
        if (parameters.length == 0)
        {
            final var iv = generateIv();
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(iv)); //parameters? GetIv?
            return new NamedParameter[] {new NamedParameter(IvParameterName, iv)};
        }

        throw new IllegalArgumentException("Unexpected parameters length or items.");
    }

    @Override
    public byte[] decrypt(final byte[] encryptedContent)
            throws BadPaddingException, IllegalBlockSizeException
    {
        if (encryptedContent == null)
            throw new IllegalArgumentException();

        return cipher.doFinal(encryptedContent);
    }

    private byte[] generateIv() {
        var result = new byte[16];
        new SecureRandom().nextBytes(result);
        return result;
    }

    //Parameters will be empty going in for aes
    @Override
    public byte[] encrypt(final byte[] plainContent)
            throws BadPaddingException, IllegalBlockSizeException
    {
        if (plainContent == null)
            throw new IllegalArgumentException();

        return cipher.doFinal(plainContent);
    }
}
