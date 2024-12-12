package br.com.woodriver.rpg.adapter.output.database.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.apache.logging.log4j.util.Strings
import java.math.BigDecimal

@Entity(name = "Item")
@Table(name = "KOT_ITEM")
data class ItemEntity(
    @Id
    @Column(name = "ITM_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    @Column(name = "ITM_NAME", unique = true)
    val name: String = Strings.EMPTY,
    @Column(name = "ITM_WEIGHT")
    val weight: BigDecimal = BigDecimal.ZERO
)
