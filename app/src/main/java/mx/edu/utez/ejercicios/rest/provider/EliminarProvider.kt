package mx.edu.utez.ejercicios.rest.provider

import mx.edu.utez.ejercicios.rest.datatype.Result
import mx.edu.utez.ejercicios.rest.services.ApiUtils
import mx.edu.utez.ejercicios.utils.EnvValues

class EliminarProvider {
    companion object {
        suspend fun delete(id: Long): Result<String> {
            val call = ApiUtils
                .apiService
                .delete("${EnvValues.BASE_URL}/users/${id}/", "Bearer ${EnvValues.BEARER_TOKEN}")

            if (call.isSuccessful)
                return Result.success("Se ha eliminado correctamente")
            return Result.error("Ha fallado la eliminación")
        }
    }
}