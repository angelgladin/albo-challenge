package me.angelgladin.detail.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import me.angelgladin.R
import me.angelgladin.data.entity.Beer
import me.angelgladin.databinding.DetailFragmentBinding

class DetailFragment : Fragment() {

    val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<DetailFragmentBinding>(
            inflater,
            R.layout.detail_fragment,
            container,
            false
        )
        context ?: return binding.root
        setupViews(binding)
        return binding.root
    }

    private fun setupViews(binding: DetailFragmentBinding) {
        binding.apply {
            (activity as AppCompatActivity).supportActionBar!!.title = args.name

            beer = with(args) {
                Beer(id, imageUrl, name, tagLine, description, firstBrewed)
            }
        }
    }


}