package br.com.woodriver.rpg.domains

import com.sun.istack.NotNull
import java.io.Serializable
import javax.persistence.*
import javax.validation.constraints.Email

@Entity
@Table(name = "KOR_PLAYER")
class Player(@Id
             @GeneratedValue
             @Column(name = "PLR_ID", unique = true, nullable = false)
             var key: Long = 1L,
             @NotNull
             @Column(name = "PLR_NAME")
             var name: String = "No Name",
             @NotNull
             @Column(name = "PLR_EMAIL")
             @Email
             var email: String = "a@a.com",
             @Column(name = "PLR_LEVEL")
             var level: Int = 1,
             @ManyToMany(cascade = [CascadeType.ALL])
             @JoinTable(name = "KOR_EFFECT_PLAYER",
                     joinColumns = [JoinColumn(name = "EFP_PLR_ID", referencedColumnName = "PLR_ID")],
                     inverseJoinColumns = [JoinColumn(name = "EFP_EFC_ID", referencedColumnName = "EFC_ID")])
             val effects: List<Effect> = listOf(),
             @OneToMany(
                     mappedBy = "equipmentId.eqpPlrId",
                     cascade = [CascadeType.ALL],
                     orphanRemoval = true
             )
             val equipment: List<Equipment> = listOf()) : Serializable {


}