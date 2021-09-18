package com.mobiquity.arnab.weather.ui.home

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobiquity.arnab.weather.R
import com.mobiquity.arnab.weather.database.entity.CityEntity
import com.mobiquity.arnab.weather.databinding.FragmentHomeBinding
import com.mobiquity.arnab.weather.ui.adapter.CityListAdapter
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class HomeFragment : Fragment(R.layout.fragment_home) {

    @Inject
    lateinit var homeViewModel: HomeViewModel
    lateinit var binding: FragmentHomeBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        binding.etSearchLocation.clearFocus()

        val rv_city_list: RecyclerView = binding.rvCityList
        rv_city_list.layoutManager = LinearLayoutManager(activity)
        val adapter: CityListAdapter = CityListAdapter(ArrayList<CityEntity>())
        rv_city_list.adapter = adapter
        homeViewModel.observeCityList(binding.etSearchLocation.text.toString()).observe(viewLifecycleOwner, Observer {
            adapter.addData(it)
        })

        binding.etSearchLocation.doAfterTextChanged {
            homeViewModel.observeCityList(it.toString()).observe(viewLifecycleOwner, Observer { adapter.addData(it) })
        }
    }

}