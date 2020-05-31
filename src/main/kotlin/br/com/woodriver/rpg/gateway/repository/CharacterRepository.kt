package br.com.woodriver.rpg.gateway.repository


import br.com.woodriver.rpg.domain.player.Character
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CharacterRepository : JpaRepository<Character, Long> {
    fun findTop10ByOrderByExpDesc() : List<Character>
    fun findFirstByKey(key: Long): Character?
    fun findCharacterByUserId(userId: Long): List<Character>
}