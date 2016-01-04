package riis.bankjoy;

public interface TaskCallBack
{
    void taskStart();
    void taskComplete();
    void handleResult(Object result);
}
