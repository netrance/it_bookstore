package lee.dorian.android.it_bookstore_search.data.repository

import io.reactivex.Single
import lee.dorian.android.it_bookstore_search.data.source.ITBookStoreRemoteDataSource
import lee.dorian.android.it_bookstore_search.domain.model.Book
import lee.dorian.android.it_bookstore_search.domain.model.BookDetails
import lee.dorian.android.it_bookstore_search.domain.repository.ITBookStoreRepository

class ITBookStoreRepositoryImpl(
    val remoteDataSource: ITBookStoreRemoteDataSource
) : ITBookStoreRepository {

    override fun requestBookSearch(query: String): Single<List<Book>> {
        return requestBookSearch(query, 1)
    }

    override fun requestBookSearch(query: String, page: Int): Single<List<Book>> {
        return remoteDataSource.searchBooks(query, page)
            .map { bookSearchResponse ->
                bookSearchResponse.books.map { bookEntity ->
                    bookEntity.toDTO()
                }
            }
    }

    override fun requestBookDetails(isbn13: String): Single<BookDetails> {
        return remoteDataSource.readBookDetails(isbn13)
            .map { bookDetailsResponse ->
                bookDetailsResponse.toDTO()
            }
    }

}