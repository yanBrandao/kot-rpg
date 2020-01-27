package br.com.woodriver.rpg.gateway.repository

import br.com.woodriver.rpg.domains.Ability
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface AbilityRepository: MongoRepository<Ability, String> {
}