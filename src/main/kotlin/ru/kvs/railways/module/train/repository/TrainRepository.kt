package ru.kvs.railways.module.train.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.kvs.railways.model.train.Train

@Repository
interface TrainRepository: JpaRepository<Train, Int>
