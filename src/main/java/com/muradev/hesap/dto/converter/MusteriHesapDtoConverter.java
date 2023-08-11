package com.muradev.hesap.dto.converter;

import com.muradev.hesap.dto.dto.MusteriHesapDto;
import com.muradev.hesap.model.Hesap;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class MusteriHesapDtoConverter {
    private final IslemDtoConverter islemDtoConverter;

    public MusteriHesapDtoConverter(IslemDtoConverter islemDtoConverter) {
        this.islemDtoConverter = islemDtoConverter;
    }

    public MusteriHesapDto convert(Hesap from) {
        return new MusteriHesapDto(
                Objects.requireNonNull(from.getId()),
                from.getBakiye(),
                from.getIslem()
                        .stream()
                        .map(islemDtoConverter::convert)
                        .collect(Collectors.toSet()),
                from.getOluşturulmaTarihi());
    }
}
