package mx.edu.utez.ejercicios.rest.services

import mx.edu.utez.ejercicios.rest.Usuario
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

/** Interfaz que nos ayudara a declarar funciones para el consumo de API REST **/
interface ApiService {
    @GET
    suspend fun getAll(@Url url : String) : Response<List<Usuario>>
}