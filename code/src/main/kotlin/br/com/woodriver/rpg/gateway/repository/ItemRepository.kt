package br.com.woodriver.rpg.gateway.repository

import br.com.woodriver.rpg.domains.Item
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ItemRepository: JpaRepository<Item, Long> {

}