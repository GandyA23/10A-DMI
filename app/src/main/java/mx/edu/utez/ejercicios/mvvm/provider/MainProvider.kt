package mx.edu.utez.ejercicios.mvvm.provider

/**
 * Los provider se van a encargar de realizar las
 * tareas pesadas como consultas
 * */
class MainProvider {

    companion object {
        fun checkLogin(user: String, password: String) : Boolean {
            return !user.isNullOrEmpty() && !password.isNullOrEmpty()
        }
    }
}