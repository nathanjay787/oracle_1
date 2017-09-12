package com.example.a1068028.notemaker_mk1;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Date;
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

        Context thisFragment = getContext();

        pinkColor.setColor(ContextCompat.getColor(thisFragment, R.color.base08));
        orangeColor.setColor(ContextCompat.getColor(thisFragment, R.color.base09));
        beigeColor.setColor(ContextCompat.getColor(thisFragment, R.color.base0A));
        greenColor.setColor(ContextCompat.getColor(thisFragment, R.color.base0B));
        aquaColor.setColor(ContextCompat.getColor(thisFragment, R.color.base0C));
        blueColor.setColor(ContextCompat.getColor(thisFragment, R.color.base0D));
        purpleColor.setColor(ContextCompat.getColor(thisFragment, R.color.base0E));
        brownColor.setColor(ContextCompat.getColor(thisFragment, R.color.base0F));

        Switch reminderSwitch = root.findViewById(R.id.reminder_Switch);

        final View baseLayout = root.findViewById(R.id.base_Layout);
        final View reminderLayout = root.findViewById(R.id.reminder_Layout);

        TextView dateText = root.findViewById(R.id.date_TextView);
        TextView timeText = root.findViewById(R.id.time_TextView);

        Date initial = new Date();
        final DialogFragment timeDialog = TimePickerDialogFragment.create(
                initial,
                new TimePickerDialog.OnTimeSetListener(){

                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {

                    }
                }
        );
        final DialogFragment dateDialog = DatePickerDialogFragment.createDatePicker(
                initial,
                new DatePickerDialog.OnDateSetListener(){

                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                    }
                });

        timeText.setOnClickListener(new TextView.OnClickListener(){

            @Override
            public void onClick(View view) {
                timeDialog.show(getFragmentManager(), "timePicker");
            }
        });
        dateText.setOnClickListener(new TextView.OnClickListener(){

            @Override
            public void onClick(View view) {
                dateDialog.show(getFragmentManager(), "datePicker");
            }
        });

        baseLayout.setBackgroundColor(pinkColor.getColor());
        reminderLayout.setVisibility(GONE);

        List<CircleView> circles = new LinkedList<>();

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
