fun main() {
    val peso: Double = 70.0
    val altura: Double = 1.75

    val imc: Double = peso / (altura * altura)

    val categoria = when {
        imc < 18.5 -> "Bajo peso"
        imc < 25.0 -> "Normal"
        imc < 30.0 -> "Sobrepeso"
        else       -> "Obesidad"
    }

    println("Calculadora de IMC")
    println("Peso   : $peso kg")
    println("Altura : $altura m")
    println("IMC    : ${String.format("%.2f", imc)}")
    println("Categoría: $categoria")
}