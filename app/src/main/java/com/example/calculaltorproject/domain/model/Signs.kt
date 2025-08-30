package com.example.calculaltorproject.domain.model

import androidx.annotation.IntRange

/** Precedence Order

1. Parentheses (grouping)
(a + b) * c

2. Postfix operators
a++, a--, f(), arr[i], member access (a.b)

3. Unary operators
+a, -a, !a, ++a, --a

4. Multiplicative
*, /, %

5. Additive
+, -

6. Range & containment
.., in, !in

7. Type checks and casts
as, as?, is, !is

8. Comparison
<, >, <=, >=

9. Equality
==, !=

10. Conjunction
&&

11. Disjunction
||

12. Elvis operator
?:

13. Assignment
=, +=, -=, *=, /=, %=

**/

sealed class Signs(val action: Actions, val precedence: Int = -1) {
    data object Sum : Signs(Actions.Sign("="), 13)
    data object Delete : Signs(Actions.Sign("C"))
    data object Brackets : Signs(Actions.Sign("( )"),1)
    data object Percentage : Signs(Actions.Sign("%"),4)
    data object ClearAll : Signs(Actions.Sign("AC"))
    data object Plus : Signs(Actions.Sign("+"),5)
    data object Minus : Signs(Actions.Sign("-"),5)
    data object Divide : Signs(Actions.Sign("/"),4)
    data object Multiply : Signs(Actions.Sign("*"),4)
    data object ChangeAdditionNegative : Signs(Actions.Sign("+/-"))
    data object Dot : Signs(Actions.Sign("."))

    data object Nine : Signs(Actions.Number("9",9))
    data object Eight : Signs(Actions.Number("8",8))
    data object Seven : Signs(Actions.Number("7",7))
    data object Six : Signs(Actions.Number("6",6))
    data object Five : Signs(Actions.Number("5",5))
    data object Four : Signs(Actions.Number("4",4))
    data object Three : Signs(Actions.Number("3",3))
    data object Two : Signs(Actions.Number("2",2))
    data object One : Signs(Actions.Number("1",1))
    data object Zero : Signs(Actions.Number("0",0))

    data class NumberHelper(val helperSign:String="", val helperNumber: Long) : Signs(Actions.Number(helperSign, helperNumber))

    data object NotImplementedYet : Signs(Actions.Sign(sign = ""))

    fun toSign(value:Long): Signs{
        return when (value.toInt()) {
            0 -> Zero
            1 -> One
            2 -> Two
            3 -> Three
            4 -> Four
            5 -> Five
            6 -> Six
            7 -> Seven
            8 -> Eight
            9 -> Nine
            else -> NumberHelper(helperNumber = value)
        }
    }
}