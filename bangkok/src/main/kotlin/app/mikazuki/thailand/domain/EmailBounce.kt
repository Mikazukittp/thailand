package app.mikazuki.thailand.domain

import java.util.*
import javax.persistence.*

@Entity(name = "bounces")
data class EmailBounce(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = -1,
        val email: String,
        val bounceType: String,
        val bounceSubType: String,
        val createdAt: Date)

