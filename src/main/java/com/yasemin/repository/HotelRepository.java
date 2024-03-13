package com.yasemin.repository;

import com.yasemin.entity.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface HotelRepository extends MongoRepository<Hotel, String> {

    Optional<List<Hotel>> findAllByNameContaining(String name);
}
