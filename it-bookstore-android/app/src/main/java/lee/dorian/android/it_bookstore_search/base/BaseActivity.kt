package lee.dorian.android.it_bookstore_search.base

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

abstract class BaseActivity<T1: ViewDataBinding, T2: BaseViewModel>(@LayoutRes private val layoutResId: Int) : AppCompatActivity() {

    protected lateinit var binding: T1
    protected abstract val viewModel: T2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResId)
        binding.lifecycleOwner = this
    }

    protected val defaultErrorMessageObserver = Observer<String> {
        if (!it.isEmpty()) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

}