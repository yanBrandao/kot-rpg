package br.com.woodriver.rpg.domains

import br.com.woodriver.rpg.domains.types.PositionType
import br.com.woodriver.rpg.domains.types.RarityType
import javax.persistence.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Entity
@Table(name = "KOR_ITEM")
@SequenceGenerator(name = "ITM_ID_SEQ", initialValue = 1006, allocationSize = 100)
class Item(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ITM_ID_SEQ")
        @Column(name = "ITM_ID", unique = true, nullable = false)
        var key: Long,
        @Column(name = "ITM_NAME")
        val name: String,
        @Column(name = "ITM_WEIGHT")
        val weight: Double,
        @Column(name = "ITM_PRICE")
        val price: Double,
        @Column(name = "ITM_POSITION")
        val position: PositionType,
        @Column(name = "ITM_RARITY")
        val rarity: RarityType,
        @Column(name = "ITM_ICON")
        var icon: String) {
}