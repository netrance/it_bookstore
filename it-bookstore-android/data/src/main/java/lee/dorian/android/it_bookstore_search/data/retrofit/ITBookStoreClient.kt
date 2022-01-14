package lee.dorian.android.it_bookstore_search.data.retrofit

import io.reactivex.Single
import lee.dorian.android.it_bookstore_search.data.model.BookDetailsResponse
import lee.dorian.android.it_bookstore_search.data.model.BookSearchResponse
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ITBookStoreClient {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    private val client = retrofit.create(ITBookStoreService::class.java)

    companion object {
        const val BASE_URL = "https://api.itbook.store/1.0/"
    }

    fun requestBookSearch(query: String, page: Int = 1): Single<BookSearchResponse> {
        return client.requestBookSearch(query, page.toString())
    }

    fun requestBookDetails(isbn13: String): Single<BookDetailsResponse> {
        return client.requestBookDetails(isbn13)
    }
}