package ser421.edu.lab_6_native;

import android.os.Parcel;
import android.os.Parcelable;

public class WeatherReport implements Parcelable {
    protected String location;
    protected double latitude;
    protected double longitude;
    protected double temperature;
    protected double humidity;
    protected double windSpeed;
    protected double cloudCover;

    public WeatherReport() {

    }

    private WeatherReport(Parcel in) {
        // This order must match the order in writeToParcel()
        location = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
        temperature = in.readDouble();
        humidity = in.readDouble();
        windSpeed = in.readDouble();
        cloudCover = in.readDouble();
    }

    public void writeToParcel(Parcel out, int flags) {
        // Again this order must match the Question(Parcel) constructor
        out.writeString(location);
        out.writeDouble(latitude);
        out.writeDouble(longitude);
        out.writeDouble(temperature);
        out.writeDouble(humidity);
        out.writeDouble(windSpeed);
        out.writeDouble(cloudCover);
    }

    // magic
    public int describeContents() {
        return 0;
    }

    // magic
    public static final Parcelable.Creator<WeatherReport> CREATOR = new Parcelable.Creator<WeatherReport>() {
        public WeatherReport createFromParcel(Parcel in) {
            return new WeatherReport(in);
        }

        public WeatherReport[] newArray(int size) {
            return new WeatherReport[size];
        }
    };
}
