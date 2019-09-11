import kotlinx.coroutines.*
import org.junit.Test
import kotlin.system.measureTimeMillis

/**
 * 2019/9/10 16:07
 * gyw 组合挂起函数 测试
 */
class MyTest3 {


    @Test
    fun test() {
//        testDefault()
//        testAsync()
//        testAsync2()
//        testAsync3()

//        testAsync4()

        testAsync5()
    }

    fun testAsync5() = runBlocking<Unit> {
        try {
            failedConcurrentSum()
        } catch(e: ArithmeticException) {
            println("Computation failed with ArithmeticException")
        }
    }

    //模拟协程异常  取消始终通过协程层次结构来进行传递
    private suspend fun failedConcurrentSum(): Int = coroutineScope {
        val one = async<Int> {
            try {
                delay(10000L) // 模拟一个长时间的运算
                42
            } finally {
                println("First child was cancelled")
            }
        }
        val two = async<Int> {
            println("Second child throws an exception")
            throw ArithmeticException()
        }
        one.await() + two.await()
    }



    private fun testAsync4() = runBlocking {
        val time = measureTimeMillis {
            println("The answer is ${concurrentSum()}")
        }

        println("Completed in $time ms")
    }
    // 使用async 的结构化并发
    suspend fun concurrentSum(): Int = coroutineScope {
        val one = async { doSomethingUsefulOne() }
        val two = async { doSomethingUsefulTwo() }

        one.await() + two.await()
    }


    /// 注意，在这个示例中我们在 `main` 函数的右边没有加上 `runBlocking`
    private fun testAsync3() {
        val time = measureTimeMillis {
            // 我们可以在协程外面启动异步执行
            val one = somethingUsefulOneAsync()
            val two = somethingUsefulTwoAsync()

            // 但是等待结果必须调用其它的挂起或者阻塞
            // 当我们等待结果的时候，这里我们使用 `runBlocking { …… }` 来阻塞主线程

            runBlocking {
                println("The answer is ${one.await() + two.await()}")
            }
        }
        println("Completed in $time ms")
    }


    // 惰性启动的async
    private fun testAsync2() = runBlocking {
        val time = measureTimeMillis {
            val one = async(start = CoroutineStart.LAZY) { doSomethingUsefulOne() }
            val two = async(start = CoroutineStart.LAZY) { doSomethingUsefulTwo() }

            one.start()
            two.start()

            println("The answer is ${one.await() + two.await()}")
        }

        println("Completed in $time ms")
    }


    // 使用async并发
    private fun testAsync() = runBlocking {
        val time = measureTimeMillis {
            val one = async { doSomethingUsefulOne() }
            val two = async { doSomethingUsefulTwo() }

            println("The answer is ${one.await() + two.await()}")
        }

        println("Completed in $time ms")
    }


    // 默认顺序调用
    private fun testDefault() = runBlocking {
        val time = measureTimeMillis {
            val one = doSomethingUsefulOne()
            val two = doSomethingUsefulTwo()

            println("The answer is ${one+two}")
        }

        println("Completed in $time ms")
    }


    private fun somethingUsefulOneAsync() = GlobalScope.async {
        doSomethingUsefulOne()
    }

    private fun somethingUsefulTwoAsync() = GlobalScope.async {
        doSomethingUsefulTwo()
    }



    private suspend fun doSomethingUsefulOne(): Int {
        delay(1000L)
        return 13
    }

    private suspend fun doSomethingUsefulTwo(): Int {
        delay(1000L)
        return 29
    }
}