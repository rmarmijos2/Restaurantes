package com.example.Restaurantes.Repository

import com.example.Restaurantes.Model.Restaurantes
import org.springframework.data.jpa.repository.JpaRepository

interface RestaurantesRepository: JpaRepository<Restaurantes, Long> {
    fun findById (id: Long?): Restaurantes?
}