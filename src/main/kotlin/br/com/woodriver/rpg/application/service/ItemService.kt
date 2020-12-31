package br.com.woodriver.rpg.application.service

import br.com.woodriver.rpg.application.domain.Item
import br.com.woodriver.rpg.application.port.input.CreateItemUseCase
import br.com.woodriver.rpg.application.port.output.ItemRepositoryPort
import org.springframework.stereotype.Service

@Service
class ItemService(
        private val itemRepositoryPort: ItemRepositoryPort
): CreateItemUseCase {
    override fun execute(item: Item): Item {
        return itemRepositoryPort.save(item)
    }
}