package vip.hengnai.wine.ui.menu.personaddress;

import vip.hengnai.wine.entity.PersonAddressEntity;
import vip.hengnai.wine.framework.IMvpListView;

/**
 * AUTHOR：dell
 * TIME:2019/8/8 2019/8/8
 * DESCRIPTION:IMineView
 * IMenuView——Activity里面要实现的方法在这里面定义
 * @author Hugh
 */
public interface IPersonAddressView extends IMvpListView<PersonAddressEntity> {
    /**
     * 删除成功回调
     * @param message
     */
    void deletePersonAddressSuccess(String message);
    /**
     * 新增和修改成功回调
     * @param message
     */
    void modifyPersonAddressSuccess(String message);
}
