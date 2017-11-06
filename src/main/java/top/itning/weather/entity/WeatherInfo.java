package top.itning.weather.entity;

/**
 * @author wangn
 */
public class WeatherInfo {
    private String date;
    private String sunrise;
    private String high;
    private String low;
    private String sunset;
    private String aqi;
    private String fx;
    private String fl;
    private String type;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getFx() {
        return fx;
    }

    public void setFx(String fx) {
        this.fx = fx;
    }

    public String getFl() {
        return fl;
    }

    public void setFl(String fl) {
        this.fl = fl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "WeatherInfo{" +
                "date='" + date + '\'' +
                ", sunrise='" + sunrise + '\'' +
                ", high='" + high + '\'' +
                ", low='" + low + '\'' +
                ", sunset='" + sunset + '\'' +
                ", aqi='" + aqi + '\'' +
                ", fx='" + fx + '\'' +
                ", fl='" + fl + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
