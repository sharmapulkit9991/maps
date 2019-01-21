package customMaps

import android.util.Log
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.ViewGroupManager
import com.facebook.react.uimanager.annotations.ReactProp


class ViewManager: ViewGroupManager<CustomMap>() {
    override fun createViewInstance(reactContext: ThemedReactContext?): CustomMap {
        return CustomMap(reactContext)
    }

    override fun getName(): String {
        return "Map"
    }

    @ReactProp(name = "region")
    fun region(map : CustomMap, coordinates: ReadableMap){
        if(coordinates.hasKey("latitude")&& coordinates.hasKey("longitude")){
            Log.e("Map","latitude:"+ coordinates.getDouble("latitude"))
            map.setRegion(coordinates.getDouble("latitude"), coordinates.getDouble("longitude"));
        }
    }

    @ReactProp(name = "minZoomLevel")
    fun setMinZoomLevel(map: CustomMap, level: Int){
        map.setMinZoomLevel(level.toFloat())
    }

    @ReactProp(name = "maxZoomLevel")
    fun maxZoomLevel(map: CustomMap, level:Int ){
        map.setMaxZoomLevel(level.toFloat())
    }

    @ReactProp(name = "initialZoomLevel")
    fun initialZoomLevel(map:CustomMap, level:Int){
        map.initialZoomLevel(level.toFloat())
    }

    @ReactProp(name="isInteractionEnable")
    fun setInteractionProp(map: CustomMap, isEnabled:Boolean){
        map.setInteractionProp(isEnabled)
    }




}