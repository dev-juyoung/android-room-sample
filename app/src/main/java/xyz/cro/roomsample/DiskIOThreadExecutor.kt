package xyz.cro.roomsample

import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * Created by juyounglee on 2018. 3. 14..
 */
class DiskIOThreadExecutor : Executor {
    private val diskIO = Executors.newSingleThreadExecutor()

    override fun execute(command: Runnable?) {
        diskIO.execute(command)
    }
}