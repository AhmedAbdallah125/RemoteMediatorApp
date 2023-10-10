package com.example.remotemediatorapp.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.remotemediatorapp.data.local.BeverageDatabase
import com.example.remotemediatorapp.data.local.BeverageEntity
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class BeverageRemoteMediator(
    val beverageApi: BeverageAPI,
    val beverageDb: BeverageDatabase
) : RemoteMediator<Int, BeverageEntity>() {

    // The load() method is responsible for updating the backing dataset and invalidating the PagingSource.
    // Some libraries that support paging (like Room) will
    // automatically handle invalidating PagingSource objects that they implement
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, BeverageEntity>
    ): MediatorResult {
        return try {
            // get next page to load
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        1
                    } else {
                        // calaualte current page
                        (lastItem.id / state.config.pageSize) + 1
                    }
                }
            }

            delay(2000L)
            // request from api
            val beverages =
                beverageApi.getBeverages(page = loadKey, pageCount = state.config.pageSize)

            // handle multiple operation and ensure that all is done or no one will be done
            beverageDb.withTransaction {
                // perform one after the other
                if (loadType == LoadType.REFRESH)
                    beverageDb.dao.clearAll()

                // Insert new users into database, which invalidates the
                // current PagingData, allowing Paging to present the updates
                // in the DB.
                beverageDb.dao.upsertAll(beverages.map { it.toBeverageEntity() })
            }
            MediatorResult.Success(endOfPaginationReached = beverages.isEmpty())

        }
        //The most common cause due to which an IOException is thrown is attempting to access a file that does not exist
        // at the specified location. Common exception classes which are derived from IOException base class
        // are EndOfStreamException, FileNotFoundException, DirectoryNotFoundException etc.
        catch (ex: IOException) {
            MediatorResult.Error(ex)
            // Exception for an unexpected, non-2xx HTTP response.
        } catch (ex: HttpException) {
            MediatorResult.Error(ex)
        }
    }
}