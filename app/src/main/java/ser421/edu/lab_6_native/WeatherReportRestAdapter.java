package ser421.edu.lab_6_native;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

class WeatherReportRestAdapter extends AsyncTask<String, String, WeatherReport> {

    @Override
    protected WeatherReport doInBackground(String... uri) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response;
        WeatherReport weatherReport = new WeatherReport();
        try {
            response = httpclient.execute(new HttpGet("http://api.openweathermap.org/data/2.5/weather?q=" + uri[0].replace(' ', ',')));
            StatusLine statusLine = response.getStatusLine();
            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                response.getEntity().writeTo(out);
                out.close();
                String responseString = out.toString();

                JSONObject jsonObject;
                try {
                    jsonObject = new JSONObject(responseString);

                    weatherReport.location = uri[0];
                    weatherReport.temperature = jsonObject.getJSONObject("main").getDouble("temp");
                    weatherReport.humidity = jsonObject.getJSONObject("main").getDouble("humidity");
                    weatherReport.windSpeed = jsonObject.getJSONObject("wind").getDouble("speed");
                    weatherReport.cloudCover = jsonObject.getJSONObject("clouds").getDouble("all");
                } catch (Exception e) {

                }
            } else{
                //Closes the connection.
                response.getEntity().getContent().close();
                throw new IOException(statusLine.getReasonPhrase());
            }
        } catch (ClientProtocolException e) {
            //TODO Handle problems..
        } catch (IOException e) {
            //TODO Handle problems..
        }
        return weatherReport;
    }

    @Override
    protected void onPostExecute(WeatherReport result) {
        super.onPostExecute(result);
        //Do anything with response..
    }
}
