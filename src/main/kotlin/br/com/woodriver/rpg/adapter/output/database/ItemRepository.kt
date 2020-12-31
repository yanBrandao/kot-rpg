package br.com.woodriver.rpg.adapter.output.database

import br.com.woodriver.rpg.adapter.output.database.converter.toDomain
import br.com.woodriver.rpg.adapter.output.database.converter.toEntity
import br.com.woodriver.rpg.adapter.output.database.jpa.ItemJpaRepository
import br.com.woodriver.rpg.adapter.output.logging.logger
import br.com.woodriver.rpg.application.domain.Item
import br.com.woodriver.rpg.application.domain.Item.Companion.ITEM_IDENTIFICATION
import br.com.woodriver.rpg.application.port.output.ItemRepositoryPort
import net.logstash.logback.argument.StructuredArguments.kv
import org.springframework.stereotype.Repository

@Repository
class ItemRepository(
        val itemJpaRepository: ItemJpaRepository
): ItemRepositoryPort {

    val logger = logger<ItemRepository>()

    override fun save(item: Item): Item {
        logger.info("Starting to save item into database")
        return itemJpaRepository.save(item.toEntity()).apply {
            logger.info("Done successfully to save item into database for [{}]",
                    kv(ITEM_IDENTIFICATION, id))
        }.toDomain()
    }

    override fun retrieveItemByName(name: String): Item {
        logger.info("Starting to retrieve $name item from database")
        return itemJpaRepository.findItemEntityByName(name).apply {
            logger.info("Done successfully to retrieve item {} from database with [{}]",
                    name, kv(ITEM_IDENTIFICATION, id)
            )
        }.toDomain()
    }
}