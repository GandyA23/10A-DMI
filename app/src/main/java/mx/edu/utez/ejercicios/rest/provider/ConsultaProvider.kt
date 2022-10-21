package mx.edu.utez.ejercicios.rest.provider

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mx.edu.utez.ejercicios.rest.model.Usuario
import mx.edu.utez.ejercicios.rest.datatype.Result
import mx.edu.utez.ejercicios.rest.model.ErrorData
import mx.edu.utez.ejercicios.rest.services.ApiUtils
import mx.edu.utez.ejercicios.utils.EnvValues

class ConsultaProvider {
    companion object {
        suspend fun getAll(): Result<List<Usuario>> {
            val call = ApiUtils
                .apiService
                .getAll("${EnvValues.BASE_URL_GO_REST}/users/", "Bearer ${EnvValues.BEARER_TOKEN}")

            if (call.isSuccessful)
                return Result.success(call.body()!!)
            return Result.error("La consulta ha fallado")
        }

        suspend fun show(id: Long): Result<Usuario> {
            val call = ApiUtils
                .apiService
                .show("${EnvValues.BASE_URL_GO_REST}/users/${id}", "Bearer ${EnvValues.BEARER_TOKEN}")

            if (call.isSuccessful)
                return Result.success(call.body()!!)

            var gson = GsonBuilder().create()
            var type = object : TypeToken<List<ErrorData>>() {}.type

            // Se obtienen los errores del json
            var errores : List<ErrorData> = gson.fromJson(
                call.errorBody()!!.charStream(),
                type
            )

            return Result.error("${errores[0].field}: ${errores[0].message}")
        }
    }
}