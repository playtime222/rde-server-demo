package nl.rijksoverheid.rdw.rde.serverdemo.web;

public class SendMessageDto {

//    private Long documentId; //Public cos Thymeleaf...

    //TODO rest of the MessageCryptoSpecs
    //private String fid; //DG14
    //private int n; //64

    //TODO multiple files
    private String contents;

//    public Long getDocumentId() {
//        return documentId;
//    }
//    public void setDocumentId(Long documentId) {
//        this.documentId = documentId;
//    }
//

    //Base64 zipped content
    public String getContents() {
        return contents;
    }
    public void setContents(String contents) {
        this.contents = contents;
    }
}
