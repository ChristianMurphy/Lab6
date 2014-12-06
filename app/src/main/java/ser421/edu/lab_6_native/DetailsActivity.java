package ser421.edu.lab_6_native;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;


public class DetailsActivity extends ActionBarActivity {
    ArrayList<WeatherReport> weatherReports = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }
        // get data via the key
        weatherReports = extras.getParcelableArrayList("reports");

        averageTemperature();
        maximumTemperature();
        averageHumidity();
        maximumHumidity();
    }

    public void averageTemperature() {
        double total = 0.0;
        for (int index = 0; index <  weatherReports.size(); index++) {
            total += 1.0;
            total += weatherReports.get(index).temperature;
        }
        total /= weatherReports.size();
        TextView averageTemperature =  (TextView) findViewById(R.id.average_temperature);
        averageTemperature.setText("Average Temperature: " + String.format("%.1f", total) + (char) 0x00B0 + "C");
    }

    public void maximumTemperature() {
        double maximum = weatherReports.get(0).temperature;
        String location = weatherReports.get(0).location;
        for (int index = 1; index <  weatherReports.size(); index++) {
            if (maximum < weatherReports.get(index).temperature){
                maximum = weatherReports.get(index).temperature;
                location = weatherReports.get(index).location;
            }
        }
        TextView maximumTemperature =  (TextView) findViewById(R.id.maximum_temperature);
        maximumTemperature.setText("The hottest city is " + location);
    }

    public void averageHumidity() {
        double total = 0.0;
        for (int index = 0; index <  weatherReports.size(); index++) {
            total += 1.0;
            total += weatherReports.get(index).humidity;
        }
        total /= weatherReports.size();
        TextView averageHumidity =  (TextView) findViewById(R.id.average_humidity);
        averageHumidity.setText("Average Humidity: " + String.format("%.1f", total) + (char) 0x00B0 + "C");
    }

    public void maximumHumidity() {
        double maximum = weatherReports.get(0).humidity;
        String location = weatherReports.get(0).location;
        for (int index = 1; index <  weatherReports.size(); index++) {
            if (maximum < weatherReports.get(index).humidity){
                maximum = weatherReports.get(index).humidity;
                location = weatherReports.get(index).location;
            }
        }
        TextView maximumHumidity =  (TextView) findViewById(R.id.maximum_humidity);
        maximumHumidity.setText("The most humid city is " + location);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
