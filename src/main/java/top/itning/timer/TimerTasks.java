package top.itning.timer;

import net.dongliu.requests.exception.RequestException;
import top.itning.curriculum.CurriculumClient;
import top.itning.weather.client.WeatherInfoClient;
import top.itning.webqq.client.SmartQQClient;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author wangn
 */
public class TimerTasks {
    private SmartQQClient client;
    private static final long PERIOD_DAY = 24 * 60 * 60 * 1000;
    //private static final long PERIOD_DAY = 5000;

    class Task extends TimerTask {

        @Override
        public void run() {
            System.out.println("run!!!");
            String msg;
            if ((msg = WeatherInfoClient.getFormatWeatherInfo()) != null) {
                try {
                    client.sendMessageToGroup(698691135, msg);
                } catch (RequestException e) {
                    System.out.println("java.net.SocketException: Socket Closed");
                }

            }
            String classInfo = CurriculumClient.getClassInfo();
            System.out.println(classInfo);
            if (!"".equals(classInfo)) {
                try {
                    client.sendMessageToGroup(698691135, classInfo);
                } catch (RequestException e) {
                    System.out.println("java.net.SocketException: Socket Closed");
                }
            }
        }
    }

    public TimerTasks(SmartQQClient client, int[] args) {
        this.client = client;
        Calendar calendar = Calendar.getInstance();
        //凌晨1点
        calendar.set(Calendar.HOUR_OF_DAY, args[0]);
        calendar.set(Calendar.MINUTE, args[1]);
        calendar.set(Calendar.SECOND, args[2]);
        //第一次执行定时任务的时间
        Date date = calendar.getTime();
        //如果第一次执行定时任务的时间 小于当前的时间
        //此时要在 第一次执行定时任务的时间加一天，以便此任务在下个时间点执行。如果不加一天，任务会立即执行。
        if (date.before(new Date())) {
            date = this.addDay(date);
        }
        Timer timer = new Timer();
        Task task = new Task();
        //安排指定的任务在指定的时间开始进行重复的固定延迟执行。
        timer.schedule(task, date, PERIOD_DAY);
    }

    private Date addDay(Date date) {
        Calendar startDT = Calendar.getInstance();
        startDT.setTime(date);
        startDT.add(Calendar.DAY_OF_MONTH, 1);
        return startDT.getTime();
    }
}
