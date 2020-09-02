package vip.hengnai.wine.ui.mine.member;

import vip.hengnai.wine.framework.BaseMvpPresenter;
import vip.hengnai.wine.framework.model.MemberModel;

/**
 * on 2019/12/18.
 *
 * @author hua.
 */

public class MemberCenterPresenter extends BaseMvpPresenter<IMemberView> {
    private MemberModel memberModel;
    public MemberCenterPresenter() {
        memberModel = new MemberModel();
    }
}
