package br.com.woodriver.rpg.gateway.repository


import br.com.woodriver.rpg.domains.Player
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PlayerRepository : JpaRepository<Player, Long> {
    fun findTop10ByOrderByExpDesc() : List<Player>
    fun findFirstByKey(key: Long): Player?
}