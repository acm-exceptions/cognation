package com.exception.jayus;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText mEmailFld;
    private EditText mPasswordFld;
    private Button mLoginBtn;
    private ProgressDialog mProgress;
    private FirebaseAuth mAuth;
    private SliderLayout mSliderLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        mEmailFld = (EditText) findViewById(R.id.et_login_email);
        mPasswordFld = (EditText) findViewById(R.id.et_login_password);
        mLoginBtn = (Button) findViewById(R.id.btn_login);

        mProgress = new ProgressDialog(LoginActivity.this);
        mAuth = FirebaseAuth.getInstance();
        mSliderLayout = (SliderLayout) findViewById(R.id.sl);

        TextSliderView textSliderView1 = new TextSliderView(LoginActivity.this);
        textSliderView1.image("https://www.cognizant.com/content/dam/Cognizant_Dotcom/homepage_images/HPmarquee_1024x730_ML.jpg").setScaleType(BaseSliderView.ScaleType.CenterCrop);

        TextSliderView textSliderView2 = new TextSliderView(LoginActivity.this);
        textSliderView2.image("https://www.cognizant.com/content/dam/Cognizant_Dotcom/homepage_images/HPmarquee_1024x730_DA.jpg").setScaleType(BaseSliderView.ScaleType.CenterCrop);

        mSliderLayout.addSlider(textSliderView1);
        mSliderLayout.addSlider(textSliderView2);

        mAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                    Toast.makeText(LoginActivity.this, firebaseAuth.getCurrentUser().getEmail(), Toast.LENGTH_LONG).show();
                }
            }
        });

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authenticate(mEmailFld.getText().toString(), mPasswordFld.getText().toString());
            }
        });

    }

    private void authenticate(String email, String password) {
        if (!(email.isEmpty() || password.isEmpty())) {
            mProgress.setMessage("Logging in...");
            mProgress.show();
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, "You have entered an invalid email/password!", Toast.LENGTH_LONG).show();
                    }
                    mProgress.dismiss();
                }
            });
        }
    }
}
