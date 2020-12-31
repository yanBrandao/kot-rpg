package br.com.woodriver.rpg.adapter.output.database

import br.com.woodriver.rpg.adapter.output.database.converter.toDomain
import br.com.woodriver.rpg.adapter.output.database.converter.toEntity
import br.com.woodriver.rpg.adapter.output.database.jpa.BagJpaRepository
import br.com.woodriver.rpg.adapter.output.logging.logger
import br.com.woodriver.rpg.application.domain.Bag
import br.com.woodriver.rpg.application.port.output.BagRepositoryPort
import net.logstash.logback.argument.StructuredArguments.kv
import org.springframework.stereotype.Repository

@Repository
class BagRepository(
        private val bagJpaRepository: BagJpaRepository,
): BagRepositoryPort {

    val logger = logger<BagRepository>()

    override fun save(bag: Bag): Bag {
        logger.info("Starting to save bag into database")
        return bagJpaRepository.save(bag.toEntity()).apply {
            logger.info("Done successfully to save bag into database for [{}]",
            kv("bagId", id))
        }.toDomain()
    }
}