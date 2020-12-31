package br.com.woodriver.rpg.application.port.output

import br.com.woodriver.rpg.application.domain.Item

interface ItemRepositoryPort {
    fun save(item: Item): Item
    fun retrieveItemByName(name: String): Item
}