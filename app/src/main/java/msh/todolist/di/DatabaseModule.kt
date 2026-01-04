package msh.todolist.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import msh.todolist.data.local.AppDatabase
import msh.todolist.data.local.TodoDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "todos-db").build()
    }

    @Provides
    @Singleton
    fun provideTodoDao(db: AppDatabase): TodoDao = db.todoDao()
}

