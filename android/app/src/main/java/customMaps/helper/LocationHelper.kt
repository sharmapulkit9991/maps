package customMaps.helper

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.*
import android.os.Looper
import com.google.android.gms.maps.model.LatLng


class LocationHelper(private val context: Context, private val callback: UserLocationCallback) : GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationCallback() {

    var client: FusedLocationProviderClient? = null;

    init {
        client = LocationServices.getFusedLocationProviderClient(context)!!
    }

    override fun onLocationResult(location: LocationResult?) {
        super.onLocationResult(location)
        val mCurrentLocation = location?.getLastLocation()
        client?.removeLocationUpdates(this)
        callback.userLatLng(LatLng(mCurrentLocation!!.latitude, mCurrentLocation.longitude))

    }

    fun getLatLng() {
        if (this.checkLocationPermission()) {
            this.connectGoogleApiClient()
        }
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onConnected(p0: Bundle?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onConnectionSuspended(p0: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun connectGoogleApiClient() {
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return
        }
        client?.requestLocationUpdates(LocationRequest()
                .setInterval(10000)
                .setFastestInterval(2000)
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY),
                this, Looper.myLooper())


    }

    private fun checkLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }
}