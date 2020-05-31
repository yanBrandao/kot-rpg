package br.com.woodriver.rpg.usecases.bag

import br.com.woodriver.rpg.domain.Bag
import br.com.woodriver.rpg.gateway.repository.BagRepository
import org.springframework.stereotype.Component

@Component
class InsertItemIntoBagPlayerUseCase(val bagRepository: BagRepository) {

    fun execute(bag: Bag): Bag{
        return bagRepository.saveAndFlush(bag)
    }
}