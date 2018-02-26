package app.mikazuki.thailand.infra.datasource.bounce

import app.mikazuki.thailand.application.bounce.BounceRepository
import app.mikazuki.thailand.domain.EmailBounce
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BounceJpaRepository : BounceRepository, JpaRepository<EmailBounce, Long>