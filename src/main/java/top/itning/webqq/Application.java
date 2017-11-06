package top.itning.webqq;


import org.apache.log4j.Logger;
import top.itning.timer.TimerTasks;
import top.itning.webqq.callback.MessageCallback;
import top.itning.webqq.client.SmartQQClient;
import top.itning.webqq.model.DiscussMessage;
import top.itning.webqq.model.GroupMessage;
import top.itning.webqq.model.Message;

import java.io.IOException;

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
        // client.getGroupList().forEach(System.out::println);
        //client.getFriendList().forEach(System.out::println);
        if (args.length != 3) {
            LOGGER.error("args-->" + args.length);
            return;
        }
        int[] argsInt = new int[3];
        for (int i = 0; i < args.length; i++) {
            argsInt[i] = Integer.parseInt(args[i]);
        }
        System.out.println(2);
        client.sendMessageToGroup(698691135, "已开启");
        client.sendMessageToFriend(1911338, "1");
        new TimerTasks(client, argsInt);
        client.sendMessageToFriend(1911338, "1");
        System.out.println(2);
    }

    private static void close() {
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
