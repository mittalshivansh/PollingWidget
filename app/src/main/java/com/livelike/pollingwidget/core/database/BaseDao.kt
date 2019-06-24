package com.livelike.pollingwidget.core.database

import androidx.room.*

@Dao
abstract class BaseDao<T> {


    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun update(obj: T): Int


    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract fun updateAll(vararg objs: T): Int

    @Insert(onConflict = OnConflictStrategy.ABORT)
    abstract fun insert(obj: T): Long


    @Insert(onConflict = OnConflictStrategy.ABORT)
    abstract fun insertAll(objs: List<T>): LongArray

    @Delete
    abstract fun delete(obj: T)


}