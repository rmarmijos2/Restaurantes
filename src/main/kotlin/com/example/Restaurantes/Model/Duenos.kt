package com.example.Restaurantes.Model

import javax.persistence.*

@Entity
@Table(name = "duenos")

class Duenos {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null

    var nombre: String? = null
    var edad: Int? = null
    var email: String? = null
}