package br.com.woodriver.rpg.usecases.user

import br.com.woodriver.rpg.domain.User
import br.com.woodriver.rpg.gateway.repository.UserRepository
import org.springframework.stereotype.Component

@Component
class GetAllUserUseCase(val userRepository: UserRepository) {

    fun execute(): List<User>{
        return userRepository.findAll()
    }
}