package bwp.pastebinclient

import androidx.room.Database
import androidx.room.RoomDatabase
import bwp.pastebinclient.model.PasteDao
import bwp.pastebinclient.model.PasteInfo

@Database(entities = [PasteInfo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pasteDao(): PasteDao
}