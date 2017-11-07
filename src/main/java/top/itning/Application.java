package top.itning;


import org.apache.log4j.Logger;
import top.itning.timer.TimerTasks;
import top.itning.webqq.callback.MessageCallback;
import top.itning.webqq.client.SmartQQClient;
import top.itning.webqq.model.*;

import java.io.IOException;
import java.util.List;


/**
 * 主入口
 *
 * @author wangn
 */
public class Application {

    private static final Logger LOGGER = Logger.getLogger(Application.class);

    private static long User_ID = 0;

    /**
     * 程序应输入的参数 数
     */
    private static final int ARGS_LENGTH = 3;
    /**
     * 关闭程序命令为 close
     */
    private static final String CLOSE_COMMAND = "close";

    /**
     * 创建一个新对象时需要扫描二维码登录，并且传一个处理接收到消息的回调，如果你不需要接收消息，可以传null
     */
    private static SmartQQClient client = new SmartQQClient(new MessageCallback() {
        @Override
        public void onMessage(Message message) {
            if (User_ID == message.getUserId() && CLOSE_COMMAND.equals(message.getContent())) {
                client.sendMessageToFriend(User_ID, "关闭成功");
                close();
            }
        }

        @Override
        public void onGroupMessage(GroupMessage message) {

        }

        @Override
        public void onDiscussMessage(DiscussMessage message) {

        }
    });

    public static void main(String[] args) throws IOException, InterruptedException {
        List<Category> friendListWithCategory = client.getFriendListWithCategory();
        for (Category c : friendListWithCategory) {
            List<Friend> friends = c.getFriends();
            for (Friend friend : friends) {
                if ("她姓许º".equals(friend.getNickname()) && friend.getUserId() != 1837634447) {
                    User_ID = friend.getUserId();
                }
            }
        }
        if (User_ID == 0) {
            throw new RuntimeException("user id = 0");
        }
        LOGGER.debug("已获取到User_ID-->" + User_ID);
        client.sendMessageToFriend(User_ID, "已获取到User_ID-->" + User_ID);
        if (args.length != ARGS_LENGTH) {
            LOGGER.error("args-->" + args.length);
            return;
        }
        int[] argsInt = new int[3];
        for (int i = 0; i < args.length; i++) {
            argsInt[i] = Integer.parseInt(args[i]);
        }
        client.sendMessageToFriend(User_ID, "正在开启...");
        new TimerTasks(client, argsInt);
        client.sendMessageToFriend(User_ID, "开启成功!");
    }

    /***
     * 程序关闭
     * @author : ning
     * @date :   2017/11/7
     **/
    private static void close() {
        try {
            client.close();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
