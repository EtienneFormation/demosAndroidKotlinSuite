package fr.eni.filrouge.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import fr.eni.filrouge.data.model.Product
import fr.eni.filrouge.data.repository.ProductRepository
import fr.eni.filrouge.data.worker.SyncDataWorker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "ListProductsVM"
class ListProductsVM(val repository: ProductRepository, val workManager: WorkManager) : ViewModel() {
    /*private val _listArticle = listOf(
        Product(
            1,
            "Ceinture marron",
            30,
            "Ceinture",
            "https://www.fightingfilms.shop/wp-content/uploads/2021/06/ceinture-marron-judo-ijf.webp",
            "Toutes nos ceintures de judo sont conformes aux normes officielles de l’IJF (International Judo Federation). Comme tous les modèles de ceinture de judo, cette ceinture est faite d’un noyau de coton semi-rigide avec de nombreuses surpiqûres bien alignées. L’ensemble est renforcé par une couche extérieure avec un mélange de coton et de soie. À l’inverse des ceintures de judo fines de type Rouleau de tissu, notre ceinture est épaisse et rigide. Enfin, un élégant logo brodé en rouge vient donner son style unique à cette ceinture.",
            listOf("Marron", "Epaisse")
        ),
        Product(
            2,
            "Ceinture noire",
            30,
            "Ceinture",
            "https://www.fightingfilms.shop/wp-content/uploads/2021/06/ceinture-noire-ijf.webp",
            "Toutes nos ceintures de judo sont conformes aux normes officielles de l’IJF (International Judo Federation). Comme tous les modèles de ceinture de judo, cette ceinture est faite d’un noyau de coton semi-rigide avec de nombreuses surpiqûres bien alignées. L’ensemble est renforcé par une couche extérieure avec un mélange de coton et de soie. À l’inverse des ceintures de judo fines de type Rouleau de tissu, notre ceinture est épaisse et rigide. Enfin, un élégant logo brodé en rouge vient donner son style unique à cette ceinture.",
            listOf("Noire", "Epaisse")
        ),
        Product(
            3,
            "Ceinture bleue",
            30,
            "Ceinture",
            "https://www.fightingfilms.shop/wp-content/uploads/2021/07/ceinture-bleu-judo.webp",
            "Toutes nos ceintures de judo sont conformes aux normes officielles de l’IJF (International Judo Federation). Comme tous les modèles de ceinture de judo, cette ceinture est faite d’un noyau de coton semi-rigide avec de nombreuses surpiqûres bien alignées. L’ensemble est renforcé par une couche extérieure avec un mélange de coton et de soie. À l’inverse des ceintures de judo fines de type Rouleau de tissu, notre ceinture est épaisse et rigide. Enfin, un élégant logo brodé en rouge vient donner son style unique à cette ceinture.",
            listOf("Bleue", "Epaisse")
        ),
        Product(
            4,
            "Ceinture verte",
            30,
            "Ceinture",
            "https://www.fightingfilms.shop/wp-content/uploads/2021/07/ceinture-judo-verte.webp",
            "Toutes nos ceintures de judo sont conformes aux normes officielles de l’IJF (International Judo Federation). Comme tous les modèles de ceinture de judo, cette ceinture est faite d’un noyau de coton semi-rigide avec de nombreuses surpiqûres bien alignées. L’ensemble est renforcé par une couche extérieure avec un mélange de coton et de soie. À l’inverse des ceintures de judo fines de type Rouleau de tissu, notre ceinture est épaisse et rigide. Enfin, un élégant logo brodé en rouge vient donner son style unique à cette ceinture.",
            listOf("Verte", "Epaisse")
        ), Product(
            5,
            "Ceinture Orange",
            30,
            "Ceinture",
            "https://www.fightingfilms.shop/wp-content/uploads/2021/07/ceinture-judo-orange.webp",
            "Toutes nos ceintures de judo sont conformes aux normes officielles de l’IJF (International Judo Federation). Comme tous les modèles de ceinture de judo, cette ceinture est faite d’un noyau de coton semi-rigide avec de nombreuses surpiqûres bien alignées. L’ensemble est renforcé par une couche extérieure avec un mélange de coton et de soie. À l’inverse des ceintures de judo fines de type Rouleau de tissu, notre ceinture est épaisse et rigide. Enfin, un élégant logo brodé en rouge vient donner son style unique à cette ceinture.",
            listOf("Orange", "Epaisse")
        ),
        Product(
            6,
            "Ceinture jaune",
            30,
            "Ceinture",
            "https://www.fightingfilms.shop/wp-content/uploads/2021/07/ceinture-judo-jaune.webp",
            "Toutes nos ceintures de judo sont conformes aux normes officielles de l’IJF (International Judo Federation). Comme tous les modèles de ceinture de judo, cette ceinture est faite d’un noyau de coton semi-rigide avec de nombreuses surpiqûres bien alignées. L’ensemble est renforcé par une couche extérieure avec un mélange de coton et de soie. À l’inverse des ceintures de judo fines de type Rouleau de tissu, notre ceinture est épaisse et rigide. Enfin, un élégant logo brodé en rouge vient donner son style unique à cette ceinture.",
            listOf("Jaune", "Epaisse")
        ),
        Product(
            7,
            "Ceinture blanche",
            30,
            "Ceinture",
            "https://www.fightingfilms.shop/wp-content/uploads/2021/07/ceinture-blanche-judo.webp",
            "Toutes nos ceintures de judo sont conformes aux normes officielles de l’IJF (International Judo Federation). Comme tous les modèles de ceinture de judo, cette ceinture est faite d’un noyau de coton semi-rigide avec de nombreuses surpiqûres bien alignées. L’ensemble est renforcé par une couche extérieure avec un mélange de coton et de soie. À l’inverse des ceintures de judo fines de type Rouleau de tissu, notre ceinture est épaisse et rigide. Enfin, un élégant logo brodé en rouge vient donner son style unique à cette ceinture.",
            listOf("Blanche", "Epaisse")
        ),
        Product(
            8,
            "Rouleaux",
            28,
            "Ceinture",
            "https://www.fightingfilms.shop/wp-content/uploads/2021/06/Rouleaux-de-ceinture.png",
            "Voici le rouleau de ceinture de judo qui vous conviendra. Fabrication française, 100% coton, qualité garantie, ceux-ci sauront parfaitement correspondre à vos membres, même les plus exigeants. D’une longueur de 50m, les rouleaux de ceinture Fighting Films sont disponibles de blanc à marron",
            listOf("11 rouleaux", "Fines")
        ),
        Product(
            9,
            "Judogi 750g",
            220,
            "Judogi",
            "https://www.fightingfilms.shop/wp-content/uploads/2021/06/SS750-Stump.png",
            "Le kimono de Judo Fighting Films Superstar 750 Gr – IJF approved (approuvé par la Internationale Judo Federation  nouvelles normes IJF en place à partir de janvier 2023) se distingue par sa finition soignée ainsi que la qualité de son tissu. Ainsi, c’est un modèle qui convient parfaitement à tous les judokas désirant être à l’aise dans un kimono de haute qualité. Ce kimono est idéal pour une pratique régulière, car il est léger et apporte un confort optimal.  Enfin, Il offre une mobilité maximale de par sa coupe cintrée. C’est le kimono de judo pour compétition par excellence.",
            listOf("750g", "Homologué IJF")
        ),
        Product(
            10,
            "Judogi 500g",
            180,
            "Judogi",
            "https://www.fightingfilms.shop/wp-content/uploads/2021/06/3_4_WHITE_REDLABEL_DSC_0004-1.jpg",
            "Le kimono de Judo Fighting Films Superstar 500 Gr – IJF approved (approuvé par la Internationale Judo Federation  nouvelles normes IJF en place à partir de janvier 2023) se distingue par sa finition soignée ainsi que la qualité de son tissu. Ainsi, c’est un modèle qui convient parfaitement à tous les judokas désirant être à l’aise dans un kimono de haute qualité. Ce kimono est idéal pour une pratique régulière, car il est léger et apporte un confort optimal.  Enfin, Il offre une mobilité maximale de par sa coupe cintrée. C’est le kimono de judo pour compétition par excellence.",
            listOf("500g", "Homologué IJF")
        ),
        Product(
            11,
            "Judogi enfant",
            35,
            "Judogi",
            "https://www.fightingfilms.shop/wp-content/uploads/2021/06/kimono-HAJIME.webp",
            "Le kimono de judo pour enfant Hajime en grain de riz de très bonne qualité. Agréable à porter pour les enfants, léger et robuste à la fois, le kimono Hajime est également livré avec une ceinture blanche. Ainsi, c’est le judogi idéal pour commencer l’apprentissage du judo avec les meilleurs atouts. Avec un grammage de 320 g/m2, ce judogi de qualité est plus solide et plus durable que la plupart des autres kimonos de judo pour enfants.",
            listOf("55% coton 45% polyester", "Homologué IJF", "100 à 160cm")
        )
    )*/
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


    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val application = checkNotNull(extras[APPLICATION_KEY])
                return ListProductsVM(
                    ProductRepository(application),
                    WorkManager.getInstance(application.applicationContext)
                ) as T
            }
        }
    }
}

data class ProductListState (
    val selectedCategory: String? = null,
    val productList : List<Product> = emptyList()
)

