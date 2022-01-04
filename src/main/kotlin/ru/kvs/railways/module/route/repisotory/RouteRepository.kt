package ru.kvs.railways.module.route.repisotory

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.kvs.railways.model.route.Route

@Repository
interface RouteRepository : JpaRepository<Route, Long>
