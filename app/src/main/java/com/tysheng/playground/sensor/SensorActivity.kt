package com.tysheng.playground.sensor

import android.app.Activity
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import timber.log.Timber

/**
 * Created by tysheng
 * Date: 4/9/18 7:37 PM.
 * Email: tyshengsx@gmail.com
 */
class SensorActivity : Activity(), SensorEventListener {

    private lateinit var mSensorManager: SensorManager
    private var mLight: Sensor? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Get an instance of the sensor service, and use that to get an instance of
        // a particular sensor.
        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        // Do something here if sensor accuracy changes.
        Timber.d("onAccuracyChanged $accuracy")
    }

    override fun onSensorChanged(event: SensorEvent) {
        val millibarsOfPressure = event.values[0]
        Timber.d("onSensorChanged values ${event.values.joinToString()}" +
                " accuracy ${event.accuracy}" +
                " timestamp ${event.timestamp}")
        // Do something with this sensor data.
    }

    override fun onResume() {
        // Register a listener for the sensor.
        super.onResume()
        mSensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        // Be sure to unregister the sensor when the activity pauses.
        super.onPause()
        mSensorManager.unregisterListener(this)
    }
}