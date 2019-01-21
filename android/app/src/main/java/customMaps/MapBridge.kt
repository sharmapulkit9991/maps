package customMaps

import android.view.View
import com.facebook.react.ReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.uimanager.ReactShadowNode
import com.facebook.react.uimanager.ViewManager
import java.util.*


class MapBridge:ReactPackage{
    override fun createNativeModules(reactContext: ReactApplicationContext?): MutableList<NativeModule> {
        return Collections.emptyList<NativeModule>()
    }

    override fun createViewManagers(reactContext: ReactApplicationContext?): List<ViewManager<*, *>> {
        return Arrays.asList(
                customMaps.ViewManager()
        )
    }

}