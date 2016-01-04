package riis.horoscope;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONObject;

import java.net.URLDecoder;

import riis.horoscope.databinding.ActivityZodiacDetailBinding;

public class ZodiacDetailActivity extends Activity  {
    public static final String EXTRA_SIGN = "ZodiacSign";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int signNum = getIntent().getIntExtra(EXTRA_SIGN, -1);
        Zodiac zodiac = Zodiac.signs[signNum];

        ActivityZodiacDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_zodiac_detail);
        binding.setVariable(riis.horoscope.BR.zodiacModel, zodiac);

        new AsyncTaskParseJson(zodiac).execute();
    }

    public class AsyncTaskParseJson extends AsyncTask<String, String, String> {
        String yourJsonStringUrl = "http://a.knrz.co/horoscope-api/current/";
        String horoscope = "";

        public AsyncTaskParseJson(Zodiac sign) {
            yourJsonStringUrl += sign.getName().toLowerCase();
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected String doInBackground(String... arg0) {
            try {
                // instantiate our json parser
                JsonParser jParser = new JsonParser();

                // get json string from url
                JSONObject json = jParser.getJSONFromUrl(yourJsonStringUrl);
                horoscope = json.getString("prediction");
                horoscope = URLDecoder.decode(horoscope, "UTF-8");
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String strFromDoInBg) {
            TextView display = (TextView) findViewById(R.id.daily);
            display.setText(horoscope);
        }
    }
}
