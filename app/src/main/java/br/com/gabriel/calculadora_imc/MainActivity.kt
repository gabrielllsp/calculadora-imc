package br.com.gabriel.calculadora_imc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.gabriel.calculadora_imc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pesoEscolha.minValue = 30
        binding.pesoEscolha.maxValue = 150

        binding.alturaEscolha.minValue = 100
        binding.alturaEscolha.maxValue = 250

        binding.alturaEscolha.setOnValueChangedListener { _, _, _ ->
            calculoImc()
        }
    }

    private fun calculoImc() {
        val altura = binding.alturaEscolha.value
        val doubleAltura = altura.toDouble() / 100

        val peso = binding.pesoEscolha.value

        val imc = peso.toDouble() / (doubleAltura * doubleAltura)

        binding.resultado.text = String.format("Seu IMC e: %.2f", imc)
        binding.saudavel.text = String.format("Considered: %s", saudeMensagem(imc))

    }

    private fun saudeMensagem(imc: Double): String {
        return when {
            imc < 18.5 -> "Abaixo do Peso"
            imc < 25.0 -> "Saudável"
            imc < 30.0 -> "Excesso de peso"
            else -> "Obeso"
        }


    }
}