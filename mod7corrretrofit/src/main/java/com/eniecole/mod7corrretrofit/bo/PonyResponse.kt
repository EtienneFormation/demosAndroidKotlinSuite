package com.eniecole.mod7corrretrofit.bo


data class PonyResponse(
    val data: DataBikes
)
data class DataBikes(
    var bikes : List<Bike>
)

data class Bike(
    val bike_id: String,
    val current_fuel_percent : Float?,
    val current_range_meters: Int?,
    val is_disabled : Boolean,
    val is_reserved: Boolean,
    val lat: Float,
    val lon: Float,
    val vehicle_type_id: String
)
