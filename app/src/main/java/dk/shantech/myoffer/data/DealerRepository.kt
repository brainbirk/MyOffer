package dk.shantech.myoffer.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dk.shantech.myoffer.data.manager.LocationManager
import dk.shantech.myoffer.data.remote.BaseApiResponse
import dk.shantech.myoffer.data.remote.DealerListPagingSource
import dk.shantech.myoffer.data.remote.NETWORK_PAGE_SIZE
import dk.shantech.myoffer.data.remote.RemoteDataSource
import dk.shantech.myoffer.model.DealerFrontResponseItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@ActivityRetainedScoped
class DealerRepository @Inject constructor(private val remoteDataSource: RemoteDataSource, private val locationManager: LocationManager) :BaseApiResponse() {

    fun getDealerList(orderBy: String, types: String): Flow<PagingData<DealerFrontResponseItem>> {
        val location = locationManager.currentLocation
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                DealerListPagingSource(remoteDataSource, location, orderBy, types)
            }
        ).flow
    }
}