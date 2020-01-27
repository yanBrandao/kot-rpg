package br.com.woodriver.rpg.gateway.resolvers

import br.com.woodriver.rpg.domains.Ability
import br.com.woodriver.rpg.domains.types.AbilityType
import br.com.woodriver.rpg.gateway.repository.AbilityRepository
import com.coxautodev.graphql.tools.GraphQLQueryResolver

class AbilityQueryResolver(private val abilityRepository: AbilityRepository): GraphQLQueryResolver {
    fun abilities(): List<Ability>{
        return abilityRepository.findAll()
    }

    fun newAbility(name: String, typeAbility: String): Ability{
        var typeAbilityValue = AbilityType.DOT
        if(typeAbility.equals(AbilityType.MELEE.toString()))
            typeAbilityValue = AbilityType.MELEE
        else if (typeAbility.equals(AbilityType.PROJECTILE.toString()))
            typeAbilityValue = AbilityType.PROJECTILE
        val ability = Ability(name, typeAbilityValue)
        abilityRepository.save(ability)

        return ability
    }
}