package dk.shantech.myoffer.data.remote

import dk.shantech.myoffer.model.DealerFrontResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DealerService {

    @GET("v2/dealerfront")
    suspend fun getAllDealers(
        @Query("r_lat") latitude: String,
        @Query("r_lng") longitude: String,
        @Query("r_radius") radius: Int,
        @Query("limit") limit: Int,
        @Query("order_by") orderBy: String,
        @Query("types") types: String,
        @Query("offset") offset: Int
    ): Response<DealerFrontResponse>
}