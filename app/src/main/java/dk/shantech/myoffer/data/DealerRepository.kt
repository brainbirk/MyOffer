package dk.shantech.myoffer.data

import dagger.hilt.android.scopes.ActivityRetainedScoped
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
class DealerRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) :BaseApiResponse() {

    suspend fun getAllDealers(): Flow<NetworkResult<DealerFrontResponse>> {
        return flow {
            emit(safeApiCall {
                remoteDataSource.getAllDealers(
                    latitude = "55.343561",
                    longitude = "12.346233",
                    radius = "20000",
                    limit = 12,
                    orderBy = "name",
                    types = "paged,incito",
                    offset = 0
                )
            })
        }.flowOn(Dispatchers.IO)
    }

}