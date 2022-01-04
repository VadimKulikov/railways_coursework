package ru.kvs.railways.model.train

import ru.kvs.railways.model.composition.Composition
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "t_train")
data class Train(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    val number: Int = 0,

    @ManyToOne
    @JoinColumn(name = "comp_id")
    val composition: Composition = Composition()
)
