package edu.upc.eetac.dsa.dsa_projectandroidstudio;

import edu.upc.eetac.dsa.dsa_projectandroidstudio.models.Denuncia;
import edu.upc.eetac.dsa.dsa_projectandroidstudio.models.LogInCredentials;
import edu.upc.eetac.dsa.dsa_projectandroidstudio.models.SignUpCredentials;
import edu.upc.eetac.dsa.dsa_projectandroidstudio.models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiServices {

    @POST("/dsaApp/user/logIn")
    Call<User>logIn(@Body LogInCredentials ref);

    @POST("/dsaApp/user/addUser")
    Call<User>signUp(@Body SignUpCredentials ref);

    //POST AFEGIT PER IRENE GORDUN PEL MINIM2

    @POST("/dsaApp/user/denuncia")
    Call<User>denuncia(@Body Denuncia ref);
}

