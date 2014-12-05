package ser421.edu.lab_6_native;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class WeatherReportViewAdapter extends RecyclerView.Adapter<WeatherReportViewAdapter.WeatherReportViewHolder> {
    private List<WeatherReport> weatherReportList;

    public WeatherReportViewAdapter(List<WeatherReport> weatherReportList) {
        this.weatherReportList = weatherReportList;
    }

    @Override
    public int getItemCount() {
        return weatherReportList.size();
    }

    @Override
    public void onBindViewHolder(WeatherReportViewHolder weatherReportViewHolder, int i) {
        WeatherReport report = weatherReportList.get(i);
        weatherReportViewHolder.location.setText(report.location);
    }

    @Override
    public WeatherReportViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.weather_report_card, viewGroup, false);

        return new WeatherReportViewHolder(itemView);
    }

    public static class WeatherReportViewHolder extends RecyclerView.ViewHolder {

        protected TextView location;

        public WeatherReportViewHolder(View v) {
            super(v);
            location =  (TextView) v.findViewById(R.id.location);
        }
    }
}
