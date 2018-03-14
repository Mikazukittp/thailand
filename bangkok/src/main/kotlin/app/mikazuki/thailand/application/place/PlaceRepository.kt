package app.mikazuki.thailand.application.place

import app.mikazuki.thailand.domain.Place
import java.util.*

interface PlaceRepository {

    fun findById(id: Long): Optional<Place>

}