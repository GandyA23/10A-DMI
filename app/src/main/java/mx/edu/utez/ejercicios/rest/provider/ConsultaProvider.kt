package mx.edu.utez.ejercicios.rest.provider

import mx.edu.utez.ejercicios.rest.Usuario
import mx.edu.utez.ejercicios.rest.services.ApiUtils
import mx.edu.utez.ejercicios.utils.EnvValues

class ConsultaProvider {
    companion object {
        suspend fun getAll(): List<Usuario> {
            val call = ApiUtils
                .apiService
                .getAll("${EnvValues.BASE_URL}/users/", "Bearer ${EnvValues.BEARER_TOKEN}")

            if (call.isSuccessful)
                return call.body()!!
            return emptyList()
        }
    }
}