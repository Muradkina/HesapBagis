package com.muradev.hesap.dto

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import java.math.BigDecimal

data class CreateHesaprequest (
    @field:NotBlank(message = "MüşteriId boş bırakılamaz")
    val musteriId: String,
    @field:Min(0, message = "İlk Kredi değeri negatif bir değer olmamalıdır")
    val ilkCredi: BigDecimal
        )