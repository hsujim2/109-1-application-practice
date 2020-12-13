package com.example.kotlin_hw6_1

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.Polyline
import com.google.android.gms.maps.model.PolylineOptions

class MainActivity : AppCompatActivity(), OnMapReadyCallback {//kotlin繼承兩個class用逗號隔開
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {//kotlin用的是when，而不是switch case
            1 -> for (result in grantResults) {//我沒有宣告REQUEST_PERMISSIONS，直接用數字
                //裡面的程式碼與java相同
                    if (result != PackageManager.PERMISSION_GRANTED) {
                    finish()
                } else {
                    val map = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?//這邊的不同在於kotlin的宣告方式不同
                    map!!.getMapAsync(this)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //這邊一樣我沒宣告Requestcode，直接用1
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
        else {
            val map = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
            map!!.getMapAsync(this)//!!用於解決NULL的問題
        }
    }

    override fun onMapReady(p0: GoogleMap) {
        if (ActivityCompat.checkSelfPermission(this@MainActivity, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this@MainActivity, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) return//這邊也是基本與java差不多
        p0.setMyLocationEnabled(true)//p0是他函數產生時的變數名稱，我就不改了，所以與java不同
        ///////////以下除了宣告方式之外，都與java相同//////////////
        val m1 = MarkerOptions()
        m1.position(LatLng(25.0336111, 121.565000))
        m1.title("台北101")
        m1.draggable(true)
        p0.addMarker(m1)

        val m2 = MarkerOptions()
        m2.position(LatLng(25.047924, 121.517081))
        m2.title("台北車站")
        m2.draggable(true)
        p0.addMarker(m2)

        val polylineOpt = PolylineOptions()
        polylineOpt.add(LatLng(25.033611, 121.565000))
        polylineOpt.add(LatLng(25.032728, 121.565137))
        polylineOpt.add(LatLng(25.047924, 121.517081))
        polylineOpt.color(Color.BLUE)
        val polyline: Polyline = p0.addPolyline(polylineOpt)
        polyline.width = 10f
        p0.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(25.034, 121.545), 13f))
    }
}