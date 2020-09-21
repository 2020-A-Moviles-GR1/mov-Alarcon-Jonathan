package com.example.examen

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    var tienePermisos = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        solicitarPermisos()

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        establecerConfiguraciones(mMap)

        val direccion = LatLng(-0.208511, -78.496020)
        val puntoUsuario = LatLng(-0.208511, -78.496020)
        val zoom = 17f
        val titulo = "Canada"
        anadirMarcador(direccion, titulo)
        moverCamaraConZoom(puntoUsuario, zoom)

        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE)

        // Add a marker in Sydney and move the camera
        //val sydney = LatLng(-0.208511, -78.496020)
        //mMap.addMarker(MarkerOptions().position(sydney).title("Marcador Patria"))
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    fun moverCamaraConZoom(latLng: LatLng, zoom: Float = 10f){
        mMap.moveCamera(
            CameraUpdateFactory.newLatLngZoom(latLng, zoom)
        )
    }

    fun anadirMarcador(latLng: LatLng, title: String){
        mMap.addMarker(
            MarkerOptions().position(latLng).title(title)
        )
    }

    fun establecerConfiguraciones(mapa: GoogleMap){
        val contexto = this.applicationContext
        with(mapa){
            val permisosFindLocation = ContextCompat.checkSelfPermission(
                contexto,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
            val tienePermisos = permisosFindLocation == PackageManager.PERMISSION_GRANTED

            if(tienePermisos){
                mapa.isMyLocationEnabled = true
            }

            //this.uiSettings.isZoomControlsEnabled = true
            uiSettings.isZoomControlsEnabled = true
            uiSettings.isMyLocationButtonEnabled = true
        }
    }

    fun solicitarPermisos(){
        val permisosFindLocation = ContextCompat.checkSelfPermission(
            this.applicationContext,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )
        val tienePermisos = permisosFindLocation == PackageManager.PERMISSION_GRANTED

        if(tienePermisos){
            Log.i("mapa","Tiene permisos FINE LOCATION")
            this.tienePermisos = true
        } else {
            // Vamos a pedir los permisos al usuario
            ActivityCompat.requestPermissions(
                this, //Contexto
                arrayOf( //Arreglo de permisos
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ),
                1 // Código de respuesta
            )
        }
    }
}
