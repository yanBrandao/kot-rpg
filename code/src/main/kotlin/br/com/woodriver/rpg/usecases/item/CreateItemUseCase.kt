package br.com.woodriver.rpg.usecases.item

import br.com.woodriver.rpg.configuration.BlizzardTokenConfiguration
import br.com.woodriver.rpg.domains.Item
import br.com.woodriver.rpg.exceptions.IconEmptyException
import br.com.woodriver.rpg.gateway.client.BlizzardAPIClient
import br.com.woodriver.rpg.gateway.repository.ItemRepository
import org.springframework.stereotype.Component

@Component
class CreateItemUseCase(val itemRepository: ItemRepository,
                        val blizzardAPIClient: BlizzardAPIClient,
                        val blizzardTokenConfiguration: BlizzardTokenConfiguration) {

    fun execute(item: Item) : Item{
        if(item.icon.isEmpty())
            throw IconEmptyException("ID não pode ser vazio.")
        item.icon = blizzardAPIClient.getItemIcon(item.icon, "Bearer " +
                blizzardTokenConfiguration.getToken())
                .assets[0].value
        return itemRepository.save(item)
    }
}