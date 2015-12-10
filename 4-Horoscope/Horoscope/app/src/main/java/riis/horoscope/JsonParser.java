package riis.horoscope;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonParser {
	final String TAG = "JsonParser.java";
	private static final int TIMEOUT = 5000;

	public JSONObject getJSONFromUrl(String url) {
		// make HTTP request
		try {
			URL targetUrl = new URL(url);
			HttpURLConnection urlConnection = (HttpURLConnection) targetUrl.openConnection();
			urlConnection.setRequestMethod("GET");
			urlConnection.setConnectTimeout(TIMEOUT);
			urlConnection.setReadTimeout(TIMEOUT);

			InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            JSONObject jsonObject = createJsonObject(bufferedReader);

            urlConnection.disconnect();

            return jsonObject;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

    protected JSONObject createJsonObject(BufferedReader reader) {
        try {
            StringBuilder sb = new StringBuilder();
            JSONObject jsonObject;
            String line;
            String json;

            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }

            json = sb.toString();
            jsonObject = new JSONObject(json);

            return jsonObject;
        } catch (Exception e) {
            Log.e(TAG, "Error converting result " + e.toString());
        }

        return null;
    }

    public boolean isValidJSON(String horoscope){
		try {
			new JSONObject(horoscope);
			return true;
		} catch (JSONException e) {
            e.printStackTrace();
			return false;
		}
	}
}
