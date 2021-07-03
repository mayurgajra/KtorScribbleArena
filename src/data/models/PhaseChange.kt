package com.mayurg.data.models

import com.mayurg.data.Room
import com.mayurg.other.Constants.TYPE_PHASE_CHANGE

data class PhaseChange(
    var phase: Room.Phase?,
    var time: Long,
    var drawingPlayer: String? = null
): BaseModel(TYPE_PHASE_CHANGE)
