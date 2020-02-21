package br.com.woodriver.rpg.domains

import javax.persistence.*

@Entity
@Table(name = "KOR_CLASS")
class Clazz(@Id
        @GeneratedValue
        @Column(name = "CLS_ID", unique = true, nullable = false)
        var key: Long = 1L,
        @Column(name = "CLS_NAME")
        var name: String = "No Name"
) {
}