package dk.shantech.myoffer.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dk.shantech.myoffer.data.manager.CurrentLocation
import dk.shantech.myoffer.model.DealerFrontResponseItem

const val NETWORK_PAGE_SIZE = 5
private const val INITIAL_LOAD_SIZE = 0

class DealerListPagingSource(val remoteDataSource: RemoteDataSource, val location: CurrentLocation, val orderBy: String, val types: String) : PagingSource<Int, DealerFrontResponseItem>(){
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DealerFrontResponseItem> {
        val position = params.key ?: INITIAL_LOAD_SIZE
        val offset = if (params.key != null) ((position - 1) * NETWORK_PAGE_SIZE) else INITIAL_LOAD_SIZE
        return try {
            val response = remoteDataSource.getAllDealers(
                latitude = location.latitude,
                longitude = location.longitude,
                radius = location.radius,
                limit = params.loadSize,
                orderBy = orderBy,
                types = types,
                offset = offset
            )

            val data: List<DealerFrontResponseItem> = if (response.isSuccessful) {
                response.body() ?: emptyList()
            } else {
                emptyList()
            }

            val nextKey = if (data.isNullOrEmpty()) {
                null
            } else {
                // initial load size = 3 * NETWORK_PAGE_SIZE
                // ensure we're not requesting duplicating items, at the 2nd request
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }
            LoadResult.Page(
                data = data,
                prevKey = null, // Only paging forward.
                // assume that if a full page is not loaded, that means the end of the data
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, DealerFrontResponseItem>): Int? {
        return null
    }

}