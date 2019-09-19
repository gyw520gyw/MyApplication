import demo.gyw.com.myapplication.ext.now
import kotlinx.coroutines.*
import org.junit.Test

/**
 * 2019/9/10 11:27
 * gyw 协程的取消与超时
 */
class MyTest2 {

    @Test
    fun test() {
        testCancel()
//        testCancel2()
//        testCancel3()
//        testCancel4()
//        testCancel5()
    }



    fun testCancel5() = runBlocking {
        val result = withTimeoutOrNull(1300L) {
            repeat(1000) { i ->
                println("I'm sleeping $i ...")
                delay(500L)
            }
            "Done" // 在它运行得到结果之前取消它
        }
        println("Result is $result")
    }


    fun testCancel4() = runBlocking {
        withTimeout(1300L) {
            repeat(1000) { i ->
                println("I'm sleeping $i ...")
                delay(500L)
            }
        }
    }

    fun testCancel3() = runBlocking {
        val job = launch {
            try {
                repeat(1000) { i ->
                    println("job: I'm sleeping $i ...")
                    delay(500L)
                }
            } finally {
//                println("job: I'm running finally")
                withContext(NonCancellable) {
                    println("job: I'm running finally")
                    delay(1000L)
                    println("job: And I've just delayed for 1 sec because I'm non-cancellable")
                }
            }
        }
        delay(1300L) // 延迟一段时间
        println("main: I'm tired of waiting!")
        job.cancelAndJoin() // 取消该作业并且等待它结束
        println("main: Now I can quit.")
    }


    fun testCancel2() = runBlocking {
        val startTime = System.currentTimeMillis()
        val job = launch(Dispatchers.Default) {
            var nextPrintTime = startTime
            var i = 0
            while (isActive) { // 一个执行计算的循环，只是为了占用 CPU
                // 每秒打印消息两次
                if (System.currentTimeMillis() >= nextPrintTime) {
                    println("job: I'm sleeping ${i++} ...")
                    nextPrintTime += 500L
                }
            }
        }
        delay(1300L) // 等待一段时间
        println("main: I'm tired of waiting!")
        job.cancelAndJoin() // 取消一个作业并且等待它结束
        println("main: Now I can quit.")
    }


    // 取消协程
    fun testCancel() = runBlocking {
        val job = launch {
            repeat(100) {
                println( "job: I`m sleeping $it")
                delay(500L)
            }
        }

        delay(1300L)
        println("main: I`m tired of waiting")
        job.cancel()    //取消该作业
        job.join()      //等待作业执行结束

        println("main: Now I can quit")
    }




}