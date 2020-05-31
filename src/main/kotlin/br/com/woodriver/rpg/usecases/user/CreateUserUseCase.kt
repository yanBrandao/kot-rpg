package br.com.woodriver.rpg.usecases.user

import br.com.woodriver.rpg.domain.User
import br.com.woodriver.rpg.gateway.repository.UserRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class CreateUserUseCase(val userRepository: UserRepository) {

    fun execute(user: User): User {
        user.password = Base64.getEncoder().encodeToString(user.password.toByteArray(Charsets.UTF_8));
        return userRepository.save(user)
    }
}