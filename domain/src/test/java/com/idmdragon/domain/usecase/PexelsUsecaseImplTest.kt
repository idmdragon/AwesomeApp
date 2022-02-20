package com.idmdragon.domain.usecase

import com.idmdragon.domain.repository.PexelsRepository
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PexelsUsecaseImplTest(){

    private var params: Int = 0

    @Mock
    private lateinit var repository: PexelsRepository
    private lateinit var pexelsUsecaseImpl: PexelsUsecaseImpl

    @Before
    fun setUp(){
        pexelsUsecaseImpl = PexelsUsecaseImpl(repository)
    }

    @Test
    fun `verify usecase getAllPexels`(){
        pexelsUsecaseImpl.getListPexels()
        verify(repository).getListPexels()
    }

    @Test
    fun `verify usecase getPexelsById`(){
        pexelsUsecaseImpl.getPexelsById(params)
        verify(repository).getPexelsById(params)
    }


}