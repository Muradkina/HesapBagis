package com.muradev.hesap.service;

import com.muradev.hesap.dto.CreateHesaprequest;
import com.muradev.hesap.dto.dto.HesapDto;
import com.muradev.hesap.dto.converter.HesapDtoConverter;
import com.muradev.hesap.model.Hesap;
import com.muradev.hesap.model.Islem;
import com.muradev.hesap.model.Musteri;
import com.muradev.hesap.repository.HesapRepo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;

@Service
public class HesapService {

    private final HesapRepo repo;
    private final MusteriService musteriService; // CustomerServiste proctected kullandıgım için burda kullanabilirim
    private final HesapDtoConverter converter;
    private final Clock clock;

    public HesapService(HesapRepo repo, MusteriService musteriService,
                        HesapDtoConverter converter, Clock clock) {
        this.repo = repo;
        this.musteriService = musteriService;
        this.converter = converter;
        this.clock = clock;
    }

    public HesapDto createHesap(CreateHesaprequest createHesaprequest) {
        Musteri musteri = musteriService.findMusteriById(createHesaprequest.getMusteriId());

        Hesap hesap = new Hesap(musteri,
                createHesaprequest.getIlkCredi(),
                getLocalDateTimeNow());

        if (createHesaprequest.getIlkCredi().compareTo(BigDecimal.ZERO) > 0) {
            Islem islem = new Islem(createHesaprequest.getIlkCredi(),
                    getLocalDateTimeNow(), hesap);
            hesap.getIslem().add(islem);
        }
         return converter.convertHesap(repo.save(hesap));
    }

    private LocalDateTime getLocalDateTimeNow() {
        Instant instant = clock.instant();
        return LocalDateTime.ofInstant(
                instant,
                Clock.systemDefaultZone().getZone());
    }
}
