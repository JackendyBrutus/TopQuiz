package com.jb.topquiz.controller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jb.topquiz.R;
import com.jb.topquiz.model.User;


public class MainActivity extends AppCompatActivity {

    //MY CODE
    //DECLARATION DES VARIABLES REPRESENTANT LES COMPOSANTS
    private TextView mGreetingTextView;
    private EditText mNameEditText;
    private Button mPlayButton;

    //CREATION DE CODE DE REQUETE POUR L'ACTIVITE
    private static final int GAME_ACTIVITY_REQUEST_CODE = 42;

    //DECLARATION ET INITIALISATION DES VARIABLES PERMETTANT D'ENREGISTRER LE USERNAME ET LE SCORE DANS UN FICHIER XML
    private static final String SHARED_PREF_USER_INFO = "SHARE_PREF_USER_INFO";
    private static final String SHARED_PREF_USER_INFO_NAME = "SHARED_PREF_USER_INFO_NAME";
    private static final String SHARED_PREF_USER_INFO_SCORE = "SHARED_PREF_USER_INFO_SCORE";

    //CREATION DE L'ATTRIBUT USER
    private User mUser;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (GAME_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode) {
            // Fetch the score from the Intent
            int score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE, 0);

            //SAVE SCORE IN XML FILE VIA SHAREDPREFERENCES
            getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE)
                    .edit()
                    .putInt(SHARED_PREF_USER_INFO_SCORE, score)
                    .apply();
        }

        //APPEL A LA METHODE UPDATEDISPLAY POUR MODIFIER L'AFFICHAGE LORSQU'UNE PARTIE SE TERMINE (LORSQUE LA GAMEACTIVITY EST FERMEE)
        updateDisplay(mGreetingTextView, mNameEditText, mPlayButton);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //MY CODE
        //INITIALISATION DES VARIABLES AVEC LES COMPOSANTS CORRESPONDANTS
        mGreetingTextView = findViewById(R.id.main_textview_greeting);
        mNameEditText = findViewById(R.id.main_edittext_name);
        mPlayButton = findViewById(R.id.main_button_play);

        //INITIALISATION DE L'ATTRIBUT DE TYPE USER
        mUser = new User();

        //DESACTIVATION DU BOUTON AU DEMARRAGE DE L'APPLICATION
        mPlayButton.setEnabled(false);

        //APPEL DE LA METHODE UPDATEDISPLAY POUR MODIFIER L'AFFICHAGE S'IL Y A UN SCORE ET UN USERNAME STOCKES DANS LE FICHIER XML GERE PAR L'API SHAREPREFERENCES
        updateDisplay(mGreetingTextView, mNameEditText, mPlayButton);

        //ETRE NOTIFIE QUAND L'UTILISATEUR COMMENCE A SAISIR DU TEXTE
        mNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //ACTIVATION DU BOUTON DES QUE L'UTILISATEUR ENTRE SON NOM DANS LE CHAMP TEXT
                mPlayButton.setEnabled(!s.toString().isEmpty());
            }
        });

        //
        mPlayButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //SAVE USERNAME IN XML FILE VIA SHAREDPREFERENCES
                SharedPreferences preferences = getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(SHARED_PREF_USER_INFO_NAME, mNameEditText.getText().toString());
                editor.apply();

                Intent gameActivityIntent = new Intent(MainActivity.this, GameActivity.class);
                startActivityForResult(gameActivityIntent, GAME_ACTIVITY_REQUEST_CODE);
                mUser.setFirstname(mNameEditText.getText().toString());
            }
        });

    }

    //CREATION DE LA METHODE UPDATEDISPLAY
    private void updateDisplay(TextView textView, EditText editText, Button button){
        String firstname = getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE).getString(SHARED_PREF_USER_INFO_NAME, null);
        int score = getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE).getInt(SHARED_PREF_USER_INFO_SCORE, 0);

        if(firstname != null && score != 0){
            textView.setText("Welcome back, " + firstname + "!\nYour last score was " + score + ", will you do better this time?");
            editText.setText(firstname);
            editText.setSelection(mNameEditText.getText().length());
            button.setEnabled(true);
        }
    }
}