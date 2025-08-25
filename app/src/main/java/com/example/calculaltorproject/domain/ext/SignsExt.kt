package com.example.calculaltorproject.domain.ext

import com.example.calculaltorproject.domain.model.Actions
import com.example.calculaltorproject.domain.model.Signs

fun Signs.toVisualRepresentation(): String = when (val action = this.action) {
    is Actions.Sign -> action.sign
    is Actions.Number -> action.number
}