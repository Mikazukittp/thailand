package app.mikazuki.thailand.party

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PartyService @Autowired constructor(private val repository: PartyRepository) {

    @Transactional
    fun findByHash(hash: String): Party? {
        return repository.findByHash(hash)
    }

    @Transactional
    fun findAllByUserId(userId: Long): List<Party> {
        return repository.findAllByUserId(userId)
    }
}