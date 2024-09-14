package com.desserttime.review

import com.desserttime.core.base.BaseState

data class ReviewState(
    var storeName: String = "",
    var storeMenu: String = ""
) : BaseState
