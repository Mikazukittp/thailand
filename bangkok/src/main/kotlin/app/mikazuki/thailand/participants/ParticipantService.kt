package app.mikazuki.thailand.participants

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ParticipantService @Autowired constructor(private val repository: ParticipantRepository) {

    @Transactional
    fun findAllByPartyId(partyId: Long) = repository.findAllByPartyId(partyId)

    @Transactional
    fun save(participant: Participant) = repository.save(participant)
}