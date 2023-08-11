package com.muradev.hesap.model

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import org.hibernate.annotations.GenericGenerator

@Entity
data class Musteri(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String?,
    val adi: String?,
    val soyadi: String?,

    @OneToMany(mappedBy = "musteri", fetch = FetchType.EAGER)
    val hesaplar: Set<Hesap>

) {

    constructor(name: String, surname: String) : this("", name, surname, HashSet())

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Musteri

        if (id != other.id) return false
        if (adi != other.adi) return false
        if (soyadi != other.soyadi) return false
        if (hesaplar != other.hesaplar) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (adi?.hashCode() ?: 0)
        result = 31 * result + (soyadi?.hashCode() ?: 0)
        return result
    }

}