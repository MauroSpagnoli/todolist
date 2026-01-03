// kotlin
package msh.todolist.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TodoDao {

    @Query("SELECT * FROM todos")
    suspend fun getAll(): List<TodoEntity>

    @Insert
    suspend fun insert(todo: TodoEntity): Long

    @Update
    suspend fun update(todo: TodoEntity): Int

    @Query("DELETE FROM todos WHERE id = :id")
    suspend fun delete(id: Long): Int
}
