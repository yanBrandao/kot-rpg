package br.com.woodriver.rpg.gateway.repository


import br.com.woodriver.rpg.domain.Player
import org.springframework.data.mongodb.repository.MongoRepository

interface PlayerRepository : MongoRepository<Player, String> {
    fun findTop10ByOrderByLevelDesc() : List<Player>
}