package com.muradev.hesap.dto.converter;

import com.muradev.hesap.dto.dto.HesapDto;
import com.muradev.hesap.model.Hesap;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class HesapDtoConverter {

    private final MusteriDtoConverter musteriDtoConverter;
    private final IslemDtoConverter islemDtoConverter;

    public HesapDtoConverter(MusteriDtoConverter musteriDtoConverter, IslemDtoConverter islemDtoConverter) {
        this.musteriDtoConverter = musteriDtoConverter;

        this.islemDtoConverter = islemDtoConverter;
    }

    public HesapDto convertHesap(Hesap from) {
        return new HesapDto(from.getId(),
                from.getBakiye(),
                from.getOluşturulmaTarihi(),
                musteriDtoConverter.convertToHesapMusteri(Optional.ofNullable(from.getMusteri())),
                Objects.requireNonNull(from.getIslem())
                        .stream()
                        .map(islemDtoConverter::convert)
                        .collect(Collectors.toSet()));
    }
}
