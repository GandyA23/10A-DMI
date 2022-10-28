package mx.edu.utez.ejercicios.mapas

import android.Manifest
import android.content.pm.PackageManager
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
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
     * Obtiene un string de dirección dado una latitude y longitude*/
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
}