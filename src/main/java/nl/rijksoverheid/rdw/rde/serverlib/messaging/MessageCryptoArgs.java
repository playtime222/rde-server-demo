package nl.rijksoverheid.rdw.rde.serverlib.messaging;



/**
 * This is the part that will go WITH the encrypted message and is NOT encrypted, put the rest in DocumentEnrollment and message/conversation config.
 * NB Fcont is ONLY needed on the server to simulate the RB call to get the SecretKey
 *
 * Serialised to JSON
 */
public class MessageCryptoArgs
{
    //The document the receiver needs to decrypt
    private String documentDisplayName;

    private String cipher; //AES/CBC/NoPadding
    private NamedParameter[] namedCipherParameters = new NamedParameter[0]; //Other encryption parameters e.g. the AES IV

    //Parameters for obtaining the decryption key
    private byte[] caEncryptedCommand; //Need this to obtain
    private byte[] pcdPublicKey; //from enrollment EAC CA session

    //Could get these 2 by doing starting a standard CA session before the decryption call
    private String caProtocolOid; //from enrollment EAC CA session


    public String getCaProtocolOid()
    {
        return caProtocolOid;
    }

    public void setCaProtocolOid(final String caProtocolOid)
    {
        this.caProtocolOid = caProtocolOid;
    }

    public String getDocumentDisplayName()
    {
        return documentDisplayName;
    }

    public void setDocumentDisplayName(final String documentDisplayName) { this.documentDisplayName = documentDisplayName; }

    public String getCipher()
    {
        return cipher;
    }

    public void setCipher(final String cipher)
    {
        this.cipher = cipher;
    }

    public NamedParameter[] getNamedCipherParameters()
    {
        return namedCipherParameters;
    }

    public void setNamedCipherParameters(final NamedParameter[] namedCipherParameters) { this.namedCipherParameters = namedCipherParameters; }

    public byte[] getCaEncryptedCommand()
    {
        return caEncryptedCommand;
    }

    public void setCaEncryptedCommand(final byte[] caEncryptedCommand)
    {
        this.caEncryptedCommand = caEncryptedCommand;
    }

    public byte[] getPcdPublicKey()
    {
        return pcdPublicKey;
    }

    public void setPcdPublicKey(final byte[] pcdPublicKey)
    {
        this.pcdPublicKey = pcdPublicKey;
    }
}

