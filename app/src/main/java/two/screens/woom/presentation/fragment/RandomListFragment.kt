package two.screens.woom.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_random_list.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import two.screens.woom.R
import two.screens.woom.core.Failure
import two.screens.woom.core.failure
import two.screens.woom.core.observe
import two.screens.woom.domain.model.RandomItem
import two.screens.woom.presentation.adapter.RandomAdapter
import two.screens.woom.presentation.viewmodel.RandomListViewModel
import androidx.recyclerview.widget.RecyclerView

class RandomListFragment : Fragment() {

    private val viewModel by sharedViewModel<RandomListViewModel>()
    private var randomAdapter: RandomAdapter? = null

    private var page: Int = PAGINATION

    companion object {
        const val PAGINATION = 10
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_random_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.let { vm ->
            observe(vm.showRandomList) { showRandomList(it) }
            observe(vm.showProgressBar) { showProgressBar(it) }
            failure(vm.failure) { handleFailure(it) }
        }

        setupRecyclerView()

        viewModel.getRandomList(page)

    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(context)
        recycler_view.layoutManager = layoutManager

        randomAdapter = context?.let { RandomAdapter(it) { item : RandomItem, view: View -> selectionListener(item, view) } }
        recycler_view.adapter = randomAdapter


        recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1)) {
                    page += PAGINATION
                    viewModel.getRandomList(page)
                }
            }
        })
    }

    private fun showRandomList(randomItems: List<RandomItem>?) {
        randomItems?.let { randomAdapter?.setData(it) }
    }

    private fun selectionListener(item : RandomItem, view: View) {

        viewModel.randomItemSelected = item

        val extras = FragmentNavigatorExtras(
            view  to "secondTransitionName"
        )

        /*imageView_picture_male.startAnimationWithOffset(R.anim.bounce, 0)
        constrainLayout_random_list.startAnimationWithOffset(R.anim.fade_out, 100)

        Handler().postDelayed({
            view?.findNavController()?.navigate(R.id.randomItemFragment, null, null, null)
        }, 1000)*/

        this.view?.findNavController()?.navigate(R.id.randomItemFragment, null, null, extras )
    }

    private fun showProgressBar(isLoading: Boolean?) {
        if (isLoading == null) return

        val alpha = if(isLoading) 1.0f else 0.0f
        progress_bar_loading.animate()
            .alpha(alpha)
            .withStartAction { if(isLoading) progress_bar_loading?.let { it.visibility =  View.VISIBLE } }
            .withEndAction { if(!isLoading) progress_bar_loading?.let { it.visibility =  View.GONE } }
            .duration = 1000
    }

    private fun handleFailure(failure: Failure?) = when(failure) {
        is Failure.NetworkConnection -> { notify(view, R.string.all_toast_nointernetconnection) }
        is Failure.ServerError -> { notify(view, R.string.all_toast_unknownbackenderror) }
        else -> {  }
    }

    private fun notify(view: View?, @StringRes message: Int) =
        view?.let { Snackbar.make(it, message, Snackbar.LENGTH_LONG).show() }

}

