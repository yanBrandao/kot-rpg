package br.com.woodriver.rpg.usecase.item

import br.com.woodriver.rpg.domains.Item
import br.com.woodriver.rpg.gateway.repository.ItemRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Component
class GetAllPaginatedUseCase(val itemRepository: ItemRepository) {
    fun execute(pageable: Pageable): Page<Item> {
        return itemRepository.findAll(pageable)
    }
}