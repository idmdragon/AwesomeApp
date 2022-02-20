package com.idmdragon.feature.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.idmdragon.domain.model.Pexels
import com.idmdragon.domain.usecase.PexelsUsecase
import com.idmdragon.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private var params: Int = 0

    @get:Rule
    val instantExecutor = InstantTaskExecutorRule()


    private val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        viewModel = DetailViewModel(usecase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Mock
    private lateinit var usecase: PexelsUsecase

    @Mock
    private lateinit var fakePexels: Pexels

    private val flowUser: Flow<Resource<Pexels>> = flow { Resource.Success(fakePexels) }

    @Mock
    private lateinit var observerPexels: Observer<Resource<Pexels>>

    @Test
    fun `verify get detail success`() {

        `when`(usecase.getPexelsById(params)).thenReturn(flowUser)
        viewModel.getPexelsById(params).observeForever(observerPexels)

        verify(usecase).getPexelsById(params)
    }

}