package br.com.woodriver.rpg.usecase.item

import br.com.woodriver.rpg.domains.Item
import br.com.woodriver.rpg.exceptions.IconEmptyException
import br.com.woodriver.rpg.gateway.repository.ItemRepository
import org.springframework.stereotype.Component

@Component
class CreateItemUseCase(val itemRepository: ItemRepository,
                        val getBlizzardItemsUseCase: GetBlizzardItemsUseCase) {

    fun execute(item: Item) : Item{
        if(item.icon.isEmpty())
            throw IconEmptyException("zica")
        item.icon = getBlizzardItemsUseCase.execute(item.icon).assets[0].value
        return itemRepository.save(item);
    }
}