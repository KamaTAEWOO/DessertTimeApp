package com.desserttime.category

import androidx.lifecycle.viewModelScope
import com.desserttime.core.base.BaseEvent
import com.desserttime.core.base.BaseState
import com.desserttime.core.base.BaseViewModel
import com.desserttime.domain.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
) : BaseViewModel<CategoryState, CategoryEvent>(
    initialState = CategoryState()
) {
    private val TAG = "CategoryViewModel::"
    override fun reduceState(currentState: CategoryState, event: CategoryEvent): CategoryState =
        when (event) {
            is CategoryEvent.RequestCategoryData -> {
                currentState.copy(allCategory = event.allCategory)
            }

            else -> currentState
        }

    // category data 받아오기
    fun requestCategoryData() {
        categoryRepository.requestAllCategories()
            .onEach {
                // Log 찍기
                Timber.i("$TAG requestCategoryData: $it")
            }
            .catch {
                // Error 처리
                Timber.e("$TAG $it")
            }
            .launchIn(viewModelScope)
    }
}
