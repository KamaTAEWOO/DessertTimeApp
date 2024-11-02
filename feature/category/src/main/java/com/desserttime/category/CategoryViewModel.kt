package com.desserttime.category

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.desserttime.core.base.BaseViewModel
import com.desserttime.domain.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

private const val TAG = "CategoryViewModel::"

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
) : BaseViewModel<CategoryState, CategoryEvent>(
    initialState = CategoryState()
) {
    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> get() = _isLoading

    private fun setLoading(loading: Boolean) {
        _isLoading.value = loading
    }

    override fun reduceState(currentState: CategoryState, event: CategoryEvent): CategoryState =
        when (event) {
            is CategoryEvent.RequestCategoryData -> {
                currentState.copy(allCategory = event.allCategory)
            }
            else -> currentState
        }

    // category data 받아오기
    fun requestCategoryData() {
        setLoading(true)

        categoryRepository.requestAllCategories()
            .onEach {
                // Timber.i("$TAG requestCategoryData: $it")
                sendAction(CategoryEvent.RequestCategoryData(it))
            }
            .catch {
                // Error 처리
                Timber.e("$TAG $it")
            }
            .onCompletion {
                setLoading(false)
            }
            .launchIn(viewModelScope)
    }
}
