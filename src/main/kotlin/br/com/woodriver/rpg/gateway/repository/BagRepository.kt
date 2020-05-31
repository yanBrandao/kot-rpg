package br.com.woodriver.rpg.gateway.repository

import br.com.woodriver.rpg.domain.Bag
import br.com.woodriver.rpg.domain.utils.compositekeys.BagId
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BagRepository: JpaRepository<Bag, BagId> {

    fun findAllByBagIdBagCrtIdKey(pageable: Pageable, key: Long): Page<Bag>

    fun findFirstByBagIdBagItmIdKeyAndBagIdBagCrtIdKey(itemKey: Long,
                                                       characterKey: Long): Bag

}