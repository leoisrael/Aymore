package com.example.aymore

import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.net.wifi.WifiManager
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private var map: GoogleMap? = null
    private var marker: Marker? = null
    private var ghostMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        findViewById<Button>(R.id.toggleGhostButton).setOnClickListener {
            ghostMode = !ghostMode
            if (ghostMode) {
                injectGhostLocation()
            } else {
                disableMock()
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        updateMarker(LatLng(0.0, 0.0))
    }

    private fun updateMarker(pos: LatLng) {
        if (marker == null) {
            marker = map?.addMarker(MarkerOptions().position(pos))
        } else {
            marker?.position = pos
        }
        map?.moveCamera(CameraUpdateFactory.newLatLngZoom(pos, 16f))
    }

    private fun injectGhostLocation() {
        val lm = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val provider = LocationManager.GPS_PROVIDER
        lm.addTestProvider(
            provider, false, false, false, false, true,
            true, true, 0, 5
        )
        lm.setTestProviderEnabled(provider, true)

        val location = Location(provider).apply {
            latitude = estimateLatitude()
            longitude = estimateLongitude()
            accuracy = 20f
            time = System.currentTimeMillis()
        }
        lm.setTestProviderLocation(provider, location)
        updateMarker(LatLng(location.latitude, location.longitude))
    }

    private fun disableMock() {
        val lm = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        lm.clearTestProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    private fun estimateLatitude(): Double {
        val wifi = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val bssid = wifi.connectionInfo.bssid ?: return 0.0
        // TODO: map BSSID to location using external service
        return 0.0
    }

    private fun estimateLongitude(): Double {
        val wifi = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val bssid = wifi.connectionInfo.bssid ?: return 0.0
        // TODO: map BSSID to location using external service
        return 0.0
    }
}
