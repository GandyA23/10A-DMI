package mx.edu.utez.ejercicios.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "empleado")
data class EmpleadoEntity(
    @PrimaryKey(autoGenerate = true)
    var id : Long?,
    var nombre : String,
    var telefono : String
)