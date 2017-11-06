package top.itning.weather.client;

import com.alibaba.fastjson.JSONObject;
import net.dongliu.requests.Client;
import net.dongliu.requests.Session;
import top.itning.weather.entity.Weather;
import top.itning.weather.entity.WeatherData;
import top.itning.weather.entity.WeatherInfo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wangn
 */
public class WeatherInfoClient {
    private WeatherInfoClient() {
    }

    private static final int SUCCESS_CODE = 200;
    private static final String URL = "http://www.sojson.com/open/api/weather/json.shtml?city=宾县";

    public static Weather getWeatherInfo() {
        Client build = Client.pooled().maxPerRoute(5).maxTotal(10).build();
        Session session = build.session();
        String body = session.get(URL).text().getBody();
        JSONObject jsonObject = JSONObject.parseObject(body);
        return JSONObject.toJavaObject(jsonObject, Weather.class);
    }

    public static String getFormatWeatherInfo() {
        Weather weatherInfo = getWeatherInfo();
        if (SUCCESS_CODE == weatherInfo.getStatus()) {
            WeatherData data = weatherInfo.getData();
            WeatherInfo info = data.getForecast()[0];
            return "今天是" +
                    new SimpleDateFormat("yyyy年MM月dd日 EE ").format(new Date()) +
                    "\n" + weatherInfo.getCity() + "天气:\n湿度:" + data.getShidu() +
                    "\nPM2.5:" + data.getPm25() + "\n可吸入颗粒物:" + data.getPm10() + "\n" +
                    data.getQuality() + " " + data.getGanmao() +
                    "\n日出:" + info.getSunrise() + "\t日落:" + info.getSunset() + "\n" + info.getLow() + " " + info.getHigh() + "\n" +
                    info.getType() + " " + info.getFx() + " " + info.getFl();
        }
        return null;
    }
}