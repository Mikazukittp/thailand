package app.mikazuki.thailand.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {

    fun findByNameOrEmail(username: String, email: String): User?

}