package mx.edu.utez.ejercicios.rest.services

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    var retrofit: Retrofit? = null

    fun getClient(url: String): Retrofit? {
        // En caso de que retrofit sea núlo, crea uno nuevo
        if (retrofit == null) {
            val gson = GsonBuilder()
                .setLenient()
                .serializeNulls()
                .create()

            // Crea el interceptor para todas las peticiones
            val logInterceptor = HttpLoggingInterceptor()

            // Intercepta todas las peticiones a nivel de BODY
            logInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient
                .Builder()
                .addInterceptor(logInterceptor)
                .build()

            retrofit = Retrofit
                .Builder()
                .client(client)
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }

        return retrofit
    }
}