package br.futurodev.joinville.m1s10.controllers;

import br.futurodev.joinville.m1s10.dtos.logins.LoginRequestDto;
import br.futurodev.joinville.m1s10.dtos.logins.LoginResponseDto;
import br.futurodev.joinville.m1s10.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("login")
public class LoginController {

    private final UserService userService;

    @PostMapping
    public LoginResponseDto login(@RequestBody LoginRequestDto dto) {
        return userService.authenticate(dto);
    }

}
