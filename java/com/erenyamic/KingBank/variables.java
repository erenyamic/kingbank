package com.erenyamic.KingBank;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class variables {
    Button sign_up,turn_to_login,verify_btn,getSign_up;
    Intent intent;
    EditText sign_email,sign_pass,log_email,log_pass,verify;
    FirebaseAuth auth;
    ImageView lion;
    View undated,term,usd_r;
    RadioButton undated_btn,term_btn,usd_btn;
    TextView type_account,amount;
    FirebaseStorage storage;
    FirebaseFirestore firestore;
    StorageReference storageReference;
    SharedPreferences sharedPreferences;
    FirebaseUser user;
    long pressedTime;
    TextView ibn;
    String url="https://cryptoymc.com/userLogin.php";

}
