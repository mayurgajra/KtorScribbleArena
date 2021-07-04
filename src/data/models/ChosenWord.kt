package com.mayurg.data.models

import com.mayurg.other.Constants.TYPE_CHOSEN_WORD

data class ChosenWord(
    val chosenWord: String,
    val roomName: String,
): BaseModel(TYPE_CHOSEN_WORD)
