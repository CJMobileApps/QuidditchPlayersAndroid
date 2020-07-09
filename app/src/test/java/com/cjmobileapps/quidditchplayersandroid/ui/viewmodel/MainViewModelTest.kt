package com.cjmobileapps.quidditchplayersandroid.ui.viewmodel

import com.cjmobileapps.quidditchplayersandroid.BaseTest
import com.cjmobileapps.quidditchplayersandroid.network.models.Player
import com.cjmobileapps.quidditchplayersandroid.network.models.Position
import com.cjmobileapps.quidditchplayersandroid.network.models.Status
import com.cjmobileapps.quidditchplayersandroid.network.models.service.QuidditchPlayersService
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Flowable
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import org.junit.*
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.lang.Exception


class MainViewModelTest : BaseTest() {

    @Mock
    lateinit var mockQuidditchPlayersService: QuidditchPlayersService

    private lateinit var mainViewModel: MainViewModel

    private val mockPlayersWithoutPositionName = listOf(
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

    private val mockPlayersWithPositionNames = listOf(
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
                    imageUrl = "https://cjmobileappsimages.s3.us-east-2.amazonaws.com/Quidditch+Images/harry+potter.jpg",
                    positionName = "Seeker"
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
                    imageUrl = "https://cjmobileappsimages.s3.us-east-2.amazonaws.com/Quidditch+Images/fred+weasley.jpg",
                    positionName = "Beater"
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
                    imageUrl = "https://cjmobileappsimages.s3.us-east-2.amazonaws.com/Quidditch+Images/george+weasley.jpg",
                    positionName = "Beater"
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
    fun setUpMainViewModel() {
        MockitoAnnotations.initMocks(this)
        this.mainViewModel = MainViewModel(mockQuidditchPlayersService)
    }

    @Test
    fun getPlayers_returnGetPlayersAndStatuses_Success() {

        // when
        whenever(mockQuidditchPlayersService.getPlayers()).thenReturn(Single.just(mockPlayersWithoutPositionName))
        whenever(mockQuidditchPlayersService.getPositions()).thenReturn(Single.just(mockPositions))
        whenever(mockQuidditchPlayersService.getStatuses()).thenReturn(Flowable.just(mockStatus))

        // then
        mainViewModel.getPlayers()

        // verify
        assertEquals(mockPlayersWithPositionNames, mainViewModel.players.value)
        assertEquals(mockStatus, mainViewModel.status.value)
    }

    @Test
    fun getPlayers_getPlayersThrowError_causingFailure() {

        // when
        whenever(mockQuidditchPlayersService.getPlayers()).thenReturn(Single.error(Exception("exception")))
        whenever(mockQuidditchPlayersService.getPositions()).thenReturn(Single.just(mockPositions))
        whenever(mockQuidditchPlayersService.getStatuses()).thenReturn(Flowable.just(mockStatus))

        // then
        mainViewModel.getPlayers()

        // verify
        assertEquals(null, mainViewModel.players.value)
        assertEquals(mockStatus, mainViewModel.status.value)
    }

    @Test
    fun getPlayers_getPositionsThrowError_causingFailure() {

        // when
        whenever(mockQuidditchPlayersService.getPlayers()).thenReturn(Single.just(mockPlayersWithoutPositionName))
        whenever(mockQuidditchPlayersService.getPositions()).thenReturn(Single.error(Exception("exception")))
        whenever(mockQuidditchPlayersService.getStatuses()).thenReturn(Flowable.just(mockStatus))

        // then
        mainViewModel.getPlayers()

        // verify
        assertEquals(null, mainViewModel.players.value)
        assertEquals(mockStatus, mainViewModel.status.value)
    }

    @Test
    fun getPlayers_getStatusesThrowError_causingFailure() {

        // when
        whenever(mockQuidditchPlayersService.getPlayers()).thenReturn(Single.just(mockPlayersWithoutPositionName))
        whenever(mockQuidditchPlayersService.getPositions()).thenReturn(Single.just(mockPositions))
        whenever(mockQuidditchPlayersService.getStatuses()).thenReturn(Flowable.error(Exception("exception")))

        // then
        mainViewModel.getPlayers()

        // verify
        assertEquals(mockPlayersWithPositionNames, mainViewModel.players.value)
        assertEquals(null, mainViewModel.status.value)
    }
}
