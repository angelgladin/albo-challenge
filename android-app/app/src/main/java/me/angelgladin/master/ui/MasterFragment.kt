package me.angelgladin.master.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import me.angelgladin.R
import me.angelgladin.data.Result
import me.angelgladin.databinding.MasterFragmentBinding

@AndroidEntryPoint
class MasterFragment : Fragment() {

    private val viewModel: MasterViewModel by navGraphViewModels(R.id.nav_graph) {
        defaultViewModelProviderFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = MasterFragmentBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = BeerAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager= LinearLayoutManager(context)

        subscribeUi(binding, adapter)
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun subscribeUi(binding: MasterFragmentBinding, adapter: BeerAdapter) {
        viewModel.beers.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Result.Status.SUCCESS -> {
                    Log.e("ssss", it.toString())
                    it.data?.let { adapter.submitList(it) }
                }
                Result.Status.ERROR -> {
                    Log.e("eeee", it.toString())

                }
                Result.Status.LOADING -> {
                    Log.e("llll", it.toString())


                }
            }
        })

    }


}