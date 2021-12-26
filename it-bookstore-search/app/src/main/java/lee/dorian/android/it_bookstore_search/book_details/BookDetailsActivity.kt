package lee.dorian.android.it_bookstore_search.book_details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import lee.dorian.android.it_bookstore_search.R
import lee.dorian.android.it_bookstore_search.base.BaseActivity
import lee.dorian.android.it_bookstore_search.databinding.ActivityBookDetailsBinding

class BookDetailsActivity : BaseActivity<ActivityBookDetailsBinding>(R.layout.activity_book_details) {

    companion object {
        const val INTENT_KEY_ISBN13 = "isbn13"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.apply {
            viewModel = ViewModelProvider(this@BookDetailsActivity).get(BookDetailsViewModel::class.java)
            val isbn13 = intent.getStringExtra(INTENT_KEY_ISBN13) ?: ""
            if (!isbn13.isEmpty()) {
                viewModel?.loadBookDetails(isbn13)
            }
        }

        binding.viewModel?.errorMessage?.observe(this) {
            if (!it.isEmpty()) {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }

    }

}