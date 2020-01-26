package br.com.woodriver.rpg.domains

import com.sun.istack.NotNull
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "KOR_PLAYER")
class Player(@Id
             @GeneratedValue
             @Column(name = "PLR_ID", unique = true, nullable = false)
                  var key: Long = 1L,
                  @NotNull
                  @Column(name = "PLR_NAME")
                  var name: String = "No Name",
             @Column(name = "PLR_LEVEL")
                  var level: Int = 1,
                  @ManyToMany(cascade = [CascadeType.ALL])
                  @JoinTable(name = "KOR_BAG",
                          joinColumns = [JoinColumn(name = "BAG_PLR_ID", referencedColumnName = "PLR_ID")],
                          inverseJoinColumns = [JoinColumn(name = "BAG_ITM_ID", referencedColumnName = "ITM_ID")])
                  var bag: List<Item> = mutableListOf(),
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
                  val equipment: List<Equipment> = listOf()) : Serializable{



}