package br.com.woodriver.rpg.application.domain

import br.com.woodriver.rpg.application.port.output.BagRepositoryPort
import org.apache.logging.log4j.util.Strings

data class Bag(
        val color: String = Strings.EMPTY,
        val slots: Int = 0,
        val items: ArrayList<Item> = arrayListOf()
) {
    fun save(bagRepository: BagRepositoryPort): Bag {
        return bagRepository.save(this)
    }

    fun haveItem(item: Item): Int {
        var index = -1
        this.items.forEachIndexed { i, itm ->
            if (itm.name == item.name)
                index = i
        }
        return index
    }

    companion object {
        const val BAG_IDENTIFICATION = "BagId"
    }
}