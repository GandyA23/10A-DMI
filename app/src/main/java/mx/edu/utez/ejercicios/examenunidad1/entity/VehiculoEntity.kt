package mx.edu.utez.ejercicios.examenunidad1.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vehiculo")
data class VehiculoEntity(
    @PrimaryKey(autoGenerate = true)
    var id : Long?,
    var marca : String,
    var subMarca : String,
    var ano : String
)