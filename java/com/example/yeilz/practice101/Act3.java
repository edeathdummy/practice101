package com.example.yeilz.practice101;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by yeilz on 4/14/16.
 */
public class Act3 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.act3, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialization
        final EditText first = (EditText) view.findViewById(R.id.et_firstWord);
        final EditText secnd = (EditText) view.findViewById(R.id.et_secondWord);

        final TextView isNotAnagram = (TextView) view.findViewById(R.id.tv_isNotAnagram);

        Button checkAnagram = (Button) view.findViewById(R.id.btn_anagram);

        // Button was click
        checkAnagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstWord = first.getText().toString();
                String secndWord = secnd.getText().toString();
                if(firstWord.isEmpty() || secndWord.isEmpty())
                {
                    isNotAnagram.setVisibility(View.VISIBLE);
                    isNotAnagram.setText("First Word and Second Word are needed to continue.");
                } else {
                    isNotAnagram.setVisibility(View.GONE);
                    // get only numbers and letters
                    StringBuilder validFirstWord = new StringBuilder(validateString(firstWord));
                    StringBuilder validSecndWord = new StringBuilder(validateString(secndWord));

                    if(sortString(validFirstWord).equals(sortString(validSecndWord)))
                    {
                        android.app.AlertDialog.Builder err = new android.app.AlertDialog.Builder(getContext());
                        err.setMessage("It's an Anagram");
                        err.setCancelable(false);
//                        err.setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                // go to the next view
//                                // initialize fragment2
//                                // replace fragment1 to fragment2 for the next activity
//                                Act3 fragment3 = new Act3();
//                                FragmentManager manager = getFragmentManager();
//                                FragmentTransaction transaction = manager.beginTransaction();
//                                transaction.replace(R.id.ll_main, fragment3);
//                                transaction.commit();
//                            }
//                        });
                        err.setNegativeButton("Try Again", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                // clear all the view for the next round :)
                                first.setText("");
                                secnd.setText("");
                            }
                        });
                        android.app.AlertDialog createDialog = err.create();
                        createDialog.show();
                    } else {
                        Log.wtf("false", "Not Anagram");
                        isNotAnagram.setVisibility(View.VISIBLE);
                        isNotAnagram.setText("The Word is not an Anagram.");
                    }
                }
            }
        });
    }

    public String validateString(String str)
    {
        String validStr = "";
        for(int i=0; i<str.length(); i++)
        {
            // check if isLetterOrDigit
            if(Character.isLetterOrDigit(str.charAt(i)))
            {
                // concat valid character
                validStr = validStr + str.charAt(i);
            }
        }
        // return new String and transform to uppercase
        return validStr.toUpperCase();
    }

    public String sortString(StringBuilder str)
    {
        Character temp;
        for(int x=0; x<str.length()-1; x++)
        {
            Log.wtf("x",x+"");
            for(int y=0; y<str.length()-(x+1); y++)
            {
                if(str.charAt(y) >= str.charAt(y+1))
                {
                    temp = str.charAt(y+1);
                    str.setCharAt(y+1, str.charAt(y));
                    str.setCharAt(y, temp);
                }
            }
        }
        return str.toString();
    }
}
