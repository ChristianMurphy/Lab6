package ser421.edu.lab_6_native;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

class WeatherReportRestAdapter extends AsyncTask<String, String, WeatherReport> {

    @Override
    protected WeatherReport doInBackground(String... uri) {
        // stores the report to be returned
        WeatherReport weatherReport = new WeatherReport();
        try {
            // create holders for client and response
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response;

            // get the data for the location specified
            response = httpclient.execute(new HttpGet("http://api.openweathermap.org/data/2.5/weather?q=" + uri[0].replace(' ', ',')));

            // check the connection is okay
            StatusLine statusLine = response.getStatusLine();
            if(statusLine.getStatusCode() == HttpStatus.SC_OK) {
                // excessive work to get a string
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                response.getEntity().writeTo(out);
                out.close();
                String responseString = out.toString();

                // apparently one try is not enough
                try {
                    // parse the response text into a json object
                    JSONObject jsonObject = new JSONObject(responseString);

                    // map the json values to the weather report values
                    weatherReport.location = uri[0];
                    weatherReport.temperature = jsonObject.getJSONObject("main").getDouble("temp") - 273.15;
                    weatherReport.humidity = jsonObject.getJSONObject("main").getDouble("humidity");
                    weatherReport.windSpeed = jsonObject.getJSONObject("wind").getDouble("speed") * 2.2369362920544;
                    weatherReport.cloudCover = jsonObject.getJSONObject("clouds").getDouble("all");
                } catch (Exception e) {

                }
            }
            // some thing is not okay
            else{
                //Closes the connection.
                response.getEntity().getContent().close();
                throw new IOException(statusLine.getReasonPhrase());
            }
        }
        // something really bad happened
        catch (Exception e) {
            e.printStackTrace();
        }

        // hand back the weather report
        return weatherReport;
    }

    @Override
    protected void onPostExecute(WeatherReport result) {
        super.onPostExecute(result);
    }
}
