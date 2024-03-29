package com.yasemin.service;

import com.yasemin.dto.request.AuthRegisterRequestDto;
import com.yasemin.dto.request.LoginRequestDto;
import com.yasemin.entity.Auth;
import com.yasemin.entity.UserProfile;
import com.yasemin.exception.ErrorType;
import com.yasemin.exception.TatilKiralamaException;
import com.yasemin.repository.AuthRepository;
import com.yasemin.repository.UserProfileRepository;
import com.yasemin.utility.JwtTokenManager;
import com.yasemin.utility.MailCheckManager;
import com.yasemin.utility.enums.EStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    private final UserProfileRepository userProfileRepository;
    private final UserProfileService userProfileService;
    private final JwtTokenManager jwtTokenManager;
    private final MailCheckManager  mailCheckManager;
    private final MailSenderService mailSenderService;

    public Boolean register(AuthRegisterRequestDto dto) {
        Optional<Auth> optionalAuth=authRepository.findByEmail(dto.getEmail());
        if (optionalAuth.isPresent()) {
           throw new TatilKiralamaException(ErrorType.ERROR_DUPLICATE_EMAIL);
        }
        if (!dto.getPassword().equals(dto.getRePassword())){
            throw new TatilKiralamaException(ErrorType.ERROR_PASSWORD_NOT_MATCH);
        }

      Auth auth = authRepository.save(Auth.builder()
                       .name(dto.getName())
                       .surname(dto.getSurname())
                       .phone(dto.getPhone())
               .email(dto.getEmail())
               .password(dto.getPassword())
               .build());
        mailSenderService.sendMail(auth.getActivationCode(),auth.getEmail());
        userProfileService.createUserProfile(auth);

        return true;

    }
    public Boolean activation(String activationCode) {
        Optional<Auth> auth = authRepository.findByActivationCode(activationCode);
        if (auth.isEmpty())
            throw new TatilKiralamaException(ErrorType.USER_NOT_FOUND);
        if (auth.get().getActivationCode().equals(activationCode)) {
            auth.get().setStatus(EStatus.ACTIVE);
            authRepository.save(auth.get());
            userProfileService.activation(auth.get().getId());
            return true;
        }
        return false;
    }

    public String login(LoginRequestDto dto) {
        Optional<UserProfile> user=userProfileRepository.findByEmailAndPassword(dto.getEmail(),dto.getPassword());
        if (user.isEmpty()){
            throw new TatilKiralamaException(ErrorType.ERROR_INVALID_LOGIN_PARAMETER);
        }
        return jwtTokenManager.createToken(user.get().getId(),mailCheckManager.checkMail(user.get().getEmail())).orElseThrow(() -> new TatilKiralamaException(ErrorType.TOKEN_OLUSTURULAMADI));
    }
}
