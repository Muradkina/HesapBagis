package com.muradev.hesap.model

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
data class Hesap(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",
    val bakiye: BigDecimal? = BigDecimal.ZERO,
    val oluşturulmaTarihi: LocalDateTime,
    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "musteri_id", nullable = false)
    val musteri: Musteri?,

   /* mappedBy transaction değeri transaction modelinin içerisinde karşılık gelen isimdir.
    yani  Transaction da isim  değişirsem gidip account da da ismi değiştirmek zorunda kalıcam
    mappedBy bunun önğne geçiyor
*/
    @OneToMany(mappedBy = "hesap", fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    val islem: Set<Islem> = HashSet()

    //ManyToOne kullandğım için HasCode  fonks. ovvirede etmemiz lazım. Neden etmemiz lazım?

) {

    constructor(musteri: Musteri, bakiye: BigDecimal, oluşturulmaTarihi: LocalDateTime) : this(
        "",
        musteri = musteri,
        bakiye = bakiye,
        oluşturulmaTarihi = oluşturulmaTarihi
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Hesap

        if (id != other.id) return false
        if (bakiye != other.bakiye) return false
        if (oluşturulmaTarihi != other.oluşturulmaTarihi) return false
        if (musteri != other.musteri) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (bakiye?.hashCode() ?: 0)
        result = 31 * result + oluşturulmaTarihi.hashCode()
        result = 31 * result + (musteri?.hashCode() ?: 0)
        return result
    }
}
