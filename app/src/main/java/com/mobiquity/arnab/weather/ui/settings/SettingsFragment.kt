package com.mobiquity.arnab.weather.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.switchmaterial.SwitchMaterial
import com.mobiquity.arnab.weather.R

class SettingsFragment : Fragment() {

    companion object {
        fun newInstance() = SettingsFragment()
    }

    private lateinit var viewModel: SettingsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val unitSwitch: SwitchMaterial = view.findViewById(R.id.switch_unit)
        unitSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                Snackbar.make(buttonView, "Imperial(°F)", Snackbar.LENGTH_LONG).setAction("Action", null).show()
            } else {
                Snackbar.make(buttonView, "Metric(°C)", Snackbar.LENGTH_LONG).setAction("Action", null).show()
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SettingsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}