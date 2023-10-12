/** Expandable cardView code referenced from
 * https://www.geeksforgeeks.org/how-to-create-an-expandable-cardview-in-android/
 *
 *
 * **/

package com.example.pandacare;


import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class PandaDiet extends AppCompatActivity {

    // initialising variables for widgets in xml file
    CardView cardView1, cardView2;
    LinearLayout hiddenView,hiddenView2;
    ImageButton arrow,arrow2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panda_diet);

        // initialising all the views
        cardView1 = findViewById(R.id.menu1_cardview);
        arrow = findViewById(R.id.arrow_button);
        hiddenView = findViewById(R.id.hidden_view);

        cardView2 = findViewById(R.id.menu2_cardview);
        arrow2 = findViewById(R.id.arrow_button2);
        hiddenView2 = findViewById(R.id.hidden_view2);

        // arrow for menu 1
        arrow.setOnClickListener(view -> {
            // check if CardView is already expanded, if yes then set its visibility to gone
            if (hiddenView.getVisibility() == View.VISIBLE) {
                // The transition of the hiddenView is carried out by the TransitionManager class
                // Here use an object of the AutoTransition Class to create a default transition
                TransitionManager.beginDelayedTransition(cardView1, new AutoTransition());
                hiddenView.setVisibility(View.GONE);

               // change the expand less icon to expand more
                arrow.setImageResource(R.drawable.ic_baseline_expand_more_24);
            }

            // check to make sure CardView not expanded, then set its visibility to visible
            else {
                TransitionManager.beginDelayedTransition(cardView1, new AutoTransition());
                hiddenView.setVisibility(View.VISIBLE);
                arrow.setImageResource(R.drawable.ic_baseline_expand_less_24);
            }
        });

        // arrow for menu 2
        arrow2.setOnClickListener(view ->{
            if (hiddenView2.getVisibility() == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(cardView2, new AutoTransition());
                hiddenView2.setVisibility(View.GONE);
                arrow2.setImageResource(R.drawable.ic_baseline_expand_more_24);
            }
            else {
                TransitionManager.beginDelayedTransition(cardView2, new AutoTransition());
                hiddenView2.setVisibility(View.VISIBLE);
                arrow2.setImageResource(R.drawable.ic_baseline_expand_less_24);
            }

        });
    }

}