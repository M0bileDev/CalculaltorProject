package com.example.calculaltorproject.framework

import com.example.calculaltorproject.FastTest
import com.example.calculaltorproject.domain.model.Signs
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.experimental.categories.Category

@Category(FastTest::class)
class MainActivityViewModelTest {

    lateinit var viewModel: MainActivityViewModel

    @Before
    fun setup(){
        viewModel = MainActivityViewModel()
    }

    @Test
    fun givenViewModel_whenOnActionChangedNumberAdded_thenSignsListIsGreaterByNumber(){
        //given view model

        //when onActionChanged Number One is added
        viewModel.onActionChanged(Signs.One)

        //then
        assertEquals(viewModel.signs.size, 1)
        assertEquals(viewModel.signs.last(), Signs.One)
    }

    @Test
    fun givenViewModel_whenSignsListHasValueAndOnDeleteClicked_thenSignsListIsReducedByOneSign(){
        //given view model

        //first we have to populate the signs list
        viewModel.onActionChanged(Signs.One)

        //when onDeleteClicked than list is reduced
        viewModel.onDeleteClicked()

        //then list size is 0
        assertEquals(viewModel.signs.size, 0)
    }

    @Test
    fun givenViewModel_whenOnActionChangedNumberAdded_thenDisplayContainsNumberValue(){
        //given view model

        //when onActionChanged 1
        viewModel.onActionChanged(Signs.One)


        //then display value is 1
        assertEquals(viewModel.display.value, "1")
    }

    @Test
    fun givenViewModel_whenSignsListHasValueAndOnDeleteClicked_thenDisplayIsEmpty(){
        //given view model

        //first we have to populate the signs list
        viewModel.onActionChanged(Signs.One)

        //when
        viewModel.onDeleteClicked()

        //then display is empty
        assertEquals(viewModel.display.value, "")
    }

    @Test
    fun givenViewModel_whenOnActionChanged_thenPreviewHasRightValue(){
        //given view model

        //when
        viewModel.onActionChanged(Signs.One)
        viewModel.onActionChanged(Signs.Plus)
        viewModel.onActionChanged(Signs.One)

        //then display is empty
        assertEquals(viewModel.preview.value, "2.0")
    }


}