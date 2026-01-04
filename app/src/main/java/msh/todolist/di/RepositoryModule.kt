package msh.todolist.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import msh.todolist.domain.repository.ITodoRepository
import msh.todolist.data.repository.TodoRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {


    @Binds
    @Singleton
    abstract fun bindTodoRepository(impl: TodoRepository): ITodoRepository

}