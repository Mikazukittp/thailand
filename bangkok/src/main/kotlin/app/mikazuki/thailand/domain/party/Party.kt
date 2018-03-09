package app.mikazuki.thailand.domain.party

import java.sql.Date
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "parties")
class Party(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = -1,
        val userId: Long,
        val placeId: Long,
        val name: String,
        val date: Date,
        val mPrice: Int,
        val fPrice: Int,
        val hash: String)
