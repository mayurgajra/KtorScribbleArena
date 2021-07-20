package com.mayurg.data.models

import com.mayurg.other.Constants.TYPE_CURRENT_ROUND_DRAW_INFO

data class RoundDrawInfo(
    val data: List<String>
) : BaseModel(TYPE_CURRENT_ROUND_DRAW_INFO)
