package me.angelgladin.master.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import me.angelgladin.data.entity.Beer
import me.angelgladin.databinding.ItemBeerBinding

class BeerAdapter :
    PagedListAdapter<Beer, BeerAdapter.ViewHolder>(DiffCallback()) {
    private lateinit var recyclerView: RecyclerView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemBeerBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val beer = getItem(position)
        beer?.let {
            holder.apply {
                bind(
                    {
                        with(beer) {
                            val direction =
                                MasterFragmentDirections.actionMainFragmentToDetailFragment(
                                    name,
                                    image_url,
                                    tagLine,
                                    description,
                                    firstBrewed
                                )
                            it.findNavController().navigate(direction)
                        }
                    },
                    beer
                )
            }
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    inner class ViewHolder(private val binding: ItemBeerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(onClickListener: View.OnClickListener, beerItem: Beer) {
            binding.apply {
                clickListener = onClickListener
                beer = beerItem
                executePendingBindings()
            }
        }
    }

    companion object {
        class DiffCallback : DiffUtil.ItemCallback<Beer>() {
            override fun areItemsTheSame(oldItem: Beer, newItem: Beer): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Beer, newItem: Beer): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }

}
