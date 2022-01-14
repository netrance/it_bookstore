package lee.dorian.android.it_bookstore_search.data.source

import io.reactivex.Single
import lee.dorian.android.it_bookstore_search.data.model.BookDetailsResponse
import lee.dorian.android.it_bookstore_search.data.model.BookSearchResponse
import lee.dorian.android.it_bookstore_search.data.retrofit.ITBookStoreClient

class ITBookStoreRemoteDataSource(private val client: ITBookStoreClient): ITBookStoreDataSource {

    override fun searchBooks(query: String, page: Int): Single<BookSearchResponse> {
        return client.requestBookSearch(query, page)
    }

    override fun readBookDetails(isbn13: String): Single<BookDetailsResponse> {
        return client.requestBookDetails(isbn13)
    }

}