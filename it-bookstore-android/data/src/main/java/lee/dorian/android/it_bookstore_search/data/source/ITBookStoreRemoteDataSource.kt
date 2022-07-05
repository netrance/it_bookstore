package lee.dorian.android.it_bookstore_search.data.source

import io.reactivex.Single
import lee.dorian.android.it_bookstore_search.data.model.BookDetailsResponse
import lee.dorian.android.it_bookstore_search.data.model.BookSearchResponse
import lee.dorian.android.it_bookstore_search.data.retrofit.ITBookStoreClient

class ITBookStoreRemoteDataSource: ITBookStoreDataSource {

    override fun searchBooks(query: String, page: Int): Single<BookSearchResponse> {
        return ITBookStoreClient.apiService.requestBookSearch(query, "$page")
    }

    override fun readBookDetails(isbn13: String): Single<BookDetailsResponse> {
        return ITBookStoreClient.apiService.requestBookDetails(isbn13)
    }

}