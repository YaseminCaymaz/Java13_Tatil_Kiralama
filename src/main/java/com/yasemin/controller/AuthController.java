package com.yasemin.controller;

import com.yasemin.dto.request.AuthRegisterRequestDto;
import com.yasemin.dto.request.LoginRequestDto;
import com.yasemin.entity.UserProfile;
import com.yasemin.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.yasemin.constants.RestApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(AUTH)
public class AuthController {
    private final AuthService authService;

    @PostMapping(REGISTER)
    @CrossOrigin("*")
    public ResponseEntity<Boolean> register(@RequestBody AuthRegisterRequestDto dto) {
        return ResponseEntity.ok(authService.register(dto));
    }
    @PostMapping(LOGIN)
    @CrossOrigin("*")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto dto) {
        return ResponseEntity.ok(authService.login(dto));
    }
    @PostMapping(ACTIVATION)
    @CrossOrigin("*")
    public ResponseEntity<Boolean> activation(@RequestParam String activationCode) {
        return ResponseEntity.ok(authService.activation(activationCode));
    }
}
