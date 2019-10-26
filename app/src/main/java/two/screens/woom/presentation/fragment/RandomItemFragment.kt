package two.screens.woom.presentation.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import two.screens.woom.R
import two.screens.woom.presentation.viewmodel.RandomListViewModel
import kotlinx.android.synthetic.main.fragment_random_item.*

class RandomItemFragment : Fragment() {

    private val viewModel by sharedViewModel<RandomListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_random_item, container, false)

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = viewModel.randomItemSelected

        val activity = activity as AppCompatActivity
        activity.setSupportActionBar(toolbar)
        activity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        Glide.with(context!!).load(args.picture.large).centerCrop().into(imageView_picture)


        textView_gender.text = args.gender
        textView_name.text = "${args.name.first} ${args.login.username}"
        textView_userLocation.text = "${args.location.street.name}, ${args.location.city}, ${args.location.title}"
        textView_registeredDate.text = args.registered.number
        textView_mail.text = args.email

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> findNavController().navigateUp()
        }
        return super.onOptionsItemSelected(item)
    }

}