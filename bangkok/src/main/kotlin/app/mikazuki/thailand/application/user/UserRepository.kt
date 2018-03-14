package app.mikazuki.thailand.application.user

import app.mikazuki.thailand.domain.User

interface UserRepository {

    fun findByNameOrEmail(username: String, email: String): User?

}