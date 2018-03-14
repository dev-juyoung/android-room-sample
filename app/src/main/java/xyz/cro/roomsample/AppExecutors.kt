package xyz.cro.roomsample

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor

/**
 * Created by juyounglee on 2018. 3. 14..
 */
class AppExecutors(
        val diskIO: Executor = DiskIOThreadExecutor(),
        val mainThread: Executor = MainThreadExecutor()
) {
    private class MainThreadExecutor : Executor {
        private val mainThreadHandler = Handler(Looper.getMainLooper())

        override fun execute(command: Runnable?) {
            mainThreadHandler.post(command)
        }
    }
}