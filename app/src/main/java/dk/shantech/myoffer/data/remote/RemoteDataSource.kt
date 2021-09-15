package dk.shantech.myoffer.data.remote

import dk.shantech.myoffer.data.DealerService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val dealerService: DealerService) {

    suspend fun getAllDealers() = dealerService.getAllDealers()

}