package lee.dorian.android.it_bookstore_search.book_search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import lee.dorian.android.it_bookstore_search.databinding.LayoutBookBinding
import lee.dorian.android.it_bookstore_search.domain.model.Book

class BookListAdapter(
    val onBookItemClickListener: OnBookItemClickListener
) : RecyclerView.Adapter<BookListAdapter.BookListViewHolder>() {

    val bookList = mutableListOf<Book>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListViewHolder {
        val binding = LayoutBookBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return BookListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookListViewHolder, position: Int) {
        holder.bind(bookList.get(position))
    }

    override fun getItemCount(): Int = bookList.size

    fun setBookList(bookList: List<Book>) {
        this.bookList.clear()
        this.bookList.addAll(bookList)
        notifyDataSetChanged()
    }

    interface OnBookItemClickListener {
        fun onClick(v: View, book: Book)
    }

    inner class BookListViewHolder(
        private val binding: LayoutBookBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(book: Book) {
            binding.book = book
            binding.root.setOnClickListener {
                onBookItemClickListener.onClick(it, book)
            }
        }

    }
}