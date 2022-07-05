package lee.dorian.android.it_bookstore_search.data.retrofit

import io.reactivex.Single
import lee.dorian.android.it_bookstore_search.data.model.BookDetailsResponse
import lee.dorian.android.it_bookstore_search.data.model.BookSearchResponse
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ITBookStoreClient {

    const val BASE_URL = "https://api.itbook.store/1.0/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    val apiService = retrofit.create(ITBookStoreService::class.java)

}