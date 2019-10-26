package two.screens.woom.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import two.screens.woom.R
import two.screens.woom.domain.model.RandomItem
import two.screens.woom.presentation.viewholder.RandomViewHolder

class RandomAdapter(
    private val context: Context,
    private val clickListener: (RandomItem, View) -> Unit)
: RecyclerView.Adapter<RandomViewHolder>() {

    private var listItems: List<RandomItem> = listOf()

    companion object {
        const val MALE = 0
        const val FEMALE = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RandomViewHolder {
        return  when (viewType) {
            MALE -> RandomViewHolder(LayoutInflater.from(context).inflate(R.layout.item_random_item_male, parent, false))
            FEMALE ->  RandomViewHolder(LayoutInflater.from(context).inflate(R.layout.item_random_item_female, parent, false))
            else -> {
                RandomViewHolder(LayoutInflater.from(context).inflate(R.layout.item_random_item_female, parent, false))
            }
        }
    }

    override fun onBindViewHolder(holder: RandomViewHolder, position: Int) {
        holder.setData(listItems[position]) { item, view -> clickListener(item, view) }
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    override fun getItemViewType(position: Int): Int {
        return when  {
            listItems[position].gender == "male" -> MALE
            listItems[position].gender == "female" -> FEMALE
            else -> FEMALE
        }
    }

    fun setData(newData: List<RandomItem>) {

        this.listItems = newData
        notifyDataSetChanged()
    }
}