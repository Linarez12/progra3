data class Estudiante(
    val nombre: String,
    val grado: Int,
    val notas: List<Double>
) {
    val promedio: Double
        get() = notas.sum() / notas.size
}

fun main() {

    val estudiantes = listOf(
        Estudiante("Ana Garcia",    9, listOf(85.0, 92.0, 78.0, 90.0, 88.0)),
        Estudiante("Carlos Lopez",  8, listOf(60.0, 55.0, 70.0, 65.0, 58.0)),
        Estudiante("Maria Torres",  9, listOf(95.0, 98.0, 92.0, 97.0, 99.0)),
        Estudiante("Luis Ramirez", 10, listOf(72.0, 68.0, 75.0, 80.0, 71.0)),
        Estudiante("Sofia Diaz",    8, listOf(45.0, 50.0, 40.0, 55.0, 48.0)),
        Estudiante("Pedro Ruiz",   10, listOf(88.0, 84.0, 91.0, 87.0, 85.0))
    )

    println("--- Lista de estudiantes ---")
    for (e in estudiantes) {
        println("${e.nombre} - Grado: ${e.grado} - Promedio: ${String.format("%.1f", e.promedio)}")
    }

    val aprobados = estudiantes.filter { it.promedio >= 70.0 }

    println("\n--- Estudiantes aprobados ---")
    for (e in aprobados) {
        println("${e.nombre}: ${String.format("%.1f", e.promedio)}")
    }

    val ordenados = estudiantes.sortedByDescending { it.promedio }

    println("\n--- Top 3 ---")
    val top3 = ordenados.take(3)
    for (i in top3.indices) {
        println("${i + 1}. ${top3[i].nombre} - ${String.format("%.1f", top3[i].promedio)}")
    }
}