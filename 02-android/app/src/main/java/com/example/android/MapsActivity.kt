package com.example.android

import android.content.pm.PackageManager
import android.icu.text.CaseMap
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

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        // 1) Chequear permisos
        // 2) Establecer configuraciones
        establecerConfiguraciones(mMap)

        val direccion = LatLng(-0.208085, -78.507360)
        val puntoUsuario = LatLng(-0.209812, -78.506255)
        val zoom = 17f
        val titulo = "Canada"
        anadirMarcador(direccion, titulo)
        moverCamaraConZoom(puntoUsuario, zoom)

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        //mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
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


    // Funcion para establecer configuraciones
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

    //Funcion para solicitar permisos

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
