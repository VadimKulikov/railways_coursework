package ru.kvs.railways.model.composition

import ru.kvs.railways.model.carriage.Carriage
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.Table

@Entity
@Table(name = "t_composition")
data class Composition(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @ManyToMany
    @JoinTable(
        name = "t_composition_carriage",
        joinColumns = [JoinColumn(name = "comp_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "carriage_id", referencedColumnName = "id")]
    )
    val carriages: List<Carriage> = arrayListOf(),

)
