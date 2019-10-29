package demo.gyw.com.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import demo.gyw.com.myapplication.ext.log
import demo.gyw.com.myapplication.weiget.RulerView
import kotlinx.android.synthetic.main.activity_ruler_view.*
import org.jetbrains.anko.toast


class RulerViewActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ruler_view)

        rulerView.setOnChooseResulterListener(object : RulerView.OnChooseResulterListener {
            override fun onEndResult(result: String) {
                toast("result  $result")
            }

            override fun onScrollResult(result: String) {
                log("result  $result")
            }
        })


        rulerViewBtn.setOnClickListener {
            var result = rulerViewEt.text.toString().trim()
            rulerView.computeScrollTo(result.toFloat())
        }
    }

}
