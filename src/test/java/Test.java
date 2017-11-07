import com.alibaba.fastjson.JSONObject;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import top.itning.curriculum.CurriculumClient;
import top.itning.weather.entity.Weather;
import top.itning.weather.entity.WeatherData;
import top.itning.weather.entity.WeatherInfo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.*;


public class Test {
    private static final String URL = "http://www.sojson.com/open/api/weather/json.shtml?city=宾县";
    private static final String DATA = "{\"date\":\"20171106\",\"message\":\"Success !\",\"status\":200,\"city\":\"宾县\",\"count\":2,\"data\":{\"shidu\":\"51%\",\"pm25\":146.0,\"pm10\":154.0,\"quality\":\"中度污染\",\"wendu\":\"11\",\"ganmao\":\"儿童、老年人及心脏、呼吸系统疾病患者人群应减少长时间或高强度户外锻炼，一般人群适量减少户外运动\",\"yesterday\":{\"date\":\"05日星期日\",\"sunrise\":\"06:14\",\"high\":\"高温 10.0℃\",\"low\":\"低温 3.0℃\",\"sunset\":\"16:13\",\"aqi\":126.0,\"fx\":\"西南风\",\"fl\":\"4-5级\",\"type\":\"晴\",\"notice\":\"天气干燥，请适当增加室内湿度\"},\"forecast\":[{\"date\":\"06日星期一\",\"sunrise\":\"06:15\",\"high\":\"高温 12.0℃\",\"low\":\"低温 0.0℃\",\"sunset\":\"16:11\",\"aqi\":139.0,\"fx\":\"西南风\",\"fl\":\"3-4级\",\"type\":\"多云\",\"notice\":\"今日多云，骑上单车去看看世界吧\"},{\"date\":\"07日星期二\",\"sunrise\":\"06:17\",\"high\":\"高温 6.0℃\",\"low\":\"低温 -4.0℃\",\"sunset\":\"16:10\",\"aqi\":158.0,\"fx\":\"北风\",\"fl\":\"3-4级\",\"type\":\"阵雨\",\"notice\":\"愿雨后清新的空气给您带来好心情！\"},{\"date\":\"08日星期三\",\"sunrise\":\"06:18\",\"high\":\"高温 4.0℃\",\"low\":\"低温 -5.0℃\",\"sunset\":\"16:09\",\"aqi\":109.0,\"fx\":\"西风\",\"fl\":\"3-4级\",\"type\":\"多云\",\"notice\":\"今日多云，骑上单车去看看世界吧\"},{\"date\":\"09日星期四\",\"sunrise\":\"06:19\",\"high\":\"高温 6.0℃\",\"low\":\"低温 -4.0℃\",\"sunset\":\"16:07\",\"aqi\":122.0,\"fx\":\"西南风\",\"fl\":\"3-4级\",\"type\":\"多云\",\"notice\":\"绵绵的云朵，形状千变万化\"},{\"date\":\"10日星期五\",\"sunrise\":\"06:21\",\"high\":\"高温 0.0℃\",\"low\":\"低温 -10.0℃\",\"sunset\":\"16:06\",\"aqi\":90.0,\"fx\":\"西风\",\"fl\":\"3-4级\",\"type\":\"阵雪\",\"notice\":\"出现降雪的区域，会给出行带来不便\"}]}}";

    @org.junit.Test
    public void test() {
       /* Client build = Client.pooled().maxPerRoute(5).maxTotal(10).build();
        Session session = build.session();
        String body = session.get(URL).text().getBody();
        JSONObject jsonObject=JSONObject.parseObject(body);
        Weather weather = JSONObject.toJavaObject(jsonObject, Weather.class);
        System.out.println(weather);*/
        JSONObject jsonObject = JSONObject.parseObject(DATA);
        Weather weatherInfo = JSONObject.toJavaObject(jsonObject, Weather.class);
        if (200 == weatherInfo.getStatus()) {
            WeatherData data = weatherInfo.getData();
            WeatherInfo info = data.getForecast()[0];
            String msg = "今天是" +
                    new SimpleDateFormat("yyyy年MM月dd日 EE ").format(new Date()) +
                    "\n" + weatherInfo.getCity() + "天气:\n湿度:" + data.getShidu() +
                    "\nPM2.5:" + data.getPm25() + "\n可吸入颗粒物:" + data.getPm10() + "\n" +
                    data.getQuality() + " " + data.getGanmao() +
                    "\n日出:" + info.getSunrise() + "\t日落:" + info.getSunset() + "\n" + info.getLow() + " " + info.getHigh() + "\n" +
                    info.getType() + " " + info.getFx() + " " + info.getFl();
            System.out.println(msg);
        }
    }

    @org.junit.Test
    public void tests() {
        String classInfo = CurriculumClient.getClassInfo();
        System.out.println(classInfo.equals(""));
    }

    @org.junit.Test
    public void testss() {
        String ee = new SimpleDateFormat("F").format(new Date());
        System.out.println(ee);
    }

    @org.junit.Test
    public void testsss() throws InterruptedException {
        // new TimerTasks();
        Thread.sleep(999999);
    }

    @org.junit.Test
    public void testa() throws InterruptedException {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(2, namedThreadFactory);
        scheduledThreadPoolExecutor.scheduleAtFixedRate(() -> {

        }, 0, 500, TimeUnit.MILLISECONDS);
        Thread.sleep(99999999);
    }

}
