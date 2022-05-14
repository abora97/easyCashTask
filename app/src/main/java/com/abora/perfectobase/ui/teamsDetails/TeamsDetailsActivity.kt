package com.abora.perfectobase.ui.teamsDetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.abora.perfectobase.R
import com.abora.perfectobase.base.BaseActivity
import com.abora.perfectobase.data.models.Teams
import com.abora.perfectobase.databinding.ActivityTeamsDetailsBinding
import com.abora.perfectobase.ui.main.MainViewModel
import com.abora.perfectobase.utils.MyUtils.openBrowser
import com.abora.perfectobase.utils.MyUtils.phoneCall
import com.abora.perfectobase.utils.MyUtils.sendEmail
import kotlinx.android.synthetic.main.activity_teams_details.*
import kotlinx.android.synthetic.main.toolbar_with_text.*
import kotlin.reflect.KClass

class TeamsDetailsActivity : BaseActivity<ActivityTeamsDetailsBinding, MainViewModel>(),
    TeamsAdapter.MyTeamsAction {

    private lateinit var teamsAdapter: TeamsAdapter

    override fun resourceId(): Int = R.layout.activity_teams_details

    override fun viewModelClass(): KClass<MainViewModel> = MainViewModel::class

    override fun setUI(savedInstanceState: Bundle?) {
        teamsAdapter = TeamsAdapter(this)
        recTeam.adapter = teamsAdapter
    }

    override fun observer() {
        viewModel.teamDetailsResponse.observe(this) {
            dataBinding.data = it
            teamsAdapter.setData(it.teams.toMutableList())
        }
    }

    override fun clicks() {
        btnToolBack.visibility = View.VISIBLE
        btnToolBack.setOnClickListener {
            onBackPressed()
        }
    }

    override fun callApis() {
        viewModel.getTeamDetails("2000")
    }

    override fun onTeamClick(data: Teams, type: String, position: Int) {
        when (type) {
            "phone"->{
                phoneCall(data.phone)
            }
            "Website"->{
                openBrowser(data.website)
            }
            "email"->{
                sendEmail(data.email)
            }
        }
    }
}