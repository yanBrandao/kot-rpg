package br.com.woodriver.rpg.gateway.http

import br.com.woodriver.rpg.domain.Clazz
import br.com.woodriver.rpg.usecases.clazz.CreateClazzUseCase
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("clazzes")
class ClazzController(val createClazzUseCase: CreateClazzUseCase) {

    @PostMapping
    fun create(@RequestBody clazz: Clazz): Clazz{
        return createClazzUseCase.execute(clazz, false)
    }

    @PutMapping
    fun update(@RequestBody clazz: Clazz): Clazz{
        return createClazzUseCase.execute(clazz, true)
    }




}