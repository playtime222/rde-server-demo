package nl.rijksoverheid.rdw.rde.serverlib.remoteapi;

public class DocumentAddResult {

    private EnrollDocumentResult result;

    public DocumentAddResult(final EnrollDocumentResult result) {
        this.result = result;
    }

    public EnrollDocumentResult getResult() {
        return result;
    }

}
