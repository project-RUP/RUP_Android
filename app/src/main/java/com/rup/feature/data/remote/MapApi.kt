package com.rup.feature.data.remote

import com.google.gson.JsonElement
import com.rup.feature.data.remote.dto.map.MapApiPram
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MapApi {
    @POST("/v1/promises/{promiseId}/locations")
    suspend fun promisesLocations(
        @Path(value = "promiseId") promiseId: String,
        @Body mapApiPram: MapApiPram
    ): JsonElement
}