package com.muradev.hesap.dto.converter;

import com.muradev.hesap.dto.dto.IslemDto;
import com.muradev.hesap.model.Islem;
import org.springframework.stereotype.Component;

@Component
public class IslemDtoConverter {

    public IslemDto convert(Islem islem) {
        return new IslemDto(islem.getId(),
                islem.getIslemTuru(),
                islem.getMiktar(),
                islem.getIslemTarihi());
    }
}
