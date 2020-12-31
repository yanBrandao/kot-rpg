package br.com.woodriver.rpg.application.port.input

import br.com.woodriver.rpg.application.domain.Item

interface CreateItemUseCase {
    fun execute(item: Item): Item
}