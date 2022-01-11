package ru.kvs.railways.model.route

import com.vladmihalcea.hibernate.type.json.JsonType
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "t_route")
@TypeDef(name = "jsonb", typeClass = JsonType::class)
data class Route(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long = 0,

    @Type(type = "jsonb")
    @Column(name = "stations", columnDefinition = "jsonb")
    val stations: List<RouteStation> = arrayListOf(),

    val name: String = ""
)
