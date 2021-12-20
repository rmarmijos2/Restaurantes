package com.example.Restaurantes.Repository

import com.example.Restaurantes.Model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRepository: JpaRepository<User, Long> {
    fun findById (id: Long?): User?
    @Query(value = "SELECT * FROM USERS u WHERE u.username = :username", nativeQuery = true)
    fun findByUsername (username: String?): User?
}