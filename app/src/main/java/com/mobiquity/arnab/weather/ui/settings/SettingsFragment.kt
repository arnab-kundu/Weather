package com.mobiquity.arnab.weather.ui.settings

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.switchmaterial.SwitchMaterial
import com.mobiquity.arnab.weather.R
import com.mobiquity.arnab.weather.databinding.FragmentSettingsBinding
import com.mobiquity.arnab.weather.enums.Units
import com.mobiquity.arnab.weather.utils.Constants
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

// TODO 1
class SettingsFragment : Fragment(R.layout.fragment_settings) {

    //TODO 2
    @Inject
    lateinit var viewModel: SettingsViewModel
    lateinit var binding: FragmentSettingsBinding

    lateinit var unitSwitch: SwitchMaterial

    //TODO 3
    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //TODO 4
        binding = FragmentSettingsBinding.bind(view)

        unitSwitch = binding.switchUnit
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val sp: SharedPreferences = requireActivity().getSharedPreferences(Constants.SP_NAME, MODE_PRIVATE)
        unitSwitch.isChecked = sp.getString("unit", Units.metric.name) != Units.metric.name
        unitSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            val editor = sp.edit()

            if (isChecked) {
                editor.putString("unit", Units.imperial.name)
                Snackbar.make(buttonView, "Imperial(°F)", Snackbar.LENGTH_LONG).setAction("Action", null).show()
            } else {
                editor.putString("unit", Units.metric.name)
                Snackbar.make(buttonView, "Metric(°C)", Snackbar.LENGTH_LONG).setAction("Action", null).show()
            }
            editor.apply()
        }

        binding.btnReset.setOnClickListener {
            viewModel.resetSavedCities()
        }

    }

}