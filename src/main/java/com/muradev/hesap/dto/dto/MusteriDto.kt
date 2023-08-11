package com.muradev.hesap.dto.dto

data class MusteriDto(
    val id: String?,
    val adi: String?,
    val soyadi: String?,
    val hesaplar: Set<MusteriHesapDto>?
)
