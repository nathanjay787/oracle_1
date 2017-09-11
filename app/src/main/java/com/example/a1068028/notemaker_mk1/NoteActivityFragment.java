package com.example.a1068028.notemaker_mk1;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import java.util.LinkedList;
import java.util.List;

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

        Switch reminderSwitch = root.findViewById(R.id.reminder_Switch);

        final View baseLayout = root.findViewById(R.id.base_Layout);
        final View reminderLayout = root.findViewById(R.id.reminder_Layout);

        baseLayout.setBackgroundColor(pinkColor.getColor());
        reminderLayout.setVisibility(GONE);

        List<CircleView> circles = new LinkedList<CircleView>();

        circles.add(pinkColor);
        circles.add(orangeColor);
        circles.add(beigeColor);
        circles.add(greenColor);
        circles.add(aquaColor);
        circles.add(blueColor);
        circles.add(purpleColor);
        circles.add(brownColor);

        CircleView.OnClickListener handler = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int clickedID = view.getId();
                CircleView clicked = view.findViewById(clickedID);
                baseLayout.setBackgroundColor(clicked.getColor());
            }
        };

        for (CircleView c : circles) {
            c.setOnClickListener(handler);
        }

        reminderSwitch.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    reminderLayout.setVisibility(View.VISIBLE);
                }
                else{
                    reminderLayout.setVisibility(GONE);
                }
            }
        });

        return root;
    }
}
