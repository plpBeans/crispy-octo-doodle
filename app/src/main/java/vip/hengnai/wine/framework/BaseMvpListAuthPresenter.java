package vip.hengnai.wine.framework;

import java.util.List;

/**
 *
 * @author hua
 * @date 2019/8/21
 */

public abstract class BaseMvpListAuthPresenter<V extends IMvpListView<E>, E> extends BaseMvpPresenter<V> {


    public int getPageSize() {
        return 20;
    }

    private boolean isLoadingMore(int offset) {
        return offset > 0;
    }

    /**
     * @param datas
     * @param offset
     */
    protected void resolveNext(List<E> datas, int offset) {
        getView().hideLoadingView();
        if (null == datas) {
            getView().noMoreData();
        } else {
            //不是第一次请求，数量正好和分页数一样
            if(0!=offset&&datas.size()==0){
                getView().noMoreData();
            }else{
                if (isLoadingMore(offset)) {
                    getView().appendDatas(datas);
                } else {
                    getView().showDatas(datas);
                }
                if (datas.size() < getPageSize()) {
                    getView().noMoreData();
                }
            }

        }
    }
}
