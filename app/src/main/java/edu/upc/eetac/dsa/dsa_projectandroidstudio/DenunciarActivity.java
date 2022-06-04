package edu.upc.eetac.dsa.dsa_projectandroidstudio;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.upc.eetac.dsa.dsa_projectandroidstudio.models.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//CLASSE AFEGIDA PEL MINIM2 IRENE GORDUN

public class DenunciarActivity extends AppCompatActivity {
    ApiServices services;

    //atributs enunciat
    EditText date;
    private EditText informer;
    private EditText message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denuncia);

        //elements del layout
        date = findViewById(R.id.dateText);
        informer = findViewById(R.id.userNameText);
        message = findViewById(R.id.descripcioText);

        Button enviar = findViewById(R.id.send_btn);

        //apreta botó enviar
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = date.getText().toString();
                String informer = informer.getText().toString();
                String message = message.getText().toString();

                services = ApiRetrofit.getApiService().create(ApiServices.class);

                //services.denuncia(new Denuncia(date, informer, message));

                //per guardar nom per futures denuncies
                SharedPreferences sharedPreferences = getSharedPreferences("userName", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("User", informer);
                editor.commit();


                //denuncia
                Denuncia ref = new Denuncia(date, informer, message);
                Call<User> call = services.denuncia(ref);

                call.enqueue(new Callback<Denuncia>(){
                    @Override
                    public void onResponse(Call<Denuncia> call, Response<Denuncia> response) {
                        //success
                        if(response.code() == 200) {
                            Log.d("User", "Denuncia completada");
                            Toast.makeText(getApplicationContext(),"Denuncia creada amb exit", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), DoneActivity.class);
                        }
                    }

                    @Override
                    public void onFailure(Call<Denuncia> call, Throwable t) {
                        Log.d("User", "Denuncia sense exit");
                        Toast.makeText(getApplicationContext(),"Denuncia sense exit", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), ErrorActivity.class);
                    }
                }
            }
        });
    }
}
