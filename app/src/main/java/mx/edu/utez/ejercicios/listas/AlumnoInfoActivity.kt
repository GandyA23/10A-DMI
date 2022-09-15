package mx.edu.utez.ejercicios.listas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import mx.edu.utez.ejercicios.databinding.ActivityAlumnoInfoBinding

class AlumnoInfoActivity : AppCompatActivity() {

    lateinit var binding :ActivityAlumnoInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlumnoInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val alumno = intent.getSerializableExtra("alumno") as Alumno
        alumno.edad = "${alumno.edad} años"
        alumno.estatus = "Estatus: ${alumno.estatus}"

        Glide.with(this).load(alumno.foto).into(binding.circleImageViewPerfil)

        binding.alumnoXml = alumno

    }
}