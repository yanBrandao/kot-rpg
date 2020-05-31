package br.com.woodriver.rpg.domain

import javax.persistence.*

@Entity
@Table(name = "KOR_CLASS")
class Clazz(@Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "CLS_ID", unique = true, nullable = false)
        var key: Long = 1L,
        @Column(name = "CLS_NAME")
        var name: String = "No Name"
) {
}