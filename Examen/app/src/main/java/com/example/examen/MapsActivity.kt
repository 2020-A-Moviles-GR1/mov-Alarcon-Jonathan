package com.example.examen

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    var tienePermisos = false
    var nombresFar = arrayListOf<String>()
    var listaFarmacias1 = arrayListOf<FarmaciaAtributos>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        solicitarPermisos()
        //farmaciasDefecto()
        //val nombreFarmacia = intent.getStringExtra("Nombre")
        //Log.i("Nombre: ", nombreFarmacia)
        listaFarmacias1 = ServicioBDDMemoria.listaFarmacias
        for (e in listaFarmacias1){
            nombresFar.add(e.nombreFarmacia)
            Log.i("nombres","el nombre es tal: ${e.nombreFarmacia}")
        }


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
        farmaciasDefecto()

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

    fun farmaciasDefecto(){

        for (e in nombresFar){
            if (e.equals("Farm-Labs")){
                val direccion4 = LatLng(-0.209418, -78.494870)
                val titulo4 = "Farm-Labs"
                mMap.addMarker(MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.farmlabs)).position(direccion4).title(titulo4))
                mMap.setOnMarkerClickListener ( GoogleMap.OnMarkerClickListener{
                    val uri = Uri.parse("https://www.farmlab.ie/")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                    true
                } )
            }

            if (e.equals("Fybeca")){
                val direccion1 = LatLng(-0.207248, -78.496822)
                val titulo1 = "Fybeca"

                //anadirMarcador(direccion1, titulo1)
                mMap.addMarker(MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.fybeca)).position(direccion1).title(titulo1))
                mMap.setOnMarkerClickListener ( GoogleMap.OnMarkerClickListener{
                    val uri = Uri.parse("https://www.fybeca.com/FybecaWeb/pages/home.jsf")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                    true
                } )
            }

            if(e.equals("Medicity")){
                val direccion2 = LatLng(-0.207197, -78.495733)
                val titulo2 = "Medicity"
                anadirMarcador(direccion2, titulo2)
                mMap.addMarker(MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.medicity)).position(direccion2).title(titulo2))
                mMap.setOnMarkerClickListener ( GoogleMap.OnMarkerClickListener{
                    val uri = Uri.parse("https://www.farmaciasmedicity.com/")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                    true
                } )
            }

            if(e.equals("GreenFarm")){
                val direccion3 = LatLng(-0.207876, -78.494529)
                val titulo3 = "GreenFarm"
                mMap.addMarker(MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.greenfarm)).position(direccion3).title(titulo3))
                mMap.setOnMarkerClickListener ( GoogleMap.OnMarkerClickListener{
                    val uri = Uri.parse("https://www.greenfarm.technology/about-2/")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                    true
                } )
            }

            if(e.equals("Pharma Care")){
                val direccion5 = LatLng(-0.209303, -78.496168)
                val titulo5 = "Pharma Care"
                mMap.addMarker(MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.farmacare)).position(direccion5).title(titulo5))
                mMap.setOnMarkerClickListener ( GoogleMap.OnMarkerClickListener{
                    val uri = Uri.parse("https://infricomedcare.com/pharma-care/")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                    true
                } )
            }
        }

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
