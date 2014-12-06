package ser421.edu.lab_6_native;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.RecyclerView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerList = (RecyclerView) findViewById(R.id.cardList);
        recyclerList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerList.setLayoutManager(linearLayoutManager);

        // TODO this is only test data
        List<WeatherReport> test = new ArrayList<WeatherReport>();
        WeatherReport testReport1 = new WeatherReport();
        testReport1.location = "Mesa";
        testReport1.temperature = 50;
        testReport1.humidity = 50;
        testReport1.windSpeed = 50;
        testReport1.cloudCover = 50;

        WeatherReport testReport2 = new WeatherReport();
        try {
            String location = "London UK";
            testReport2 = new RequestTask().execute(location).get();
        } catch (Exception e){
            e.printStackTrace();
        }

        test.add(testReport1);
        test.add(testReport2);

        WeatherReportViewAdapter weatherReportViewAdapter = new WeatherReportViewAdapter(test);
        recyclerList.setAdapter(weatherReportViewAdapter);

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
