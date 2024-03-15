package com.yasemin.controller;

import com.yasemin.dto.request.HotelRequestDto;
import com.yasemin.entity.Hotel;
import com.yasemin.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.yasemin.constants.RestApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(HOTEL)
public class HotelController {
    private final HotelService hotelService;
    @GetMapping(ADD)
    public ResponseEntity<Boolean> createHotel(@RequestBody HotelRequestDto dto){
        return ResponseEntity.ok(hotelService.createHotel(dto));
    }
    @GetMapping(GET_ALL)
    public ResponseEntity<List<Hotel>> getAllHotels(){
        return ResponseEntity.ok(hotelService.getAllHotels());
    }
    @GetMapping(GET_BY_ID)
    public ResponseEntity<Hotel> getHotelById(@RequestParam String id){
        return ResponseEntity.ok(hotelService.getHotelById(id));
    }
    @DeleteMapping(DELETE_BY_ID)
    @CrossOrigin("*")
    public ResponseEntity<Boolean> deleteHotel(@RequestParam String id,String token){
        return ResponseEntity.ok(hotelService.deleteHotel(id,token));
    }
    @GetMapping(GET_ALL_BY_NAME)
    public ResponseEntity<List<Hotel>> getAllHotelsByName(@RequestParam String name){
        return ResponseEntity.ok(hotelService.getAllHotelsByName(name));
    }
}
