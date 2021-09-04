package com.mobiquity.arnab.weather.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobiquity.arnab.weather.database.entity.CityEntity
import com.mobiquity.arnab.weather.databinding.FragmentHomeBinding
import com.mobiquity.arnab.weather.ui.adapter.CityListAdapter
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance


class HomeFragment : Fragment(), KodeinAware {

    override val kodein by kodein()
    private val factory: HomeViewModelFactory by instance()

    private lateinit var homeViewModel: HomeViewModel
    lateinit var dataBindingUtil: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        dataBindingUtil = FragmentHomeBinding.inflate(inflater, container, false)
        homeViewModel = ViewModelProviders.of(this, factory).get(HomeViewModel::class.java)
        dataBindingUtil.viewmodel = homeViewModel
        dataBindingUtil.etSearchLocation.clearFocus()

        val rv_city_list: RecyclerView = dataBindingUtil.rvCityList
        rv_city_list.layoutManager = LinearLayoutManager(activity)
        val adapter: CityListAdapter = CityListAdapter(ArrayList<CityEntity>())
        rv_city_list.adapter = adapter
        homeViewModel.observeCityList().observe(viewLifecycleOwner, Observer {
            adapter.addData(it)
        })

        dataBindingUtil.etSearchLocation.doAfterTextChanged {
            homeViewModel.observeCityList().observe(viewLifecycleOwner, Observer { adapter.addData(it) })
        }

        return dataBindingUtil.root
    }


}