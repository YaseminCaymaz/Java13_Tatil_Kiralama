package com.yasemin.repository;

import com.yasemin.entity.Comments;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


import java.util.List;

public interface CommentRepository extends MongoRepository<Comments, String> {
    List<Comments> findAllByUserId(String userId);

    List<Comments> findAllByHotelId(String hotelId);

    @Query(value = "{ 'hotelId' : ?0 }", fields = "{ 'point' : 1 }")
    List<Comments> findOnlyPointByHotelId(String hotelId);
}
