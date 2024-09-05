package com.desserttime.domain.model

//object LikeData {
//    val icLikeProfile: Int = 0 //R.drawable.ic_like_profile
//    val nickName: Int = 0 // R.string.txt_like_nickname
//    val date: Int = 0 // R.string.txt_like_date
//    val likeCount: Int = 0 // R.string.txt_like_count
//    val title: Int = 0 // R.string.txt_like_title
//    const val score: Int = 3
//    val likePicture: Int = 0 //R.drawable.ic_like_picture
//    val content: Int = 0 // R.string.txt_like_content
//    val materialArr = listOf(
//        0, // R.string.txt_review_write_material_selection_2,
//        0, // R.string.txt_review_write_material_selection_6,
//        0, // R.string.txt_review_write_material_selection_2,
//        0, // R.string.txt_review_write_material_selection_6,
//        0, // R.string.txt_review_write_material_selection_2,
//        0 // R.string.txt_review_write_material_selection_6
//    )
//}

data class LikeData (
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


