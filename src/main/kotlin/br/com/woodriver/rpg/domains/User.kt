package br.com.woodriver.rpg.domains

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "KOT_USER")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @JsonIgnore
        @Column("USR_ID")
        val key: Long = 0,
        @Column("USR_NAME")
        val name: String = "",
        @Column("USR_EMAIL")
        val email: String = "",
        @Column("USR_PASSWORD")
        var password: String = ""
) {
}