package demo.gyw.com.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import demo.gyw.com.myapplication.ext.log
import kotlinx.coroutines.*
import org.jetbrains.anko.toast

class KotlinxCoroutineActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlinx_coroutine)

//        initData()
    }

    private fun initData() {
        // 处理
        launch({main()},
                { toast("error..") }
        )
    }

    suspend fun main() {
         log(1)
         val job = GlobalScope.launch(start = CoroutineStart.LAZY) {
             log(2)
         }
         log(3)
         job.start()
         log(4)
    }


    private fun launch(block: suspend () -> Unit, error: suspend (Throwable) -> Unit) = GlobalScope.launch {
        try {
            block()
        } catch (e: Throwable) {
            error(e)
        }
    }

}
