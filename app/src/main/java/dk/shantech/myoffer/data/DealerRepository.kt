package dk.shantech.myoffer.data

import dagger.hilt.android.scopes.ActivityRetainedScoped
import dk.shantech.myoffer.data.manager.LocationManager
import dk.shantech.myoffer.data.remote.BaseApiResponse
import dk.shantech.myoffer.data.remote.RemoteDataSource
import dk.shantech.myoffer.model.DealerFrontResponse
import dk.shantech.myoffer.model.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class DealerRepository @Inject constructor(private val remoteDataSource: RemoteDataSource, private val locationManager: LocationManager) :BaseApiResponse() {

    suspend fun getDealers(orderBy: String, types: String, limit: Int, offset: Int): Flow<NetworkResult<DealerFrontResponse>> {
        val location = locationManager.currentLocation
        return flow {
            emit(safeApiCall {
                remoteDataSource.getAllDealers(
                    latitude = location.latitude,
                    longitude = location.longitude,
                    radius = location.radius,
                    limit = limit,
                    orderBy = orderBy,
                    types = types,
                    offset = offset
                )
            })
        }.flowOn(Dispatchers.IO)
    }

}