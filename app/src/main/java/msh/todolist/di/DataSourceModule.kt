import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import msh.todolist.data.ConfigDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideConfigDataSource(
        @ApplicationContext context: Context
    ): ConfigDataSource {
        return ConfigDataSource(context)
    }
}