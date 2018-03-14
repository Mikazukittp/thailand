package app.mikazuki.thailand.application.party

import app.mikazuki.thailand.domain.Party

interface PartyRepository {

    fun findByHash(hash: String): Party?

    fun findAllByUserId(userId: Long): List<Party>

}