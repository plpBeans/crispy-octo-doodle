package vip.hengnai.wine.framework;

import java.util.List;

/**
 *
 * @author hua
 * @date 2019/8/21
 */

public interface IMvpListView<E> extends IMvpView {
    /**
     * 加载数据
     * @param datas
     */
    void showDatas(List<E> datas);

    /**
     * 加载更多数据
     * @param datas
     */
    void appendDatas(List<E> datas);

    /**
     * 加载更多
     */
    void showLoadingMore();

    /**
     * 没有更多
     */
    void noMoreData();


}
