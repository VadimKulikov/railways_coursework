package ru.kvs.railways.module.carriage.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.kvs.railways.model.carriage.Carriage

@Repository
interface CarriageRepository: JpaRepository<Carriage, Int>
