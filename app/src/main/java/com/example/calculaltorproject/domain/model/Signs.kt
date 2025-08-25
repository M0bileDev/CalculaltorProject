package com.example.calculaltorproject.domain.model

sealed class Signs(val action: Actions) {
    data object Sum : Signs(Actions.Sign("="))
    data object Delete : Signs(Actions.Sign("C"))
    data object Brackets : Signs(Actions.Sign("( )"))
    data object Percentage : Signs(Actions.Sign("%"))
    data object ClearAll : Signs(Actions.Sign("AC"))
    data object Plus : Signs(Actions.Sign("+"))
    data object Minus : Signs(Actions.Sign("-"))
    data object Divide : Signs(Actions.Sign("/"))
    data object Multiply : Signs(Actions.Sign("*"))
    data object ChangeAdditionNegative : Signs(Actions.Sign("+/-"))
    data object Dot : Signs(Actions.Sign("."))

    data object Nine : Signs(Actions.Number("9"))
    data object Eight : Signs(Actions.Number("8"))
    data object Seven : Signs(Actions.Number("7"))
    data object Six : Signs(Actions.Number("6"))
    data object Five : Signs(Actions.Number("5"))
    data object Four : Signs(Actions.Number("4"))
    data object Three : Signs(Actions.Number("3"))
    data object Two : Signs(Actions.Number("2"))
    data object One : Signs(Actions.Number("1"))
    data object Zero : Signs(Actions.Number("0"))

    data object NotImplementedYet : Signs(Actions.Sign(sign = ""))
}