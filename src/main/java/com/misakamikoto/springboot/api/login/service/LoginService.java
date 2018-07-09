package com.misakamikoto.springboot.api.login.service;

import com.misakamikoto.springboot.api.login.dto.Login;
import com.misakamikoto.springboot.api.login.dto.Member;
import com.misakamikoto.springboot.api.login.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    private String LOGIN_SUCCESS_MESSAGE = "Login Successful";
    private String LOGIN_FAIL_MESSAGE = "Login Fail. Confirm your password";

    private String SIGNIN_SUCCESS_MESSAGE = "Signin Successful";
    private String SIGNIN_FAIL_MESSAGE = "Signin Fail. Duplicated id";

    @Autowired
    LoginRepository loginRepository;

    public Login signIn(Member member) {
        Optional<Member> result = this.findMember(member.getId());
        if(!result.isPresent()) {
            this.createMember(member);
        }
        return createSignin(result.isPresent());
    }

    public Login logIn(Member member) {
        Optional<Member> findMember = this.findMember(member.getId());
        return progressLogin(findMember, member);
    }

    private Optional<Member> findMember(String id) {
        return loginRepository.findById(id);
    }

    private Login createSignin(boolean isDuplicate) {
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

    private Login progressLogin(Optional<Member> findMember, Member member) {
        Login login = new Login();

        if(findMember.isPresent()) {
            if(this.checkPassword(findMember.get().getPassword(), member.getPassword())) {
                login.setStatus(true);
                login.setUserName(findMember.get().getName());
                login.setMessage(LOGIN_SUCCESS_MESSAGE);

            } else {
                login.setStatus(false);
                login.setMessage(LOGIN_FAIL_MESSAGE);
            }

        } else {
            login.setStatus(false);
            login.setMessage(LOGIN_FAIL_MESSAGE);
        }

        return login;
    }

    private boolean checkPassword(String findMemberPassword, String memberPassword) {
        if(memberPassword.equals(findMemberPassword)) {
            return true;

        } else {
            return false;
        }
    }
}
