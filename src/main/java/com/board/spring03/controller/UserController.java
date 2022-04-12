package com.board.spring03.controller;

import com.board.spring03.dto.UserRequestDto;
import com.board.spring03.model.User;
import com.board.spring03.repository.UserRepository;
import com.board.spring03.security.UserDetailsImpl;
import com.board.spring03.service.KakaoUserService;
import com.board.spring03.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final KakaoUserService kakaoUserService;

    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public String login(Model model, @AuthenticationPrincipal UserDetailsImpl userDetail) {
        if(userDetail != null) {
            model.addAttribute("loggedIn", true);
            model.addAttribute("message", "이미 로그인 하셨습니다.");
        } else
            model.addAttribute("loggedIn", false);
        return "login/loginForm";
    }

//    // 회원 가입 페이지
//    @GetMapping("/user/signup")
//    public String signup() {
//
//        return "login/signup";
//    }

//    // 회원 가입 요청 처리
//    @PostMapping("/user/signup")
//    public String registerUser(UserRequestDto requestDto) {
//        userService.registerUser(requestDto);
//        return "redirect:/user/login";
//    }


    @GetMapping("/user/signup")
    public String addForm(@ModelAttribute("userRequestDto") UserRequestDto userRequestDto) {

        return "login/signup";
    }

    @PostMapping("/user/signup")
    public String save(@Valid @ModelAttribute UserRequestDto userRequestDto, BindingResult result) {
        Optional<User> found = userRepository.findByUsername(userRequestDto.getUsername());
        if (found.isPresent()) {
            result.reject("usernameCheck","이미 등록된 ID입니다.");
        }
        if(!userRequestDto.getPassword().equals(userRequestDto.getPassword2())){
            result.reject("passwordCheck","패스워드가 일치하지 않습니다.");
        }
        if (userRequestDto.getPassword().contains(userRequestDto.getUsername())){
            result.reject("passwordCheck2","아이디와 동일한 값을 포함 할 수 없습니다.");
        }

        if (result.hasErrors()) {
            return "login/signup";
        }
        userService.registerUser(userRequestDto);
        return "redirect:/user/login";
    }


    @GetMapping("/user/info")
    public User userinfo( @AuthenticationPrincipal UserDetailsImpl userDetail){
        return userDetail.getUser();
    }


    @GetMapping("/user/kakao/callback")
    public String kakaoLogin(@RequestParam String code) throws JsonProcessingException {
// authorizedCode: 카카오 서버로부터 받은 인가 코드
        kakaoUserService.kakaoLogin(code);

        return "redirect:/";
    }
}