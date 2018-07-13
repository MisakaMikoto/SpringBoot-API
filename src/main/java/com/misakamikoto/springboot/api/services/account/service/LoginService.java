package com.misakamikoto.springboot.api.services.account.service;

import com.misakamikoto.springboot.api.services.account.dto.Login;
import com.misakamikoto.springboot.api.services.account.dto.Member;
import com.misakamikoto.springboot.api.services.account.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    private static final String LOGIN_SUCCESS_MESSAGE = "로그인에 성공하였습니다.";
    private static final String LOGIN_FAIL_MESSAGE = "로그인에 실패하였습니다. 비밀번호를 확인해 주세요.";

    private static final String SIGNIN_SUCCESS_MESSAGE = "회원가입에 성공하였습니다.";
    private static final String SIGNIN_FAIL_MESSAGE = "회원가입이 ID 중복으로 인해 실패하였습니다.";

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
}