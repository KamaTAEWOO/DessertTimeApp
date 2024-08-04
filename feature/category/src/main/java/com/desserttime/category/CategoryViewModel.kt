package com.desserttime.category

import com.desserttime.core.base.BaseEvent
import com.desserttime.core.base.BaseState
import com.desserttime.core.base.BaseViewModel
import com.desserttime.domain.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
) : BaseViewModel<BaseState, BaseEvent>(
    initialState = CategoryState()
) {

    override fun reduceState(currentState: BaseState, event: BaseEvent): BaseState {
        TODO("Not yet implemented")
    }

    // category data 받아오기
    private fun requestCategoryData() {

    }
}
