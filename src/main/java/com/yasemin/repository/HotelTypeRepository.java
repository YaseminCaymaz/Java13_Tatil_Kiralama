package com.yasemin.repository;

import com.yasemin.entity.HotelType;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HotelTypeRepository extends MongoRepository<HotelType, String> {


}
