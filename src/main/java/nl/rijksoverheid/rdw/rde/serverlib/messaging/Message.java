package nl.rijksoverheid.rdw.rde.serverlib.messaging;

/**
 * Unencrypted content
 */
public class Message //implements Parcelable
{
    private final String note;
    private final MessageCryptoArgs messageCryptoArgs;
    private final FileArgs[] files;

    public Message(final String note, final MessageCryptoArgs messageCryptoArgs, final FileArgs[] files)
    {
        this.note = note;
        this.messageCryptoArgs = messageCryptoArgs;
        this.files = files;
    }

    public String getNote()
    {
        return note;
    }

    public MessageCryptoArgs getMessageCryptoArgs()
    {
        return messageCryptoArgs;
    }

    public FileArgs[] getFiles()
    {
        return files;
    }

//    @Override
//    public int describeContents()
//    {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(final Parcel parcel, final int i)
//    {
//
//    }
}