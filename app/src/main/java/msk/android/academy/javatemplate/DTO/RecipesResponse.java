package msk.android.academy.javatemplate.DTO;

import java.util.List;

import io.reactivex.annotations.Nullable;

public class RecipesResponse {
    private List<RecipesDTO> results;

    @Nullable
    public List<RecipesDTO> getData() {
        //results.forEach(item -> );
        return results;
    }
}
