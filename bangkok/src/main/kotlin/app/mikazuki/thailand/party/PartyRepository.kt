package app.mikazuki.thailand.party

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PartyRepository : JpaRepository<Party, Long> {

    fun findByHash(hash: String): Party?
    fun findAllByUserId(userId: Long): List<Party>
}