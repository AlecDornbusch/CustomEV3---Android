package com.cgi.de.azubi.lejoslibraray

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.cgi.de.azubi.cgiazubislejoslibrary.CustomEV3
import kotlinx.android.synthetic.main.main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    lateinit var customEV3 : CustomEV3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
    }

    override fun onCreateView(parent: View?, name: String?, context: Context?, attrs: AttributeSet?): View {
        buttonConnect.setOnClickListener {
            try {
                customEV3  = CustomEV3( ipTextLayout.text.toString())
            }catch (e : Exception){
                ipTextLayout.setBackgroundColor(Color.RED)
            }
        }


        return super.onCreateView(parent, name, context, attrs)
    }

    fun main() {
        var ev3 = CustomEV3("172.20.10.14")
        ev3.createMotor("A", 'M')
        ev3.createMotor("B", 'L')
        ev3.createMotor("C", 'L')
    }
}
