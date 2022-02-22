package sabri.elmahdi.formula1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sabri.elmahdi.formula1.adapter.DriversAdapter;
import sabri.elmahdi.formula1.service.ApiResponse;
import sabri.elmahdi.formula1.service.Driver;
import sabri.elmahdi.formula1.service.FormulaService;
import sabri.elmahdi.formula1.service.Team;

public class MainActivity extends AppCompatActivity implements DriversAdapter.OnNoteListener{
    List<ApiResponse> drivers =new ArrayList<ApiResponse>();
    List<ApiResponse> newDrivers =new ArrayList<ApiResponse>();
    DriversAdapter adapter;
    Drawable drawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rvDrivers = (RecyclerView) findViewById(R.id.rvDrivers);
        DriversAdapter.OnNoteListener context= this;

        FormulaService formulaService=new Retrofit.Builder().baseUrl(FormulaService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FormulaService.class);

        formulaService.getDrivers().enqueue(new Callback<List<ApiResponse>>() {
            @Override
            public void onResponse(Call<List<ApiResponse>> call, Response<List<ApiResponse>> response) {
                drivers=response.body();
                newDrivers.addAll(drivers);

                adapter = new DriversAdapter(newDrivers,getApplicationContext(),context);

                rvDrivers.setAdapter(adapter);

                rvDrivers.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

            }
            @Override
            public void onFailure(Call<List<ApiResponse>> call, Throwable t) {
                t.getCause();
            }
        });


        EditText yourEditText = (EditText) findViewById(R.id.editText);

        yourEditText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                newDrivers.clear();
                for (ApiResponse driver: drivers) {
                    if (driver.getDriver().getName().contains(String.valueOf(s))) {
                        newDrivers.add(driver);
                    }
                }
                adapter.notifyDataSetChanged();

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });



        appBarColorChange();
    }

    private void appBarColorChange() {
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        Resources res = getResources();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#db2e20"));
        actionBar.setBackgroundDrawable(colorDrawable);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.icon_id) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Partiel Février 2022");
            alert.setMessage("SABRI EL MAHDI");
            alert.setPositiveButton("Fermer", null);
            alert.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onNoteClick(int position) {
        DriverDetailsApplication app = (DriverDetailsApplication) getApplicationContext();
        app.setDriver(drivers.get(position));
        Intent intent = new Intent(this.getApplicationContext(), DetailsActivity.class);
        startActivity(intent);
    }
}