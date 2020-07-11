package com.cjmobileapps.quidditchplayersandroid.network

import com.cjmobileapps.quidditchplayersandroid.BaseTest
import com.cjmobileapps.quidditchplayersandroid.fakedata.FakeData
import com.cjmobileapps.quidditchplayersandroid.network.service.QuidditchPlayersService
import com.cjmobileapps.quidditchplayersandroid.network.util.ResponseWrapper
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Flowable
import io.reactivex.Single
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class QuidditchPlayersServiceTest : BaseTest() {

    @Mock
    lateinit var mockQuidditchPlayersApi: QuidditchPlayersApi

    @Mock
    lateinit var mockWebSocketRepository: WebSocketRepository

    private lateinit var quidditchPlayersService: QuidditchPlayersService

    private val mockPlayers = ResponseWrapper(
            data = FakeData.players,
            statusCode = 200
    )

    private val mockPositions = ResponseWrapper(
            data = FakeData.positions,
            statusCode = 200
    )

    private val mockStatus = FakeData.status

    @Before
    fun setUpQuidditchPlayersService() {
        MockitoAnnotations.initMocks(this)
        this.quidditchPlayersService = QuidditchPlayersService(
                quidditchPlayersApi = mockQuidditchPlayersApi,
                webSocketRepository = mockWebSocketRepository)
    }

    @Test
    fun getPlayers_whenServiceGetPlayersCalled() {

        // When
        whenever(mockQuidditchPlayersApi.getPlayers()).thenReturn(Single.just(mockPlayers))

        // Then
        val players = quidditchPlayersService.getPlayers()

        // Verify
        assertEquals(players.blockingGet(), Single.just(mockPlayers).blockingGet())
    }

    @Test
    fun getPositions_whenServiceGetPositionsCalled() {

        // When
        whenever(mockQuidditchPlayersApi.getPositions()).thenReturn(Single.just(mockPositions))

        // Then
        val positions = quidditchPlayersService.getPositions()

        // Verify
        assertEquals(positions.blockingGet(), Single.just(mockPositions).blockingGet())
    }

    @Test
    fun getStatuses_whenServiceGetStatusesCalled() {

        // When
        whenever(mockWebSocketRepository.getStatuses()).thenReturn(Flowable.just(mockStatus))

        // Then
        val status = quidditchPlayersService.getStatuses()

        // Verify
        assertEquals(status.blockingFirst(), Flowable.just(mockStatus).blockingFirst())
    }

    @Test
    fun endStatuses_whenServiceEndStatusesCalled() {

        // When
        whenever(mockWebSocketRepository.endStatusUpdates()).thenReturn(Single.just(true))

        // Then
        val isSuccess = quidditchPlayersService.endStatusUpdates()

        // Verify
        assertEquals(isSuccess.blockingGet(), true)
    }
}
