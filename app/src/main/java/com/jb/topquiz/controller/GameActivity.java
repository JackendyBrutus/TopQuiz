package com.jb.topquiz.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jb.topquiz.R;
import com.jb.topquiz.model.Question;
import com.jb.topquiz.model.QuestionBank;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class GameActivity extends AppCompatActivity implements View.OnClickListener{

    //MY CODE
    //DECLARATION DES VARIABLES REPRESENTANT LES COMPOSANTS
    private TextView mQuestionTextView;
    private Button mAnswer1Button;
    private Button mAnswer2Button;
    private Button mAnswer3Button;
    private Button mAnswer4Button;

    //CREATION DE L'ATTRIBUT DE TYPE QUESTIONBANK ET INITIALISATION
    QuestionBank mQuestionBank = generateQuestions();

    private Question mCurrentQuestion;

    //DECLARATION DE VARIABLE REPRESENTANT LE NOMBRE DE QUESTION
    private int mRemainingQuestionCount;

    //DECLARATION DE VARIABLE REPRESENTANT LE SCORE DE L'UTILISTEUR
    private int mScore;

    private int numberOfAskedQuestions;
    private int passScore;

    //CREATION DE CLE
    public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";

    public static final String BUNDLE_STATE_SCORE = "BUNDLE_STATE_SCORE";
    public static final String BUNDLE_STATE_QUESTION = "BUNDLE_STATE_QUESTION";

    //DECLARATION DE VARIABLE BOOLEENE PERMETTANT DE GERER L'ACTIVATION DE LA DESACTIVATION DES BOUTONS
    private boolean mEnableTouchEvents;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return mEnableTouchEvents && super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(BUNDLE_STATE_SCORE, mScore);
        outState.putInt(BUNDLE_STATE_QUESTION, mRemainingQuestionCount);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //MYCODE
        //INITIALISATION DES VARIABLES AVEC LES COMPOSANTS CORRESPONDANTS
        mQuestionTextView = findViewById(R.id.game_activity_textview_question);
        mAnswer1Button = findViewById(R.id.game_activity_button_1);
        mAnswer2Button = findViewById(R.id.game_activity_button_2);
        mAnswer3Button = findViewById(R.id.game_activity_button_3);
        mAnswer4Button = findViewById(R.id.game_activity_button_4);

        //CREATION DE LISTENER POUR CHAQUE BOUTON
        mAnswer1Button.setOnClickListener(this);
        mAnswer2Button.setOnClickListener(this);
        mAnswer3Button.setOnClickListener(this);
        mAnswer4Button.setOnClickListener(this);

        mCurrentQuestion = mQuestionBank.getCurrentQuestion();

        //APPEL DE LA METHODE DISPLAY QUESTION
        displayQuestion(mCurrentQuestion);

        //ACTIVATION DES BOUTONS
        mEnableTouchEvents = true;

        numberOfAskedQuestions = 10;

        //INITIALISATION DE LA VARIABLE REPRESENTANT LE NOMBRE DE QUESTION ET LE SCORE DU JOUEUR
        if(savedInstanceState != null){
            mRemainingQuestionCount = savedInstanceState.getInt(BUNDLE_STATE_QUESTION);
            mScore = savedInstanceState.getInt(BUNDLE_STATE_SCORE);
        }
        else{
            mRemainingQuestionCount = numberOfAskedQuestions;
            mScore = 0;
        }
        passScore = (numberOfAskedQuestions % 2) == 0 ? numberOfAskedQuestions / 2 : (numberOfAskedQuestions / 2) + 1;
    }


    //IMPLEMENTATION DE LA METHODE DISPLAYQUESTION
    private void displayQuestion(final Question question){
        mQuestionTextView.setText(question.getQuestion());
        mAnswer1Button.setText(question.getChoiceList().get(0));
        mAnswer2Button.setText(question.getChoiceList().get(1));
        mAnswer3Button.setText(question.getChoiceList().get(2));
        mAnswer4Button.setText(question.getChoiceList().get(3));
    }


    //IMPLEMENTATION DE LA METHODE generateQuestions
    private QuestionBank generateQuestions(){
        Question question1 = new Question(
                "Who is the creator of Android?",
                Arrays.asList(
                        "Andy Rubin",
                        "Steve Wozniak",
                        "Jake Wharton",
                        "Paul Smith"
                ),
                0
        );

        Question question2 = new Question(
                "When did the first man land on the moon?",
                Arrays.asList(
                        "1958",
                        "1962",
                        "1967",
                        "1969"
                ),
                3
        );

        Question question3 = new Question(
                "What is the house number of The Simpsons?",
                Arrays.asList(
                        "42",
                        "101",
                        "666",
                        "742"
                ),
                3
        );

        Question question4 = new Question(
                "What is the name of the network of computers from which the Internet has emerged?",
                Arrays.asList(
                        "Parnet",
                       "Arpanet",
                        "Netpar",
                        "Netarpa"
                ),
                1
        );

        Question question5 = new Question(
                "In what year was Google launched on the web?",
                Arrays.asList(
                        "1997",
                        "2008",
                        "1998",
                        "1999"
                ),
                2
        );

        Question question6 = new Question(
                "What is the country top-level domain of Belgium?",
                Arrays.asList(
                        "The .com domain",
                        "The .fr domain",
                        "The .be domain",
                        "The .edu domain"
                ),
                2
        );

        Question question7 = new Question(
                "Which unit is an indication for the sound quality of MP3?",
                Arrays.asList(
                        "Kpsb",
                        "Kspb",
                        "Ksbp",
                        "Kbps"
                ),
                3
        );

        Question question8 = new Question(
                "In computing what is Ram short for?",
                Arrays.asList(
                        "Random Access Memory",
                        "Raw Amazon Memory",
                        "Reason Acts Meaningfully",
                        "React Access Multiple"
                ),
                0
        );

        Question question9 = new Question(
                "What does USB stand for in the computer world?",
                Arrays.asList(
                        "Universal Serial Bios",
                        "Universal Serious Bios",
                        "Unity Serial Bus",
                        "Universal Serial Bus"
                ),
                3
        );

        Question question10 = new Question(
                "What does \"FTP\" stand for in the computer and internet world?",
                Arrays.asList(
                        "File Track Protocol",
                        "File Transfer Protocol",
                        "File Treatment Protocol",
                        "Find Task Protocol"
                ),
                1
        );

        Question question11 = new Question(
                "Which device do we use to look at the stars?",
                Arrays.asList(
                        "Kaleidoscope",
                        "Stethoscope",
                        "Telescope",
                        "Oscilloscope"
                ),
                1
        );

        Question question12 = new Question(
                "Which device is used to measure the air pressure?",
                Arrays.asList(
                        "Microscope",
                        "Stethoscope",
                        "Barometer",
                        "Oscilloscope"
                ),
                2
        );

        Question question13 = new Question(
                "Who is the father of the atomic bomb?",
                Arrays.asList(
                        "Jhon Dee",
                        "Robert Oppenheimer",
                        "Robert Green",
                        "Nicolas Tesla"
                ),
                1
        );

        Question question14 = new Question(
                "Two brothers invented the hot air balloon. What was their surname?",
                Arrays.asList(
                        "Montgolfier",
                        "Montgolier",
                        "Montgolia",
                        "Mongol"
                ),
                0
        );

        Question question15 = new Question(
                "Who was the inventor of the steam engine?",
                Arrays.asList(
                        "James Watt",
                        "James Madison",
                        "Jean Lafortune",
                        "James Brown"
                ),
                0
        );

        Question question16 = new Question(
                "Which device was invented by Henry Mill?",
                Arrays.asList(
                        "Thermometer",
                        "Computer",
                        "Typewriter",
                        "Potentiometer"
                ),
                2
        );

        Question question17 = new Question(
                "What is the lightest existing metal?",
                Arrays.asList(
                        "Beryllium",
                        "Lithium",
                        "magnesium",
                        "Aluminium"
                ),
                1
        );

        Question question18 = new Question(
                "What are the three primary colors?",
                Arrays.asList(
                        "Red, Green, Blue",
                        "Cyan, Magenta, Yellow",
                        "Blue, Black, White",
                        "Blue, Yellow, Red"
                ),
                3
        );

        Question question19 = new Question(
                "Which planet is nearest the sun?",
                Arrays.asList(
                        "Jupiter",
                        "X",
                        "Venus",
                        "Mercury"
                ),
                3
        );

        Question question20 = new Question(
                "What is the lightest chemical element?",
                Arrays.asList(
                        "Oxygen",
                        "Hydrogen",
                        "Nitrogen",
                        "Helium"
                ),
                1
        );

        Question question21 = new Question(
                "What is the brightest star in the northern hemisphere?",
                Arrays.asList(
                        "Canopus",
                        "Sirius",
                        "Arcturus",
                        "Vega"
                ),
                1
        );

        Question question22 = new Question(
                "In astronomy, what are Pallas, Vesta and Davida?",
                Arrays.asList(
                        "Comet",
                        "Asteroids",
                        "Meteorite",
                        "Black hole"
                ),
                1
        );

        Question question23 = new Question(
                "What is the color of giraffe tongue",
                Arrays.asList(
                        "Black or Grey",
                        "Blue or Black",
                        "Blue or Purple",
                        "Red or Pink"
                ),
                2
        );

        Question question24 = new Question(
                "Who was the first person to step on the moon?",
                Arrays.asList(
                        "Galileo Galilei",
                        "Nicolaus Copernicus",
                        "Neil Armstrong",
                        "Isaac Newton"
                ),
                2
        );

        Question question25 = new Question(
                "Who did the Mona Lisa paint?",
                Arrays.asList(
                        "Claude Monet",
                        "Pablo Picasso",
                        "Leonardo Da Vinci",
                        "Gustave Courbet"
                ),
                2
        );

        return new QuestionBank(Arrays.asList(question1, question2, question3, question4, question5, question6, question7, question8, question9, question10,
                question11, question12, question13, question14, question15, question16, question17, question18, question19, question20,
                question21, question22, question23, question24, question25));
    }

    @Override
    public void onClick(View v) {
        int index;

        if (v == mAnswer1Button) {
            index = 0;
        } else if (v == mAnswer2Button) {
            index = 1;
        } else if (v == mAnswer3Button) {
            index = 2;
        } else if (v == mAnswer4Button) {
            index = 3;
        } else {
            throw new IllegalStateException("Unknown clicked view : " + v);
        }

        //VERIFICATION DE LA REPONSE CHOSIE PAR L'UTILISATEUR
        MediaPlayer song;

        if(index == mQuestionBank.getCurrentQuestion().getAnswerIndex()){
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            mScore++;

            //SI REPONSE CORRECT, CHANGE LA COULEUR DU BOUTON EN VERT
            switch (index){
                case 0 :
                    mAnswer1Button.setBackgroundColor(Color.GREEN);
                    break;
                case 1:
                    mAnswer2Button.setBackgroundColor(Color.GREEN);
                    break;
                case 2:
                    mAnswer3Button.setBackgroundColor(Color.GREEN);
                    break;
                case 3 :
                    mAnswer4Button.setBackgroundColor(Color.GREEN);
                    break;
                default : break;
            }

           //SI REPONSE CORRECTE, JOUER SONG APPROPRIE
            song = MediaPlayer.create(this, R.raw.correct_answer_sound_effect);
            song.start();
        }
        else{
            Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show();

            //SI REPONSE INCORRECT, CHANGE LA COULEUR DU BOUTON EN ROUGE
            switch (index){
                case 0 :
                    mAnswer1Button.setBackgroundColor(Color.RED);
                    break;
                case 1:
                    mAnswer2Button.setBackgroundColor(Color.RED);
                    break;
                case 2:
                    mAnswer3Button.setBackgroundColor(Color.RED);
                    break;
                case 3 :
                    mAnswer4Button.setBackgroundColor(Color.RED);
                    break;
                default : break;
            }

            //SI REPONSE INCORRECTE, JOUER SONG APPORPRIE
            song = MediaPlayer.create(this, R.raw.wrong_answer_sound_effect);
            song.start();
        }

        //DESACTIVATION DES BOUTONS
        mEnableTouchEvents = false;

        //... PERMETTANT D'ATTENDRE QUE LE MESSAGE TOAST DISPARAIT AVANT D'AFFICHER LA QUESTION SUIVANTE
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //VERIFIE SI ON DOIT ARRETER LE JEU
                mRemainingQuestionCount--;

                if (mRemainingQuestionCount > 0) {
                    mCurrentQuestion = mQuestionBank.getNextQuestion();
                    displayQuestion(mCurrentQuestion);

                    //REMODIFIE LA COULEUR DU BOUTON QUI A ETE CHANGE APRES CLICK DANS SA COULEUR INITIALE
                    switch (index){
                        case 0:
                            mAnswer1Button.setBackgroundColor(getResources().getColor(R.color.purple_500));
                            break;
                        case 1:
                            mAnswer2Button.setBackgroundColor(getResources().getColor(R.color.purple_500));
                            break;
                        case 2:
                            mAnswer3Button.setBackgroundColor(getResources().getColor(R.color.purple_500));
                            break;
                        case 3:
                            mAnswer4Button.setBackgroundColor(getResources().getColor(R.color.purple_500));
                            break;
                        default: break;
                    }
                } else {
                    endGame();
                }

                //REACTIVATIONS DES BOUTONS
                mEnableTouchEvents = true;
            }
        }, 2000);

    }

    private void endGame(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle((mScore >= passScore ? "Well done!" : "Too bad!"))
                .setMessage("Your score is " + mScore + " out of " + numberOfAskedQuestions)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //RENVOIS DU SCORE A LA MAINACTIVITY
                        Intent intent = new Intent();
                        intent.putExtra(BUNDLE_EXTRA_SCORE, mScore);
                        setResult(RESULT_OK, intent);

                        finish();
                    }
                })
                .create()
                .show();
    }

}