package top.itning.weather.client;

import com.alibaba.fastjson.JSONObject;
import net.dongliu.requests.Client;
import net.dongliu.requests.Session;
import top.itning.weather.entity.Weather;
import top.itning.weather.entity.WeatherData;
import top.itning.weather.entity.WeatherInfo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/***
 *天气信息客户端
 * @author : ning
 * @version : 1.0.0
 * @date :   2017/11/7
 **/
public class WeatherInfoClient {
    private WeatherInfoClient() {
    }

    private static final int SUCCESS_CODE = 200;
    private static final String URL = "http://www.sojson.com/open/api/weather/json.shtml?city=宾县";

    /***
     * 获取天气信息
     * @author : ning
     * @return Weather Bean--top.itning.weather.entity.Weather
     * @date :   2017/11/7
     **/
    private static Weather getWeatherInfo() {
        Client build = Client.pooled().maxPerRoute(5).maxTotal(10).build();
        Session session = build.session();
        String body = session.get(URL).text().getBody();
        JSONObject jsonObject = JSONObject.parseObject(body);
        return JSONObject.toJavaObject(jsonObject, Weather.class);
    }

    /***
     * 获取格式化后的天气信息
     * @author : ning
     * @return 格式化后的天气信息--java.lang.String
     * @date :   2017/11/7
     **/
    public static String getFormatWeatherInfo() {
        Weather weatherInfo = getWeatherInfo();
        if (SUCCESS_CODE == weatherInfo.getStatus()) {
            WeatherData data = weatherInfo.getData();
            WeatherInfo info = data.getForecast()[0];
            return "今天是" +
                    new SimpleDateFormat("yyyy年MM月dd日 EE ", Locale.CHINESE).format(new Date()) +
                    "\n" + weatherInfo.getCity() + "天气:\n湿度:" + data.getShidu() +
                    "\nPM2.5:" + data.getPm25() + "\n可吸入颗粒物:" + data.getPm10() + "\n" +
                    data.getQuality() + " " + data.getGanmao() +
                    "\n日出:" + info.getSunrise() + "\t日落:" + info.getSunset() + "\n" + info.getLow() + " " + info.getHigh() + "\n" +
                    info.getType() + " " + info.getFx() + " " + info.getFl();
        }
        return null;
    }
}
