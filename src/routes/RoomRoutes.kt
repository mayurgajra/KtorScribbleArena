package com.mayurg.routes

import com.mayurg.data.Room
import com.mayurg.data.models.BasicApiResponse
import com.mayurg.data.models.CreateRoomRequest
import com.mayurg.other.Constants.MAX_ROOM_SIZE
import com.mayurg.server
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.http.HttpStatusCode.Companion.BadRequest
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.createRoomRoute() {
    route("/api/createRoom") {
        post {
            val roomRequest = call.receiveOrNull<CreateRoomRequest>()
            if (roomRequest == null) {
                call.respond(BadRequest)
                return@post
            }
            if (server.rooms[roomRequest.name] != null) {
                call.respond(OK, BasicApiResponse(false, "Room already exists."))
                return@post
            }
            if (roomRequest.maxPlayers < 2) {
                call.respond(OK, BasicApiResponse(false, "The minimum room size is 2"))
                return@post
            }

            if (roomRequest.maxPlayers > MAX_ROOM_SIZE) {
                call.respond(OK, BasicApiResponse(false, "The maxim room size is $MAX_ROOM_SIZE"))
                return@post
            }

            val room = Room(roomRequest.name, roomRequest.maxPlayers)

            server.rooms[roomRequest.name] = room
            println("Room created: ${roomRequest.name}")
            call.respond(OK, BasicApiResponse(true))

        }
    }
}