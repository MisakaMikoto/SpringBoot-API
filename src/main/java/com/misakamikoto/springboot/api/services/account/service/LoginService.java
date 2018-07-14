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

    // enum refactoring
    private static final String LOGIN_SUCCESS_MESSAGE = "로그인에 성공하였습니다.";
    private static final String LOGIN_FAIL_MESSAGE = "로그인에 실패하였습니다. 비밀번호를 확인해 주세요.";

    private static final String SIGNIN_SUCCESS_MESSAGE = "회원 가입이 완료되었습니다.";
    private static final String SIGNIN_FAIL_MESSAGE = "회원 가입이 ID 중복으로 인해 실패하였습니다.";

    private static final String MODIFY_SUCCESS_MESSAGE = "회원 정보 수정이 완료되었습니다. ";
    private static final String MODIFY_FAIL_ID_MESSAGE = "회원 정보 수정에 대한 ID 정보를 찾을 수 없어 실패하였습니다.";
    private static final String MODIFY_FAIL_PASSWORD_MESSAGE = "회원 정보 수정이 PASSWORD 불일치로 인해 실패하였습니다.";

    @Autowired
    LoginRepository loginRepository;

    // BCryptPasswordEncoder (password)
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public Login signIn(Member member) {
        Optional<Member> findMember = this.findMember(member.getId());
        if(!findMember.isPresent()) {
            this.createMember(member);
        }
        return createSignin(findMember.isPresent());
    }

    public Login logIn(Member member) {
        Optional<Member> findMember = this.findMember(member.getId());
        return progressLogin(findMember, member);
    }

    public Login modify(Member member) {
        String type;
        boolean isSuccess = false;

        Optional<Member> findMember = this.findMember(member.getId());
        if(findMember.isPresent()) {
            type = "password";

            isSuccess = this.checkPassword(member.getPassword(), findMember.get().getPassword());
            findMember.get().setPassword(member.getModifyPassword());
            if(isSuccess) {
                this.updateMember(findMember);
            }
        } else {
            type = "id";
        }
        return createModify(type, isSuccess);
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

    private Login createModify(String type, boolean isSuccess) {
        Login login = new Login();
        login.setStatus(isSuccess);

        if(isSuccess) {
            login.setMessage(MODIFY_SUCCESS_MESSAGE);

        } else {
            if("id".equals(type)) {
                login.setMessage(MODIFY_FAIL_ID_MESSAGE);

            } else {
                login.setMessage(MODIFY_FAIL_PASSWORD_MESSAGE);
            }
        }
        return login;
    }

    private void createMember(Member member) {
        member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));
        loginRepository.save(member);
    }

    private void updateMember(Optional<Member> member) {
        member.get().setPassword(bCryptPasswordEncoder.encode(member.get().getPassword()));
        loginRepository.save(member.get());
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
