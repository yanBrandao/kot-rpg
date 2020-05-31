package br.com.woodriver.rpg.usecases.clazz

import br.com.woodriver.rpg.domain.Clazz
import br.com.woodriver.rpg.gateway.repository.ClazzRepository
import org.springframework.stereotype.Component

@Component
class CreateClazzUseCase(val clazzRepository: ClazzRepository) {

    fun execute(clazz: Clazz, isUpdate: Boolean = false) : Clazz {
        return clazzRepository.save(clazz)
    }
}