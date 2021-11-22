package com.example.Restaurantes.Model

import javax.persistence.*

@Entity
@Table(name = "categoria")

class Categorias {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null

    var tipo: String? = null
}