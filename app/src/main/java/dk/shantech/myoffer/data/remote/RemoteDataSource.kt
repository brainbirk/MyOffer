package dk.shantech.myoffer.data.remote

import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val dealerService: DealerService) {

    suspend fun getAllDealers(
        latitude: String,
        longitude: String,
        radius: String,
        limit: Int,
        orderBy: String,
        types: String,
        offset: Int
    ) = dealerService.getAllDealers(
        latitude = latitude,
        longitude = longitude,
        radius = radius,
        limit = limit,
        orderBy = orderBy,
        types = types,
        offset = offset
    )

}