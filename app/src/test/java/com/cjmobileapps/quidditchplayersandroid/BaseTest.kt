package com.cjmobileapps.quidditchplayersandroid

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Rule
import org.mockito.Mockito
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

abstract class BaseTest {

    //Use this any() instead of the directly using Mockito.any()
    fun <T> any(): T {
        Mockito.any<T>()
        return null as T
    }

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    companion object {

        private val immediate = object : Scheduler() {
            override fun scheduleDirect(run: Runnable,
                                        delay: Long, unit: TimeUnit): Disposable {
                return super.scheduleDirect(run, 0, unit)
            }

            override fun createWorker(): Scheduler.Worker {
                return ExecutorScheduler.ExecutorWorker(
                        Executor { it.run() })
            }
        }

        @JvmStatic
        @BeforeClass
        fun before() {
            RxJavaPlugins.setInitIoSchedulerHandler { immediate }
            RxJavaPlugins.setInitComputationSchedulerHandler { immediate }
            RxJavaPlugins.setInitNewThreadSchedulerHandler { immediate }
            RxJavaPlugins.setInitSingleSchedulerHandler { immediate }
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediate }
        }

        @JvmStatic
        @AfterClass
        fun after() {
            RxAndroidPlugins.reset()
            RxJavaPlugins.reset()
        }
    }
}
