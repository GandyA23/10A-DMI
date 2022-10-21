package mx.edu.utez.ejercicios.rest.provider

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mx.edu.utez.ejercicios.rest.model.ErrorData
import mx.edu.utez.ejercicios.rest.model.Usuario
import mx.edu.utez.ejercicios.rest.datatype.Result
import mx.edu.utez.ejercicios.rest.services.ApiUtils
import mx.edu.utez.ejercicios.utils.EnvValues

class RegistroProvider {
    companion object {
        suspend fun registro(usuario: Usuario) : Result<Usuario> {
            var call = ApiUtils.apiService.create(
                "${EnvValues.BASE_URL_GO_REST}/users/",
                usuario,
                "Bearer ${EnvValues.BEARER_TOKEN}"
            )

            return if (call.isSuccessful) {
                Result.success(call.body()!!)
            }  else {
                var gson = GsonBuilder().create()
                var type = object : TypeToken<List<ErrorData>>() {}.type

                // Se obtienen los errores del json
                var errores : List<ErrorData> = gson.fromJson(
                    call.errorBody()!!.charStream(),
                    type
                )

                Result.error("${errores[0].field}: ${errores[0].message}")
            }
        }
    }
}