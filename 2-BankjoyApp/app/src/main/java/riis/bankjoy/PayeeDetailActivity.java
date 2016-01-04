package riis.bankjoy;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class PayeeDetailActivity extends AppCompatActivity implements TaskCallBack
{
    private ProgressDialog mProgressDialog;
    private TextView mNameTextView;
    private TextView mNicknameTextView;
    private TextView mDescriptionTextView;
    private TextView mPhoneNumberTextView;
    private TextView mAddressTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payee_detail);

        long id = getIntent().getExtras().getLong("payeeId");

        mNameTextView = (TextView) findViewById(R.id.payeeNameTextView);
        mNicknameTextView = (TextView) findViewById(R.id.payeeNicknameTextView);
        mAddressTextView = (TextView) findViewById(R.id.payeeAddressTextView);
        mDescriptionTextView = (TextView) findViewById(R.id.payeeDescriptionTextView);
        mPhoneNumberTextView = (TextView) findViewById(R.id.payeePhoneNumberTextView);

        new PayeeDetailTask(this).execute(
                Vault.decrypt(getSharedPreferences(Vault.PREF_NAME, MODE_PRIVATE)
                        .getString(Vault.VAULT_STRING, "")),
                Long.toString(id));
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
        String detailResult = (String) result;

        if(!detailResult.isEmpty())
        {
            try
            {
                JSONObject detailResponse = new JSONObject(detailResult);
                JSONObject dataValue = detailResponse.getJSONObject("dataValue");

                mNameTextView.setText(dataValue.getString("name"));
                mNicknameTextView.setText(dataValue.getString("nickname"));
                mAddressTextView.setText(dataValue.getString("address"));
                mDescriptionTextView.setText(dataValue.getString("description"));
                mPhoneNumberTextView.setText(dataValue.getString("phoneNumber"));
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
    }
}
