package br.com.woodriver.rpg.adapter.output.database.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.apache.logging.log4j.util.Strings

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
