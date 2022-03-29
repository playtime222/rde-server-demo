package nl.rijksoverheid.rdw.rde.serverlib.messaging;

public class NamedParameter
{

    private final String name;
    private final byte[] value;

    public NamedParameter(final String name,final byte[] value)
    {
        if (name == null)
            throw new IllegalArgumentException();

        if (value == null)
            throw new IllegalArgumentException();

        this.name = name;
        this.value = value;
    }

    public String getName()
    {
        return name;
    }

    public byte[] getValue()
    {
        return value;
    }
}
