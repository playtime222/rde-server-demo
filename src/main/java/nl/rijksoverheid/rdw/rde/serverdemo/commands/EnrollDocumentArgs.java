package nl.rijksoverheid.rdw.rde.serverdemo.commands;

//TODO builder? mapper?
//TODO validation
public class EnrollDocumentArgs
{
    private String enrollmentId;

    //TODO Optional mnemonic only
    private String displayName;

    private byte[] dataGroup14;

    private byte[] pcdPublicKey;
    private byte[] encryptedCommand;

    //TODO allow multiple
    private int shortFileId; //Field on document which is the target of the RB call.
    private byte[] fileContents; //Field on document which is the target of the RB call.
    private int fileReadLength;

    @Deprecated
    private byte[] rbResponse;
    private byte[] pcdPrivateKey;

    public String getEnrollmentId() {
        return enrollmentId;
    }
    public void setEnrollmentId(String enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public byte[] getDataGroup14() {
        return dataGroup14;
    }

    public void setDataGroup14(byte[] dataGroup14) {
        this.dataGroup14 = dataGroup14;
    }

    public byte[] getPcdPublicKey() {
        return pcdPublicKey;
    }

    public void setPcdPublicKey(byte[] pcdPublicKey) {
        this.pcdPublicKey = pcdPublicKey;
    }

    public byte[] getEncryptedCommand() {
        return encryptedCommand;
    }

    public void setEncryptedCommand(byte[] encryptedCommand) {
        this.encryptedCommand = encryptedCommand;
    }

    public int getShortFileId() {
        return shortFileId;
    }

    public void setShortFileId(int shortFileId) {
        this.shortFileId = shortFileId;
    }

    public byte[] getFileContents() {
        return fileContents;
    }

    public void setFileContents(byte[] fileContents) {
        this.fileContents = fileContents;
    }

    public int getFileReadLength() {
        return fileReadLength;
    }

    public void setFileReadLength(int fileReadLength) {
        this.fileReadLength = fileReadLength;
    }

    @Deprecated
    public byte[] getRbResponse() {
        return rbResponse;
    }

    @Deprecated
    public void setRbResponse(byte[] rbResponse) {
        this.rbResponse = rbResponse;
    }

    public byte[] getPcdPrivateKey() {
        return pcdPrivateKey;
    }

    public void setPcdPrivateKey(byte[] pcdPrivateKey) {
        this.pcdPrivateKey = pcdPrivateKey;
    }
}
