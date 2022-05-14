package com.abora.perfectobase.ui.competitionDetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.abora.perfectobase.R
import com.abora.perfectobase.base.BaseActivity
import com.abora.perfectobase.data.models.CompetitionsData
import com.abora.perfectobase.data.models.Seasons
import com.abora.perfectobase.databinding.ActivityCompetitionDetailsBinding
import com.abora.perfectobase.ui.main.MainViewModel
import com.abora.perfectobase.ui.teamsDetails.TeamsDetailsActivity
import com.abora.perfectobase.utils.MyUtils.openActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_competition_details.*
import kotlinx.android.synthetic.main.toolbar_with_text.*
import kotlin.reflect.KClass

class CompetitionDetails : BaseActivity<ActivityCompetitionDetailsBinding, MainViewModel>(),
    SeasonsAdapter.MySeasonAction {

    private lateinit var seasonsAdapter:SeasonsAdapter

    lateinit var competitionsData: CompetitionsData

    override fun resourceId(): Int = R.layout.activity_competition_details

    override fun viewModelClass(): KClass<MainViewModel> = MainViewModel::class

    override fun setUI(savedInstanceState: Bundle?) {
        intent.extras.let {
            if (it != null) {
                if (it.containsKey("data")) {
                    competitionsData =
                        Gson().fromJson(it.getString("data", ""), CompetitionsData::class.java)
                    dataBinding.toolTitle = competitionsData.name
                }
            }else{
                dataBinding.toolTitle="FIFA World Cup"
            }
        }

        seasonsAdapter=SeasonsAdapter(this)
        recSeason.adapter=seasonsAdapter
    }

    override fun observer() {
        viewModel.competitionsDetailsResponse.observe(this) {
            dataBinding.data=it
            seasonsAdapter.setData(it.seasons.toMutableList())
        }
    }

    override fun clicks() {
        btnToolBack.visibility = View.VISIBLE
        btnToolBack.setOnClickListener {
            onBackPressed()
        }

        tvShowTeam.setOnClickListener {
            openActivity(TeamsDetailsActivity::class.java)
        }
    }

    override fun callApis() {
        viewModel.getCompetitionsDetails("2000")
    }

    override fun onSeasonClick(data: Seasons, type: String, position: Int) {

    }
}