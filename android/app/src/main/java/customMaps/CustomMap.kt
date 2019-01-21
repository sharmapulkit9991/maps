package customMaps

import android.os.Handler
import android.util.Log
import com.facebook.react.bridge.LifecycleEventListener
import com.facebook.react.bridge.WritableNativeMap
import com.facebook.react.uimanager.ThemedReactContext
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng

class CustomMap(private val context: ThemedReactContext?) : MapView(context), OnMapReadyCallback, LifecycleEventListener, GoogleMap.OnMapClickListener {

    private var gMap: GoogleMap? = null
    private var isMapReady: Boolean = false
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0
    private var zoomLevel: Float = 0.0f;
    private var maxZoomPreference: Float = 0.0f
    private var minZoomPreference: Float = 0.0f
    private var isInteractionEnabled:Boolean=true
    private val TAG :String="CustomMap"


    init {
        this.getMapAsync(this)
        context?.addLifecycleEventListener(this)
        this.onCreate(null)
    }

    override fun onMapClick(p0: LatLng?) {
        Log.e("Map", "clicked")
    }

    override fun onHostResume() {
        this.onResume()
    }

    override fun onHostPause() {
        this.onPause()
    }

    override fun onHostDestroy() {
        this.onDestroy()
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        isMapReady = true
        gMap = googleMap
        initializeMap()
        this.mapInteractionListeners()
    }


    fun setRegion(latitude: Double, longitude: Double) {
        if (isMapReady) {
            moveCamera(LatLng(latitude, longitude))
        } else {
            this.latitude = latitude
            this.longitude = longitude
        }

    }

    fun setMinZoomLevel(level: Float) {
        if (this.isMapReady) {
            gMap?.setMinZoomPreference(level);
        } else {
            this.minZoomPreference = level
        }
    }

    fun setMaxZoomLevel(level: Float) {
        if (this.isMapReady) {
            gMap?.setMaxZoomPreference(level);
        } else {
            this.maxZoomPreference = level;
        }
    }

    fun initialZoomLevel(level: Float) {
        this.zoomLevel = level
    }

    fun setInteractionProp(isEnabled:Boolean){
        if (this.isMapReady) {
            gMap?.uiSettings?.isScrollGesturesEnabled = isEnabled
        } else {
            this.isInteractionEnabled = isEnabled
        }
    }

    private fun mapInteractionListeners(){
        gMap?.setOnMapClickListener(this)
        gMap?.setOnCameraMoveStartedListener({ reason ->
            when(reason){
                GoogleMap.OnCameraMoveStartedListener.REASON_GESTURE ->{
                    Log.d(TAG, "The user gestured on the map. ")

                }
                GoogleMap.OnCameraMoveStartedListener.REASON_API_ANIMATION->{
                    Log.d(TAG, "The user tapped something on the map.")

                }
                GoogleMap.OnCameraMoveStartedListener.REASON_DEVELOPER_ANIMATION->{
                    Log.d(TAG, "The app moved the camera.")
                }
                else ->{
                    Log.d(TAG," some other reason")
                }


            }
        })

    }

    private fun initializeMap() {
        if (latitude > 0 && longitude > 0) {
            moveCamera(LatLng(latitude, longitude))
        }
        if (minZoomPreference > 0.0f) {
            gMap?.setMinZoomPreference(minZoomPreference)
            gMap?.setMaxZoomPreference(maxZoomPreference)
        }

        gMap?.uiSettings?.isScrollGesturesEnabled = isEnabled
    }

    private fun addMarkerToMap() {}

    private fun moveCamera(latLng: LatLng) {
        gMap?.clear()
        val cameraPosition: CameraPosition = CameraPosition.Builder().target(latLng).zoom(this.zoomLevel).build()
        gMap?.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 2000, null)
    }




}