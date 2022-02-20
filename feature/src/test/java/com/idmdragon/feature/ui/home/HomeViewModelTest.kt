package com.idmdragon.feature.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.paging.PagingData
import com.idmdragon.domain.model.Pexels
import com.idmdragon.domain.usecase.PexelsUsecase
import com.idmdragon.domain.utils.Resource
import com.idmdragon.feature.ui.detail.DetailViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel

    @get:Rule
    val instantExecutor = InstantTaskExecutorRule()


    private val dispatcher = TestCoroutineDispatcher()


    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Mock
    private lateinit var usecase: PexelsUsecase

    @Mock
    private lateinit var flowPexels: Flow<PagingData<Pexels>>

    @Mock
    private lateinit var observerPexels: Observer<PagingData<Pexels>>

    @Before
    fun setUp() {
        viewModel = HomeViewModel(usecase)
    }

    @Test
    fun `verify getAllPexels`() = runBlockingTest {

        `when`(usecase.getListPexels()).thenReturn(flowPexels)
        viewModel.getAllPexels().observeForever(observerPexels)

        verify(usecase).getListPexels()
    }

}
