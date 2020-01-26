package br.com.woodriver.rpg.gateway.repository


import br.com.woodriver.rpg.domains.Player
import org.springframework.data.jpa.repository.JpaRepository

interface PlayerRepository : JpaRepository<Player, Long> {
    fun findTop10ByOrderByLevelDesc() : List<Player>
}