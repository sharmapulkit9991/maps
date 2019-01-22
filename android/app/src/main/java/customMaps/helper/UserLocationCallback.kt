package customMaps.helper

import com.google.android.gms.maps.model.LatLng

interface UserLocationCallback {
    fun userLatLng(coordinates:LatLng)
}