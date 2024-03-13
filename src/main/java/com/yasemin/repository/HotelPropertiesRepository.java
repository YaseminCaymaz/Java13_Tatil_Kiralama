package com.yasemin.repository;

import com.yasemin.entity.HotelProperties;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HotelPropertiesRepository extends MongoRepository<HotelProperties, String> {
}
