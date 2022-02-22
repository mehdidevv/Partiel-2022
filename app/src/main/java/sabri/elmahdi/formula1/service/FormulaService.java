package sabri.elmahdi.formula1.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FormulaService {
    public static final String ENDPOINT = "https://busin.fr/";

    @GET("drivers.json")
    Call<List<ApiResponse>> getDrivers();

    @GET("teams.json")
    Call<TeamApiResponse> getTeams();
}
