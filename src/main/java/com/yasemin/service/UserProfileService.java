package com.yasemin.service;

import com.yasemin.dto.request.AuthRegisterRequestDto;
import com.yasemin.dto.request.CommentRequestDto;
import com.yasemin.dto.request.UpdateUserProfileRequestDto;
import com.yasemin.entity.Auth;
import com.yasemin.entity.Booking;
import com.yasemin.entity.FavoriHotel;
import com.yasemin.entity.UserProfile;
import com.yasemin.exception.ErrorType;
import com.yasemin.exception.TatilKiralamaException;
import com.yasemin.repository.BookingRepository;
import com.yasemin.repository.FavoriHotelRepository;
import com.yasemin.repository.UserProfileRepository;
import com.yasemin.utility.JwtTokenManager;
import com.yasemin.utility.enums.ERole;
import com.yasemin.utility.enums.EStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserProfileService {
    private final UserProfileRepository userProfileRepository;
    private final FavoriHotelRepository favoriHotelRepository;
    private final JwtTokenManager jwtTokenManager;
    private final BookingRepository bookingRepository;

    public void createUserProfile(Auth auth) {
        userProfileRepository.save(UserProfile.builder()
                        .authId(auth.getId())
                .name(auth.getName())
                .surname(auth.getSurname())
                .email(auth.getEmail())
                .password(auth.getPassword())
                .build());
    }
    public String userDogrula(String token){
        Optional<String> role=jwtTokenManager.getRoleFromToken(token);
        if (role.isEmpty()){
            throw new TatilKiralamaException(ErrorType.INVALID_TOKEN_ERROR);
        }
        if(!role.get().toUpperCase().equals(ERole.USER.toString())){
            throw new TatilKiralamaException(ErrorType.ROLE_NOT_FOUND);
        }
        Optional<String> userId = jwtTokenManager.validateToken(token);
        if(userId.isEmpty()){
            throw new TatilKiralamaException(ErrorType.INVALID_TOKEN_ERROR);
        }
        return userId.get();
    }

    public UserProfile update(UpdateUserProfileRequestDto dto) {
        String userId=userDogrula(dto.getToken());
        Optional<UserProfile> optionalUserProfile = userProfileRepository.findById(userId);
        if(optionalUserProfile.isEmpty()){
            throw new TatilKiralamaException(ErrorType.USER_NOT_FOUND);
        }
        optionalUserProfile.get().setName(dto.getName());
        optionalUserProfile.get().setSurname(dto.getSurname());
        optionalUserProfile.get().setEmail(dto.getEmail());
        optionalUserProfile.get().setPassword(dto.getPassword());
        return userProfileRepository.save(optionalUserProfile.get());
    }

    public UserProfile getUserProfile(String token) {
        String userId=userDogrula(token);
        Optional<UserProfile> optionalUserProfile = userProfileRepository.findById(userId);
        if(optionalUserProfile.isEmpty()){
            throw new TatilKiralamaException(ErrorType.USER_NOT_FOUND);
        }
        return optionalUserProfile.get();
    }


    public List<Booking> rezervasyonGecmisi(String token) {
        String userId=userDogrula(token);
        List<Booking> bookings=bookingRepository.findAllByUserId(userId);
        if (bookings.isEmpty()){
            throw new TatilKiralamaException(ErrorType.BOOKING_NOT_FOUND);
        }
        return bookings;

    }

    public Boolean favoriAdd(String token, String hotelId) {
        String userId=userDogrula(token);
        if (favoriHotelRepository.existsByUserIdAndHotelId(userId,hotelId)){
            throw new TatilKiralamaException(ErrorType.FAVORI_HOTEL_EXIST);
        }
        favoriHotelRepository.save(FavoriHotel.builder()
                .userId(userId)
                .hotelId(hotelId)
                .build());
        return true;
    }

    public List<FavoriHotel> favoriGetAll(String token) {
        String userId=userDogrula(token);
        List<FavoriHotel> favoriHotelList=favoriHotelRepository.findAllByUserId(userId);
        if (favoriHotelList.isEmpty()){
            throw new TatilKiralamaException(ErrorType.FAVORI_HOTEL_NOT_FOUND);
        }
        return favoriHotelList;
    }

    public void activation(String id) {
        Optional<UserProfile> optionalAuthId = userProfileRepository.findByAuthId(id);
        if(optionalAuthId.isEmpty()){
            throw new TatilKiralamaException(ErrorType.USER_NOT_FOUND);
        }
        optionalAuthId.get().setStatus(EStatus.ACTIVE);
        userProfileRepository.save(optionalAuthId.get());
    }
}
