package com.example.toeic.feature.register;

import com.example.base.BasePresenterImpl;
import com.example.toeic.data.model.RegisterResponse;
import com.example.toeic.data.model.User;
import com.example.toeic.data.network.Service;

import io.reactivex.observers.DisposableSingleObserver;

import static com.example.toeic.ultis.Constraints.EMPTY_STRING;

public class RegisterPresentImpl extends BasePresenterImpl implements RegisterPresent {
    @Override
    public void register(String username, String password, String confirmPassword) {
        if (passwordIsValid(password, confirmPassword)) {
            User user = new User(username, password);
            callApi(Service
                    .callUserService()
                    .register(user)
                    .compose(Service.getRxSingleSchedulers().applySchedulers())
                    .subscribeWith(new DisposableSingleObserver<RegisterResponse>() {
                        @Override
                        public void onSuccess(RegisterResponse registerResponse) {
                            String error = registerResponse.getError();
                            if (error != null && !error.equals(EMPTY_STRING)) {
                                ((RegisterView) view).onRegisterFalse(error);
                            } else {
                                ((RegisterView) view).onRegisterSuccess();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {

                        }
                    }));
        } else {
            ((RegisterView) view).onPasswordConfirmNotMatch();
        }

    }

    private boolean passwordIsValid(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }
}
