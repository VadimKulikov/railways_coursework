package ru.kvs.railways.module.route.repisotory

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import ru.kvs.railways.model.route.PopularRouteProjection
import ru.kvs.railways.model.route.Route

@Repository
interface RouteRepository : JpaRepository<Route, Long> {

    @Query(
        """
            SELECT * FROM get_popular_routes(?1, ?2, ?3);
        """,
        nativeQuery = true
    )
    fun getPopularRoutes(month: Int, year: Int, amount: Int): List<PopularRouteProjection>
}
