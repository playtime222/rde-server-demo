package nl.rijksoverheid.rdw.rde.serverdemo.web;

public class SendMessageDto {

    //Enrolled document which in turn is the receiving user
    private Long documentId;

    //TODO whatever else the simulator needs to create the 'outer' AES secret by running the simulator
    //TODO aka Session Key Z?
    private String ephemeralKeyBase64;

    //Repeat of the plain-text note in the zipped result
    private String note;


    //Properties from the enrolled document for the simulator
    private String fid; //DG14
    private int n; //64

    public int getN() {return n;}
    public void setN(int n) {this.n = n;}

    public String getFcontBase64() {return fcontBase64;}
    public void setFcontBase64(String fcontBase64) {this.fcontBase64 = fcontBase64;}

    public String getEphemeralKeyBase64() {return ephemeralKeyBase64;}
    public void setEphemeralKeyBase64(String ephemeralKeyBase64) {this.ephemeralKeyBase64 = ephemeralKeyBase64;}

    private String fcontBase64; //64
    //TODO Public key from DG14?

    //Result returned by the browser:

    //Base64 of the encrypted/formatted message
    private String result;

    public Long getDocumentId() {return documentId;}
    public void setDocumentId(Long value) {
        documentId = value;}

    public String getNote() {return note;}
    public void setNote(String value) {note = value;}

    public String getResult() { return result;}
    public void setResult(String result) {
        this.result = result;
    }

    public String getFid() {return fid;}
    public void setFid(String fid) {this.fid = fid;}
}
