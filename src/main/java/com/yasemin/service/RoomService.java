package com.yasemin.service;

import com.yasemin.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomService {
   private final RoomRepository roomRepository;
}
