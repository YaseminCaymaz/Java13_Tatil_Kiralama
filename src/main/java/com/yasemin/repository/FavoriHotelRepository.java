package com.yasemin.repository;

import com.yasemin.entity.FavoriHotel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

public interface FavoriHotelRepository extends MongoRepository<FavoriHotel, String> {
    boolean existsByUserIdAndHotelId(String userId, String hotelId);

    List<FavoriHotel> findAllByUserId(String userId);

}
