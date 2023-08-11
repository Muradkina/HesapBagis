package com.muradev.hesap.dto.dto

import java.math.BigDecimal
import java.time.LocalDateTime

// DTO lar benim modellerimde olmayan ama eklemek istediğim başka değerleri de eklememem için bana kolaylık sağlar
//APİ Responsevlerimiz de daha esnek olmamızı sağlar kontrolleri elde tutmamızı sağlar bi nevi

data class HesapDto (
    val id: String?,
    val bakiye: BigDecimal?,
    val olusturulmaTarihi: LocalDateTime?,
    val musteri: HesapMusteriDto?,
    val islemler: Set<IslemDto>?
)