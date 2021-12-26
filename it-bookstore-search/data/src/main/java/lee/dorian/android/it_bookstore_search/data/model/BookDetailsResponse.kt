package lee.dorian.android.it_bookstore_search.data.model

import lee.dorian.android.it_bookstore_search.domain.model.BookDetails

data class BookDetailsResponse(
    val error: String,
    val title: String,
    val subtitle: String,
    val authors: String,
    val publisher: String,
    val language: String,
    val isbn10: String,
    val isbn13: String,
    val pages: String,
    val year: String,
    val rating: String,
    val desc: String,
    val price: String,
    val image: String,
    val url: String
) {
    object Mapper {
        fun toBookDetailsFrom(response: BookDetailsResponse): BookDetails {
            return BookDetails(
                response.title,
                response.subtitle,
                response.authors,
                response.publisher,
                response.language,
                response.isbn10,
                response.isbn13,
                response.pages,
                response.year,
                response.rating,
                response.desc,
                response.price,
                response.image,
                response.url
            )
        }
    }
}