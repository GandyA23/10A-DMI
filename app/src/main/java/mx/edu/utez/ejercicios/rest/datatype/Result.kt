package mx.edu.utez.ejercicios.rest.datatype

data class Result<out T> (
    var resultType : ResultType?,
    val data: T? = null,
    val error: String? = null
) {
    companion object {
        fun <T> success(data: T?): Result<T> {
            return Result(ResultType.SUCCESS, data)
        }

        fun <T> error(error: String? = null): Result<T> {
            return Result(ResultType.ERROR, error = error)
        }
    }
}