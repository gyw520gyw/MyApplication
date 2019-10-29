package demo.gyw.com.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_custom_group.*
import org.jetbrains.anko.startActivity

class CustomGroupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_group)

        initListener()
    }

    private fun initListener() {

        mShimmerLayout2.setOnClickListener {
            startActivity<ShimmerLayoutActivity2>()
        }

        mShimmerLayout.setOnClickListener {
            startActivity<ShimmerLayoutActivity>()
        }


        mScrollerLayout.setOnClickListener {
            startActivity<ScrollerLayoutActivity>()
        }

        mWaterFallLayout.setOnClickListener {
            startActivity<WaterFallActivity>()
        }

        mRulerView.setOnClickListener {
            startActivity<RulerViewActivity>()
        }
    }
}
