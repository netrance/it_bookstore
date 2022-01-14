package lee.dorian.android.it_bookstore_search.data.retrofit

import io.reactivex.Single
import lee.dorian.android.it_bookstore_search.data.model.BookDetailsResponse
import lee.dorian.android.it_bookstore_search.data.model.BookSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ITBookStoreService {

    // 도서 검색 API 실행
    @GET("search/{query}/{page}")
    fun requestBookSearch(
        @Path("query") query: String,
        @Path("page") page: String
    ): Single<BookSearchResponse>

    // 도서 상세보기 API 실행
    @GET("books/{isbn13}")
    fun requestBookDetails(
        @Path("isbn13") page: String
    ): Single<BookDetailsResponse>

}