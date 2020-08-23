package br.com.woodriver.blizzard.usecases.token

import br.com.woodriver.blizzard.domains.OAuthBlizzard
import org.springframework.stereotype.Component
import java.util.*

@Component
class GetTokenUseCase() {
    fun execute(): OAuthBlizzard {
        return OAuthBlizzard(UUID.randomUUID().toString(), "Bearer", 9999999)

    }
}