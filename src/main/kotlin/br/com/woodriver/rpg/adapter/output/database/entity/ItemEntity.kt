package br.com.woodriver.rpg.adapter.output.database.entity

import org.apache.logging.log4j.util.Strings
import java.math.BigDecimal
import javax.persistence.*

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