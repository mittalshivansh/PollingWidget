package com.livelike.pollingwidget.core.database

import androidx.room.*

@Dao
abstract class BaseDao<T> {


    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun update(obj: T): Int

    @Transaction
    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun updateAll(vararg objs: T): Int

    @Insert(onConflict = OnConflictStrategy.ABORT)
    abstract suspend fun insert(obj: T): Long

    @Transaction
    @Insert(onConflict = OnConflictStrategy.ABORT)
    abstract suspend fun insertAll(objs: List<T>): LongArray

    @Delete
    abstract suspend fun delete(obj: T)


}