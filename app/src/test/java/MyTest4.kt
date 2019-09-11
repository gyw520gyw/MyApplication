import demo.gyw.com.myapplication.ext.now
import kotlinx.coroutines.*
import org.junit.Test

/**
 * 2019/9/10 17:15
 * gyw 协程上下文与调度器
 */
class MyTest4 {

    @Test
    fun test() {
//        test1()
//        test2()

//        test3()

//        test4()

//        println(" My job is ${coroutineContext[Job]}")

//        test5()

        test6()
    }


    // 父协程总是等待素有的子协程执行结束
    private fun test6() = runBlocking {
        // 启动一个协程来处理某种传入请求（request）
        val request = launch {
            repeat(3) { i -> // 启动少量的子作业
                launch  {
                    delay((i + 1) * 200L) // 延迟 200 毫秒、400 毫秒、600 毫秒的时间
                    println("Coroutine $i is done")
                }
            }
            println("request: I'm done and I don't explicitly join my children that are still active")
        }
        request.join() // 等待请求的完成，包括其所有子协程
        println("Now processing of the request is complete")
    }


    // 子协程
    private fun test5() = runBlocking{
        // 启动一个协程来处理某种传入请求（request）
        val request = launch {
            // 孵化了两个子作业, 其中一个通过 GlobalScope 启动
            GlobalScope.launch {
                println("job1: I run in GlobalScope and execute independently!")
                delay(1000)
                println("job1: I am not affected by cancellation of the request")
            }
            // 另一个则承袭了父协程的上下文
            launch {
                delay(100)
                println("job2: I am a child of the request coroutine")
                delay(1000)
                println("job2: I will not execute this line if my parent request is cancelled")
            }
        }
        delay(500)
        request.cancel() // 取消请求（request）的执行
        delay(1000) // 延迟一秒钟来看看发生了什么
        println("main: Who has survived request cancellation?")
    }


    // 在不同的线程间跳转
    @ObsoleteCoroutinesApi
    private fun test4()  {
        newSingleThreadContext("Ctx1").use { ctx1->
            newSingleThreadContext("Ctx2").use { ctx2->
                runBlocking(ctx1) {
                    log("start in ctx1")
                    withContext(ctx2) {
                        log("working in ctx2")
                    }
                    log("back to ctx1")
                }
            }
        }
    }


    //
    private fun test3() = runBlocking {
        val a = async {
            log("I'm computing a piece of the answer")
            6
        }
        val b = async {
            log("I'm computing another piece of the answer")
            7
        }
        log("The answer is ${a.await() * b.await()}")
    }


    //非受限调度器  受限调度器
    private fun test2() = runBlocking {
        launch(Dispatchers.Unconfined) { // 非受限的——将和主线程一起工作
            println("Unconfined      : I'm working in thread ${Thread.currentThread().name}")
            delay(500)
            println("Unconfined      : After delay in thread ${Thread.currentThread().name}")
        }
        launch { // 父协程的上下文，主 runBlocking 协程
            println("main runBlocking: I'm working in thread ${Thread.currentThread().name}")
            delay(1000)
            println("main runBlocking: After delay in thread ${Thread.currentThread().name}")
        }
    }


    // 调度器与线程
    private fun test1() = runBlocking {
        launch { // 运行在父协程的上下文中，即 runBlocking 主协程
            println("main runBlocking      : I'm working in thread ${Thread.currentThread().name}")
        }
        launch(Dispatchers.Unconfined) { // 不受限的——将工作在主线程中
            println("Unconfined            : I'm working in thread ${Thread.currentThread().name}")
        }
        launch(Dispatchers.Default) { // 将会获取默认调度器
            println("Default               : I'm working in thread ${Thread.currentThread().name}")
        }
        launch(newSingleThreadContext("MyOwnThread")) { // 将使它获得一个新的线程
            println("newSingleThreadContext: I'm working in thread ${Thread.currentThread().name}")
        }
    }

}



fun log(msg: Any?) = println("${now()} [${Thread.currentThread().name}] $msg")