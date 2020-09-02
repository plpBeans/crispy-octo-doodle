package vip.hengnai.wine.ui.home;


import vip.hengnai.wine.framework.BaseMvpPresenter;
import vip.hengnai.wine.framework.model.HomeModel;
import vip.hengnai.wine.framework.model.MineModel;


/**
 * AUTHOR：dell
 * TIME:2019/8/20 2019/8/20
 * DESCRIPTION:MinePresenter
 * @author Hugh
 */
public class HomePresenter extends BaseMvpPresenter<IHomeView> {

    private HomeModel homeModel;

    public HomePresenter() {
        homeModel = new HomeModel();
    }




}
