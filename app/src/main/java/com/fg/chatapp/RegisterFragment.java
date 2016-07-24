package com.fg.chatapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by fred on 7/9/2016.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener{

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonRegister;
    private ProgressDialog progressDialog;

    FirebaseAuth firebaseAuth;

    private static final String TAG = "register";
    public RegisterFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_layout, null);

        firebaseAuth = FirebaseAuth.getInstance();

        editTextEmail = (EditText) view.findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) view.findViewById(R.id.editTextPassword);

        buttonRegister = (Button) view.findViewById(R.id.buttonRegister);

        progressDialog = new ProgressDialog(getContext());

        buttonRegister.setOnClickListener(this);
        return view;
    }

    private void registerUser(String email, String password){

        Log.d(TAG, "EmailAndPassword" +email);
        if (!validateEmail()){
            return;
        }
        if (!validatePassword()){
            return;
        }

        //if the email and password are not empty
        //displaying a progress dialog
        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();

        //create a new user
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if (task.isSuccessful()) {
                            //display some message here
                            Toast.makeText(getContext(), "Successfully registered", Toast.LENGTH_LONG).show();
                            getFragmentManager().beginTransaction().replace(R.id.containerView, new SigninFragment()).commit();
                        } else {
                            //display some message here
                            Toast.makeText(getContext(), "Registration Error", Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }

    //  validate email input method
    private boolean validateEmail(){
        boolean isValid = true;
        // get email address string from editText view
        String email = editTextEmail.getText().toString().trim();
        if (email.isEmpty() || !isEmailValid(email)){
            editTextEmail.setError(getString(R.string.email_password_input));
            isValid = false;
        }
        return isValid;
    }

    // validate password input method
    private boolean validatePassword(){
        boolean isValid = true;
        // get password string from editText view
        String password = editTextPassword.getText().toString().trim();
        if (password.isEmpty() || !isPasswordValid(password)){
            editTextPassword.setError(getString(R.string.email_password_input));
            isValid = false;
        }
        return isValid;
    }

    private static boolean isEmailValid(String email){
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private static boolean isPasswordValid(String password){
        return password.length() >=5;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonRegister:
                registerUser(editTextEmail.getText().toString().trim(), editTextPassword.getText().toString().trim());
                break;
        }

    }
}
