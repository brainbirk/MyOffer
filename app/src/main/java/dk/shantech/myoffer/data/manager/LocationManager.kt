package dk.shantech.myoffer.data.manager

import javax.inject.Inject


class LocationManager @Inject constructor(){

    var currentLocation: CurrentLocation = CurrentLocation(
        latitude = "55.343561",
        longitude = "12.346233",
        radius = 20000
    )

}

data class CurrentLocation(val latitude: String, val longitude: String, val radius: Int)