package br.com.woodriver.rpg.application.port.input

import br.com.woodriver.rpg.application.domain.Bag

interface CreateBagUseCase {
    fun execute(bag: Bag): Bag
}