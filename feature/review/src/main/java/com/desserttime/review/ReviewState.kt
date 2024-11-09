package com.desserttime.review

import com.desserttime.core.base.BaseState

data class ReviewState(
    var storeName: String = "",
    var storeMenu: String = "",
    var storeMaterialList: List<Int> = emptyList(),
    var storeContent: String = "",
    val storeCategoryId: Int = -1,
    var storeScore: Int = -1
) : BaseState
