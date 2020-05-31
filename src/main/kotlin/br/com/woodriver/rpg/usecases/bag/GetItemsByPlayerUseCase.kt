package br.com.woodriver.rpg.usecases.bag

import br.com.woodriver.rpg.domain.Bag
import br.com.woodriver.rpg.gateway.repository.BagRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Component
class GetItemsByPlayerUseCase(val bagRepository: BagRepository) {

    fun execute(pageable: Pageable, key: Long): Page<Bag>{
        return bagRepository.findAllByBagIdBagPlrIdKey(pageable, key)
    }
}