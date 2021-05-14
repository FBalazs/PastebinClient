package bwp.pastebinclient.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PasteDao {
    @Query("SELECT * FROM paste")
    fun getAll(): List<PasteInfo>

    @Insert
    fun insertAll(vararg pastes: PasteInfo)

    @Delete
    fun delete(paste: PasteInfo)
}