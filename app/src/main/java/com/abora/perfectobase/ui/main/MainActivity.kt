package com.abora.perfectobase.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.abora.perfectobase.R
import com.abora.perfectobase.base.BaseActivity
import com.abora.perfectobase.data.models.CompetitionsData
import com.abora.perfectobase.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.reflect.KClass

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(),
    CompetitionsAdapter.MyCategoryAction {

    lateinit var competitionsAdapter:CompetitionsAdapter

    override fun resourceId(): Int = R.layout.activity_main

    override fun viewModelClass(): KClass<MainViewModel> = MainViewModel::class

    override fun setUI(savedInstanceState: Bundle?) {

        competitionsAdapter= CompetitionsAdapter(this)
        recCompetitions.adapter=competitionsAdapter
    }

    override fun observer() {
        viewModel.competitionsResponse.observe(this) {
            competitionsAdapter.setData(it)
        }
    }

    override fun clicks() {

    }

    override fun callApis() {
        viewModel.getCompetitions()
    }

    override fun onCategoryClick(data: CompetitionsData, type: String, position: Int) {

    }
}