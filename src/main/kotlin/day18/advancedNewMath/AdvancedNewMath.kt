package day18.advancedNewMath

import java.math.BigInteger

fun evaluateWithAdvancedNewMath(expression: String) : BigInteger {
    if(expression.contains('(')) {
        return(solveEquation(reduceFirstParentheses(expression)))
    }
    return solveEquation(expression)
}

private fun reduceFirstParentheses(expression: String) : String {
    val parentheseRegex = """(\([\d\s\+\*]+\))""".toRegex()
    val result = parentheseRegex.find(expression)!!
    val groups = result.groups!!
    val equation = expression.subSequence(groups[1]!!.range.first+1, groups[1]!!.range.last)
    val value = solveEquation(equation.toString())
    val newExpression = expression.replaceRange(groups[1]!!.range, value.toString())

    if(newExpression.contains('(')) {
        return reduceFirstParentheses(newExpression)
    }

    return newExpression
}

private fun solveEquation(equation: String): BigInteger {
    var newEquation = equation
    if(equation.contains('+')) {
        newEquation = solveAddition(equation)
    }
    return solveMultiplication(newEquation)
}

private fun solveAddition(equation: String) : String {
    val additionRegex = """(\d+)\s([\+])\s(\d+)""".toRegex()
    val matchResult = additionRegex.find(equation)!!
    val op1 = matchResult.groupValues[1].toBigInteger()
    val op2 = matchResult.groupValues[3].toBigInteger()
    var result = op1 + op2

    val newEquation = additionRegex.replaceFirst(equation, result.toString())
    return if( newEquation.contains('+')){
        solveAddition(newEquation)
    } else {
        newEquation
    }
}

private fun solveMultiplication(equation: String) : BigInteger {
    if(!equation.contains('*')) {
        return equation.toBigInteger()
    }

    val additionRegex = """(\d+)\s([\*])\s(\d+)""".toRegex()
    val matchResult = additionRegex.find(equation)!!
    val op1 = matchResult.groupValues[1].toBigInteger()
    val op2 = matchResult.groupValues[3].toBigInteger()
    var result = op1 * op2

    val newEquation = additionRegex.replaceFirst(equation, result.toString())
    return if( newEquation.contains('*')){
        solveMultiplication(newEquation)
    } else {
        result
    }
}