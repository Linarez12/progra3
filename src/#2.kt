fun celsiusAFahrenheit(c: Double): Double = c * 9.0 / 5.0 + 32.0
fun fahrenheitACelsius(f: Double): Double = (f - 32.0) * 5.0 / 9.0
fun celsiusAKelvin(c: Double):    Double = c + 273.15
fun kelvinACelsius(k: Double):    Double = k - 273.15
fun fahrenheitAKelvin(f: Double): Double = celsiusAKelvin(fahrenheitACelsius(f))
fun kelvinAFahrenheit(k: Double): Double = celsiusAFahrenheit(kelvinACelsius(k))

fun convertir(valor: Double, desde: String, hasta: String): Double =
    when ("$desde->$hasta") {
        "C->F" -> celsiusAFahrenheit(valor)
        "F->C" -> fahrenheitACelsius(valor)
        "C->K" -> celsiusAKelvin(valor)
        "K->C" -> kelvinACelsius(valor)
        "F->K" -> fahrenheitAKelvin(valor)
        "K->F" -> kelvinAFahrenheit(valor)
        else   -> if (desde == hasta) valor
        else throw IllegalArgumentException("Conversión no soportada: $desde -> $hasta")
    }

fun mostrarConversion(valor: Double, desde: String, hasta: String) {
    val simbolos = mapOf("C" to "°C", "F" to "°F", "K" to "K")
    val resultado = convertir(valor, desde, hasta)
    val simDesde  = simbolos[desde] ?: desde
    val simHasta  = simbolos[hasta] ?: hasta
    println("  $valor$simDesde  →  ${String.format("%.2f", resultado)}$simHasta")
}

fun main() {
    println("CONVERSOR DE TEMPERATURA")

    println("\n Pruebas requeridas:")
    mostrarConversion(100.0, "C", "F")   // 100°C → 212°F
    mostrarConversion(0.0,   "C", "K")   // 0°C   → 273.15K
    mostrarConversion(32.0,  "F", "C")   // 32°F  → 0°C

    println("\n Conversiones adicionales:")
    mostrarConversion(300.0, "K", "C")   // 300K  → 26.85°C
    mostrarConversion(98.6,  "F", "K")   // 98.6°F → 310.15K
    mostrarConversion(-40.0, "C", "F")   // -40°C → -40°F (punto de cruce)
}