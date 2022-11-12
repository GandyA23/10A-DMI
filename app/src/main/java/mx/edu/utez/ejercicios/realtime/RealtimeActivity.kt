package mx.edu.utez.ejercicios.realtime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import mx.edu.utez.ejercicios.databinding.ActivityRealtimeBinding

class RealtimeActivity : AppCompatActivity() {

    lateinit var binding: ActivityRealtimeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRealtimeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonSave.setOnClickListener {
            save(
                binding.editTextName.text.toString(),
                binding.editTextLastName.text.toString(),
                binding.editTextUsername.text.toString(),
            )
        }

        binding.buttonQuery.setOnClickListener {
            query(
                binding.editTextUsername.text.toString()
            )
        }

        binding.buttonUpdate.setOnClickListener {
            update(
                binding.editTextName.text.toString(),
                binding.editTextLastName.text.toString(),
                binding.editTextUsername.text.toString(),
            )
        }
    }

    private fun update(name: String, lastName: String, username: String) {
        val model = UserRealtime(name, lastName, username)

        // Save data in realtime database
        FirebaseDatabase
            .getInstance()
            .getReference("users")
            .child(username)
            .updateChildren(model.toMap())
            .addOnSuccessListener {
                Toast.makeText(applicationContext, "Username updated successful!", Toast.LENGTH_SHORT).show()

                // Clean inputs
                binding.editTextName.text.clear()
                binding.editTextLastName.text.clear()
                binding.editTextUsername.text.clear()

            }.addOnFailureListener {
                Toast.makeText(applicationContext, "Error -> ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun save (name: String, lastName: String, username: String) {
        val model = UserRealtime(name, lastName, username)

        // Save data in realtime database
        FirebaseDatabase
            .getInstance()
            .getReference("users")
            .child(username)
            .setValue(model)
            .addOnSuccessListener {
                Toast.makeText(applicationContext, "Username store successful!", Toast.LENGTH_SHORT).show()

                // Clean inputs
                binding.editTextName.text.clear()
                binding.editTextLastName.text.clear()
                binding.editTextUsername.text.clear()

            }.addOnFailureListener {
                Toast.makeText(applicationContext, "Error -> ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }

    fun query (username: String) {
        FirebaseDatabase
            .getInstance()
            .getReference("users")
            .child(username)
            .get()
            .addOnSuccessListener {
                var message = "User not found"

                if (it.exists()) {
                    message = "User found!"

                    binding.editTextName.setText(it.child("name").value.toString())
                    binding.editTextLastName.setText(it.child("lastName").value.toString())
                }

                Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(applicationContext, "Error -> ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }
}