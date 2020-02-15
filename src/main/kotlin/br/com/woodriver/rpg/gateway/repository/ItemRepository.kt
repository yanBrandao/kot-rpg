package br.com.woodriver.rpg.gateway.repository

import br.com.woodriver.rpg.domains.Item
import org.springframework.data.jpa.repository.JpaRepository

interface ItemRepository: JpaRepository<Item, Long> {

}