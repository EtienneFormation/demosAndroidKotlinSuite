package fr.eni.filrouge.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.eni.filrouge.data.model.Product
import fr.eni.filrouge.data.repository.ProductRepository
import fr.eni.filrouge.data.worker.SyncDataWorker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

private const val TAG = "ListProductsVM"

@HiltViewModel
class ListProductsVM @Inject constructor(val repository: ProductRepository, val workManager: WorkManager) : ViewModel() {
    //Données privées "Full" = non filtrées
    private var listProductsFull : List<Product> = emptyList()
    private var listCategoriesFull : List<String>  = emptyList()
    //Etats UI

    var productsState by mutableStateOf(ProductListState())
        private set

    init {
        viewModelScope.launch {
            _loadProducts()
            _syncApiToDb()
        }
    }
    private suspend fun _syncApiToDb(){
        val syncDbRequest = OneTimeWorkRequestBuilder<SyncDataWorker>()
            .setConstraints(Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build())
            .build()
        workManager.enqueue(syncDbRequest)
        workManager.getWorkInfoByIdLiveData(syncDbRequest.id).asFlow().collect {
            when(it.state){
                WorkInfo.State.SUCCEEDED -> Log.i(TAG, "Sync result: Synchro OK")
                WorkInfo.State.FAILED -> Log.i(TAG, "Sync result: ${it.outputData.getString("error")}")
                else-> Log.i(TAG, "Sync result: Autres Status")

            }
        }
    }

    private suspend fun _loadProducts(){
        withContext(Dispatchers.IO){
            val products = repository.fetchProducts()
            productsState = productsState.copy(
                productList = products
            )
        }
    }

    //Si aucune sélectionné, modifier l'état avec le liste complète
    fun selectCategory(category : String) {
            if (category == productsState.selectedCategory) {
                productsState =productsState.copy(selectedCategory = null)
            } else {
                productsState =productsState.copy(
                    selectedCategory = category,
                    productsState.productList.filter { it.category == category }
                )
            }
    }

    fun getCategories() : List<String> = listCategoriesFull

    fun getById(id : Int) : Product? {
        if(productsState.productList.isEmpty()){
            return null
        }
        return productsState.productList.find { it.id == id }
    }
}

data class ProductListState (
    val selectedCategory: String? = null,
    val productList : List<Product> = emptyList()
)

