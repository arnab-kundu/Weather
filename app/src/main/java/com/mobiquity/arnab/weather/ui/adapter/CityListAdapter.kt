package com.mobiquity.arnab.weather.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobiquity.arnab.weather.R
import com.mobiquity.arnab.weather.database.entity.CityEntity
import com.mobiquity.arnab.weather.ui.activity.CityActivity
import kotlinx.android.synthetic.main.row_city.view.*

class CityListAdapter(private val cityList: ArrayList<CityEntity>) : RecyclerView.Adapter<CityListAdapter.CityViewHolder>() {

    //   private val cityList: ArrayList<CityEntity> = ArrayList()
    private lateinit var ctx: Context

    //class CityViewHolder(val itemBinding: RowCityBinding) : RecyclerView.ViewHolder(itemBinding.root)


    class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_city, parent, false)
        ctx = parent.context
        return CityViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.itemView.tv_city_name.text = cityList[position].name
        holder.itemView.root.setOnClickListener {
            val intent = Intent(ctx, CityActivity::class.java)
            intent.putExtra("city", cityList[position].name)
            ctx.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return cityList.size
    }


    fun addData(list: List<CityEntity>) {
        cityList.clear()
        cityList.addAll(list)
        notifyDataSetChanged()
    }
}