package com.yasemin.controller;

import com.yasemin.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.yasemin.constants.RestApiUrls.ROOM;

@RestController
@RequiredArgsConstructor
@RequestMapping(ROOM)
public class RoomController {
    private final RoomService roomService;
}
