package ser421.edu.lab_6_native;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;
import org.json.JSONObject;

public class WeatherReportRestAdapter {
    OkHttpClient client = new OkHttpClient();

    String getLocation(String location) throws IOException {
        Request request = new Request.Builder()
                .url(String.format("http://api.openweathermap.org/data/2.5/weather?q=%s", location))
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
