package app.mikazuki.thailand.infra.datasource.participant

import app.mikazuki.thailand.application.participant.ParticipantRepository
import app.mikazuki.thailand.domain.Participant
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ParticipantJpaRepository : ParticipantRepository, JpaRepository<Participant, Long>