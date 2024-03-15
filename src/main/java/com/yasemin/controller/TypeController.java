package com.yasemin.controller;

import com.yasemin.service.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.yasemin.constants.RestApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(TYPE)
public class TypeController {
    private final TypeService typeService;

    @GetMapping(ADD)
    public ResponseEntity<Boolean> addType(@RequestParam String name){
        return ResponseEntity.ok(typeService.addType(name));
    }
    @PutMapping(UPDATE)
    @CrossOrigin("*")
    public ResponseEntity<Boolean> updateType(@RequestParam String name){
        return ResponseEntity.ok(typeService.updateType(name));
    }
    @DeleteMapping(DELETE)
    @CrossOrigin("*")
    public ResponseEntity<Boolean> deleteType(@RequestParam String name){
        return ResponseEntity.ok(typeService.deleteType(name));
    }

}
