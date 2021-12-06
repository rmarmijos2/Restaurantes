package com.example.Restaurantes.Model

import javax.persistence.*

@Entity
@Table(name = "restaurantes")

class Restaurantes {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null

    var nombre: String? = null
    var calificacion: Int? = null
    var direccion: String? = null
    @Column(name="iddueno")
    var idDueno: Long? = null

    @Column(name="idcategoria")
    var idCategoria: Long? = null
}