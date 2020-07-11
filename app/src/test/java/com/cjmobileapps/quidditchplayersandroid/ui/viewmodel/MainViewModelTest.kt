package com.cjmobileapps.quidditchplayersandroid.ui.viewmodel

import com.cjmobileapps.quidditchplayersandroid.BaseTest
import com.cjmobileapps.quidditchplayersandroid.fakedata.FakeData
import com.cjmobileapps.quidditchplayersandroid.network.models.Player
import com.cjmobileapps.quidditchplayersandroid.network.service.QuidditchPlayersService
import com.cjmobileapps.quidditchplayersandroid.network.util.ResponseWrapper
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Flowable
import io.reactivex.Single
import org.junit.*
import org.junit.Assert.assertEquals
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.lang.Exception


class MainViewModelTest : BaseTest() {

    @Mock
    lateinit var mockQuidditchPlayersService: QuidditchPlayersService

    private lateinit var mainViewModel: MainViewModel

    private val mockPlayersWithoutPositionName = ResponseWrapper(
            data = FakeData.players,
            statusCode = 200
    )

    private val mockPlayersWithPositionNames: ResponseWrapper<List<Player>> = {
        val players = FakeData.players.toList()
        players[0].positionName = "Seeker"
        players[1].positionName = "Chaser"
        players[2].positionName = "Chaser"
        players[3].positionName = "Beater"
        players[4].positionName = "Beater"
        players[5].positionName = "Chaser"
        players[6].positionName = "Keeper"

        ResponseWrapper(
                data = players,
                statusCode = 200
        )
    }.invoke()



    private val mockPositions = ResponseWrapper(
            data = FakeData.positions,
            statusCode = 200
    )

    private val mockStatus = FakeData.status


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
        assertEquals(mockPlayersWithPositionNames.data, mainViewModel.players.value)
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
        assertEquals(mockPlayersWithPositionNames.data, mainViewModel.players.value)
        assertEquals(null, mainViewModel.status.value)
    }
}
