package br.com.woodriver.rpg.application.port.input

import br.com.woodriver.rpg.application.domain.Bag
import br.com.woodriver.rpg.application.domain.Item

interface AddBagItemUseCase {
    fun execute(item: Item, id: Long): Bag
}