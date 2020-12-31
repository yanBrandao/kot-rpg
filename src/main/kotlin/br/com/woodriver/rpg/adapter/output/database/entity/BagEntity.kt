package br.com.woodriver.rpg.adapter.output.database.entity

import org.apache.logging.log4j.util.Strings
import javax.persistence.*

@Entity(name = "Bag")
@Table(name = "KOT_BAG")
data class BagEntity(
        @Id
    @Column(name = "BAG_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
        val color: String = Strings.EMPTY,
        val slots: Int = 0
)