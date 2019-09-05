package demo.gyw.com.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.widget.RelativeLayout
import demo.gyw.com.myapplication.fragment.hencode.PageFragment
import kotlinx.android.synthetic.main.activity_hencode.*

class HencodeActivity : AppCompatActivity() {

    var pageModels: ArrayList<PageModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_hencode)

        initData()
        initView()

//            RelativeLayout
    }

    private fun initData() {
        this.pageModels.add(PageModel(R.layout.sample_water_wave_view, R.string.title_hencode_8, R.layout.sample_flipboard_view))
        this.pageModels.add(PageModel(R.layout.sample_weather_rain_view, R.string.title_hencode_7, R.layout.sample_weather_rain_view))
        this.pageModels.add(PageModel(R.layout.sample_weather_cloud_view, R.string.title_hencode_6, R.layout.sample_weather_cloud_view))
        this.pageModels.add(PageModel(R.layout.sample_weather_snow_view, R.string.title_hencode_5, R.layout.sample_weather_snow_view))
        this.pageModels.add(PageModel(R.layout.sample_flipboard_view, R.string.title_hencode_4, R.layout.sample_flipboard_view))
        this.pageModels.add(PageModel(R.layout.sample_thumbs_up_view, R.string.title_hencode_1, R.layout.sample_thumbs_up_view))
    }

    private fun initView() {
        pager.adapter = object : FragmentPagerAdapter(supportFragmentManager) {

            override fun getItem(position: Int): Fragment {
                val pageModel = pageModels[position]
                return PageFragment.newInstance(pageModel.sampleLayoutRes, pageModel.practiceLayoutRes)
            }

            override fun getCount(): Int {
                return pageModels.size
            }

            override fun getPageTitle(position: Int): CharSequence {
                return getString(pageModels[position].titleRes)
            }
        }

        tabLayout.setupWithViewPager(pager)

    }

    data class  PageModel(@LayoutRes var sampleLayoutRes: Int, @StringRes var titleRes: Int, @LayoutRes var practiceLayoutRes: Int)
}
