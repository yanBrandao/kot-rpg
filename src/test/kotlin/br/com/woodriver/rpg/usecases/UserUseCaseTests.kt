package br.com.woodriver.rpg.usecases

import br.com.woodriver.rpg.domains.User
import br.com.woodriver.rpg.exceptions.PlayerAlreadyCreatedException
import br.com.woodriver.rpg.exceptions.UsernameOrPasswordException
import br.com.woodriver.rpg.gateway.repository.UserRepository
import br.com.woodriver.rpg.usecases.user.CreateUserUseCase
import br.com.woodriver.rpg.usecases.user.LoginUserUseCase
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.dao.EmptyResultDataAccessException

class UserUseCaseTests {

    private lateinit var loginUserUseCase: LoginUserUseCase
    private lateinit var createUserUseCase: CreateUserUseCase

    @Mock
    lateinit var userRepository: UserRepository

    @BeforeEach
    fun setup(){
        userRepository = Mockito.mock(UserRepository::class.java)
        val user = userSample()
        `when`(userRepository.findFirstByEmail(Mockito.anyString())).thenReturn(user)
        loginUserUseCase = LoginUserUseCase(userRepository)
        createUserUseCase = CreateUserUseCase(userRepository)
    }

    @Test
    fun `As user, I want to login with my credentials`(){
        val email = "yan@gmail.com"
        val password = "yan123"
        val response = loginUserUseCase.execute(email, password)

        Assertions.assertEquals("1", response)
    }

    @Test
    fun `As user, I want to be notified when I wrong my password`(){
        val email = "yan@gmail.com"
        val password = "yan1234"

        assertThrows<UsernameOrPasswordException> { loginUserUseCase.execute(email, password) }
    }

    @Test
    fun `As user, I want to be notified when I use an email that doesn't exists`(){
        `when`(userRepository.findFirstByEmail(Mockito.anyString())).thenThrow(EmptyResultDataAccessException::class.java)
        val email = "yan@gmail.com"
        val password = "yan1234"

        assertThrows<UsernameOrPasswordException> { loginUserUseCase.execute(email, password) }
    }

    @Test
    fun `As an new user, I want to create an User`(){
        var user = User(1L, "Yan", "yan@gmail.com", "yan123")
        var userResponse = userSample()
        `when`<User>(userRepository.save(Mockito.any())).thenReturn(userResponse)

        val response = createUserUseCase.execute(user)

        Assertions.assertEquals(userResponse.password, response.password)
        Assertions.assertEquals(userResponse.name, response.name)
        Assertions.assertEquals(userResponse.key, response.key)
    }

    private fun userSample(): User = User(1L, "Yan", "yan@gmail.com", "eWFuMTIz")
}