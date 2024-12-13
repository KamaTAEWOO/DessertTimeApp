package com.desserttime.domain.model

data class LikeData(
    val icLikeProfile: Int,
    val nickName: Int,
    val date: Int,
    val likeCount: Int,
    val title: Int,
    val score: Int,
    val likePicture: Int,
    val content: Int,
    val materialArr: List<Int>
)
