import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test

/**
 * 2019/9/11 14:58
 * gyw 协程通道
 */
class MyTest6 {

    @Test
    fun test() {
//        testChannel()
//        testChannelClose()
//        testChannel2()
        testChannel3()
    }

    //使用管道生产素数
    private fun testChannel3() = runBlocking {
        var cur = numbersFrom(2)
        for(i in 1..10) {
            val prime = cur.receive()
            println("prime : $prime")
            cur = filter(cur, prime)
        }

        coroutineContext.cancelChildren()
    }


    //管道 管道是一种一个协程在流中开始生产可能无穷多个元素的模式
    private fun testChannel2() = runBlocking {
        val numbers = produceNumbers()
        val squares = square(numbers)
        for (i in 1..5) {
            println("value = ${squares.receive()}")
        }

        println("Done")
        coroutineContext.cancelChildren() // 取消子线程
    }


    // 通道关闭 迭代通道
    private fun testChannelClose() = runBlocking {
        val channel = Channel<Int>()
        launch {
            for(i in 1..5) {
                channel.send(i * 10)
            }
            channel.close()
        }


        for (i in channel) {
            println(i)
        }

        println("Done")

    }

    //通道基础 Channel
    private fun testChannel() = runBlocking {
        val channel = Channel<Int>()
        launch {
            for (i in 1..5)
                channel.send(i * i)
        }
        repeat(4) {
            println(channel.receive())
        }

        println("Done")
    }








}

// testChannel3
fun CoroutineScope.filter(numbers: ReceiveChannel<Int>, prime: Int) = produce {
    for (x in numbers) {
        if (x % prime != 0) {
            send(x)
        }
    }
}

fun CoroutineScope.numbersFrom(start: Int) = produce {
    var x = start
    while (true){
        send(x++) // 开启了一个无限的整数流
    }
}



// testChannel2
fun CoroutineScope.square(numbers: ReceiveChannel<Int>) : ReceiveChannel<Int> = produce {
    for(x in numbers) {
        println("square $x")
        send(x * x)
    }
}

fun CoroutineScope.produceNumbers() = produce {
    var x = 1
    while (true) {
        println("produceNumbers $x")
        send(x++)
    }

}
