package com.cjmobileapps.quidditchplayersandroid.network

import android.util.Log
import com.cjmobileapps.quidditchplayersandroid.network.models.Status
import com.google.gson.Gson
import okhttp3.*

class WebSocketRepository(private val client: OkHttpClient, private val url: String) {

    private var webSocket: WebSocket? = null
    private val CLOSE_NORMAL = 1000

    fun connectToStatuses(statusListener: StatusListener) {
        if (webSocket == null) {
            webSocket = client.newWebSocket(Request.Builder().url(url).build(), object: WebSocketListener() {

                override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                    Log.d("HERE_","onCLosed")
                    super.onClosed(webSocket, code, reason)
                }

                override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                    Log.d("HERE_", "t: " + t.toString())
                    Log.d("HERE_", "response: " + response)
                    super.onFailure(webSocket, t, response)
                }

                override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                    Log.d("HERE_", "reason " + reason)
                    super.onClosing(webSocket, code, reason)
                }

                override fun onMessage(webSocket: WebSocket, message: String) {
                    val status = Gson().fromJson(message, Status::class.java)
                    statusListener.onStatus(status)
                }
            })
        }
    }

    fun disconnectFromStatuses() : Boolean {
        webSocket?.close(CLOSE_NORMAL, "Disconnect from statuses")
        return true
    }

    interface StatusListener {
        fun onStatus(status: Status)
    }
}