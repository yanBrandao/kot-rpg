package br.com.woodriver.rpg.usecases.user

import br.com.woodriver.rpg.domain.User
import br.com.woodriver.rpg.exceptions.UsernameOrPasswordException
import br.com.woodriver.rpg.gateway.repository.UserRepository
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Component
import java.util.*


@Component
class LoginUserUseCase(val userRepository: UserRepository) {

    fun execute(email: String, password: String): User{
        try {
            val user = userRepository.findFirstByEmail(email)
            val decodedBytes: ByteArray = Base64.getDecoder().decode(user.password)
            val decodedString = String(decodedBytes)
            if (password == decodedString)
                return user
            else
                throw UsernameOrPasswordException("Password Invalid")
        } catch (ex: EmptyResultDataAccessException){
            throw UsernameOrPasswordException("Username Invalid")
        }
    }
}