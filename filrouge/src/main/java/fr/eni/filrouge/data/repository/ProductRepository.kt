package fr.eni.filrouge.data.repository

import android.content.Context
import fr.eni.filrouge.data.dao.db.dbConfiguration.DatabaseClient
import fr.eni.filrouge.data.dao.rest.RetrofitClient
import fr.eni.filrouge.data.model.Product

enum class ModeOffline{offline, online}
enum class SyncStatus{success, fail, empty_internet_response}
class ProductRepository(appContext: Context) {
    private var MODE : ModeOffline = ModeOffline.online;
    val productApiDao = RetrofitClient.productApiService
    val productRoomDao =  DatabaseClient(appContext).appDatabase.productDao()


    suspend fun syncApiDB() : SyncStatus{
        //Je télécharge mes données du productApiDao (getAll)
        try{
            val listProductsAPI = productApiDao.getProducts().execute().body()?.map { productApi ->
                Product(
                    productApi.id,
                    productApi.title,
                    productApi.price.times(100).toInt(),
                    productApi.category,
                    productApi.image,
                    productApi.description
                )
            }
            if(listProductsAPI != null){
                //Je rentre ces données dans la BDD (productRoomDao)
                productRoomDao.deleteAll()
                productRoomDao.insertAll(listProductsAPI)
                return  SyncStatus.success
            }else{
                return SyncStatus.empty_internet_response
            }
        }catch (e : Exception){
            return SyncStatus.fail;
        }

    }

    //"suspend" permet de lancer une tâche asynchrone dans le thread principal (thread léger)
    suspend fun fetchProducts() : List<Product> {
        if(MODE == ModeOffline.online)
            return productApiDao.getProducts().execute().body()?.map { productApi ->
                Product(
                    productApi.id,
                    productApi.title,
                    productApi.price.times(100).toInt(),
                    productApi.category,
                    productApi.image,
                    productApi.description
                )
            } ?: emptyList()
        else{
            return productRoomDao.getAll()
        }

    }

    suspend fun fetchProductById(id : Int):Product?{
        if(MODE == ModeOffline.online){
            val productApi = productApiDao.getProductById(id).execute().body()
            return productApi?.let {
                Product(
                    productApi.id,
                    productApi.title,
                    productApi.price.times(100).toInt(),
                    productApi.category,
                    productApi.image,
                    productApi.description
                )
            }
        }
        else{
            return productRoomDao.getById(id)
        }
    }

    fun  putProduct(product: Product) : Unit{

    }

    fun  deleteProduct(id : Int) : Unit{

    }
}