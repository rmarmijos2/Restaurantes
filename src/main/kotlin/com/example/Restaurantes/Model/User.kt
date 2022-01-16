package com.example.Restaurantes.Model

import javax.persistence.*

@Entity
@Table(name = "user")

class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null

    var username: String? = null
    var password: String? = null
    var cedula: String? = null
}