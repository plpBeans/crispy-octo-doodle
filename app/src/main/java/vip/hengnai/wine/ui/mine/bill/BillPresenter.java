package vip.hengnai.wine.ui.mine.bill;

import vip.hengnai.wine.framework.BaseMvpPresenter;
import vip.hengnai.wine.framework.model.BillModel;

/**
 * on 2019/12/14.
 *
 * @author hua.
 */

public class BillPresenter extends BaseMvpPresenter<IBillView> {
    private BillModel billModel;
    public BillPresenter() {
        billModel = new BillModel();
    }
}
