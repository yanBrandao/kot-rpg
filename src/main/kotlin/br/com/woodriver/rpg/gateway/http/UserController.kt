package br.com.woodriver.rpg.gateway.http

import br.com.woodriver.rpg.domain.User
import br.com.woodriver.rpg.usecases.user.CreateUserUseCase
import br.com.woodriver.rpg.usecases.user.GetAllUserUseCase
import br.com.woodriver.rpg.usecases.user.LoginUserUseCase
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/users")
class UserController(val createUserUseCase: CreateUserUseCase,
                     val loginUserUseCase: LoginUserUseCase,
                    val getAllUserUseCase: GetAllUserUseCase) {

    @PostMapping
    fun create(@RequestBody user: User): ResponseEntity<User>{
        return ResponseEntity.created(URI("")).body(createUserUseCase.execute(user))
    }

    @GetMapping
    fun allUser(): ResponseEntity<List<User>>{
        return ResponseEntity.created(URI("")).body(getAllUserUseCase.execute())
    }

    @ApiResponses(value = [
        ApiResponse(code = 400, message = "Bad Request that means you wrong password or username.")
    ])
    @PostMapping(value = ["/login"])
    fun login(@RequestHeader(name = "x-kot-email") email: String,
              @RequestHeader(name = "x-kot-password") password: String): ResponseEntity<User>{
        return ResponseEntity.ok(loginUserUseCase.execute(email, password))
    }
}