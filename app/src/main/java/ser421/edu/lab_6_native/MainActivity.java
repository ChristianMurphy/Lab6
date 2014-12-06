package ser421.edu.lab_6_native;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {
    protected List<WeatherReport> weatherReports = new ArrayList<WeatherReport>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        update(getWindow().getDecorView().findViewById(android.R.id.content));
    }

    public void update(View view) {
        RecyclerView recyclerList = (RecyclerView) findViewById(R.id.cardList);
        recyclerList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerList.setLayoutManager(linearLayoutManager);
        List<WeatherReport> weatherReports = new ArrayList<WeatherReport>();
        EditText locationEditElement = (EditText) findViewById(R.id.locations_text);
        String[] locations = locationEditElement.getText().toString().split(",");
        for (String location : locations) {
            WeatherReport weatherReport = new WeatherReport();
            try {
                weatherReport = new WeatherReportRestAdapter().execute(location).get();
            } catch (Exception e){
                e.printStackTrace();
            }
            weatherReports.add(weatherReport);
        }

        WeatherReportViewAdapter weatherReportViewAdapter = new WeatherReportViewAdapter(weatherReports);
        recyclerList.setAdapter(weatherReportViewAdapter);
    }

    public void openDetails(View view) {
        Intent intent = new Intent(this, DetailsActivity.class);
        ArrayList test = new ArrayList(12);
        // pass data into intent
        intent.putExtra("test", test);
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
