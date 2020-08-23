package br.com.woodriver.blizzard.usecases.blizzard

import br.com.woodriver.blizzard.domains.BlizzardItem
import br.com.woodriver.blizzard.domains.BlizzardItemDetail
import org.springframework.stereotype.Component

@Component
class GetBlizzardItemUseCase() {
    fun execute(itemId: String): BlizzardItem {
        val itemDetail = BlizzardItemDetail("Key", "Value $itemId")
        val listItem = ArrayList<BlizzardItemDetail>()
        listItem.add(itemDetail)
        return BlizzardItem("links", listItem)
    }
}