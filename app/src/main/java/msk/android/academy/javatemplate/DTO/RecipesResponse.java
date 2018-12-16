package msk.android.academy.javatemplate.DTO;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.reactivex.annotations.Nullable;

public class RecipesResponse {
    private List<HitsDTO> hits;

    @Nullable
    public List<HitsDTO> getData() {
        //results.forEach(item -> );
        return hits;
    }
}
