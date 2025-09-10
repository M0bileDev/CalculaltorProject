package com.example.calculaltorproject.framework

import androidx.lifecycle.ViewModel
import com.example.calculaltorproject.domain.ext.previewComposer
import com.example.calculaltorproject.domain.ext.signComposer
import com.example.calculaltorproject.domain.ext.textComposer
import com.example.calculaltorproject.domain.model.Signs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainActivityViewModel : ViewModel() {

    private val _signsInUse = mutableListOf<Signs>()
    val signs: List<Signs> get() = _signsInUse.toList()

    private val _display = MutableStateFlow("")
    val display get() = _display.asStateFlow()

    private val _preview = MutableStateFlow("")
    val preview get() = _preview.asStateFlow()

    fun onDeleteClicked() {
        _signsInUse.signComposer(
            Signs.Delete,
            onSignCompleted = ::onSignCompleted,
            onSignError = {},
            onError = {})
    }

    fun onActionChanged(sign: Signs) {
        when (sign) {
            Signs.NotImplementedYet -> {
                println("Not implemented yet")
            }

            else -> {
                _signsInUse.signComposer(
                    sign,
                    onSignCompleted = ::onSignCompleted,
                    onSignError = {

                    },
                    onError = {

                    })
            }
        }
    }

    private fun onSignCompleted() {
        _signsInUse.textComposer(onTextCompleted = { newText ->
            _display.value = newText
        })

        _signsInUse.toMutableList()
            .previewComposer(onPreviewCompleted = { newPreview ->
                _preview.value = newPreview
            })
    }

}