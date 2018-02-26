package app.mikazuki.thailand.participants

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ParticipantRepository : JpaRepository<Participant, Long> {

    fun findAllByPartyId(partyId: Long): List<Participant>
}