package fr.eni.filrouge.data.repository

import android.app.Application
import fr.eni.filrouge.data.dao.db.dbConfiguration.DatabaseClient
import fr.eni.filrouge.data.dao.rest.RetrofitClient
import fr.eni.filrouge.data.model.Product

enum class ModeOffline{offline, online}
class ProductRepository(val app: Application) {
    private var MODE : ModeOffline = ModeOffline.online;
    val productApiDao = RetrofitClient.productApiService
    val productRoomDao =  DatabaseClient(app.applicationContext).appDatabase.productDao()


    suspend fun syncApiDB(){
        //Je télécharge mes données du productApiDao (getAll)

        //Je rentre ces données dans la BDD (productRoomDao)

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