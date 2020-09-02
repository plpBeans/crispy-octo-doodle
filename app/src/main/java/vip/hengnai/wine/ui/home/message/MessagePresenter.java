package vip.hengnai.wine.ui.home.message;

import vip.hengnai.wine.entity.MessageEntity;
import vip.hengnai.wine.framework.BaseMvpListAuthPresenter;
import vip.hengnai.wine.framework.model.MessageModel;

/**
 * on 2019/12/23.
 *
 * @author hua.
 */

public class MessagePresenter extends BaseMvpListAuthPresenter<IMessageView,MessageEntity> {
    private MessageModel messageModel;

    public MessagePresenter() {
        messageModel = new MessageModel();
    }
}
