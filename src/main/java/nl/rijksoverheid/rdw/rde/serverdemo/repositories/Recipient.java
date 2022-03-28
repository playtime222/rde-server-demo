package nl.rijksoverheid.rdw.rde.serverdemo.repositories;

public class Recipient
{
    public Recipient(long documentId, String email, String displayName) {
        DocumentId = documentId;
        this.email = email;
        this.displayName = displayName;
    }

    long DocumentId;
    String email;
    String displayName;

    public long getDocumentId() {
        return DocumentId;
    }

    public void setDocumentId(long documentId) {
        DocumentId = documentId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
