package app.mikazuki.thailand.place

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PlaceService @Autowired constructor(private val repository: PlaceRepository) {

    fun findById(id: Long) = repository.findById(id)
}