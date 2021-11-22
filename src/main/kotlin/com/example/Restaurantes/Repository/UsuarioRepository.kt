package com.example.Restaurantes.Repository

import com.example.Restaurantes.Model.Usuario
import org.springframework.data.jpa.repository.JpaRepository

interface UsuarioRepository: JpaRepository<Usuario, Long> {
    fun findById (id: Long?): Usuario?
}