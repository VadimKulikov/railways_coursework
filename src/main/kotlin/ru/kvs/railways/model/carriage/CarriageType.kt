package ru.kvs.railways.model.carriage

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "t_carriage_type")
data class CarriageType(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    val name: String = ""
)
