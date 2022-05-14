package com.abora.perfectobase.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.abora.perfectobase.R
import com.abora.perfectobase.base.BaseActivity
import com.abora.perfectobase.data.models.CompetitionsData
import com.abora.perfectobase.databinding.ActivityMainBinding
import com.abora.perfectobase.ui.competitionDetails.CompetitionDetails
import com.abora.perfectobase.utils.MyUtils.openActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.reflect.KClass

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(),
    CompetitionsAdapter.MyCompetitionsAction {

    private lateinit var competitionsAdapter: CompetitionsAdapter

    override fun resourceId(): Int = R.layout.activity_main

    override fun viewModelClass(): KClass<MainViewModel> = MainViewModel::class

    override fun setUI(savedInstanceState: Bundle?) {

        competitionsAdapter = CompetitionsAdapter(this)
        recCompetitions.adapter = competitionsAdapter
    }

    override fun observer() {
        viewModel.competitionsResponse.observe(this) {
            competitionsAdapter.setData(it)
        }
    }

    override fun clicks() {
        btnCurrentCompetition.setOnClickListener {
            openActivity(CompetitionDetails::class.java)
        }
    }

    override fun callApis() {
        viewModel.getCompetitions()
    }

    override fun onCompetitionsClick(data: CompetitionsData, type: String, position: Int) {
        openActivity(CompetitionDetails::class.java, Intent().putExtra("data", Gson().toJson(data)))
    }

}