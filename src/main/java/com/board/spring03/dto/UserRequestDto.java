package com.board.spring03.dto;

import lombok.*;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class UserRequestDto {

    @NotBlank
    @Pattern(regexp = "^(?=.*[a-zA-Z])[-a-zA-Z0-9]{3,10}$", message = "아이디 형식이 올바르지 않습니다.")
    private String username;

    @NotBlank
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z])[0-9a-zA-Z~!@#$%^&*]{4,20}$", message = "비밀번호 형식이 올바르지 않습니다.")
    private String password;

//    @NotBlank
//    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "닉네임 형식이 올바르지 않습니다.")
//    private String nickname;

    @NotBlank
    private String password2;

//    @AssertTrue(message = "패스워드가 일치하지 않습니다.")
//    public boolean checkPassword = password.equals(password2);


    @NotBlank
    @Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    private boolean admin = false;
    private String adminToken = "";


}
