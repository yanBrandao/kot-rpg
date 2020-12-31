package br.com.woodriver.rpg.adapter.output.database.jpa

import br.com.woodriver.rpg.adapter.output.database.entity.ItemEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ItemJpaRepository: JpaRepository<ItemEntity, Long> {

    fun findItemEntityByName(name: String): ItemEntity
}