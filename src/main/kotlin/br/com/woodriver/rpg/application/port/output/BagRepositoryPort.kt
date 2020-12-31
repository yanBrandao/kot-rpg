package br.com.woodriver.rpg.application.port.output

import br.com.woodriver.rpg.application.domain.Bag

interface BagRepositoryPort {
    fun save(bag: Bag): Bag
}