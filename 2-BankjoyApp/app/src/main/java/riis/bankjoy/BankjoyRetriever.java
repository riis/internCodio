package riis.bankjoy;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;

public class BankjoyRetriever
{
    private static final int TIMEOUT = 5000;
    private static String mUuid = UUID.randomUUID().toString();

    public String login(String userName, String password)
    {
        try
        {
            URL targetUrl = new URL(Vault.getLoginUrl());
            HttpURLConnection urlConnection = (HttpURLConnection) targetUrl.openConnection();
            urlConnection.setConnectTimeout(TIMEOUT);
            urlConnection.setReadTimeout(TIMEOUT);

            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json");

            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);

            JSONObject loginData = new JSONObject();
            loginData.put("Username", userName);
            loginData.put("Password", password);
            loginData.put("Uuid", mUuid);
            loginData.put("Remember", false);

            DataOutputStream outputStream = new DataOutputStream(urlConnection.getOutputStream());
            outputStream.writeBytes(loginData.toString());
            outputStream.flush();
            outputStream.close();

            StringBuilder result = new StringBuilder();

            InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result.append(line);
            }

            urlConnection.disconnect();

            return result.toString();
        }
        catch (IOException | JSONException e)
        {
            e.printStackTrace();
        }

        return "";
    }

    public String getPayeeList(String token)
    {
        try
        {
            URL targetUrl = new URL(Vault.getPayeeListUrl());
            HttpsURLConnection urlConnection = (HttpsURLConnection) targetUrl.openConnection();
            urlConnection.setConnectTimeout(TIMEOUT);
            urlConnection.setReadTimeout(TIMEOUT);

            urlConnection.setRequestMethod("GET");
            urlConnection.setDoInput(true);

            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Authorization", "Token " + token);

            StringBuilder result = new StringBuilder();

            InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result.append(line);
            }

            urlConnection.disconnect();

            return result.toString();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return "";
    }

    public String getPayeeInformation(String token, String id)
    {
        try
        {
            URL targetUrl = new URL(Vault.getPayeeInfoUrl(id));
            HttpsURLConnection urlConnection = (HttpsURLConnection) targetUrl.openConnection();
            urlConnection.setConnectTimeout(TIMEOUT);
            urlConnection.setReadTimeout(TIMEOUT);

            urlConnection.setRequestMethod("GET");
            urlConnection.setDoInput(true);

            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Authorization", "Token " + token);

            StringBuilder result = new StringBuilder();

            InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result.append(line);
            }

            urlConnection.disconnect();

            return result.toString();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return "";
    }
}
