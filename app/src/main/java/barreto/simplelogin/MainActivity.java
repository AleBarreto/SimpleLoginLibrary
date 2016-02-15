package barreto.simplelogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.facebook.FacebookException;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;

import barreto.simpleloginlibrary.api_login.FacebookSign;
import barreto.simpleloginlibrary.api_login.GoogleSign;

public class MainActivity extends AppCompatActivity implements GoogleSign.InfoLoginGoogleCallback, FacebookSign.InfoLoginFaceCallback {

    GoogleSign googleSign; // Google sign-in
    FacebookSign facebookSign; // Facebook sign-in

    // if you use custom button
    SignInButton signInButton;// Button custom google
    LoginButton loginButton; // Button custom facebook;

    /**
     * Bind Views, custom button
     */
    private void bindViews(){
        signInButton = (SignInButton)findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                googleSign.signIn(); // when click the button, will be signed
            }
        });
        loginButton = (LoginButton)findViewById(R.id.login_button);
        facebookSign.signInWithFaceButton(loginButton); // when click the button, will be signed
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // FragmentActivity and interface listener
        googleSign      = new GoogleSign(this,this);

        // FragmentActivity and interface listener
        facebookSign    = new FacebookSign(this,this);

        setContentView(R.layout.activity_main);

        bindViews();//call method.

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        googleSign.resultGoogleLogin(requestCode, resultCode, data); // result
        facebookSign.resultFaceLogin(requestCode,resultCode,data); // result
    }

    // EVENT CLICK BUTTON SIMPLE
    public void loginButtonGoogle (View view) {
        googleSign.signIn();
    }

    public void loginButtonFacebook (View view) {
        facebookSign.signIn();
    }

    // LISTNER GOOGLE SIGN-IN

    @Override
    public void getInfoLoginGoogle(GoogleSignInAccount account) {
        Log.w("LOG","Name "+account.getDisplayName());
        Log.v("LOG","Email " + account.getEmail());
        Log.v("LOG","Photo "+account.getPhotoUrl());
    }

    @Override
    public void connectionFailedApiClient(ConnectionResult connectionResult) {
        Log.e("LOG","Connection Failed API "+connectionResult.getErrorMessage());
    }

    @Override
    public void loginFailed() {
        Log.e("LOG","Login Failed");
    }

    // LISTNER FACEBOOK SIGN-IN

    @Override
    public void getInfoFace(String id, String name, String email, String photo) {
        Log.w("LOG","Name "+name);
        Log.w("LOG","Email "+email);
        Log.w("LOG","Photo "+photo);
    }

    @Override
    public void cancelLoginFace() {
        Log.e("LOG","Login cancel");
    }

    @Override
    public void erroLoginFace(FacebookException e) {
        Log.e("LOG","Erro Login Face "+e.getMessage());
    }
}
