package com.muradev.hesap.dto.converter;

import com.muradev.hesap.dto.dto.HesapMusteriDto;
import com.muradev.hesap.dto.dto.MusteriDto;
import com.muradev.hesap.model.Musteri;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MusteriDtoConverter {

    private final MusteriHesapDtoConverter musteriHesapDtoConverter;

    public MusteriDtoConverter(MusteriHesapDtoConverter converter) {
        this.musteriHesapDtoConverter = converter;
    }

    public HesapMusteriDto convertToHesapMusteri(Optional<Musteri> from) {
        return from.map(f -> new HesapMusteriDto(f.getId(), f.getAdi(), f.getSoyadi())).orElse(null);
    }

    public MusteriDto convertToMusteriDto(Musteri from) {
        return new MusteriDto(
                from.getId(),
                from.getAdi(),
                from.getSoyadi(),
                from.getHesaplar()
                        .stream()
                        .map(musteriHesapDtoConverter::convert)
                        .collect(Collectors.toSet()));

    }

}
