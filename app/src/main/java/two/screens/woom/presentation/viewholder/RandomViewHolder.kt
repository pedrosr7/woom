package two.screens.woom.presentation.viewholder

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_random_item_female.view.*
import kotlinx.android.synthetic.main.item_random_item_male.view.*
import kotlinx.android.synthetic.main.item_random_item_male.view.textView_mail
import kotlinx.android.synthetic.main.item_random_item_male.view.textView_name
import kotlinx.android.synthetic.main.item_random_item_male.view.textView_phone
import two.screens.woom.domain.model.RandomItem

class RandomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    @SuppressLint("SetTextI18n")
    fun setData(item: RandomItem, clickListener: (RandomItem, View) -> Unit) = with(itemView) {

        val idImage = if (item.gender == "male") {
            imageView_picture_male
        }else {
            imageView_picture_female
        }

        Glide.with(context!!).load(item.picture.medium).centerCrop().into(idImage)

        textView_name.text = "${item.name.first} ${item.name.last}"
        textView_mail.text = item.email
        textView_phone.text = item.phone

        itemView.setOnClickListener {
            clickListener(item, idImage)
        }
    }

}