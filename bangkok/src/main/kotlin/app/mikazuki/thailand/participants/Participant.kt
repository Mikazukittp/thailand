package app.mikazuki.thailand.participants

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity(name = "participants")
data class Participant(
        @Id @GeneratedValue
        val id: Long = -1,
        val partyId: Long,
        val firstName: String,
        val lastName: String,
        val email: String,
        val gender: Int,
        val side: Boolean,
        val attendance: Boolean,
        val postalCode: String?,
        val message: String?,
        val address: String?,
        val phone: String?)

