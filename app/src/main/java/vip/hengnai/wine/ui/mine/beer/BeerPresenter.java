package vip.hengnai.wine.ui.mine.beer;

import vip.hengnai.wine.framework.BaseMvpPresenter;
import vip.hengnai.wine.framework.model.BeerModel;

/**
 * on 2019/12/21.
 *
 * @author hua.
 */

public class BeerPresenter extends BaseMvpPresenter<IBeerView> {
    private BeerModel beerModel;
    public BeerPresenter() {
        beerModel = new BeerModel();
    }
}
