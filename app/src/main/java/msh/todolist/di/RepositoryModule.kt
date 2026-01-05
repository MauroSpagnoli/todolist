package msh.todolist.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import msh.todolist.domain.repository.TodoRepository
import msh.todolist.data.repository.TodoRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {


    @Binds
    @Singleton
    abstract fun bindTodoRepository(impl: TodoRepositoryImpl): TodoRepository

}