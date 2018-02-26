package app.mikazuki.thailand.application.bounce

import app.mikazuki.thailand.domain.EmailBounce
import org.springframework.stereotype.Repository

@Repository
interface BounceRepository {

    fun deleteAllByEmail(email: String)

    fun save(emailBounce: EmailBounce): EmailBounce

}