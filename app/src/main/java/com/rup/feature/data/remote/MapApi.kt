package com.rup.feature.data.remote

import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MapApi {
    @POST("/v1/promises/{promiseId}/locations")
    fun promisesLocations(
        @Path(value = "promiseId") promiseId: String
    )
}