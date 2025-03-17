import android.devicelock.DeviceId
import com.example.myapplication.ui.cart.CartResponse
import com.example.myapplication.ui.dashboard.AddToCartRequest
import com.example.myapplication.ui.dashboard.AddToCartResponse
import com.example.myapplication.ui.dashboard.Product
import com.example.myapplication.ui.dashboard.ProductResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface {
    @GET("home/featureproducts") // Ensure this matches your API endpoint correctly
    @Headers("DeviceId:44b4d8cd-7a2d-4a5f-a0e2-798021f1e294")
    suspend fun getProducts(): ProductResponse

    @POST("shoppingCart/addproducttocart/catalog/{productId}/1")
    @Headers("DeviceId:44b4d8cd-7a2d-4a5f-a0e2-798021f1e294")
    suspend fun addToCart(@Path("productId") productId: Int, @Body request: AddToCartRequest): Response<AddToCartResponse>

    @GET("shoppingcart/cart")
    @Headers("DeviceId:44b4d8cd-7a2d-4a5f-a0e2-798021f1e294")
    suspend fun getCart(): CartResponse
}