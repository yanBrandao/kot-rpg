package br.com.woodriver.rpg

import br.com.woodriver.blizzard.main
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class 		RpgApplicationTests {

	@Test
	fun contextLoads() {
	}

	@Test
	fun mainTest(){
		main(arrayOf())
	}

}
