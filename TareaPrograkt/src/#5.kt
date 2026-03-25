data class Producto(
    val id: Int,
    val nombre: String,
    val precio: Double,
    var stock: Int
)

sealed class OpInventario {
    data class Exito(val datos: Any) : OpInventario()
    data class Error(val mensaje: String) : OpInventario()
    object SinStock : OpInventario()
}

val inventario = mutableListOf<Producto>()

fun agregarProducto(producto: Producto): OpInventario {
    val existe = inventario.any { it.id == producto.id }
    if (existe) return OpInventario.Error("Ya existe un producto con id ${producto.id}")
    inventario.add(producto)
    return OpInventario.Exito(producto)
}

fun buscarPorId(id: Int): Producto? {
    return inventario.find { it.id == id }
}

fun actualizarStock(id: Int, cantidad: Int): OpInventario {
    val producto = buscarPorId(id) ?: return OpInventario.Error("Producto con id $id no encontrado")
    if (producto.stock + cantidad < 0) return OpInventario.SinStock
    producto.stock += cantidad
    return OpInventario.Exito(producto)
}

fun listarDisponibles(): List<Producto> {
    return inventario.filter { it.stock > 0 }
}

fun List<Producto>.valorTotal(): Double {
    return sumOf { it.precio * it.stock }
}

fun List<Producto>.buscarPorNombre(query: String): List<Producto> {
    return filter { it.nombre.contains(query, ignoreCase = true) }
}

fun mostrarResultado(operacion: OpInventario) {
    when (operacion) {
        is OpInventario.Exito   -> println("OK: ${ operacion.datos}")
        is OpInventario.Error   -> println("Error: ${operacion.mensaje}")
        is OpInventario.SinStock -> println("Error: stock insuficiente")
    }
}

fun main() {

    println("--- Agregando productos ---")
    mostrarResultado(agregarProducto(Producto(1, "Laptop Lenovo",  5500.00, 10)))
    mostrarResultado(agregarProducto(Producto(2, "Mouse Logitech",  150.00, 25)))
    mostrarResultado(agregarProducto(Producto(3, "Teclado Genius",  200.00,  0)))
    mostrarResultado(agregarProducto(Producto(4, "Monitor LG",     2800.00,  5)))
    mostrarResultado(agregarProducto(Producto(5, "Laptop HP",      4900.00,  3)))
    mostrarResultado(agregarProducto(Producto(1, "Duplicado",         0.00,  0)))

    println("\n--- Buscar por id ---")
    val encontrado = buscarPorId(3)
    println(encontrado ?: "No encontrado")

    val noExiste = buscarPorId(99)
    println(noExiste ?: "No encontrado")

    println("\n--- Actualizar stock ---")
    mostrarResultado(actualizarStock(3, 15))
    mostrarResultado(actualizarStock(2, -30))
    mostrarResultado(actualizarStock(99, 5))

    println("\n--- Productos disponibles ---")
    for (p in listarDisponibles()) {
        println("${p.id}. ${p.nombre} - Stock: ${p.stock} - Precio: Q${String.format("%.2f", p.precio)}")
    }

    println("\n--- Buscar por nombre 'laptop' ---")
    val resultado = inventario.buscarPorNombre("laptop")
    for (p in resultado) {
        println("${p.nombre} - Q${String.format("%.2f", p.precio)}")
    }

    println("\n--- Valor total del inventario ---")
    println("Q${String.format("%.2f", inventario.valorTotal())}")

    println("\n--- Valor total solo disponibles ---")
    println("Q${String.format("%.2f", listarDisponibles().valorTotal())}")
}