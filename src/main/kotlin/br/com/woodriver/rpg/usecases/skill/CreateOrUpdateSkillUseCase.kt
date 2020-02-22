package br.com.woodriver.rpg.usecases.skill

import br.com.woodriver.rpg.domains.Skill
import br.com.woodriver.rpg.gateway.repository.SkillRepository
import org.springframework.stereotype.Component

@Component
class CreateOrUpdateSkillUseCase(val skillRepository: SkillRepository) {

    fun execute(skill: Skill, isUpdate: Boolean = false) : Skill {
        return skillRepository.save(skill)
    }
}