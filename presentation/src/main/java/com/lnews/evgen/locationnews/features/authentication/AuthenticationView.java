package com.lnews.evgen.locationnews.features.authentication;

import android.support.v4.app.Fragment;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.lnews.evgen.locationnews.features.base.BaseActivityView;

@StateStrategyType(OneExecutionStateStrategy.class)
interface AuthenticationView extends BaseActivityView {
    void showFragment(Fragment fragment);

    void showProgressBar();

    void hideProgressBar();
}
