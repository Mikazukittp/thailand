package app.mikazuki.thailand.infra.datasource.place

import app.mikazuki.thailand.application.place.PlaceRepository
import app.mikazuki.thailand.domain.Place
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PlaceJpaRepository : PlaceRepository, JpaRepository<Place, Long>