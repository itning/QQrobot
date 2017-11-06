package top.itning.webqq.callback;


import top.itning.webqq.model.DiscussMessage;
import top.itning.webqq.model.GroupMessage;
import top.itning.webqq.model.Message;

/**
 * 收到消息的回调
 * @author ScienJus
 * @date 2015/12/18.
 */
public interface MessageCallback {

    /**
     * 收到私聊消息后的回调
     * @param message 私聊消息
     */
    void onMessage(Message message);

    /**
     * 收到群消息后的回调
     * @param message 群消息
     */
    void onGroupMessage(GroupMessage message);

    /**
     * 收到讨论组消息后的回调
     * @param message 讨论组消息
     */
    void onDiscussMessage(DiscussMessage message);
}
