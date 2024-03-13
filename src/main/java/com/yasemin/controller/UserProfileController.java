package com.yasemin.controller;

import com.yasemin.dto.request.CommentRequestDto;
import com.yasemin.dto.request.UpdateUserProfileRequestDto;
import com.yasemin.entity.Booking;
import com.yasemin.entity.FavoriHotel;
import com.yasemin.entity.UserProfile;
import com.yasemin.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.yasemin.constants.RestApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(USER_PROFILE)
public class UserProfileController {
    private final UserProfileService userProfileService;

    @PostMapping(GET_USER_PROFILE)
    public ResponseEntity<UserProfile> getUserProfile(String token){
        return ResponseEntity.ok(userProfileService.getUserProfile(token));
    }
    @GetMapping(REZERVASYON_GECMISI)
    public ResponseEntity<List<Booking>> rezervasyonGecmisi(String token){
        return ResponseEntity.ok(userProfileService.rezervasyonGecmisi(token));
    }
    @GetMapping(FAVORI_ADD)
    public ResponseEntity<Boolean> favoriAdd(String token,String hotelId){
        return ResponseEntity.ok(userProfileService.favoriAdd(token,hotelId));
    }
    @GetMapping(FAVORI_GET_ALL)
    public ResponseEntity<List<FavoriHotel>> favoriGetAll(String token){
        return ResponseEntity.ok(userProfileService.favoriGetAll(token));
    }
    @PutMapping(UPDATE)
    public ResponseEntity<UserProfile> update(@RequestBody UpdateUserProfileRequestDto dto){
        return ResponseEntity.ok(userProfileService.update(dto));
    }
}
