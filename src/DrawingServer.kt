package com.mayurg

import com.mayurg.data.Player
import com.mayurg.data.Room
import java.util.concurrent.ConcurrentHashMap

class DrawingServer {

    val rooms = ConcurrentHashMap<String, Room>()
    val players = ConcurrentHashMap<String, Player>()

    fun playerJoined(player: Player) {
        players[player.clientId] = player
    }

    fun playerLeft(clientId: String,immediatelyDisconnect:Boolean = false){
        val playersRoom = getRoomWithClientId(clientId)
        if (immediatelyDisconnect){
            playersRoom?.removePlayer(clientId)
            players.remove(clientId)
        }
    }

    fun getRoomWithClientId(clientId: String): Room? {
        val filteredRooms = rooms.filterValues { room ->
            room.players.find { player ->
                player.clientId == clientId
            } != null
        }

        return if (filteredRooms.values.isEmpty()) {
            null
        } else {
            filteredRooms.values.toList()[0]
        }
    }

}