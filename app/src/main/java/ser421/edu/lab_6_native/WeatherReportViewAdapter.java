package ser421.edu.lab_6_native;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class WeatherReportViewAdapter extends RecyclerView.Adapter<WeatherReportViewAdapter.WeatherReportViewHolder> {
    // stores all the weather reports
    private List<WeatherReport> weatherReportList;

    public WeatherReportViewAdapter(List<WeatherReport> weatherReportList) {
        // takes the weather reports and stores internally
        this.weatherReportList = weatherReportList;
    }

    @Override
    public int getItemCount() {
        // used by android to know how many times to iterate
        return weatherReportList.size();
    }

    @Override
    public void onBindViewHolder(WeatherReportViewHolder weatherReportViewHolder, int i) {
        // get a weather report from the list
        WeatherReport report = weatherReportList.get(i);

        // fill in the card text fields with the information
        weatherReportViewHolder.location.setText(report.location);
        weatherReportViewHolder.temperature.setText("Temperature: " + String.format("%.1f", report.temperature) + (char) 0x00B0 + "C");
        weatherReportViewHolder.humidity.setText("Humidity: " + String.format("%.1f", report.humidity) + "%");
        weatherReportViewHolder.windSpeed.setText("Wind Speed: " + String.format("%.1f", report.windSpeed) + "MPH");
        weatherReportViewHolder.cloudCover.setText("Cloud Cover: " + String.format("%.1f", report.cloudCover) + "%");
    }

    @Override
    public WeatherReportViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // adds the view during run time
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.weather_report_card, viewGroup, false);

        return new WeatherReportViewHolder(itemView);
    }

    public static class WeatherReportViewHolder extends RecyclerView.ViewHolder {
        // this stores all the parts of a Card
        protected TextView location;
        protected TextView temperature;
        protected TextView humidity;
        protected TextView windSpeed;
        protected TextView cloudCover;

        public WeatherReportViewHolder(View v) {
            super(v);
            // this grabs all the parts of a Card
            location =  (TextView) v.findViewById(R.id.location);
            temperature =  (TextView) v.findViewById(R.id.temperature);
            humidity =  (TextView) v.findViewById(R.id.humidity);
            windSpeed =  (TextView) v.findViewById(R.id.wind_speed);
            cloudCover =  (TextView) v.findViewById(R.id.cloud_cover);
        }
    }
}
