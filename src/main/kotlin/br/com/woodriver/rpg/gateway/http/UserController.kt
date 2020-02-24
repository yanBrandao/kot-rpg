package br.com.woodriver.rpg.gateway.http

import br.com.woodriver.rpg.domains.User
import br.com.woodriver.rpg.usecases.user.CreateUserUseCase
import br.com.woodriver.rpg.usecases.user.LoginUserUseCase
import io.swagger.annotations.Api
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/users")
class UserController(val createUserUseCase: CreateUserUseCase,
                     val loginUserUseCase: LoginUserUseCase) {

    @PostMapping
    fun create(@RequestBody user: User): ResponseEntity<User>{
        return ResponseEntity.created(URI("")).body(createUserUseCase.execute(user))
    }

    @ApiResponses(value = [
        ApiResponse(code = 400, message = "Bad Request that means you wrong password or username.")
    ])
    @PostMapping(value = ["/login"])
    fun login(@RequestHeader(name = "x-kot-email") email: String,
              @RequestHeader(name = "x-kot-password") password: String): ResponseEntity<String>{
        return ResponseEntity.ok(loginUserUseCase.execute(email, password))
    }
}