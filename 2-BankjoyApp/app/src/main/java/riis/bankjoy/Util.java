package riis.bankjoy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Util
{
    public static String parseToken(String loginResponse)
    {
        String token = "";
        try
        {
            JSONObject loginResult = new JSONObject(loginResponse);
            JSONObject dataValue = loginResult.getJSONObject("dataValue");
            token = dataValue.getString("token");
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return token;
    }

    public static ArrayList<Payee> parsePayeeList(String payeeResponse)
    {
        ArrayList<Payee> payeeList = new ArrayList<>();

        try
        {
            JSONObject payeeResult = new JSONObject(payeeResponse);
            JSONArray dataValue = payeeResult.getJSONArray("dataValue");

            for (int i = 0; i < dataValue.length(); i++)
            {
                Payee payee = new Payee();
                payee.setId(dataValue.getJSONObject(i).getLong("id"));
                payee.setName(dataValue.getJSONObject(i).getString("name"));
                payeeList.add(payee);
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return payeeList;
    }
}
