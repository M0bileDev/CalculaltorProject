package com.example.calculaltorproject.domain.model

sealed interface InputErrors {
    data object SignAlreadyUsed : InputErrors
    data object UnsupportedLastSign : InputErrors
    data object NothingToDelete : InputErrors
    data object MoreThanFifteenSameDigits : InputErrors
    data object NoValueOnWhichCalculationsCanBeMade: InputErrors
}