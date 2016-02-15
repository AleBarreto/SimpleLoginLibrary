package barreto.simpleloginlibrary.api_login;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by Alessandro on 09/01/2016.
 */
public class GoogleSign {

    private static final int SIGN_IN = 10;

    private GoogleApiClient mGoogleApiClient;
    private FragmentActivity mActivity;
    private InfoLoginGoogleCallback googleCallback;

    public GoogleSign(FragmentActivity mActivity, InfoLoginGoogleCallback googleCallback) {
        this.mActivity = mActivity;
        this.googleCallback = googleCallback;
        getConfigDefaultLogin();
    }

    private void getConfigDefaultLogin(){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(mActivity.getApplication().getApplicationContext())
                .enableAutoManage(mActivity, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(ConnectionResult connectionResult) {
                        googleCallback.connectionFailedApiClient(connectionResult);
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

    }

    public void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        mActivity.startActivityForResult(signInIntent, SIGN_IN);
    }

    public void resultGoogleLogin(int requestCode, int resultCode, Intent data){
        if (requestCode == SIGN_IN){
            if (data != null){
                GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                if (result != null && result.isSuccess()){
                    googleCallback.getInfoLoginGoogle(result.getSignInAccount());
                }else{
                    googleCallback.loginFailed();
                }
            }
        }
    }


    public interface InfoLoginGoogleCallback{
        void getInfoLoginGoogle(GoogleSignInAccount account);
        void connectionFailedApiClient(ConnectionResult connectionResult);
        void loginFailed();
    }

}
