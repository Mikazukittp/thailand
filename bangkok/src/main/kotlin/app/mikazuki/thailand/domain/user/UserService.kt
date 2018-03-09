package app.mikazuki.thailand.domain.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService @Autowired constructor(private val repository: UserRepository) : UserDetailsService {

    @Transactional
    override fun loadUserByUsername(username: String?): UserDetails {
        if (username?.isEmpty() != false) throw UsernameNotFoundException("")
        return repository.findByNameOrEmail(username, username) ?: throw UsernameNotFoundException("")
    }
}
