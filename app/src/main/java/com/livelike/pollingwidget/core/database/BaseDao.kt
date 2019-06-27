package com.livelike.pollingwidget.core.database

import androidx.room.*

@Dao
abstract class BaseDao<T> {


    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun update(obj: T): Int

    @Transaction
    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun updateAll(vararg objs: T): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertOrUpdate(obj: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertOrUpdateAll(objs: List<T>): LongArray

    @Delete
    abstract fun delete(obj: T)


}