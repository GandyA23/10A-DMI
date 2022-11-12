package mx.edu.utez.ejercicios.realtime

data class UserRealtime(
    val name: String,
    val lastName: String,
    val userName: String
) {
    fun toMap (): Map<String, Any> {
        return mapOf(
            "name" to name,
            "lastName" to lastName,
        )
    }
}