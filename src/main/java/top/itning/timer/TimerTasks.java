package top.itning.timer;

import net.dongliu.requests.exception.RequestException;
import org.apache.log4j.Logger;
import top.itning.curriculum.CurriculumClient;
import top.itning.weather.client.WeatherInfoClient;
import top.itning.webqq.client.SmartQQClient;
import top.itning.webqq.model.Group;

import java.util.*;

/***
 * 定时器任务类
 * @author : ning
 * @version : 1.0.0
 * @date :   2017/11/7
 **/
public class TimerTasks {
    private static final Logger LOGGER = Logger.getLogger(TimerTasks.class);
    private SmartQQClient client;
    /**
     * 重复周期;单位ms
     */
    private static final long PERIOD_DAY = 24 * 60 * 60 * 1000;

    /***
     * 任务线程
     * @author : ning
     * @version : 1.0.0
     * @date :   2017/11/7
     **/
    class Task extends TimerTask {

        @Override
        public void run() {
            // 每次登陆后ID会变 所以动态获取ID
            long groupId = 0;
            List<Group> groupList = client.getGroupList();
            for (Group group : groupList) {
                if ("╭ァ編徎縱扖門到瓬棄".equals(group.getName())) {
                    groupId = group.getId();
                }
            }
            LOGGER.debug("已获取群ID-->"+groupId);
            if (groupId == 0) {
                throw new RuntimeException("group id = 0");
            }
            String msg;
            if ((msg = WeatherInfoClient.getFormatWeatherInfo()) != null) {
                try {
                    client.sendMessageToGroup(groupId, msg);
                } catch (RequestException e) {
                    System.out.println("java.net.SocketException: Socket Closed");
                }

            }
            String classInfo = CurriculumClient.getClassInfo();
            System.out.println(classInfo);
            if (!"".equals(classInfo)) {
                try {
                    client.sendMessageToGroup(groupId, classInfo);
                } catch (RequestException e) {
                    System.out.println("java.net.SocketException: Socket Closed");
                }
            }
        }
    }

    /**
     * 构造方法
     *
     * @param client SmartQQClient实例
     * @param args   定时参数
     * @author : ning
     **/
    public TimerTasks(SmartQQClient client, int[] args) {
        this.client = client;
        Calendar calendar = Calendar.getInstance();
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

    /***
     * 增加日期
     * @author : ning
     * @param date Date实例
     * @return Date实例--java.util.Date
     * @date :   2017/11/7
     **/
    private Date addDay(Date date) {
        LOGGER.debug("已增加日期");
        Calendar startDT = Calendar.getInstance();
        startDT.setTime(date);
        startDT.add(Calendar.DAY_OF_MONTH, 1);
        return startDT.getTime();
    }
}
