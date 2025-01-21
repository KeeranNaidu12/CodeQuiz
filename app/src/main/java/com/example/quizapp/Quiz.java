package com.example.quizapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class Quiz extends AppCompatActivity {

    ImageView q1,q2,q3,q4,q5,q6;
    TextView t,questionCount;
    RadioGroup questionSet;
    RadioButton o1,o2,o3,o4;

    Button next;

    int counter = 1;
    int questionCounter = 0;
    int correct = 0;

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
        questionSet = findViewById(R.id.radioGroup1);

        o1 = findViewById(R.id.q11);
        o2 = findViewById(R.id.q12);
        o3 = findViewById(R.id.q13);
        o4 = findViewById(R.id.q14);

        //Initializing the Next button
        next = findViewById(R.id.next);


        //Here are my arrays to store the questions, answers and images corresponding to my images
        String[] questions = {
                "What programming language is this?",
                "What data structure is this?",
                "What is the space complexity of a Queue?",
                "What basic principle does a Stack use?",
                "Which algorithm finds the shortest path between nodes in a weighted graph?",
                "What is the fastest time complexity?"
        };

        String answers[][] = {
                {"Python","JavaScript","Java","HTML"},
                {"Heap","Binary Tree", "Stack", "Queue"},
                {"O(1)","log(N)","O(N)","Nlog(N)"},
                {"FIFO","HIFO","MIFO","LIFO"},
                {"Dijkstra's algorithm","Sorting algorithm","Greedy Algorithm","Binary Sort"},
                {"Nlog(N)","O(N)","Nlog(N)","O(1)"}
        };

        String[] correctAnswers = {
                "Java", "Binary Tree", "O(1)", "LIFO", "Dijkstra's algorithm","O(1)"
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

                    int possibleAnswer = questionSet.getCheckedRadioButtonId();
                    if(possibleAnswer != -1) {
                        RadioButton chosenAnswer = findViewById(possibleAnswer);
                        String finalizedAnswer = chosenAnswer.getText().toString();
                        correct = checkAnswer(finalizedAnswer, correctAnswers[questionCounter], correct);
                        if (counter < 6) {
                            switchImages(images, images[counter]);
                            counter++;
                            questionCount.setText("Question " + counter + "/6");
                            questionCounter++;

                            switchOptions(answers, questionCounter);
                            switchQuestion(questions[questionCounter]);
                            questionSet.clearCheck();
                        } else {
                            Intent intent = new Intent(Quiz.this, ResultsScreen.class);
                            intent.putExtra("total", correct);
                            startActivity(intent);
                        }
                    }else{
                        Toast.makeText(Quiz.this,"Please select an answer!",Toast.LENGTH_SHORT).show();
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

    private void switchOptions(String[][] answers,int questionCounter){
        o1.setText(answers[questionCounter][0]);
        o2.setText(answers[questionCounter][1]);
        o3.setText(answers[questionCounter][2]);
        o4.setText(answers[questionCounter][3]);
    }

    private int checkAnswer(String answer, String checkAnswers, int correct) {
        if (answer.equals(checkAnswers))
            correct++;
        return correct;
    }

}