package dk.shantech.myoffer.data

import dk.shantech.myoffer.model.DealerFrontResponse
import retrofit2.Response
import retrofit2.http.GET

interface DealerService {

    @GET("v2/dealerfront")
    suspend fun getAllDealers(): Response<DealerFrontResponse>

}