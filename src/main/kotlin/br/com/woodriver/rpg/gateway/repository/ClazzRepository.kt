package br.com.woodriver.rpg.gateway.repository


import br.com.woodriver.rpg.domain.Clazz
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ClazzRepository : JpaRepository<Clazz, Long> {
    fun findFirstByKey(key: Long): Clazz?
}