package com.mayurg

import com.google.gson.Gson
import com.mayurg.routes.createRoomRoute
import com.mayurg.routes.gameWebSocketRoute
import com.mayurg.routes.getRoomsRoute
import com.mayurg.routes.joinRoomRoute
import com.mayurg.session.DrawingSession
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.routing.*
import io.ktor.sessions.*
import io.ktor.util.*
import io.ktor.websocket.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

val server = DrawingServer()
val gson = Gson()

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(Sessions) {
        cookie<DrawingSession>("SESSION")
    }
    intercept(ApplicationCallPipeline.Features){
        if (call.sessions.get<DrawingSession>() == null){
            val clientId = call.parameters["client_id"] ?: ""
            call.sessions.set(DrawingSession(clientId, generateNonce()))
        }
    }
    install(ContentNegotiation) {
        gson {
        }
    }
    install(CallLogging)
    install(WebSockets)
    install(Routing){
        createRoomRoute()
        getRoomsRoute()
        joinRoomRoute()
        gameWebSocketRoute()
    }
}

