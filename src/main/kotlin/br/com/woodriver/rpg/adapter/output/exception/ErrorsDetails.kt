package br.com.woodriver.rpg.adapter.output.exception

import org.apache.logging.log4j.util.Strings
import java.time.LocalDateTime

data class ErrorsDetails(
        val time: LocalDateTime = LocalDateTime.now(),
        val message: String = Strings.EMPTY,
        val details: String = Strings.EMPTY
)
