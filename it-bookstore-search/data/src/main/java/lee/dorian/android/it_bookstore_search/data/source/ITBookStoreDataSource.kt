package lee.dorian.android.it_bookstore_search.data.source

import io.reactivex.Flowable
import lee.dorian.android.it_bookstore_search.data.model.BookDetailsResponse
import lee.dorian.android.it_bookstore_search.data.model.BookSearchResponse

interface ITBookStoreDataSource {

    fun searchBooks(query: String, page: Int): Flowable<BookSearchResponse>

    fun readBookDetails(isbn13: String): Flowable<BookDetailsResponse>

}