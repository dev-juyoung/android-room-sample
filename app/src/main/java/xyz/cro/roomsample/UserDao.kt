package xyz.cro.roomsample

import android.arch.persistence.room.*

/**
 * Created by juyounglee on 2018. 3. 14..
 */
@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(user: User)

    @Delete
    fun delete(user: User)
}