package br.com.woodriver.rpg.adapter.output.database.jpa

import br.com.woodriver.rpg.adapter.output.database.entity.ItemsInBagEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ItemsInBagJpaRepository: JpaRepository<ItemsInBagEntity, ItemsInBagEntity> {

    fun findItemsInBagEntitiesByBagId(bag_id: Long): List<ItemsInBagEntity>
}