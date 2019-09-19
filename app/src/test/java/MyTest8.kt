import kotlinx.coroutines.*
import org.junit.Test
import java.lang.Exception

/**
 * 2019/9/12 14:45
 * gyw
 * 该地址上的示例 http://www.imooc.com/article/285100
 */
class MyTest8 {

    @Test
    fun test() {
//        testLaunch()
//        testLaunch2()
//        testLaunch3()
//        testLaunch4()

        testCancel()
    }


    private fun testCancel() = runBlocking {
        val job1 = launch {
            log(1)
            try {
                delay(1000)
            } catch (e: Exception) {
                log("cancelled $e")
            }

            log(2)
        }
        delay(100)
        log(3)
        job1.cancel()
        log(4)
    }


    //Defalut 立即执行
    private fun testLaunch4() = runBlocking{
        log(1)
        val job = GlobalScope.launch {
            log(2)
        }
        log(3)
        job.join()
        log(4)

    }


    //Atomic //立即执行协程, 但开始前无法取消
    private fun testLaunch3() = runBlocking {
        log(1)
        val job = GlobalScope.launch(start = CoroutineStart.ATOMIC) {
            log(2)
        }
        job.join()
        log(3)
    }



    //LAZY 只在需要的时候执行
    private fun testLaunch2() = runBlocking {
        log(1)
        val job = GlobalScope.launch(start = CoroutineStart.LAZY) {
            log(2)
        }
        log(3)
//        job.start() //等待协程执行 在执行后面的
        job.join()  // 主动触发协程
        log(4)
    }




    //UNDISPATCHED
    private fun testLaunch() = runBlocking {
        log(1)

        val job = GlobalScope.launch(start = CoroutineStart.UNDISPATCHED) {
            log(2)
            delay(100)
            log(3)
        }

        log(4)
        job.join()
        log(5)
    }


}