package com.lnews.evgen.locationnews.features.passrecovery;

import com.arellomobile.mvp.InjectViewState;
import com.lnews.evgen.domain.interactors.AuthorizationInteractor;
import com.lnews.evgen.locationnews.R;
import com.lnews.evgen.locationnews.di.Injector;
import com.lnews.evgen.locationnews.di.annotations.PerFragment;
import com.lnews.evgen.locationnews.features.base.BasePresenter;
import io.reactivex.observers.DisposableCompletableObserver;
import javax.inject.Inject;

@InjectViewState
@PerFragment(PassRecoveryFragment.class)
public class PassRecoveryPresenter extends BasePresenter<PassRecoveryView> {

    private final AuthorizationInteractor authorizationInteractor;

    @Inject
    PassRecoveryPresenter(AuthorizationInteractor authorizationInteractor) {
        this.authorizationInteractor = authorizationInteractor;
    }

    @Override
    protected void clearComponent() {
        Injector.getInstance().clearPassRecoveryComponent();
    }

    @Override
    public void onDestroy() {
        clearComponent();
        super.onDestroy();
    }

    public void btnResetPassListener(String email) {
        if (email.isEmpty()) {
            getViewState().showToast(R.string.auth_email_field_empty);
            return;
        }

        authorizationInteractor.resetPassRequest(email, new DisposableCompletableObserver() {

            @Override
            public void onComplete() {
                getViewState().showToast(R.string.auth_password_reset_confirm);
            }

            @Override
            public void onError(Throwable e) {
                handleError(e);
            }
        });
    }
}
