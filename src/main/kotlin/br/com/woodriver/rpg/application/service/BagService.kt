package br.com.woodriver.rpg.application.service

import br.com.woodriver.rpg.adapter.output.logging.logger
import br.com.woodriver.rpg.application.domain.Bag
import br.com.woodriver.rpg.application.domain.Bag.Companion.BAG_IDENTIFICATION
import br.com.woodriver.rpg.application.domain.Item
import br.com.woodriver.rpg.application.port.input.AddBagItemUseCase
import br.com.woodriver.rpg.application.port.input.CreateBagUseCase
import br.com.woodriver.rpg.application.port.output.BagRepositoryPort
import br.com.woodriver.rpg.application.port.output.ItemRepositoryPort
import br.com.woodriver.rpg.application.port.output.ItemsInBagRepositoryPort
import net.logstash.logback.argument.StructuredArguments.kv
import org.springframework.stereotype.Service

@Service
class BagService(
        private val bagRepository: BagRepositoryPort,
        private val itemsInBagRepository: ItemsInBagRepositoryPort
): AddBagItemUseCase, CreateBagUseCase {

    val logger = logger<BagService>()

    override fun execute(item: Item, id: Long): Bag {
        logger.info("Starting to add item [{}] to [{}]",
                item.name, kv(BAG_IDENTIFICATION, id)
        )
        val bag = itemsInBagRepository.retrieveBag(id)
        when(val itemIndex = bag.haveItem(item)) {
            -1 -> bag.items.add(item)
            else -> bag.items[itemIndex].quantity += item.quantity
        }
        return itemsInBagRepository.save(bag, id)
    }

    override fun execute(bag: Bag): Bag {
        return bag.save(bagRepository)
    }
}