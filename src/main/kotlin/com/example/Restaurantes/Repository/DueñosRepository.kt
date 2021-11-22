package com.example.Restaurantes.Repository

import com.example.Restaurantes.Model.Dueños
import org.springframework.data.jpa.repository.JpaRepository

interface DueñosRepository: JpaRepository<Dueños, Long> {
    fun findById (id: Long?): Dueños?
}