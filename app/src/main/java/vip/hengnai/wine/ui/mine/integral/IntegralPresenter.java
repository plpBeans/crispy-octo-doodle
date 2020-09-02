package vip.hengnai.wine.ui.mine.integral;

import vip.hengnai.wine.framework.BaseMvpPresenter;
import vip.hengnai.wine.framework.model.IntegralModel;

/**
 * on 2019/12/17.
 *
 * @author hua.
 */

public class IntegralPresenter extends BaseMvpPresenter<IIntegralView> {
    private IntegralModel integralModel;
    public IntegralPresenter() {
        integralModel = new IntegralModel();
    }
}
