package nl.rijksoverheid.rdw.rde.serverlib.messaging.zipV2;

public class Metadata {
    public String[] getFilenames() {
        return filenames;
    }

    public void setFilenames(String[] filenames) {
        this.filenames = filenames;
    }

    private String[] filenames;
}
