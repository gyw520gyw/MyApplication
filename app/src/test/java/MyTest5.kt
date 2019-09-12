import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.system.measureTimeMillis

/**
 * 2019/9/11 9:43
 * gyw 协程异步流
 */
@FlowPreview
class MyTest5 {

    @Test
    fun test() {
//        test1()
//        test2()
//        test3()
        test4()
    }


    private fun test4() = runBlocking {
        val startTime = System.currentTimeMillis()
        (1..3).asFlow().onEach{ delay(200)}
                .flatMapConcat{requestFlow(it)}.collect { value ->
                    println("$value  | at ${System.currentTimeMillis() - startTime} ms from start") }
    }

    // 展平流
    private fun requestFlow(i: Int): Flow<String> = flow {
        emit("$i first")
        delay(500)
        emit("$i second")
    }




    //组合多个流 zip / combine
    private fun test3() = runBlocking {
        val nums = (1..3).asFlow().onEach{ delay(300)}
        val strs = flowOf("one", "two", "three").onEach{ delay(600)}
        val startTime = System.currentTimeMillis()
        nums.combine(strs) {
            a, b -> "$a -> $b"
        }.collect{
            println("$it | at ${System.currentTimeMillis() - startTime} ms from start") }
    }


    // 缓冲buffer 合并conflate 处理最新值collectLatest
    private fun test2() = runBlocking {
        val time = measureTimeMillis {
            foo().collectLatest {
                value ->
                println("Collecting $value")
                delay(300)
                println("Done $value")
            }
        }

        println("Collected in $time ms")
    }



    // Flow流
    private fun test1() = runBlocking {
        launch {
            for (k in 1..3) {
                println("I`m not blocked $k")
                delay(100)
            }
        }

        foo().collect { value -> println(value) }
    }


    private fun foo(): Flow<Int> = flow{
        for(i in 1..3) {
            delay(100)
//            Thread.sleep(1000)
            emit(i) // 发射值
        }
    }

}