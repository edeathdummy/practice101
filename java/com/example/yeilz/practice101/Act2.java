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
public class Act2 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.act2, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialization
        final TextView message = (TextView) view.findViewById(R.id.tv_palindrome);

        final EditText word = (EditText) view.findViewById(R.id.et_palindrome);

        Button check = (Button) view.findViewById(R.id.btn_palindrome);

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // check if user input a word
                if(word.getText().toString().isEmpty())
                {
                    message.setVisibility(View.VISIBLE);
                    message.setText("Please enter a word to continue.");
                } else {
                    message.setVisibility(View.GONE);
                    // get the user word
                    String msg = word.getText().toString();

                    // filter the string in user input
                    // reverse string
                    // String reverse = "";
                    // for(int i=msg.length()-1; i>=0; i--)
                    // {
                    //    reverse = reverse + msg.charAt(i);
                    // }
                    // if(msg.equals(reverse)) {
                    //     Log.d("isMatch","Is A Palindrome");
                    // } else {
                    //     Log.d("isMatch","Is NOT A Palindrome");
                    // }

                    // pass string to a function
                    // function will return all valid character Letter or Digit
                    // function will transform string to uppercase
                    msg = filterString(msg);
                    // the function return TRUE if it's a palindrome word and FALSE it it is not.
                    if(isPalindrome(msg))
                    {
                        message.setVisibility(View.GONE);
                        android.app.AlertDialog.Builder err = new android.app.AlertDialog.Builder(getContext());
                        err.setMessage("The inputed word/s \"" + word.getText().toString() + "\" is a Palindrome.");
                        err.setCancelable(false);
                        err.setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // go to the next view
                                // initialize fragment2
                                // replace fragment1 to fragment2 for the next activity
                                Act3 fragment3 = new Act3();
                                FragmentManager manager = getFragmentManager();
                                FragmentTransaction transaction = manager.beginTransaction();
                                transaction.replace(R.id.ll_main, fragment3);
                                transaction.commit();
                            }
                        });
                        err.setNegativeButton("Try Again", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                // clear all the view for the next round :)
                                word.setText("");
                            }
                        });
                        android.app.AlertDialog createDialog = err.create();
                        createDialog.show();
                    } else {
                        message.setText("The inputed word/s \""+word.getText().toString()+"\" is NOT a Palindrome.");
                        message.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

    public String filterString(String str)
    {
        String validStr = "";
        for(int i=0; i<str.length(); i++)
        {
            // check if isLetterOrDigit and concat string
            if(Character.isLetterOrDigit(str.charAt(i)))
            {
                // concat string
                validStr = validStr + str.charAt(i);
            }
        }
        // return valid string
        return validStr.toUpperCase();
    }

    public boolean isPalindrome(String str)
    {
        if(str.length() == 0 || str.length() == 1)
        {
            Log.wtf("adsf","true");
            return true;
        }
        if(str.charAt(0) == str.charAt(str.length()-1))
        {
            // trim the string first and last
            str = str.substring(1, str.length() -1);
            // call again the function to test together with the new string
            return isPalindrome(str);
        }
        Log.wtf("asdf","false");
        return false;
    }

}
