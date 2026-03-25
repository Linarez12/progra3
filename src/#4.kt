data class Contacto(
    val nombre: String,
    val telefono: String?,
    val email: String?
)

val directorio = mapOf<String, Contacto?>(
    "001" to Contacto("Carlos Lopez", "502-4567-8901", "carlos@gmail.com"),
    "002" to Contacto("Ana Garcia", null, "ana@hotmail.com"),
    "003" to Contacto("Pedro Ruiz", "502-3456-7890", null),
    "004" to Contacto("Sofia Diaz", null, null),
    "005" to null
)

fun buscarContacto(id: String): Contacto? {
    return directorio[id]
}

fun mostrarContacto(id: String) {
    println("Buscando id: $id")

    val contacto = buscarContacto(id)

    contacto?.let {
        println("Nombre   : ${it.nombre}")
        println("Telefono : ${it.telefono ?: "No disponible"}")
        println("Email    : ${it.email ?: "No disponible"}")
    } ?: println("Contacto no encontrado")

    println()
}

fun main() {
    println("--- Directorio de Contactos ---\n")

    mostrarContacto("001")
    mostrarContacto("002")
    mostrarContacto("003")
    mostrarContacto("004")
    mostrarContacto("005")
    mostrarContacto("999")
}