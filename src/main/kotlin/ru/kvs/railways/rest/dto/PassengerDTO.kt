package ru.kvs.railways.rest.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "DTO пассажира")
data class PassengerDTO(
    @Schema(description = "Имя")
    var name: String = "",

    @Schema(description = "Фамилия")
    var surname: String = "",

    @Schema(description = "Отчество (не обязательный параметр)")
    var patronymic: String? = null,

    @Schema(description = "Номер паспорта")
    var documentNumber: String = ""
)
