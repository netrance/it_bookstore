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
    fun toDTO(): BookDetails = BookDetails(
        title,
        subtitle,
        authors,
        publisher,
        language,
        isbn10,
        isbn13,
        pages,
        year,
        rating,
        desc,
        price,
        image,
        url
    )
}