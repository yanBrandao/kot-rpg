package br.com.woodriver.rpg.adapter.output.database.jpa

import br.com.woodriver.rpg.adapter.output.database.entity.BagEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BagJpaRepository: JpaRepository<BagEntity, Long>