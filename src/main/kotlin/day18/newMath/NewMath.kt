package day18.newMath

import java.math.BigInteger

fun evaluateWithNewMath(expression: String) : BigInteger {
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

private fun solveEquation(equation: String) : BigInteger {
    val calcRegex = """(\d+)\s([\+\*])\s(\d+)""".toRegex()
    val matchResult = calcRegex.find(equation)!!
    val op1 = matchResult.groupValues[1].toBigInteger()
    val op2 = matchResult.groupValues[3].toBigInteger()
    val operator = matchResult.groupValues[2]
    var result = BigInteger.ZERO
    when(operator) {
        "*" -> result = op1 * op2
        "+" -> result = op1 + op2
    }
    val newEquation = calcRegex.replaceFirst(equation, result.toString())
    if(newEquation.contains('*') || newEquation.contains('+')){
        return solveEquation(newEquation)
    } else {
        return result
    }
}