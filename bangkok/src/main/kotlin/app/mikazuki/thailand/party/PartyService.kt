package app.mikazuki.thailand.party

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PartyService @Autowired constructor(private val repository: PartyRepository) {

    @Transactional
    fun findByHash(hash: String) = repository.findByHash(hash)

    @Transactional
    fun findAllByUserId(userId: Long) = repository.findAllByUserId(userId)
}