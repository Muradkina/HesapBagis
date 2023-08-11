package com.muradev.hesap.model

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
data class Islem(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String?,
    val islemTuru: IslemTuru? = IslemTuru.INITIAL,
    val miktar: BigDecimal?,
    val islemTarihi: LocalDateTime?,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "hesap_id", nullable = false)
    val hesap: Hesap

) {

    constructor(miktar: BigDecimal, islemTarihi: LocalDateTime, hesap: Hesap) : this(
        id = null,
        miktar = miktar,
        islemTarihi = islemTarihi,
        islemTuru = IslemTuru.INITIAL,
        hesap = hesap
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Islem

        if (id != other.id) return false
        if (islemTuru != other.islemTuru) return false
        if (miktar != other.miktar) return false
        if (islemTarihi != other.islemTarihi) return false
        if (hesap != other.hesap) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (islemTuru?.hashCode() ?: 0)
        result = 31 * result + (miktar?.hashCode() ?: 0)
        result = 31 * result + (islemTarihi?.hashCode() ?: 0)
        return result
    }
}

enum class IslemTuru {
    INITIAL, TRANSFER
}

