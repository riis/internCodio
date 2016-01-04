package riis.bankjoy;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity implements TaskCallBack
{
    private Button mLoginButton;
    private EditText mUserNameEditText;
    private EditText mPasswordEditText;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUserNameEditText = (EditText) findViewById(R.id.userNameEditText);
        mPasswordEditText = (EditText) findViewById(R.id.passwordEditText);
        mLoginButton = (Button) findViewById(R.id.loginButton);

        setUpListeners();
    }

    private void setUpListeners()
    {
        mLoginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                new LoginTask(LoginActivity.this).execute(mUserNameEditText.getText().toString(),
                        mPasswordEditText.getText().toString());
            }
        });
    }

    @Override
    public void taskStart()
    {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Logging in");
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
        String loginResponse = (String) result;

        if(loginResponse.isEmpty())
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Error");
            builder.setMessage("Invalid credentials");
            builder.setNeutralButton(android.R.string.ok, null);
            builder.show();
        }
        else
        {
            String token = Util.parseToken(loginResponse);

            if(token.isEmpty())
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Error");
                builder.setMessage("Invalid credentials");
                builder.setNeutralButton(android.R.string.ok, null);
                builder.show();
            }
            else
            {
                SharedPreferences preferences = getSharedPreferences(Vault.PREF_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(Vault.VAULT_STRING, Vault.encrypt(token));
                editor.apply();

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
        }
    }
}
