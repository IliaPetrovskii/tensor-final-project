package com.example.diplomproject.data.network

import com.example.diplomproject.BASE_URL
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface KPApi {
    @Headers("X-API-KEY: 74de9f89-4b87-4eff-a301-e9c24f768104")
    @GET("api/v2.2/films?order=RATING&type=ALL&ratingFrom=0&ratingTo=10&yearFrom=1000&yearTo=3000")
    fun getMoviesByName2(
        @Query("keyword") keyword: String = "Чужой",
        @Query("page") page: Int = 1
    ): Call<ApiResult>

    companion object {

        fun create() : KPApi {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
            return retrofit.create(KPApi::class.java)
        }
    }
}