package com.idmdragon.data.source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.idmdragon.data.source.remote.response.PagingDataResponse
import retrofit2.HttpException
import java.io.IOException

@ExperimentalPagingApi
abstract class PagingMediator<ResultType : Any, RequestType> : RemoteMediator<Int, ResultType>() {

    private var currentPage = 1

    override suspend fun load(
        loadType: LoadType, state: PagingState<Int, ResultType>
    ): MediatorResult {

        return try {
            val response = createCall(currentPage)
            val isEndOfList = response.next_page.isEmpty()
            if (response.photos.isEmpty()){
                MediatorResult.Error(Exception())
            }else{
                currentPage++
                saveCallResult(response.photos)
                MediatorResult.Success(endOfPaginationReached = isEndOfList)
            }

        } catch (exception: IOException) {
            MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            MediatorResult.Error(exception)
        }
    }

    abstract suspend fun createCall(page: Int): PagingDataResponse<RequestType>

    abstract suspend fun saveCallResult(response: List<RequestType>)

}