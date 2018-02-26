package app.mikazuki.thailand.user

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity(name = "users")
data class User(
        @Id @GeneratedValue
        val id: Long = -1,
        val name: String,
        val email: String,
        @Column(name = "password")
        val hashedPassword: String
) : UserDetails {
    override fun getAuthorities() = mutableListOf<GrantedAuthority>()

    override fun getUsername() = name

    override fun getPassword() = hashedPassword

    override fun isEnabled() = true

    override fun isCredentialsNonExpired() = true

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true
}