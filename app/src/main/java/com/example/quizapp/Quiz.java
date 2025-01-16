package com.example.quizapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;



public class Quiz extends AppCompatActivity {

    ImageView q1,q2,q3,q4,q5,q6;
    TextView t,questionCount;
    RadioGroup r1,r2,r3,r4,r5,r6;
    RadioButton o1,o2,o3,o4,o5,o6;

    Button next;

    int counter = 1;
    int questionCounter = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz);

        //Initializing my question counter
        questionCount = findViewById(R.id.questionCount);

        //Initializing my questions
        t = findViewById(R.id.prompt);

        //Initializing my images
        q1 = findViewById(R.id.image1);
        q2 = findViewById(R.id.image2);
        q3 = findViewById(R.id.image3);
        q4 = findViewById(R.id.image4);
        q5 = findViewById(R.id.image5);
        q6 = findViewById(R.id.image6);

        //Initializing the Radio options
        r1 = findViewById(R.id.radioGroup1);

        //Initializing the Next button
        next = findViewById(R.id.next);


        //Here are my arrays to store the questions and images corresponding to my images
        String[] questions = {
                "What programming language is this?",
                "What data structure is this?",
                "What is the space complexity of a Queue?",
                "What basic principle does a Stack use?",
                "Which algorithm finds the shortest path between nodes in a weighted graph?",
                "What is the fastest time complexity?"
        };
        ImageView[] images = {
                q1,q2,q3,q4,q5,q6
        };

        //Ensuring only the first image appears
        for(int i = 1; i < images.length; i++){
            images[i].setVisibility(View.INVISIBLE);
        }
        images[0].setVisibility(View.VISIBLE);

        //When you select the next button
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(counter < 6) {
                    switchImages(images, images[counter]);
                    counter++;
                    questionCount.setText("Question " + counter + "/6");
                    questionCounter++;
                    switchQuestion(questions[questionCounter]);
                }
                else{
                    Intent
                }
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void switchImages(ImageView[] images, ImageView image) {
        for(int i = 0; i < images.length; i++){
            images[i].setVisibility(View.INVISIBLE);
        }
        image.setVisibility(View.VISIBLE);
    }

    private void switchQuestion(String question) {
        t.setText(question);
    }

}