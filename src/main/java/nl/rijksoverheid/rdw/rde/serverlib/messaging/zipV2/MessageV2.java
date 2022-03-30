package nl.rijksoverheid.rdw.rde.serverlib.messaging.zipV2;

import nl.rijksoverheid.rdw.rde.serverlib.messaging.Message;

public class MessageV2 {
    private final String note;
    private final RdeSessionArgs rdeSessionArgs;
    private final MessageFile[] files;

    public MessageV2(String note, RdeSessionArgs rdeSessionArgs, MessageFile[] objects) {

        this.note = note;
        this.rdeSessionArgs = rdeSessionArgs;
        this.files = objects;
    }

    public String getNote() {
        return note;
    }

    public MessageFile[] getFiles() {
        return files;
    }

    public RdeSessionArgs getRdeSessionArgs() {
        return rdeSessionArgs;
    }
}
