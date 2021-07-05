package org.zerock.j08.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.j08.dto.FoodStoreDTO;
import org.zerock.j08.dto.ListFoodStoreDTO;
import org.zerock.j08.service.FoodStoreService;

import java.util.List;

@RestController
@RequestMapping("/stores")
@Log4j2
@RequiredArgsConstructor
public class FoodStoreController {

    private final FoodStoreService foodStoreService;

    @PostMapping("/")
    public ResponseEntity<Long> register(@RequestBody FoodStoreDTO dto) {

        log.info(dto);

        Long fno = foodStoreService.register(dto);

        return ResponseEntity.ok(fno);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ListFoodStoreDTO>> getList(){

        return ResponseEntity.ok(foodStoreService.getList());
    }
}
