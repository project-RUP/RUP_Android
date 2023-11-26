package com.rup.feature.presentation.Main

import android.content.Intent
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rup.core.base.BaseBindingActivity
import com.rup.databinding.ActivityMainPageBinding
import com.rup.feature.presentation.Coin.CoinActivity
import com.rup.feature.presentation.Main.model.maindto
import com.rup.feature.presentation.MainPage.MyPageActivity
import com.rup.feature.presentation.map.MapActivity
import com.rup.feature.presentation.point.PointActivity


class MainPageActivity : BaseBindingActivity<ActivityMainPageBinding, MainPageViewModel>(), OnItemClickListener {

    override val bindingInflater: (LayoutInflater) -> ActivityMainPageBinding = ActivityMainPageBinding::inflate

    override fun setup() {
        binding.myRecyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = MainRecyclerViewAdapter(this,emptyList())
        binding.myRecyclerView.adapter = adapter

        // Observe Maindtos LiveData
        viewModel.Maindtos.observe(this) { Maindtos ->
            adapter.updateData(Maindtos)
        }

        binding.profile.setOnClickListener {
            moveIntent(MyPageActivity::class.java)
        }

        binding.coinBtn.setOnClickListener{
            moveIntent(PointActivity::class.java)
        }

        binding.fab.setOnClickListener(){
            moveIntent(MakingAppointmentActivity::class.java)
        }
    }

    override fun onItemClick(item: maindto) {
        // 아이템 클릭 시, OtherActivity로 이동
        Intent(
            this,
            MapActivity::class.java,
        ).apply {
            this.putExtra(MapActivity.StartArgs.key, MapActivity.StartArgs(item.id))
        }.let {
            moveIntent(it)
        }
    }

    override val viewModel: MainPageViewModel
        get() = ViewModelProvider(this).get(MainPageViewModel::class.java)
}
