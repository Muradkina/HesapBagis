package com.muradev.hesap.controller;

import com.muradev.hesap.dto.CreateHesaprequest;
import com.muradev.hesap.dto.dto.HesapDto;
import com.muradev.hesap.service.HesapService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/hesap") //versiyonlama api lerimizi 1 den fazla kullanmamızı sağlar
public class HesapController {
    private final HesapService hesapService;

    public HesapController(HesapService hesapService) {
        this.hesapService = hesapService;
    }
    //accountu create edeceğim için postMapping ilişkisi kurdum
    //PostMapping ve PutMapping arasında ki fark nedir ?
    //POST kaynağa veri göndermek için kullanılır
    // PUT ise aynı kaynağa aynı adres ile erişilir ve eğer içerik var ise gelen veriler ile değiştirilir
    //eğer içerik yok ise yeni içerik yaratılır. Kısaca PUT veri güncellemek için kullanılır.
    @PostMapping
    public ResponseEntity<HesapDto> createAccount(@Valid @RequestBody CreateHesaprequest hesaprequest) {
        return ResponseEntity.ok(hesapService.createHesap(hesaprequest));
    }
}
