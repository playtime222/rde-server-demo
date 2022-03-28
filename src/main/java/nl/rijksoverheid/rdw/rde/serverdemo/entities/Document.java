package nl.rijksoverheid.rdw.rde.serverdemo.entities;

import javax.persistence.*;

@Entity
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User owner;

    @Column(nullable = true)
    //TODO Optional mnemonic only
    private String displayName;
    private byte[] dataGroup14;

    private byte[] pcdPrivateKey; //Server-side/remote encryption
    private byte[] pcdPublicKey;
    private byte[] encryptedCommand;

    //TODO allow multiple
    private int shortFileId; //Field on document which is the target of the RB call.
    private byte[] fileContents; //Field on document which is the target of the RB call.
    private int fileReadLength;

    private byte[] rbResponse; //Demo only

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
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

    public byte[] getRbResponse() {
        return rbResponse;
    }

    public void setRbResponse(byte[] rbResponse) {
        this.rbResponse = rbResponse;
    }

    public void setPcdPrivateKey(byte[] pcdPrivateKey) {
        this.pcdPrivateKey = pcdPrivateKey;
    }

    public byte[] getPcdPrivateKey() {
        return pcdPrivateKey;
    }
}
