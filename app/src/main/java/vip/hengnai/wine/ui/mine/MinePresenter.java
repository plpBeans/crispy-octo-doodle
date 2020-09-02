package vip.hengnai.wine.ui.mine;

import vip.hengnai.wine.entity.HomeDateEntity;
import vip.hengnai.wine.framework.BaseMvpListAuthPresenter;
import vip.hengnai.wine.framework.BaseMvpPresenter;
import vip.hengnai.wine.framework.model.MineModel;


/**
 * @author Hugh
 */
public class MinePresenter extends BaseMvpPresenter<IMineView> {
    private MineModel mineModel;

    public MinePresenter() {
        mineModel = new MineModel();
    }



}