package com.board.spring03.service;


import com.board.spring03.dto.UserRequestDto;
import com.board.spring03.model.User;
import com.board.spring03.model.UserRoleEnum;
import com.board.spring03.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final PasswordEncoder passwordEncoder; //패스워드 암호화
    private final UserRepository userRepository;
    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC"; //임의로 만든 임시 관리자 토큰

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public void registerUser(UserRequestDto userRequestDto) {
        // 회원 ID 중복 확인
        String username = userRequestDto.getUsername();
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }

        // 패스워드 암호화

        if (userRequestDto.getPassword().contains(username)) {
            throw new IllegalArgumentException("아이디와 동일한 값을 포함 할 수 없습니다.");
        }
        if (!userRequestDto.getPassword().equals(userRequestDto.getPassword2())) {
            throw new IllegalArgumentException("패스워드가 일치하지 않습니다.");
        }
        String password = passwordEncoder.encode(userRequestDto.getPassword());
        String email = userRequestDto.getEmail();

        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (userRequestDto.isAdmin()) {
            if (!userRequestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        User user = new User(username, password, email, role);
        userRepository.save(user);
    }



}
