package app.mikazuki.thailand.infra.datasource.user

import app.mikazuki.thailand.application.user.UserRepository
import app.mikazuki.thailand.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserJpaRepository : UserRepository, JpaRepository<User, Long>