package riis.bankjoy;

public class Payee
{
    private long mId;
    private String mName;

    public void setId(long id)
    {
        mId = id;
    }

    public long getId()
    {
        return mId;
    }

    public void setName(String name)
    {
        mName = name;
    }

    public String getName()
    {
        return mName;
    }
}
