package top.itning.weather.entity;

import java.util.Arrays;

/**
 * @author wangn
 */
public class WeatherData {
    private String shidu;
    private Integer pm25;
    private Integer pm10;
    private String quality;
    private String wendu;
    private String ganmao;
    private WeatherInfo[] forecast;

    public String getShidu() {
        return shidu;
    }

    public void setShidu(String shidu) {
        this.shidu = shidu;
    }

    public Integer getPm25() {
        return pm25;
    }

    public void setPm25(Integer pm25) {
        this.pm25 = pm25;
    }

    public Integer getPm10() {
        return pm10;
    }

    public void setPm10(Integer pm10) {
        this.pm10 = pm10;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getWendu() {
        return wendu;
    }

    public void setWendu(String wendu) {
        this.wendu = wendu;
    }

    public String getGanmao() {
        return ganmao;
    }

    public void setGanmao(String ganmao) {
        this.ganmao = ganmao;
    }

    public WeatherInfo[] getForecast() {
        return forecast;
    }

    public void setForecast(WeatherInfo[] forecast) {
        this.forecast = forecast;
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "shidu='" + shidu + '\'' +
                ", pm25=" + pm25 +
                ", pm10=" + pm10 +
                ", quality='" + quality + '\'' +
                ", wendu='" + wendu + '\'' +
                ", ganmao='" + ganmao + '\'' +
                ", forecast=" + Arrays.toString(forecast) +
                '}';
    }
}
