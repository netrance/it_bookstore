package lee.dorian.android.it_bookstore_search.data.model

import lee.dorian.android.it_bookstore_search.domain.model.Book
import java.util.*

data class BookEntity(
    val title: String,
    val subtitle: String,
    val isbn13: String,
    val price: String,
    val image: String,
    val url: String
) {

    object Mapper {
        fun toBookFrom(bookEntity: BookEntity): Book {
            return Book(
                bookEntity.title,
                bookEntity.subtitle,
                bookEntity.isbn13,
                bookEntity.price,
                bookEntity.image,
                bookEntity.url
            )
        }

        fun toBookEntityFrom(book: Book): BookEntity {
            return BookEntity(
                book.title,
                book.subtitle,
                book.isbn13,
                book.price,
                book.image,
                book.url
            )
        }

        fun toBookListFrom(bookEntityList: List<BookEntity>): List<Book> {
            val bookList = LinkedList<Book>()

            for (bookEntity in bookEntityList) {
                val book = toBookFrom(bookEntity)
                bookList.add(book)
            }

            return bookList
        }
    }

}
