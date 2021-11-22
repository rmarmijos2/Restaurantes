package com.example.Restaurantes.Repository

import com.example.Restaurantes.Model.Categorias
import org.springframework.data.jpa.repository.JpaRepository

interface CategoriasRepository: JpaRepository<Categorias, Long> {
    fun findById (id: Long?): Categorias?
}