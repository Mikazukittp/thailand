package app.mikazuki.thailand.application.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService @Autowired constructor(private val repository: UserRepository) {

    fun findByNameOrEmail(username: String, email: String) = repository.findByNameOrEmail(username, email)

}
