package sabri.elmahdi.formula1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sabri.elmahdi.formula1.adapter.DriversAdapter;
import sabri.elmahdi.formula1.adapter.RoundedAdapter;
import sabri.elmahdi.formula1.service.ApiResponse;
import sabri.elmahdi.formula1.service.FormulaService;
import sabri.elmahdi.formula1.service.Team;
import sabri.elmahdi.formula1.service.TeamApiResponse;
import sabri.elmahdi.formula1.service.TeamDetail;

public class DetailsActivity extends AppCompatActivity implements RoundedAdapter.OnNoteListener{
    List<ApiResponse> drivers =new ArrayList<ApiResponse>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        DriverDetailsApplication app = (DriverDetailsApplication) getApplicationContext();

        TextView firstName = (TextView) findViewById(R.id.firstName);
        TextView lastName = (TextView) findViewById(R.id.lastName);
        TextView points = (TextView) findViewById(R.id.points);
        TextView position = (TextView) findViewById(R.id.position);
        TextView score = (TextView) findViewById(R.id.score);
        ImageView imageImageView = findViewById(R.id.picture);
        ImageView teamImageImageView = findViewById(R.id.teamImage);


        int id_separation = app.getDriver().getDriver().getName().lastIndexOf(' ');
        String firstName_ = app.getDriver().getDriver().getName().substring(0, id_separation);
        String lastName_  = app.getDriver().getDriver().getName().substring(id_separation + 1);

        firstName.setText(firstName_);
        lastName.setText(lastName_);
        points.setText("Nb Points : "+app.getDriver().getPoints());
        position.setText( "Positon au classement :" +String.valueOf(app.getDriver().getPosition()));
        Glide.with(getApplicationContext()).load(app.getDriver().getDriver().getImage()).into(imageImageView);

        FormulaService formulaService=new Retrofit.Builder().baseUrl(FormulaService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FormulaService.class);
        formulaService.getTeams().enqueue(new Callback<TeamApiResponse>() {
            @Override
            public void onResponse(Call<TeamApiResponse> call, Response<TeamApiResponse> response) {
                TeamApiResponse teams = response.body();
                TeamDetail team = teams.getTeams().stream()
                        .filter(t -> t.getTeam().getId() == app.getDriver().getTeam().getId())
                        .findAny()
                        .orElse(null);
                if (team.getPosition() ==1)
                    score.setText(String.valueOf(team.getPosition())+ "ére position au classement par équipe avec "+String.valueOf(team.getPoints()) + " pts.");
                else score.setText(String.valueOf(team.getPosition())+ "éme position au classement par équipe avec "+String.valueOf(team.getPoints()) + " pts.");

            }
            @Override
            public void onFailure(Call<TeamApiResponse> call, Throwable t) {
                t.getCause();
            }
        });

        Glide.with(getApplicationContext()).load(app.getDriver().getTeam().getLogo()).into(teamImageImageView);

        RoundedAdapter.OnNoteListener context= this;
        RecyclerView rvRoundedDrivers = (RecyclerView) findViewById(R.id.rvRoundedDrivers);
        formulaService.getDrivers().enqueue(new Callback<List<ApiResponse>>() {
            @Override
            public void onResponse(Call<List<ApiResponse>> call, Response<List<ApiResponse>> response) {
                drivers=response.body();
                RoundedAdapter adapter = new RoundedAdapter(drivers,getApplicationContext(),context);

                rvRoundedDrivers.setAdapter(adapter);
                rvRoundedDrivers.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

            }
            @Override
            public void onFailure(Call<List<ApiResponse>> call, Throwable t) {
                t.getCause();
            }
        });
        appBarColorChange();

    }

    private void appBarColorChange() {
        ActionBar actionBar;
        actionBar = getSupportActionBar();

        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#db2e20"));
        actionBar.setBackgroundDrawable(colorDrawable);
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this.getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
    @Override
    public void onNoteClick(int position) {
        DriverDetailsApplication app = (DriverDetailsApplication) getApplicationContext();
        app.setDriver(drivers.get(position));
        Intent intent = new Intent(this.getApplicationContext(), DetailsActivity.class);
        startActivity(intent);
    }
}