package com.example.yeilz.practice101;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by yeilz on 4/14/16.
 */
public class Act1 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.act1, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialization of the view
        final TextView rand1 = (TextView) view.findViewById(R.id.tv_rand1);
        final TextView rand2 = (TextView) view.findViewById(R.id.tv_rand2);
        final TextView wrong = (TextView) view.findViewById(R.id.tv_wrongChoice);

        final EditText guess = (EditText) view.findViewById(R.id.et_guess);

        Button generate = (Button) view.findViewById(R.id.btn_guess);

        // Button Generate Random Numbers is clicked
        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Generate Random Numbers
                Random random1 = new Random();
                Random random2 = new Random();

                // Assign the generated numbers to a variable for matching use
                // Set Random Numbers Minimum @ 1 and Maximum @ 20
                // show the generated random numbers in the textView
                String leftNumber = random1.nextInt(21 -1)+1 + "";
                String rytNumber  = random2.nextInt(21 -1)+1 + "";
                rand1.setText(leftNumber);
                rand2.setText(rytNumber);

                // condition
                if(guess.getText().toString().isEmpty())
                {
                    // if the textfield is empty
                    // show the warning message in the textView
                    // change the visibility Gone to Visible
                    wrong.setVisibility(View.VISIBLE);
                    wrong.setText("Please guess a number, to proceed.");
                } else
                {
                    // if the textfield is not empty
                    // check if the guess number is match between the two generated numbers
                    if(guess.getText().toString().matches(leftNumber) || guess.getText().toString().matches(rytNumber))
                    {
                        // if the user guess correct show alert dialog to congratulate
                        // set the visibility of the wrong message to GONE
                        wrong.setVisibility(View.GONE);
                        android.app.AlertDialog.Builder err = new android.app.AlertDialog.Builder(getContext());
                        err.setMessage("Congratulation your guess is correct. You can proceed to the next activity or you can try again.");
                        err.setCancelable(false);
                        err.setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // go to the next view
                                // initialize fragment2
                                // replace fragment1 to fragment2 for the next activity
                                Act2 fragment2 = new Act2();
                                FragmentManager manager = getFragmentManager();
                                FragmentTransaction transaction = manager.beginTransaction();
                                transaction.replace(R.id.ll_main, fragment2);
                                transaction.commit();
                            }
                        });
                        err.setNegativeButton("Try Again", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                // clear all the view for the next round :)
                                guess.setText("");
                                rand1.setText("-");
                                rand2.setText("-");
                            }
                        });

                        android.app.AlertDialog createDialog = err.create();
                        createDialog.show();
                    } else {
                        // if the user does not match to the generated Numbers he/she can try again
                        wrong.setVisibility(View.VISIBLE);
                        wrong.setText("Nice try, please try again.");
                    }
                }
            }
        });
    }
}
