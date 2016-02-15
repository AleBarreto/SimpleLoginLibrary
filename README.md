#Library to help developers it integrate (Google Sign-in) and (Facebook Sign-in)

It's simple:
>Login with Facebook:

```java
public class MainActivity extends AppCompatActivity
        implements FacebookSign.InfoLoginFaceCallback {
      
      FacebookSign facebookSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        facebookSign = new FacebookSign(this,this);
    
    }
    
     @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        facebookSign.resultFaceLogin(requestCode,resultCode,data);
    }

    @Override
    public void getInfoFace(String id, String name, String email, String photo) {

    }

    @Override
    public void cancelLoginFace() {

    }

    @Override
    public void erroLoginFace(FacebookException e) {

    }
}
```
>Login with Google:

```java
public class MainActivity extends AppCompatActivity 
        implements GoogleSign.InfoLoginGoogleCallback {

    GoogleSign googleSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        googleSign = new GoogleSign(this,this);

    }
    
     @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        googleSign.resultGoogleLogin(requestCode, resultCode, data);
    }

    @Override
    public void getInfoLoginGoogle(GoogleSignInAccount account) {

    }

    @Override
    public void connectionFailedApiClient(ConnectionResult connectionResult) {

    }

    @Override
    public void loginFailed() {

    }
}
```

# Getting started
###### Requisite of [Google Sign-in Console](https://developers.google.com/mobile/add?platform=android&cntapi=signin&cnturl=https:%2F%2Fdevelopers.google.com%2Fidentity%2Fsign-in%2Fandroid%2Fsign-in%3Fconfigured%3Dtrue&cntlbl=Continue%20Adding%20Sign-In).
1. add a new app.
2. follow all instructions of creation.
3. Enable API Google sign-in.
4. Copy the google-services.json file you just downloaded into the app/ or mobile/ directory of your Android Studio project.

#### Gradle Dependency
>Add it in your root build.gradle at the end of repositories:

```
allprojects {
	repositories {
		...
		maven { url "https://jitpack.io" }
	}
}
```

> File build.gradle app-level

```

//Add the dependency	
apply plugin: 'com.google.gms.google-services' // requisite of Google Services
dependencies {
        compile 'com.github.AleBarreto:SimpleLoginLibrary:-SNAPSHOT'
}

```
> File build.gradle project-level

```
classpath 'com.google.gms:google-services:2.0.0-alpha5' // requisite of Google Services
```

###### Requisite of [Facebook SDK](https://developers.facebook.com/apps/).
1. add a new app.
2. follow all instructions of creation.
3. Copy your id.

```xml

<uses-permission android:name="android.permission.INTERNET"/>

 <!-- Facebook Config -->
        <meta-data
            android:name="com.facebook.sdk.ApplicationId"
            android:value="ID_VALUE" />

        <activity
            android:name="com.facebook.FacebookActivity"
            android:configChanges="keyboard|keyboardHidden|screenLayout|screenSize|orientation"
            android:label="@string/app_name"
            android:theme="@android:style/Theme.Translucent.NoTitleBar" />
  <!-- Facebook Config END -->
```

## When click the button

```java

  public void loginButtonGoogle (View view) {
    facebookSign.signIn();
  }
  
  public void loginButtonFacebook (View view) {
    googleSign.signIn();
  }
  
  // OR
  
  @Override
  public void onClick(View v) {
      switch (v.getId()){
          case R.id.yourIdButtonGoogle:
              googleSign.signIn();
              break;
          case R.id.yourIdButtonFace:
              facebookSign.signIn();
              break;
      }
  }
  
```
