package mx.edu.utez.ejercicios.rest.services

import mx.edu.utez.ejercicios.rest.model.Usuario
import mx.edu.utez.ejercicios.utils.EnvValues
import retrofit2.Response
import retrofit2.http.*

/** Interfaz que nos ayudara a declarar funciones para el consumo de API REST **/
interface ApiService {
    @GET
    suspend fun getAll(
        @Url url : String,
        @Header("Authorization") token : String
    ) : Response<List<Usuario>>

    @GET
    suspend fun show(
        @Url url : String,
        @Header("Authorization") token : String
    ) : Response<Usuario>

    @POST
    suspend fun create(
        @Url url : String,
        @Body user: Usuario,
        @Header("Authorization") token : String
    ) : Response<Usuario>

    @DELETE
    suspend fun delete(
        @Url url : String,
        @Header("Authorization") token : String
    ) : Response<Void>
}

object ApiUtils {
    var url = "${EnvValues.BASE_URL_GO_REST}/"
    val apiService : ApiService get() = RetrofitClient
        .getClient(url)?.create(ApiService::class.java)!!
}
