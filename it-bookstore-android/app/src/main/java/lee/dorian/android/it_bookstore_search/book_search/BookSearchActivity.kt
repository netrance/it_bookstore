package lee.dorian.android.it_bookstore_search.book_search

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import lee.dorian.android.it_bookstore_search.R
import lee.dorian.android.it_bookstore_search.base.BaseActivity
import lee.dorian.android.it_bookstore_search.book_details.BookDetailsActivity
import lee.dorian.android.it_bookstore_search.databinding.ActivityBookSearchBinding
import lee.dorian.android.it_bookstore_search.domain.model.Book

class BookSearchActivity : BaseActivity<ActivityBookSearchBinding>(R.layout.activity_book_search) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.apply {
            viewModel = ViewModelProvider(this@BookSearchActivity).get(BookSearchViewModel::class.java)
            btnSearch.setOnClickListener(btnSearchClickListener)
            rvBookList.layoutManager = LinearLayoutManager(this@BookSearchActivity)
            rvBookList.addOnScrollListener(bookListScrollListener)
            rvBookList.adapter = BookListAdapter(bookItemClickListener)
        }

        binding.viewModel?.errorMessage?.observe(this) {
            if (!it.isEmpty()) {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    val btnSearchClickListener = View.OnClickListener {
        binding.viewModel?.searchBooks(binding.etQuery.text.toString())
    }

    val bookItemClickListener = object: BookListAdapter.OnBookItemClickListener {
        override fun onClick(v: View, book: Book) {
            val intent = Intent(this@BookSearchActivity, BookDetailsActivity::class.java).apply {
                putExtra(BookDetailsActivity.INTENT_KEY_ISBN13, book.isbn13)
            }
            startActivity(intent)
        }
    }

    private val bookListScrollListener: RecyclerView.OnScrollListener =
        object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val bookListSize = binding.viewModel?.bookList?.value?.size ?: 0
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                if (layoutManager.findLastCompletelyVisibleItemPosition() == bookListSize - 1) {
                    binding.viewModel?.appendBooks()
                }
            }
        }
}