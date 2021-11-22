package com.example.Restaurantes.Model

import javax.persistence.*

@Entity
@Table(name = "dueños")

class Dueños {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null

    var nombre: String? = null
    var edad: Int? = null
    var email: String? = null
}