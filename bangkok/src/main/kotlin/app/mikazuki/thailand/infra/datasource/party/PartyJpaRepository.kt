package app.mikazuki.thailand.infra.datasource.party

import app.mikazuki.thailand.application.party.PartyRepository
import app.mikazuki.thailand.domain.Party
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PartyJpaRepository : PartyRepository, JpaRepository<Party, Long>