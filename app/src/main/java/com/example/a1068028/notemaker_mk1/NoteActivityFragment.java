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

    EditText titleView;     //View for note title
    EditText bodyView;      //View for note body
    int chosenCircle;       //note category
    boolean hasReminder;    //flag for note reminder
    Calendar userCalendar;  //holds date and time for note reminder

    public NoteActivityFragment() {
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {   //menu button pressed
        switch (item.getItemId()) {                         //which button?
            case R.id.action_save : {                       //save button
                                                            //create the note object
                                                            //use the correct data types for the note:
                                                            //String from TextView text, Date from reminder (NOT calendar)
                Note note = new Note(titleView.getText().toString(), bodyView.getText().toString(), chosenCircle, hasReminder, userCalendar.getTime());
                Log.d(this.getTag(), note.toString());      //show "saved" note
                return true;                                //Possible ToDo: validate the reminder time again before saving
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_note, container, false);     //capture the root view
        setHasOptionsMenu(true);                                                    //necessary to tell fragment that there are menu options

        titleView = root.findViewById(R.id.title_EditText);                         //get references to title and body views
        bodyView = root.findViewById(R.id.body_EditText);

        CircleView pinkColor = root.findViewById(R.id.circlePink);                  //get references to category views
        CircleView orangeColor = root.findViewById(R.id.circleOrange);
        CircleView beigeColor = root.findViewById(R.id.circleBeige);
        CircleView greenColor = root.findViewById(R.id.circleGreen);
        CircleView aquaColor = root.findViewById(R.id.circleAqua);
        CircleView blueColor = root.findViewById(R.id.circleBlue);
        CircleView purpleColor = root.findViewById(R.id.circlePurple);
        CircleView brownColor = root.findViewById(R.id.circleBrown);

        Context thisFragment = getContext();                                        //necessary to set colours of category views

        pinkColor.setColor(ContextCompat.getColor(thisFragment, R.color.base08));   //set the colour for each category
        orangeColor.setColor(ContextCompat.getColor(thisFragment, R.color.base09));
        beigeColor.setColor(ContextCompat.getColor(thisFragment, R.color.base0A));
        greenColor.setColor(ContextCompat.getColor(thisFragment, R.color.base0B));
        aquaColor.setColor(ContextCompat.getColor(thisFragment, R.color.base0C));
        blueColor.setColor(ContextCompat.getColor(thisFragment, R.color.base0D));
        purpleColor.setColor(ContextCompat.getColor(thisFragment, R.color.base0E));
        brownColor.setColor(ContextCompat.getColor(thisFragment, R.color.base0F));

        Switch reminderSwitch = root.findViewById(R.id.reminder_Switch);            //get reference to reminder switch

        final View baseLayout = root.findViewById(R.id.base_Layout);                //get references to parent layout and reminer layout
        final View reminderLayout = root.findViewById(R.id.reminder_Layout);

        final TextView dateText = root.findViewById(R.id.date_TextView);            //get references to reminder date and time views
        final TextView timeText = root.findViewById(R.id.time_TextView);

        Date initial = new Date();                                                  //get the current date and time
        final SimpleDateFormat forDate = new SimpleDateFormat("MMMM d, yyyy");      //set up date and time formatting
        final SimpleDateFormat forTime = new SimpleDateFormat("h:mm a");

        Calendar calendar = Calendar.getInstance();                                 //create a default reminder date and time
        calendar.setTime(initial);
        calendar.add(Calendar.HOUR, 1);                                             //default is 1 hour from now
        Date newTime = calendar.getTime();                                          //default reminder

        dateText.setText(forDate.format(newTime));                                  //set text for default date and time
        timeText.setText(forTime.format(newTime));

        userCalendar = Calendar.getInstance();                                      //set the reminder that user will save
        userCalendar.setTime(initial);                                              //year, month, date, hour and minute set

        final DialogFragment timeDialog = TimePickerDialogFragment.create(          //setting the time using the default reminder
                newTime,
                new TimePickerDialog.OnTimeSetListener(){

                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        userCalendar.set(Calendar.HOUR, hour);                      //set hour and minute for reminder
                        userCalendar.set(Calendar.MINUTE, minute);
                        Date toCompare = new Date();                                //get the current date and time
                        if (toCompare.compareTo(userCalendar.getTime()) > 0){       //IMPORTANT: this prevents the user from picking a time in the past
                            Calendar errorCal = Calendar.getInstance();             //create a new date and time
                            errorCal.setTime(toCompare);
                            errorCal.add(Calendar.MINUTE, 1);                       //set the new date and time to 1 minute in the future
                            userCalendar = errorCal;                                //use the new, valid time for the reminder
                        }
                        timeText.setText(forTime.format(userCalendar.getTime()));   //set the time text to the chosen reminder time
                    }
                }
        );
        final DialogFragment dateDialog = DatePickerDialogFragment.createDatePicker(//setting the date using the default reminder
                newTime,
                new DatePickerDialog.OnDateSetListener(){

                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        userCalendar.set(year, month, day);                         //set the date for reminder
                        dateText.setText(forDate.format(userCalendar.getTime()));   //set the date text to the chosen reminder date
                    }
                });                                                                 //NOTE: modified the DatePickerDialog to use a minimum date - no need for validation

        timeText.setOnClickListener(new TextView.OnClickListener(){

            @Override
            public void onClick(View view) {                                      //set listener for time TextView so it opens the timepicker
                timeDialog.show(getFragmentManager(), "timePicker");
            }
        });
        dateText.setOnClickListener(new TextView.OnClickListener(){

            @Override
            public void onClick(View view) {                                      //set listener for date TextView so it opens the datepicker
                dateDialog.show(getFragmentManager(), "datePicker");
            }
        });

        baseLayout.setBackgroundColor(pinkColor.getColor());                        //set default background color to default category
        chosenCircle = pinkColor.getId();                                           //set the chosen catgory ID for the default category
        reminderLayout.setVisibility(GONE);                                         //by default, reminder is not there

        List<CircleView> circles = new LinkedList<>();                              //make a list for every note category

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
            public void onClick(View view) {                             //create the OnClick listener for every category
                int clickedID = view.getId();
                CircleView clicked = view.findViewById(clickedID);
                baseLayout.setBackgroundColor(clicked.getColor());                  //set the background colour to the circle's colour
                chosenCircle = clickedID;                                           //set the chosen category
            }
        };

        for (CircleView c : circles) {                                              //set the OnClick listener for every circle
            c.setOnClickListener(handler);
        }

        reminderSwitch.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {           //set the listener for the reminder switch
                if (b){                                                             //switch is ON
                    reminderLayout.setVisibility(View.VISIBLE);                     //show the reminder
                }
                else{                                                               //switch is OFF
                    reminderLayout.setVisibility(GONE);                             //remove the reminder
                }
                hasReminder = b;                                                    //set the reminder state to be saved
            }
        });

        return root;                                                                //return this fragment
    }
}
