package com.example.Restaurantes.Model

import javax.persistence.*

@Entity
@Table(name = "usuario")

class Usuario {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null

    var nombre: String? = null
    var edad: Int? = null
    var email: String? = null
    var telefono: Int? = null
    var genero: String? = null
    var direccion: String? = null

}
