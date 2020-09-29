package com.hynes.james.fibtest.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.hynes.james.fibtest.R
import com.hynes.james.fibtest.model.FibonacciCalculationResult

class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val inputValue = MutableLiveData<Int>()

    val fibonacciSequence = Transformations.map(inputValue) {
        measureTimeMillis({ time ->
            logCalculationResults(it, time)
        }) {
            createFibonacciSequence(it)
        }
    }

    val calculationTime = MutableLiveData<String>()

    val fibonacciCalculationResults = mutableListOf<FibonacciCalculationResult>()

    fun updateInputValue(inputValue: Int) {

        this.inputValue.value = inputValue
    }

    private fun logCalculationResults(inputValue: Int, time: Long) {

        calculationTime.value = getApplication<Application>().getString(R.string.calculation_time_format, time)
        fibonacciCalculationResults.add(FibonacciCalculationResult(inputValue, time))
    }

    private fun createFibonacciSequence(inputValue: Int): List<Long> {

        val results = mutableListOf<Long>()
        var value1 = 0L
        var value2 = 1L

        for (i in 0..inputValue) {

            results.add(value1)
            val sum = value1 + value2
            value1 = value2
            value2 = sum
        }

        return results
    }

    private inline fun <T> measureTimeMillis(loggingFunction: (Long) -> Unit, function: () -> T): T {

        val startTime = System.currentTimeMillis()
        val result: T = function.invoke()
        loggingFunction.invoke(System.currentTimeMillis() - startTime)

        return result
    }
}