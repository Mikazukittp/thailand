package app.mikazuki.thailand.application.participant

import app.mikazuki.thailand.domain.Participant

interface ParticipantRepository {

    fun findAllByPartyId(partyId: Long): List<Participant>

    fun save(participant: Participant): Participant

}