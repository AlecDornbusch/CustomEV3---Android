package com.cgi.de.azubi.cgiazubislejoslibrary

import android.os.AsyncTask
import lejos.remote.ev3.RemoteRequestEV3
import lejos.robotics.RegulatedMotor

class CustomEV3(host: String) {
    private lateinit var ev3: RemoteRequestEV3
    private lateinit var map: HashMap<String, RegulatedMotor>

    init {
        DoAsync().execute("connect", host)
    }

    fun createMotor(port: String, size: Char) {
        map.set(port, ev3.createRegulatedMotor(port, size))
    }

    fun rotateForward(port1: String, port2: String?, port3: String?, port4: String?, speed: Int) {
        if (port4 != null) {
            map.get(port4)?.speed = speed
            map.get(port3)?.speed = speed
            map.get(port2)?.speed = speed
            map.get(port1)?.speed = speed
            DoAsync().execute("drive_forward", port1, port2, port3, port4)
        } else if (port3 != null) {
            map.get(port3)?.speed = speed
            map.get(port2)?.speed = speed
            map.get(port1)?.speed = speed
            DoAsync().execute("drive_forward", port1, port2, port3)
        } else if (port2 != null) {
            map.get(port2)?.speed = speed
            map.get(port1)?.speed = speed
            DoAsync().execute("drive_forward", port1, port2)
        } else {
            map.get(port1)?.speed = speed
            DoAsync().execute("drive_forward", port1)
        }
    }

    fun rotateBackward(port1: String, port2: String?, port3: String?, port4: String?, speed: Int) {
        if (port4 != null) {
            map.get(port4)?.speed = speed
            map.get(port3)?.speed = speed
            map.get(port2)?.speed = speed
            map.get(port1)?.speed = speed
            DoAsync().execute("drive_backward", port1, port2, port3, port4)
        } else if (port3 != null) {
            map.get(port3)?.speed = speed
            map.get(port2)?.speed = speed
            map.get(port1)?.speed = speed
            DoAsync().execute("drive_backward", port1, port2, port3)
        } else if (port2 != null) {
            map.get(port2)?.speed = speed
            map.get(port1)?.speed = speed
            DoAsync().execute("drive_backward", port1, port2)
        } else {
            map.get(port1)?.speed = speed
            DoAsync().execute("drive_backward", port1)
        }
    }

    fun stopMotor(port1: String, port2: String?, port3: String?, port4: String?) {
        if (port4 != null) {
            map.get(port4)?.stop()
            map.get(port3)?.stop()
            map.get(port2)?.stop()
            map.get(port1)?.stop()
        } else if (port3 != null) {
            map.get(port3)?.stop()
            map.get(port2)?.stop()
            map.get(port1)?.stop()
        } else if (port2 != null) {
            map.get(port2)?.stop()
            map.get(port1)?.stop()
        } else {
            map.get(port1)?.stop()
        }
    }

    fun getSuperclassEv3(): RemoteRequestEV3 {
        return ev3
    }

    inner class DoAsync : AsyncTask<String?, Void, Boolean>() {
        override fun doInBackground(vararg params: String?): Boolean {
            when (params[0]) {
                "connect" -> {
                    ev3 = RemoteRequestEV3(params[1])
                    ev3.audio.systemSound(2)
                }
                "disconnect" -> ev3.disConnect()

                "drive_forward" -> {
                    map.get(params[1])?.forward()
                    if (params[2] != null)
                        map.get(params[2])?.forward()
                    if (params[3] != null)
                        map.get(params[3])?.forward()
                    if (params[4] != null)
                        map.get(params[4])?.forward()
                }
            }
            return true
        }
    }
}




