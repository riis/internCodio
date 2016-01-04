package riis.bankjoy;

import android.os.AsyncTask;

public class PayeeDetailTask extends AsyncTask<String, Void, String>
{
    private TaskCallBack mCallBack;

    public PayeeDetailTask(TaskCallBack callBack)
    {
        mCallBack = callBack;
    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
        mCallBack.taskStart();
    }

    @Override
    protected String doInBackground(String... params)
    {
        if (params.length != 2)
        {
            return null;
        }

        return new BankjoyRetriever().getPayeeInformation(params[0], params[1]);
    }

    @Override
    protected void onPostExecute(String result)
    {
        super.onPostExecute(result);
        mCallBack.handleResult(result);
        mCallBack.taskComplete();
    }
}
