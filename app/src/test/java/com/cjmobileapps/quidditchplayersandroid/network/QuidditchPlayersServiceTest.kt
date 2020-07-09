package com.cjmobileapps.quidditchplayersandroid.network

import com.cjmobileapps.quidditchplayersandroid.BaseTest
import com.cjmobileapps.quidditchplayersandroid.network.models.Player
import com.cjmobileapps.quidditchplayersandroid.network.models.Position
import com.cjmobileapps.quidditchplayersandroid.network.models.Status
import com.cjmobileapps.quidditchplayersandroid.network.models.service.QuidditchPlayersService
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Flowable
import io.reactivex.Single
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class QuidditchPlayersServiceTest: BaseTest() {

    @Mock
    lateinit var mockQuidditchPlayersApi: QuidditchPlayersApi

    @Mock
    lateinit var mockWebSocketRepository: WebSocketRepository

    private lateinit var quidditchPlayersService: QuidditchPlayersService

    //TODO move these mocks
    private val mockPlayers = listOf(
            Player(
                    id = 3,
                    firstName = "Harry",
                    lastName = "Potter",
                    yearsPlayed = listOf(1991,
                            1992,
                            1993,
                            1994,
                            1995,
                            1996,
                            1997),
                    favoriteSubject = "Defense Against The Dark Arts",
                    position = 4,
                    imageUrl = "https://cjmobileappsimages.s3.us-east-2.amazonaws.com/Quidditch+Images/harry+potter.jpg"
            ),
            Player(
                    id = 6,
                    firstName = "Fred",
                    lastName = "Weasley",
                    yearsPlayed = listOf(1990,
                            1991,
                            1992,
                            1993,
                            1994,
                            1995,
                            1996),
                    favoriteSubject = "Charms",
                    position = 2,
                    imageUrl = "https://cjmobileappsimages.s3.us-east-2.amazonaws.com/Quidditch+Images/fred+weasley.jpg"
            ),
            Player(
                    id = 6,
                    firstName = "George",
                    lastName = "Weasley",
                    yearsPlayed = listOf(1990,
                            1991,
                            1992,
                            1993,
                            1994,
                            1995,
                            1996),
                    favoriteSubject = "Charms",
                    position = 2,
                    imageUrl = "https://cjmobileappsimages.s3.us-east-2.amazonaws.com/Quidditch+Images/george+weasley.jpg"
            )
    )

    private val mockPositions = listOf(
            Position(
                    id = 1,
                    positionName = "Chaser"
            ),
            Position(
                    id = 2,
                    positionName = "Beater"
            ),
            Position(
                    id = 3,
                    positionName = "Keeper"
            ),
            Position(
                    id = 4,
                    positionName = "Seeker"
            )
    )

    private val mockStatus = Status(
            id = 2,
            status = "Alicia Spinnet is dueling a Slytherin 🐍"
    )

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
        val status  = quidditchPlayersService.getStatuses()

        // Verify
        assertEquals(status.blockingFirst(), Flowable.just(mockStatus).blockingFirst())
    }

    @Test
    fun endStatuses_whenServiceEndStatusesCalled() {

        // When
        whenever(mockWebSocketRepository.endStatusUpdates()).thenReturn(Single.just(true))

        // Then
        val isSuccess  = quidditchPlayersService.endStatusUpdates()

        // Verify
        assertEquals(isSuccess.blockingGet(), true)
    }
}
