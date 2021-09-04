package com.mobiquity.arnab.weather.ui.city

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.mobiquity.arnab.weather.R
import kotlinx.android.synthetic.main.fragment_city.*

class CityFragment : Fragment() {

    companion object {
        fun newInstance() = CityFragment()
    }

    private lateinit var viewModel: CityViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_city, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CityViewModel::class.java)
        viewModel.nameString.observe(viewLifecycleOwner, Observer { tv_city_name.text = it })
        viewModel.tempString.observe(viewLifecycleOwner, Observer { tv_temp.text = it })
        viewModel.humidityString.observe(viewLifecycleOwner, Observer { tv_humidity.text = it })
        viewModel.rainString.observe(viewLifecycleOwner, Observer { tv_rain_info.text = it })
        viewModel.windString.observe(viewLifecycleOwner, Observer { tv_wind_info.text = it })
    }

}