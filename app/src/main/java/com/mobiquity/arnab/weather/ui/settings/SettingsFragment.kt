package com.mobiquity.arnab.weather.ui.settings

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.switchmaterial.SwitchMaterial
import com.mobiquity.arnab.weather.R
import com.mobiquity.arnab.weather.database.AppDatabase
import com.mobiquity.arnab.weather.enums.Units
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SettingsFragment : Fragment() {

    companion object {
        fun newInstance() = SettingsFragment()
    }

    lateinit var unitSwitch: SwitchMaterial


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        unitSwitch = view.findViewById(R.id.switch_unit)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        unitSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            val sp: SharedPreferences = requireActivity().getSharedPreferences("Weather", MODE_PRIVATE)
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

        btn_reset.setOnClickListener {
            val db: AppDatabase = AppDatabase.invoke(requireActivity())
            CoroutineScope(Dispatchers.IO).launch {
                db.cityDao().truncateTable()
            }
        }

    }

}