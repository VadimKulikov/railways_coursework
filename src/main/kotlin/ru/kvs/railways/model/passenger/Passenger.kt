package ru.kvs.railways.model.passenger

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "t_passenger")
data class Passenger(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(length = 50)
    val name: String = "",

    @Column(length = 50)
    val surname: String = "",

    @Column(length = 50)
    val patronymic: String? = null,

    @Column(name = "doc_number", unique = true, length = 11)
    val documentNumber: String = ""
)
