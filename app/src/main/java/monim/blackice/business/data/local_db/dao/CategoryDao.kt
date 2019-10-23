package monim.blackice.business.data.local_db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import monim.blackice.business.data.local_db.entity.Category


@Dao
interface CategoryDao {

    @Query("SELECT * FROM Category")
    abstract fun getAll(): List<Category>

    @Query("SELECT * FROM Category where localId=:localId")
    abstract fun getAllById(localId: Int): List<Category>

    @Insert
    abstract fun insert(categories: List<Category>): List<Long>

    @Insert
    abstract fun insert(users: Category)

    @Query("DELETE FROM Category")
    abstract fun delete(): Int

    @Delete
    abstract fun delete(categories: List<Category>): Int

    @Delete
    abstract fun delete(category: Category): Int
}