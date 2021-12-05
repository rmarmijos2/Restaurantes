package com.example.Restaurantes.Repository

import com.example.Restaurantes.Model.Duenos
import org.springframework.data.jpa.repository.JpaRepository

interface DueñosRepository: JpaRepository<Duenos, Long> {
    fun findById (id: Long?): Duenos?
}