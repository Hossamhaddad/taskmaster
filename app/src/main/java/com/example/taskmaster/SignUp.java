package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        try {
//            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.configure(getApplicationContext());

            Log.i("MyAmplifyApp", "Initialized Amplify.");
        } catch (AmplifyException exception) {
            Log.i("Error", exception.toString());
        }
        ;
        findViewById(R.id.SignUpButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Amplify.Auth.fetchAuthSession(
                        result -> {
                            Log.i("AuthQuickStart", result.toString());
                            if (!result.isSignedIn()) {
                                Amplify.Auth.signInWithWebUI(
                                        SignUp.this,
                                        results -> Log.i("AuthQuickStart", results.toString()),
                                        error -> Log.e("AuthQuickStart", error.toString())
                                );
                            } else {
                                Intent mainAc=new Intent(SignUp.this,MainActivity.class);
                                startActivity(mainAc);
                            }
                        }, error -> Log.e("AmplifyQuickstart", error.toString()));
            }
        }) ;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("___________", "onResume: ");
        Amplify.Auth.fetchAuthSession(
                result -> {
                    Log.i("AuthQuickStart", result.toString());
                    if (!result.isSignedIn()) {
                        Amplify.Auth.signInWithWebUI(
                                this,
                                results -> Log.i("AuthQuickStart", results.toString()),
                                error -> Log.e("AuthQuickStart", error.toString())
                        );
                    } else {
                        Intent mainAct=new Intent(SignUp.this,MainActivity.class);
                        startActivity(mainAct);
                    }
                }, error -> Log.e("AmplifyQuickstart", error.toString()));
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("+++++++++++", "onActivityResult: ");
        if (requestCode == AWSCognitoAuthPlugin.WEB_UI_SIGN_IN_ACTIVITY_CODE) {
            Amplify.Auth.handleWebUISignInResponse(data);
        }
    }
}
