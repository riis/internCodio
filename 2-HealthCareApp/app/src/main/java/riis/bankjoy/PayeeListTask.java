package riis.bankjoy;

import android.os.AsyncTask;

public class PayeeListTask extends AsyncTask<String, Void, String>
{
    private TaskCallBack mCallBack;

    public PayeeListTask(TaskCallBack callBack)
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
        if(params.length != 1)
        {
            return null;

        }
        return new BankjoyRetriever().getPayeeList(params[0]);
    }

    @Override
    protected void onPostExecute(String result)
    {
        super.onPostExecute(result);
        mCallBack.handleResult(result);
        mCallBack.taskComplete();
    }
}
