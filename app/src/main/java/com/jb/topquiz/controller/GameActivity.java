package com.jb.topquiz.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

    private int passScore;

    //CREATION DE CLE
    public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";


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

        //INITIALISATION DE LA VARIABLE REPRESENTANT LE NOMBRE DE QUESTION
        mRemainingQuestionCount = 5;

        //INITIALISATION DE LA VARIABLE REPRESENTANT LE SCORE DU JOUEUR
        mScore = 0;

        passScore = (mRemainingQuestionCount % 2) == 0 ? mRemainingQuestionCount / 2 : (mRemainingQuestionCount / 2) + 1;
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

        return new QuestionBank(Arrays.asList(question1, question2, question3, question4, question5, question6, question7, question8, question9, question10));
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
        if(index == mQuestionBank.getCurrentQuestion().getAnswerIndex()){
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            mScore++;
        }
        else{
            Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show();
        }

        //VERIFIE SI ON DOIT ARRETER LE JEU
        mRemainingQuestionCount--;

        if (mRemainingQuestionCount > 0) {
            mCurrentQuestion = mQuestionBank.getNextQuestion();
            displayQuestion(mCurrentQuestion);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle((mScore >= passScore ? "Well done!" : "Too bad!"))
                    .setMessage("Your score is " + mScore)
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

}