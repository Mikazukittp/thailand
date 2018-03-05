package app.mikazuki.thailand.place

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "places")
data class Place(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = -1,
        val name: String,
        val address: String,
        val phone: String,
        val url: String)
