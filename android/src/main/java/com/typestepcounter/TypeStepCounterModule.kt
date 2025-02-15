package com.typestepcounter

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.modules.core.DeviceEventManagerModule

class TypeStepCounterModule(reactContext: ReactApplicationContext) :
  ReactContextBaseJavaModule(reactContext), SensorEventListener {

  private var sensorManager: SensorManager? = null
  private var stepCounterSensor: Sensor? = null

  init {
    sensorManager = reactContext.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    stepCounterSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
  }

  override fun getName(): String {
    return "TypeStepCounter"
  }

  @ReactMethod
  fun startStepCounter() {
    stepCounterSensor?.let {
      sensorManager?.registerListener(this, it, SensorManager.SENSOR_DELAY_UI)
    }
  }

  @ReactMethod
  fun stopStepCounter() {
    sensorManager?.unregisterListener(this)
  }

  override fun onSensorChanged(event: SensorEvent?) {
    if (event?.sensor?.type == Sensor.TYPE_STEP_COUNTER) {
      val stepCount = event.values[0].toInt()
      sendEvent("StepCounterUpdate", stepCount)
    }
  }

  override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    // Not needed for step counter
  }

  private fun sendEvent(eventName: String, stepCount: Int) {
    reactApplicationContext
      .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter::class.java)
      .emit(eventName, stepCount)
  }
}
