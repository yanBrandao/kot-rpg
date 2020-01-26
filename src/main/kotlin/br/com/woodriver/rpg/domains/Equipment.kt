package br.com.woodriver.rpg.domains

import br.com.woodriver.rpg.domains.compositekeys.EquipmentId
import br.com.woodriver.rpg.domains.types.PositionType
import javax.persistence.*

@Entity
@Table(name = "KOR_EQUIPMENT")
data class Equipment(
        @EmbeddedId
        var equipmentId: EquipmentId,
        @Column(name="EQP_POSITION", unique = true)
        var positionType: PositionType
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Equipment

        if (equipmentId != other.equipmentId) return false
        if (positionType != other.positionType) return false

        return true
    }

    override fun hashCode(): Int {
        var result = equipmentId.hashCode()
        result = 31 * result + positionType.hashCode()
        return result
    }
}