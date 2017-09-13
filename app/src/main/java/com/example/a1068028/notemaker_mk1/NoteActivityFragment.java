package com.example.a1068028.notemaker_mk1;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static android.view.View.GONE;

/**
 * A placeholder fragment containing a simple view.
 */
public class NoteActivityFragment extends Fragment {

    EditText titleView;
    EditText bodyView;
    int chosenCircle;
    boolean hasReminder;
    Calendar userCalendar;

    public NoteActivityFragment() {
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save : {
                Note note = new Note(titleView.getText().toString(), bodyView.getText().toString(), chosenCircle, hasReminder, userCalendar.getTime());
                Log.v(this.getTag(), note.toString());
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_note, container, false);
        setHasOptionsMenu(true);

        titleView = root.findViewById(R.id.title_EditText);
        bodyView = root.findViewById(R.id.body_EditText);

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

        final TextView dateText = root.findViewById(R.id.date_TextView);
        final TextView timeText = root.findViewById(R.id.time_TextView);

        Date initial = new Date();
        final SimpleDateFormat forDate = new SimpleDateFormat("MMMM d, yyyy");
        final SimpleDateFormat forTime = new SimpleDateFormat("h:mm a");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(initial);
        calendar.add(Calendar.HOUR, 1);

        Date newTime = calendar.getTime();

        dateText.setText(forDate.format(newTime));
        timeText.setText(forTime.format(newTime));

        userCalendar = Calendar.getInstance();
        userCalendar.setTime(initial);

        final DialogFragment timeDialog = TimePickerDialogFragment.create(
                newTime,
                new TimePickerDialog.OnTimeSetListener(){

                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        userCalendar.set(Calendar.HOUR, hour);
                        userCalendar.set(Calendar.MINUTE, minute);
                        Date toCompare = new Date();
                        if (toCompare.compareTo(userCalendar.getTime()) > 0){
                            Calendar errorCal = Calendar.getInstance();
                            errorCal.setTime(toCompare);
                            errorCal.add(Calendar.MINUTE, 1);
                            userCalendar = errorCal;
                        }
                        timeText.setText(forTime.format(userCalendar.getTime()));
                    }
                }
        );
        final DialogFragment dateDialog = DatePickerDialogFragment.createDatePicker(
                newTime,
                new DatePickerDialog.OnDateSetListener(){

                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        userCalendar.set(year, month, day);
                        dateText.setText(forDate.format(userCalendar.getTime()));
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
                chosenCircle = clickedID;
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
                hasReminder = b;
            }
        });

        return root;
    }
}
