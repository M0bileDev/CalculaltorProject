package com.example.calculaltorproject.domain.model

sealed interface Actions {
    data class Sign(val sign: String) : Actions
    data class Number(val number: String) : Actions

}