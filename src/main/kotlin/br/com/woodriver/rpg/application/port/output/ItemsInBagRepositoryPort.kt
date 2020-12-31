package br.com.woodriver.rpg.application.port.output

import br.com.woodriver.rpg.application.domain.Bag

interface ItemsInBagRepositoryPort {
    fun save(bag: Bag, id: Long): Bag
    fun retrieveBag(id: Long): Bag
}