package com.mayurg.other

import com.mayurg.data.models.ChatMessage

fun ChatMessage.matchesWord(word: String): Boolean {
    return message.toLowerCase().trim() == word.toLowerCase().trim()
}