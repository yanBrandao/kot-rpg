package br.com.woodriver.rpg.adapter.output.database

import br.com.woodriver.rpg.adapter.input.web.v1.converter.createBag
import br.com.woodriver.rpg.adapter.output.database.converter.toDomain
import br.com.woodriver.rpg.adapter.output.database.entity.ItemsInBagEntity
import br.com.woodriver.rpg.adapter.output.database.jpa.BagJpaRepository
import br.com.woodriver.rpg.adapter.output.database.jpa.ItemJpaRepository
import br.com.woodriver.rpg.adapter.output.database.jpa.ItemsInBagJpaRepository
import br.com.woodriver.rpg.adapter.output.database.keys.ItemsInBagKey
import br.com.woodriver.rpg.adapter.output.logging.logger
import br.com.woodriver.rpg.adapter.output.logging.objectToJson
import br.com.woodriver.rpg.application.domain.Bag
import br.com.woodriver.rpg.application.domain.Bag.Companion.BAG_IDENTIFICATION
import br.com.woodriver.rpg.application.domain.Item
import br.com.woodriver.rpg.application.port.output.ItemsInBagRepositoryPort
import net.logstash.logback.argument.StructuredArguments.kv
import org.springframework.stereotype.Repository

@Repository
class ItemsInBagRepository(
        private val itemsInBagJpaRepository: ItemsInBagJpaRepository,
        private val itemJpaRepository: ItemJpaRepository,
        private val bagJpaRepository: BagJpaRepository
): ItemsInBagRepositoryPort {

    val logger = logger<ItemsInBagRepository>()

    override fun save(bag: Bag, id: Long): Bag {
        logger.info("Starting to save items {} into [{}] bag.",
            bag.items.objectToJson(), kv(BAG_IDENTIFICATION, id))
        val itemsInBag = retrieveItemsInBagByItemName(bag, id)
        return itemsInBagJpaRepository.saveAll(itemsInBag).toDomain()
    }

    override fun retrieveBag(id: Long): Bag {
        val bag = bagJpaRepository.findById(id).get()
        return createBag(bag, retrieveItemListByBagId(id))
    }

    private fun retrieveItemListByBagId(id: Long): ArrayList<Item> {
        return itemsInBagJpaRepository.findItemsInBagEntitiesByBagId(id).map {
            itemJpaRepository.findById(it.item.id).get().toDomain(it.quantity)
        } as ArrayList<Item>
    }

    private fun retrieveItemsInBagByItemName(bag: Bag, id: Long): List<ItemsInBagEntity> {
        return bag.items.map {
            val currentItem = itemJpaRepository.findItemEntityByName(it.name)
            ItemsInBagEntity(
                    id = ItemsInBagKey(id, currentItem.id),
                    bag = bagJpaRepository.findById(id).get(),
                    item = currentItem,
                    quantity = it.quantity
            )
        }
    }
}