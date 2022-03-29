package nl.rijksoverheid.rdw.rde.serverdemo.web;

@Deprecated(since="Encryption on server - MITM")
public class SendMessageDto2 {

    private Long documentId; //Public cos Thymeleaf...
    private String note;

    //TODO rest of the MessageCryptoSpecs
    //private String fid; //DG14
    //private int n; //64

    //TODO multiple files
    private String contents;

    public Long getDocumentId() {
        return documentId;
    }
    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }

    public String getContents() {
        return contents;
    }
    public void setContents(String contents) {
        this.contents = contents;
    }
}
