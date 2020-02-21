package br.com.woodriver.rpg.usecases.clazz

import br.com.woodriver.rpg.configuration.BlizzardTokenConfiguration
import br.com.woodriver.rpg.domains.Clazz
import br.com.woodriver.rpg.domains.Item
import br.com.woodriver.rpg.domains.Skill
import br.com.woodriver.rpg.exceptions.IconEmptyException
import br.com.woodriver.rpg.gateway.client.BlizzardAPIClient
import br.com.woodriver.rpg.gateway.repository.ClazzRepository
import br.com.woodriver.rpg.gateway.repository.ItemRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component

@Component
class CreateClazzUseCase(val clazzRepository: ClazzRepository) {

    fun execute(clazz: Clazz, isUpdate: Boolean = false) : Clazz {
        return clazzRepository.save(clazz)
    }
}