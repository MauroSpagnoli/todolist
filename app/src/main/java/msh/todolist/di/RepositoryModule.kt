package msh.todolist.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import msh.todolist.domain.repository.ConfigRepository
import msh.todolist.domain.repository.IConfigRepository
import msh.todolist.domain.repository.ITodoRepository
import msh.todolist.domain.repository.TodoRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindConfigRepository(impl: ConfigRepository): IConfigRepository

    @Binds
    @Singleton
    abstract fun bindTodoRepository(impl: TodoRepository): ITodoRepository

}