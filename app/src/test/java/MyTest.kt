import kotlinx.coroutines.*
import org.junit.Test

/**
 * 2019/9/10 11:04
 * gyw  基础部分  kotlin协程测试, 实例代码来自官网
 */
class MyTest {
    @Test
    fun testMySuspendingFunction() = runBlocking<Unit> {
        // 这里我们可以使用任何喜欢的断言风格来使用挂起函数
//        testDelay()
        testJoin()

    }


    @Test
    fun testMyFun() {
//        main()
//        main2()
        main3()
//        main4()
    }

    //全局协程像守护线程
    private fun main4() = runBlocking {
        GlobalScope.launch {
            repeat(1000) {i -> println("I`m sleeping $i...")
                delay(500L)
            }
        }
        delay(1300L)
    }



    //协程很轻量
    private fun main3() = runBlocking {
        repeat(100_000) {i -> // 启动大量的协程
            launch {
                delay(1000L)
                if(i % 100 == 0) {
                    println()
                }
                print(".")
            }
        }
    }


    // 作用域构建器
    private fun main2() = runBlocking { // this: CoroutineScope
        launch {
            delay(200L)
            println("Task from runBlocking")
        }

        coroutineScope { // 创建一个协程作用域
            launch {
                delay(500L)
                println("Task from nested launch")
            }

            delay(100L)
            println("Task from coroutine scope") // 这一行会在内嵌 launch 之前输出
        }

        println("Coroutine scope is over") // 这一行在内嵌 launch 执行完毕后才输出
    }


    //结构化并发
    private fun main() = runBlocking { // this: CoroutineScope
        launch { // 在 runBlocking 作用域中启动一个新协程
            delay(1000L)
            println("World!33")
        }
        println("Hello,33")
    }


    // join
    private suspend fun testJoin() {
        val job = GlobalScope.launch { // 启动一个新协程并保持对这个作业的引用
            delay(1000L)
            println("World! 22")
        }
        println("Hello, 22")
        job.join() // 等待直到子协程执行结束
    }



    // delay
     private suspend fun testDelay() {
        GlobalScope.launch { // 在后台启动一个新的协程并继续
            delay(1000L)
            println("World!")
        }
        println("Hello,") // 主协程在这里会立即执行
        delay(2000L)      // 延迟 2 秒来保证 JVM 存活
    }







}
















