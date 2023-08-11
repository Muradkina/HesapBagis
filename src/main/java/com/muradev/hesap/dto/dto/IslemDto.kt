package com.muradev.hesap.dto.dto

import com.muradev.hesap.model.IslemTuru
import java.math.BigDecimal
import java.time.LocalDateTime

data class IslemDto (
        val id: String?,
        val islemTuru: IslemTuru? = IslemTuru.INITIAL,
        val miktar: BigDecimal?,
        val islemTarihi: LocalDateTime?
        )