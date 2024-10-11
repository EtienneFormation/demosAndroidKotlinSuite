package fr.eni.filrouge.di

import android.content.Context
import androidx.work.WorkManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import fr.eni.filrouge.data.repository.ProductRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideProductRepository(@ApplicationContext context: Context)
        = ProductRepository(context)

    @Provides
    @Singleton
    fun provideWorkManager(@ApplicationContext context: Context)
        = WorkManager.getInstance(context);


    //TODO ajouter le repositoryCart
}