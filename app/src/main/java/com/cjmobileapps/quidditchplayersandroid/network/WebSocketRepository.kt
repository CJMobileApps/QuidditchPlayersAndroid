package com.cjmobileapps.quidditchplayersandroid.network

import com.cjmobileapps.quidditchplayersandroid.network.models.Status
import com.cjmobileapps.quidditchplayersandroid.network.util.ResponseWrapper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Single
import okhttp3.*
import timber.log.Timber

class WebSocketRepository(private val client: OkHttpClient, private val url: String) : WebSocketRepositoryImpl {

    private var webSocket: WebSocket? = null
    private val CLOSE_NORMAL = 1000
    private val tag = WebSocketRepository::class.java.simpleName

    override fun getStatuses(): Flowable<Status> {
        return Flowable.create<Status>({ emitter ->
            connectToStatuses(object : StatusListener {
                override fun onStatus(status: Status) {
                    emitter.onNext(status)
                }
            })
        }, BackpressureStrategy.LATEST)
    }

    private fun connectToStatuses(statusListener: StatusListener) {
        if (webSocket == null) {
            webSocket = client.newWebSocket(Request.Builder().url(url).build(), object : WebSocketListener() {

                override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                    Timber.tag(tag).e("WebSocket closed: $reason")
                    super.onClosed(webSocket, code, reason)
                }

                override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                    Timber.tag(tag).e("WebSocket failed throwable: $t")
                    Timber.tag(tag).e("WebSocket failed response: $response")
                    super.onFailure(webSocket, t, response)
                }

                override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                    Timber.tag(tag).e("WebSocket closing: $reason")
                    super.onClosing(webSocket, code, reason)
                }

                override fun onMessage(webSocket: WebSocket, message: String) {

                    val type = object : TypeToken<ResponseWrapper<Status>>() {}.type
                    val responseStatus: ResponseWrapper<Status> = Gson().fromJson(message, type)
                    val status = responseStatus.data

                    if (status == null) {
                        Timber.tag(tag).e("WebSocket onMessage: status null")
                        return
                    }

                    statusListener.onStatus(status)
                }
            })
        }
    }

    private fun disconnectFromStatuses(): Boolean {
        webSocket?.close(CLOSE_NORMAL, "Disconnect from statuses")
        return true
    }

    override fun endStatusUpdates(): Single<Boolean> {
        return Single.create<Boolean> { emitter ->
            emitter.onSuccess(disconnectFromStatuses())
        }
    }

    interface StatusListener {
        fun onStatus(status: Status)
    }
}
