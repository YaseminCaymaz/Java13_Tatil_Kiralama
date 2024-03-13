package com.yasemin.service;

import com.yasemin.dto.request.HotelRequestDto;
import com.yasemin.entity.Comments;
import com.yasemin.entity.Hotel;
import com.yasemin.exception.ErrorType;
import com.yasemin.exception.TatilKiralamaException;
import com.yasemin.mapper.HotelMapper;
import com.yasemin.repository.*;
import com.yasemin.utility.JwtTokenManager;
import com.yasemin.utility.enums.ERole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class HotelService {
    private final HotelRepository hotelRepository;
    private final CommentRepository commentRepository;
    private final HotelTypeRepository hotelTypeRepository;
    private final HotelPropertiesRepository hotelPropertiesRepository;
    private final JwtTokenManager jwtTokenManager;
    private final HotelMapper hotelMapper;

    public Boolean createHotel(HotelRequestDto dto) {
        Optional<String> role = jwtTokenManager.getRoleFromToken(dto.getToken());
        if (role.isEmpty()) {
            throw new TatilKiralamaException(ErrorType.INVALID_TOKEN_ERROR);
        }
        if (!role.get().equals(ERole.ADMIN.toString())) {
            throw new TatilKiralamaException(ErrorType.ROLE_NOT_FOUND);
        }
        Hotel hotel = hotelRepository.save(hotelMapper.fromHotelRequestDto(dto));
//        hotelTypeRepository.save2(dto.getTypeId(),hotel.getId());
//        hotelPropertiesRepository.save2(dto.getPropertiesId(),hotel.getId());
        hotelTypeRepository.save(hotelMapper.fromTypeIdAnHotelId(dto.getTypeId(), hotel.getId()));
        hotelPropertiesRepository.save(hotelMapper.fromPropertiesIdAnHotelId(dto.getPropertiesId(), hotel.getId()));
        return true;
    }


    public Boolean deleteHotel(String id, String token) {
        Optional<String> role = jwtTokenManager.getRoleFromToken(token);
        if (role.isEmpty()) {
            throw new TatilKiralamaException(ErrorType.INVALID_TOKEN_ERROR);
        }
        if (!role.get().equals(ERole.ADMIN.toString())) {
            throw new TatilKiralamaException(ErrorType.ROLE_NOT_FOUND);
        }
        hotelRepository.deleteById(id);
        return true;
    }


    public Hotel getHotelById(String id) {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        if (hotel.isEmpty()) {
            throw new TatilKiralamaException(ErrorType.HOTEL_NOT_FOUND);
        }
        return hotel.get();
    }
    public Double getHotelPoint(String hotelId){
       Double reduce =commentRepository.findOnlyPointByHotelId(hotelId).stream().map(Comments::getPoint).reduce(0.0,Double::sum);
        return reduce / commentRepository.findAllByHotelId(hotelId).size();
    }
    public List<Hotel> getAllHotels() {
        hotelRepository.findAll().stream().forEach(hotel -> {
            hotel.setPoint(getHotelPoint(hotel.getId()));
        });
        return hotelRepository.findAll().stream().sorted().toList();
    }

    public List<Hotel> getAllHotelsByName(String name) {
        Optional<List<Hotel>> hotel = hotelRepository.findAllByNameContaining(name);
        if (hotel.isEmpty()) {
            throw new TatilKiralamaException(ErrorType.HOTEL_NOT_FOUND);
        }
        return hotel.get();
    }
}
