package com.misakamikoto.springboot.api.login.service;

import com.misakamikoto.springboot.api.login.dto.Login;
import com.misakamikoto.springboot.api.login.dto.Member;
import com.misakamikoto.springboot.api.login.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private String LOGIN_SUCCESS_MESSAGE = "Login Successful";
    private String LOGIN_FAIL_MESSAGE = "Login Fail. Confirm your password";

    private String SIGNIN_SUCCESS_MESSAGE = "Signin Successful";
    private String SIGNIN_FAIL_MESSAGE = "Signin Fail. Duplicated id";

    @Autowired
    LoginRepository loginRepository;

    public Login signIn(Member member) {
        boolean isDuplicate = this.findId(member.getId());

        if(!isDuplicate) {
            this.createMember(member);
        }
        return createSigninMessage(isDuplicate);
    }

    public Login logIn(Member member) {
        return createLoginMessage(this.findId(member.getId()));
    }

    private boolean findId(String id) {
        return loginRepository.existsById(id);
    }

    private Login createSigninMessage(boolean isDuplicate) {
        Login login = new Login();
        login.setStatus(!isDuplicate);
        if(isDuplicate) {
            login.setMessage(SIGNIN_FAIL_MESSAGE);

        } else {
            login.setMessage(SIGNIN_SUCCESS_MESSAGE);
        }
        return login;
    }

    private void createMember(Member member) {
        loginRepository.save(member);
    }

    private Login createLoginMessage(boolean isLogin) {
        Login login = new Login();
        login.setStatus(isLogin);
        if(isLogin) {
            login.setMessage(LOGIN_SUCCESS_MESSAGE);

        } else {
            login.setMessage(LOGIN_FAIL_MESSAGE);
        }
        return login;
    }
}
