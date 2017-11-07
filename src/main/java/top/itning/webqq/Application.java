package top.itning.webqq;


import org.apache.log4j.Logger;
import top.itning.timer.TimerTasks;
import top.itning.webqq.callback.MessageCallback;
import top.itning.webqq.client.SmartQQClient;
import top.itning.webqq.model.*;

import java.io.IOException;
import java.util.List;

/**
 * @author ScienJus
 * @date 2015/12/18.
 */
public class Application {
    private static final Logger LOGGER = Logger.getLogger(Application.class);
    private static final int SUCCESS_CODE = 200;
    /**
     * 创建一个新对象时需要扫描二维码登录，并且传一个处理接收到消息的回调，如果你不需要接收消息，可以传null
     */
    private static SmartQQClient client = new SmartQQClient(new MessageCallback() {
        @Override
        public void onMessage(Message message) {
            System.out.println("onMessage-->" + message.getContent());
            System.out.println(message);
            if ("close".equals(message.getContent())) {
                close();
            }
        }

        @Override
        public void onGroupMessage(GroupMessage message) {
            System.out.println("onGroupMessage-->" + message.getContent());
            System.out.println(message);
        }

        @Override
        public void onDiscussMessage(DiscussMessage message) {
            System.out.println("onDiscussMessage-->" + message.getContent());
            System.out.println(message);
        }
    });

    public static void main(String[] args) throws IOException, InterruptedException {
        long userId = 0;
        List<Category> friendListWithCategory = client.getFriendListWithCategory();
        for (Category c : friendListWithCategory) {
            List<Friend> friends = c.getFriends();
            for (Friend friend : friends) {
                if ("她姓许º".equals(friend.getNickname()) && friend.getUserId() != 1837634447) {
                    userId = friend.getUserId();
                }
            }
        }
        if (userId == 0) {
            throw new RuntimeException("user id = 0");
        }
        System.out.println(userId);
        if (args.length != 3) {
            LOGGER.error("args-->" + args.length);
            return;
        }
        int[] argsInt = new int[3];
        for (int i = 0; i < args.length; i++) {
            argsInt[i] = Integer.parseInt(args[i]);
        }
        System.out.println(2);
        client.sendMessageToFriend(userId, "已开启");
        new TimerTasks(client, argsInt);
        client.sendMessageToFriend(userId, "1");
        System.out.println(2);
    }

    private static void close() {
        try {
            client.close();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
