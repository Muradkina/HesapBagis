package com.muradev.hesap.controller;

import com.muradev.hesap.dto.dto.MusteriDto;
import com.muradev.hesap.service.MusteriService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/musteri")
public class MusteriController {
    private final MusteriService musteriService;

    public MusteriController(MusteriService musteriService) {
        this.musteriService = musteriService;
    }

    // @PathVariable  istek URI eşlemesindeki şablon değişkenlerini işlemek ve
    //bunları yöntem parametreleri olarak ayarlamak için kullanılabilir.
    //@PathVariable  ile @RequestBody arasında ki fark nedir?
    //@PathVariable  URL den bilgi alır
    // @RequestBody json dan bilgi alır
    @GetMapping("/{musteriId}")
    public ResponseEntity<MusteriDto> getByMusteriById(@PathVariable String musteriId) {
        return ResponseEntity.ok(musteriService.getAllMusteriById(musteriId));
    }

    @GetMapping
    public ResponseEntity<List<MusteriDto>> getAllMusteriler() {
        return ResponseEntity.ok(musteriService.getAllMusteriler());
    }
}
