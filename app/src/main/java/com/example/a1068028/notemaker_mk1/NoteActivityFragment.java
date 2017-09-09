package com.example.a1068028.notemaker_mk1;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static android.view.View.GONE;

/**
 * A placeholder fragment containing a simple view.
 */
public class NoteActivityFragment extends Fragment {

    public NoteActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_note, container, false);

        CircleView pinkColor = root.findViewById(R.id.circlePink);
        CircleView orangeColor = root.findViewById(R.id.circleOrange);
        CircleView beigeColor = root.findViewById(R.id.circleBeige);
        CircleView greenColor = root.findViewById(R.id.circleGreen);
        CircleView aquaColor = root.findViewById(R.id.circleAqua);
        CircleView blueColor = root.findViewById(R.id.circleBlue);
        CircleView purpleColor = root.findViewById(R.id.circlePurple);
        CircleView brownColor = root.findViewById(R.id.circleBrown);

        pinkColor.setColor(255, 0, 255);
        orangeColor.setColor(255, 165, 0);
        beigeColor.setColor(210, 180, 140);
        greenColor.setColor(127, 255, 0);
        aquaColor.setColor(0, 255, 255);
        blueColor.setColor(70, 130, 180);
        purpleColor.setColor(128, 0, 128);
        brownColor.setColor(160, 82, 45);

        View baseLayout = root.findViewById(R.id.base_Layout);
        View reminderLayout = root.findViewById(R.id.reminder_Layout);

        baseLayout.setBackgroundColor(Color.rgb(255, 0, 255));
        reminderLayout.setVisibility(GONE);
        //return inflater.inflate(R.layout.fragment_note, container, false);
        return root;
    }
}
