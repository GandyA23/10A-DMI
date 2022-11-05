package mx.edu.utez.ejercicios.mapas

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import mx.edu.utez.ejercicios.R
import mx.edu.utez.ejercicios.databinding.ActivityMapsBinding
import java.lang.Exception
import java.util.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync(this)

        binding.buttonIr.setOnClickListener {
            getCoordinates(binding.editTextDirection.text.toString())
            getDistanceBetweenTwoPoints()
        }

        binding.buttonCalculateRoute.setOnClickListener {
            openNavigation(LatLng(18.851643, -99.199913), LatLng(18.848178, -99.199407))
        }
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

        // Add a marker in UTEZ and move the camera
        val utez = LatLng(18.850253, -99.200730)
        mMap.addMarker(MarkerOptions().position(utez).title("UTEZ"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(utez))

        // Realiza el chequeo de los permisos
        // En caso
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            return
        }

        mMap.isMyLocationEnabled = true
        getMarkerLocation()
        initService()
        buildRoute()
    }

    /**
     * Obtiene la latitud y la longitud en base a la posición central de la camara
     * */
    fun getMarkerLocation() {
        mMap.setOnCameraIdleListener {
            var lat = mMap.cameraPosition.target.latitude
            var lon = mMap.cameraPosition.target.longitude

            Log.d("MapsLog", "$lat $lon")
            getDirection(lat, lon)
        }
    }

    /**
     * Obtiene un string de dirección dado una latitude y longitud
     * */
    fun getDirection(lat: Double, lon: Double) {
        try {
            var geocoder = Geocoder(this, Locale.getDefault())
            var direcciones = geocoder.getFromLocation(lat, lon, 1)

            // Dirección completa en un input
            var direccionCompleta = direcciones[0].getAddressLine(0)
            Log.d("MapsLog", "Tu dirección es $direccionCompleta")

            if (direcciones.size > 0) {
                var pais = direcciones[0].countryName
                var estado = direcciones[0].adminArea
                var municipio = direcciones[0].locality
                var calle = direcciones[0].thoroughfare
                var colonia = direcciones[0].subLocality
                var codigoPostal = direcciones[0].postalCode

                if (pais != null)
                    Log.d("MapsLog", "Tu país es $pais")

                if (estado != null)
                    Log.d("MapsLog", "Tu estado es $estado")

                if (municipio != null)
                    Log.d("MapsLog", "Tu municipio es $municipio")

                if (calle != null)
                    Log.d("MapsLog", "Tu calle es $calle")

                if (colonia != null)
                    Log.d("MapsLog", "Tu colonia es $colonia")

                if (codigoPostal != null)
                    Log.d("MapsLog", "Tu Código Postal es $codigoPostal")
            }
        } catch (e: Exception) {
            Log.e("MapsLog", e.message!!)
        }
    }

    /**
     * Obtiene las coordenadas dada una dirección en string
     * */
    fun getCoordinates (direction: String) {
        // Limpia los demás puntos
        mMap.clear()

        try {
            var geocoder = Geocoder(this, Locale.getDefault())

            // Obtiene 1 dirección en base a un string
            var directions = geocoder.getFromLocationName(direction, 1)

            if (!directions.isNullOrEmpty()){
                var directionStr = directions[0]
                var latLng = LatLng(directionStr.latitude, directionStr.longitude)

                // Añade el marcador
                mMap.addMarker(MarkerOptions().position(latLng).title("Dirección personalizada"))

                // Mueve la camara
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))

                // Realiza el zoom a la camara
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13f))
            }
        } catch (e: Exception) {
            Log.e("MapsLog", e.message!!)
        }
    }

    fun initService() {
        LocationLiveData(this).observe(this) {
            Log.d("MapsLog", "Localización real: ${it.latitude}, ${it.longitude}")
        }
    }

    fun getDistanceBetweenTwoPoints() {
        val loc1 = Location("")
        val loc2 = Location("")

        loc1.latitude = 18.850924
        loc1.longitude = -99.201572

        loc2.latitude = 18.849138
        loc2.longitude = -99.200189

        // Obtiene la distancia en metros
        var distance = loc1.distanceTo(loc2)

        Toast.makeText(applicationContext, "Distancia (mts): $distance", Toast.LENGTH_SHORT).show()
    }

    /**
     * Abre un nuevo activity con la ruta en maps
     * */
    fun openNavigation(origen: LatLng, destino: LatLng) {
        // "https://maps.google.com/maps?saddr=location1&daddr=location2"

        // Abre una vista que muestre el mapa
        var intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://maps.google.com/maps?saddr=${origen.latitude},${origen.longitude}&daddr=${destino.latitude},${destino.longitude}")
        )

        startActivity(intent)
    }

    /**
     * Muestra una ruta con puntos trazados en un mapa
     * */
    fun buildRoute() {
        // Construye el recorrido
        var polylineOptions = PolylineOptions()
            // Dirección desde una docencia hasta el CECyTE
            .add(LatLng(18.851234, -99.200416))
            .add(LatLng(18.851191, -99.200166))
            .add(LatLng(18.851049, -99.199987))
            .add(LatLng(18.851023, -99.199834))
            .add(LatLng(18.850409, -99.199877))
            .add(LatLng(18.850224, -99.199764))
            .add(LatLng(18.849947, -99.199794))
            .add(LatLng(18.849299, -99.200255))
            .add(LatLng(18.848489, -99.199637))
            .width(10f)
            .color(ContextCompat.getColor(this, R.color.teal_700))

        var polyline = mMap.addPolyline(polylineOptions)
        // Dale un estilo a la linea
        var patron = listOf(
            Dot(), Gap(10f), Dash(30f), Gap(10f)
        )

        polyline.pattern = patron

    }
}