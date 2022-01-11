package ru.kvs.railways.model.route

data class CustomDuration(
    var hours: Long = 0,
    var minutes: Long = 0
) : Comparable<CustomDuration> {
    companion object {
        private const val MINUTES_IN_HOUR: Int = 60
    }

    override fun compareTo(other: CustomDuration): Int {
        val minutes = this.hours * MINUTES_IN_HOUR + this.minutes
        val otherMinutes = other.hours * MINUTES_IN_HOUR + other.minutes

        return minutes.compareTo(otherMinutes)
    }
}
