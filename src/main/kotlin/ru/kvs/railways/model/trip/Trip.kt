package ru.kvs.railways.model.trip

import ru.kvs.railways.model.route.Route
import ru.kvs.railways.model.train.Train
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "t_trip")
data class Trip(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @OneToOne
    val train: Train? = null,

    @ManyToOne
    @JoinColumn(name = "route_id")
    val route: Route? = null,

    val departureTime: LocalDateTime = LocalDateTime.now()
)
