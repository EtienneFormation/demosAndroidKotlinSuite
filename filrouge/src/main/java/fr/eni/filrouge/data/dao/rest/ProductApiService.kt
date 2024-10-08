package fr.eni.filrouge.data.dao.rest

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApiService {
    @GET("products")
    fun getProducts() : Call<List<ProductApi>>
    @GET("products/{idProduct}")
    fun getProductById(@Path("idProduct") idProduct : Int) : Call<ProductApi>
}