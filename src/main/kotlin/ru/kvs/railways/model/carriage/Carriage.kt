package ru.kvs.railways.model.carriage

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "t_carriage")
data class Carriage(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @OneToOne
    @JoinColumn(name = "type_id")
    val type: CarriageType? = null,

    val number: Int = 0
)
