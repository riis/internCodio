package riis.bankjoy;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TaskCallBack
{
    private ProgressDialog mProgressDialog;
    private RecyclerView mPayeeRecyclerView;
    private TextView mNoDataTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNoDataTextView = (TextView) findViewById(R.id.noDataTextView);
        mPayeeRecyclerView = (RecyclerView) findViewById(R.id.payeeRecyclerView);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mPayeeRecyclerView.setLayoutManager(layoutManager);

        new PayeeListTask(this).execute(
                Vault.decrypt(getSharedPreferences(Vault.PREF_NAME, MODE_PRIVATE)
                        .getString(Vault.VAULT_STRING, "")));
    }

    @Override
    public void onBackPressed() {
        getSharedPreferences(Vault.PREF_NAME, MODE_PRIVATE).edit().clear().apply();
        super.onBackPressed();
    }

    @Override
    public void taskStart()
    {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading");
        mProgressDialog.show();
    }

    @Override
    public void taskComplete()
    {
        mProgressDialog.dismiss();
    }

    @Override
    public void handleResult(Object result)
    {
        String payeeResponse = (String) result;

        if(payeeResponse.isEmpty())
        {
            mNoDataTextView.setVisibility(View.VISIBLE);
        }
        else
        {
            ArrayList<Payee> payeeList = new ArrayList<>();

            try {
                JSONObject payeeResult = new JSONObject(payeeResponse);
                JSONArray dataValue = payeeResult.getJSONArray("dataValue");

                for (int i = 0; i < dataValue.length(); i++)
                {
                    Payee payee = new Payee();
                    payee.setId(dataValue.getJSONObject(i).getLong("id"));
                    payee.setName(dataValue.getJSONObject(i).getString("name"));
                    payeeList.add(payee);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if (payeeList.size() > 0)
            {
                mNoDataTextView.setVisibility(View.GONE);
                mPayeeRecyclerView.setAdapter(new PayeeListAdapter(this, payeeList));
            }
            else
            {
                mNoDataTextView.setVisibility(View.VISIBLE);
            }
        }
    }
}
