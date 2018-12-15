package msk.android.academy.javatemplate.NET;


import io.reactivex.Single;
import msk.android.academy.javatemplate.DTO.RecipesResponse;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EndPoint {

    //@GET("search?q={recipe}")
    @GET("search")
    Single<RecipesResponse> search(@Query("q") String q);

}