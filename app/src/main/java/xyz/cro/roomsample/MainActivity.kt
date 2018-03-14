package xyz.cro.roomsample

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val database: UserDatabase by lazy { UserDatabase.getInstance(this) }
    private val dao: UserDao by lazy { database.userDao() }

    private val appExecutor: AppExecutors by lazy { AppExecutors() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ADD USER
        buttonAddUser.setOnClickListener {
            val user = User(1, "Juyoung", "Lee")

            appExecutor.diskIO.execute {
                dao.add(user)

                appExecutor.mainThread.execute { getUserList() }
            }
        }

        // DELETE USER
        buttonDeleteUser.setOnClickListener {
            appExecutor.diskIO.execute {
                dao.getUser(1)?.let {
                    Log.d("ROOM-DATABASE", "find user: $it")
                    dao.delete(it)
                }

                appExecutor.mainThread.execute { getUserList() }
            }
        }
    }

    private fun getUserList() {
        appExecutor.diskIO.execute {
            val users = dao.getAll()

            appExecutor.mainThread.execute { Log.d("ROOM-DATABASE", "users: $users") }
        }
    }
}
