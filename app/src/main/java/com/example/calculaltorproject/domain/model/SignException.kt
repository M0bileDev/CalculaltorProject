package com.example.calculaltorproject.domain.model

sealed interface SignException {
    data object ExplicitSignException : SignException
}