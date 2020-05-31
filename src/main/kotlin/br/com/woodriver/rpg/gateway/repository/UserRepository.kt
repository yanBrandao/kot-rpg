package br.com.woodriver.rpg.gateway.repository

import br.com.woodriver.rpg.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findFirstByEmail(email: String): User
}