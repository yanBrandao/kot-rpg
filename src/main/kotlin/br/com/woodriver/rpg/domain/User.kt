package br.com.woodriver.rpg.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "KOT_USER")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "USR_ID")
        val key: Long = 0,
        @Column(name = "USR_NAME")
        val name: String = "",
        @Column(name = "USR_EMAIL", unique = true)
        val email: String = "",
        @Column(name = "USR_PASSWORD")
        var password: String = ""
) {
}