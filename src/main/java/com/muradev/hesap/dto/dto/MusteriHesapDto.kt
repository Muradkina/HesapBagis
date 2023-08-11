package com.muradev.hesap.dto.dto

import java.math.BigDecimal
import java.time.LocalDateTime
//CustomerAccountDto sadece customer bilgisinin olmadıgı dto olucak
// böylelikle api kullanıcılarına daha temiz analaşılır cevap dönmüş olurum
// kullanıcı eğer customer bilgisi çektiginde account bilgisine ulaşır
// kullanıı eğer account bilgisini çekerse customer bilgisine ulaşır
data class MusteriHesapDto (
    val id: String,
    var bakiye: BigDecimal? = BigDecimal.ZERO,
    val islemler: Set<IslemDto>?,
    val OlusturulmaTarihi: LocalDateTime
        )


