package ser421.edu.lab_6_native;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;


public class MainActivity extends Activity {
    protected ArrayList<WeatherReport> weatherReports = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        update(getWindow().getDecorView().findViewById(android.R.id.content));
    }

    public void update(View view) {
        // Create the weather report card generator
        RecyclerView recyclerList = (RecyclerView) findViewById(R.id.cardList);

        // describe the layout of the cards
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerList.setLayoutManager(linearLayoutManager);

        // get the locations from the view
        EditText locationEditElement = (EditText) findViewById(R.id.locations_text);

        // interpret to an array
        String[] locations = locationEditElement.getText().toString().split(",");

        // clear out current reports
        weatherReports = new ArrayList<>();

        // get the weather report for each location
        for (String location : locations) {
            WeatherReport weatherReport = new WeatherReport();
            try {
                weatherReport = new WeatherReportRestAdapter().execute(location).get();
            } catch (Exception e){
                e.printStackTrace();
            }
            weatherReports.add(weatherReport);
        }

        // feed the weather reports to the weather report view adapter
        WeatherReportViewAdapter weatherReportViewAdapter = new WeatherReportViewAdapter(weatherReports);
        recyclerList.setAdapter(weatherReportViewAdapter);
    }

    public void openDetails(View view) {
        Intent detailsActivity = new Intent(this, DetailsActivity.class);
        detailsActivity.putParcelableArrayListExtra("reports", weatherReports);
        startActivity(detailsActivity);
    }

    public void openMap(View view) {
        String geolocation = "geo:" + view.getTag();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(geolocation));

        Intent chooser = Intent.createChooser(intent,"Launch Maps");

        startActivity(chooser);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
