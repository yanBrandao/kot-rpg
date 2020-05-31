package br.com.woodriver.rpg.gateway.http

import br.com.woodriver.rpg.domain.Skill
import br.com.woodriver.rpg.usecases.skill.CreateOrUpdateSkillUseCase
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("skills")
class SkillController(val createOrUpdateSkillUseCase: CreateOrUpdateSkillUseCase) {

    @PostMapping
    fun create(@RequestBody skill: Skill): Skill{
        return createOrUpdateSkillUseCase.execute(skill, false)
    }

    @PutMapping(path = ["/{key}"])
    fun update(@RequestBody skill: Skill, @PathVariable key: Long): Skill{
        return createOrUpdateSkillUseCase.execute(skill, true)
    }




}