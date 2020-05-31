package br.com.woodriver.rpg.gateway.repository


import br.com.woodriver.rpg.domain.Skill
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SkillRepository : JpaRepository<Skill, Long> {
    fun findFirstByKey(key: Long): Skill?
}