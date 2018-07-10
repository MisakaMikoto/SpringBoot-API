package com.misakamikoto.springboot.api.login.service;

import com.misakamikoto.springboot.api.login.dto.Login;
import com.misakamikoto.springboot.api.login.dto.Member;
import com.misakamikoto.springboot.api.login.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class LoginService {

    private String LOGIN_SUCCESS_MESSAGE = "Login Successful";
    private String LOGIN_FAIL_MESSAGE = "Login Fail. Confirm your password";

    private String SIGNIN_SUCCESS_MESSAGE = "Signin Successful";
    private String SIGNIN_FAIL_MESSAGE = "Signin Fail. Duplicated id";

    @Autowired
    LoginRepository loginRepository;

    // BCryptPasswordEncoder (password)
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

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

    public Login loginToSession(Login login) {
        Optional<Member> findMember = this.findMember(login.getMemberId());
        Member member = new Member();
        member.setId(login.getMemberId());
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
        member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));
        loginRepository.save(member);
    }

    private Login progressLogin(Optional<Member> findMember, Member member) {
        Login login = new Login();

        if(findMember.isPresent()) {
            if(this.checkPassword(member.getPassword(), findMember.get().getPassword())) {
                login.setStatus(true);
                login.setMemberId(findMember.get().getId());
                login.setMemberName(findMember.get().getName());
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

    private boolean checkPassword(String memberPassword, String findMemberPassword) {
        if(bCryptPasswordEncoder.matches(memberPassword, findMemberPassword)) {
            return true;

        } else {
            return false;
        }
    }

    public Login checkSession(HttpSession httpSession) {
        return (Login) httpSession.getAttribute("login");
    }
}
