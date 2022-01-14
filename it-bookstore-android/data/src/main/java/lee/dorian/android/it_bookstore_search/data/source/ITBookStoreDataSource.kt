package lee.dorian.android.it_bookstore_search.data.source

import io.reactivex.Single
import lee.dorian.android.it_bookstore_search.data.model.BookDetailsResponse
import lee.dorian.android.it_bookstore_search.data.model.BookSearchResponse

interface ITBookStoreDataSource {

    fun searchBooks(query: String, page: Int): Single<BookSearchResponse>

    fun readBookDetails(isbn13: String): Single<BookDetailsResponse>

}