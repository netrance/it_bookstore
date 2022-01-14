package lee.dorian.android.it_bookstore_search.domain.usecase

import io.reactivex.Single
import lee.dorian.android.it_bookstore_search.domain.model.BookDetails
import lee.dorian.android.it_bookstore_search.domain.repository.ITBookStoreRepository

class RequestBookDetailsUseCase(val repository: ITBookStoreRepository) {

    fun run(isbn13: String): Single<BookDetails> {
        return repository.requestBookDetails(isbn13)
    }

}