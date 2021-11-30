package com.example.whetherz;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WhetherData implements Parcelable {

    private long id;
    private String whetherStateName;
    private String whetherStateAbbr;
    private String windDirectionCompass;
    private String created;
    private String applicableDate;
    private int minTemp;
    private int maxTemp;
    private double windSpeed;
    private double windDirection;
    private double airPressure;
    private int humidity;
    private double visibility;
    private int predictability;

    protected WhetherData(Parcel in) {
        id = in.readLong();
        whetherStateName = in.readString();
        whetherStateAbbr = in.readString();
        windDirectionCompass = in.readString();
        created = in.readString();
        applicableDate = in.readString();
        minTemp = in.readInt();
        maxTemp = in.readInt();
        windSpeed = in.readDouble();
        windDirection = in.readDouble();
        airPressure = in.readDouble();
        humidity = in.readInt();
        visibility = in.readDouble();
        predictability = in.readInt();
    }

    public static final Creator<WhetherData> CREATOR = new Creator<WhetherData>() {
        @Override
        public WhetherData createFromParcel(Parcel in) {
            return new WhetherData(in);
        }

        @Override
        public WhetherData[] newArray(int size) {
            return new WhetherData[size];
        }
    };

    public WhetherData() {
    }

    @Override
    public String toString() {
        return "WhetherData{" +
                "id=" + id +
                ", whetherStateName='" + whetherStateName + '\'' +
                ", whetherStateAbbr='" + whetherStateAbbr + '\'' +
                ", windDirectionCompass='" + windDirectionCompass + '\'' +
                ", created='" + created + '\'' +
                ", applicableDate='" + applicableDate + '\'' +
                ", minTemp=" + minTemp +
                ", maxTemp=" + maxTemp +
                ", windSpeed=" + windSpeed +
                ", windDirection=" + windDirection +
                ", airPressure=" + airPressure +
                ", humidity=" + humidity +
                ", visibility=" + visibility +
                ", predictability=" + predictability +
                '}';
    }

    public String getDay() {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        String day = "";
        try {
            date = format1.parse(getApplicableDate());
        } catch (ParseException e) {
            Log.e("Whether Data:", "Exception arised in parsing the applicable date");
        }
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
        if (date != null)
            day = dayFormat.format(date);
        return day;
    }

    public String getFormattedDate() {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        String formattedDate = "";
        try {
            date = format1.parse(getApplicableDate());
        } catch (ParseException e) {
            Log.e("Whether Data:", "Exception arised in parsing the applicable date");
        }
        SimpleDateFormat dayFormat = new SimpleDateFormat("MMM dd");
        if (date != null)
            formattedDate = dayFormat.format(date);
        return formattedDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWhetherStateName() {
        return whetherStateName;
    }

    public void setWhetherStateName(String whetherStateName) {
        this.whetherStateName = whetherStateName;
    }

    public String getWhetherStateAbbr() {
        return whetherStateAbbr;
    }

    public void setWhetherStateAbbr(String whetherStateAbbr) {
        this.whetherStateAbbr = whetherStateAbbr;
    }

    public String getWindDirectionCompass() {
        return windDirectionCompass;
    }

    public void setWindDirectionCompass(String windDirectionCompass) {
        this.windDirectionCompass = windDirectionCompass;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getApplicableDate() {
        return applicableDate;
    }

    public void setApplicableDate(String applicableDate) {
        this.applicableDate = applicableDate;
    }

    public int getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(int minTemp) {
        this.minTemp = minTemp;
    }

    public int getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(int maxTemp) {
        this.maxTemp = maxTemp;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(double windDirection) {
        this.windDirection = windDirection;
    }

    public double getAirPressure() {
        return airPressure;
    }

    public void setAirPressure(double airPressure) {
        this.airPressure = airPressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getVisibility() {
        return visibility;
    }

    public void setVisibility(double visibility) {
        this.visibility = visibility;
    }

    public int getPredictability() {
        return predictability;
    }

    public void setPredictability(int predictability) {
        this.predictability = predictability;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(whetherStateName);
        dest.writeString(whetherStateAbbr);
        dest.writeString(windDirectionCompass);
        dest.writeString(created);
        dest.writeString(applicableDate);
        dest.writeInt(minTemp);
        dest.writeInt(maxTemp);
        dest.writeDouble(windSpeed);
        dest.writeDouble(windDirection);
        dest.writeDouble(airPressure);
        dest.writeInt(humidity);
        dest.writeDouble(visibility);
        dest.writeInt(predictability);
    }
}
