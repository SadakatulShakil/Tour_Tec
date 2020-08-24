package com.example.tourtec.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tourtec.Activity.EventsActivity;
import com.example.tourtec.MainActivity;
import com.example.tourtec.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class HomeFragment extends Fragment {
    private CarouselView carouselView;
    private Context context;
    private Toolbar dToolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    public int[] sampleImages = {R.drawable.kuyakata, R.drawable.nafakum, R.drawable.panthumai,
            R.drawable.saintmartin, R.drawable.sajek, R.drawable.sundarban, R.drawable.tanguarhaor,
            R.drawable.bichanakandi, R.drawable.bogalake, R.drawable.madhobkundo,
            R.drawable.niladri, R.drawable.panthumai, R.drawable.chandronath};

    private CardView createEventCard, onGoingEventCard,
            eventExpensesCard, countryPlaceCard, foreignPlaceCard, weatherCard,
            websiteCard, FacebookCard;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        initNavigationViewDrawer();
       /* dToolbar.setTitle(getString(R.string.home));*/
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, dToolbar,
                R.string.drawer_open, R.string.drawer_closed);
        drawerToggle.getDrawerArrowDrawable().setColor(Color.WHITE);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        carouselView.setImageListener(imageListener);
        carouselView.setPageCount(sampleImages.length);

        ClickEvents();

    }

    private void ClickEvents() {

        createEventCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EventsActivity.class);
                context.startActivity(intent);
            }
        });
    }

    private void initView(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dToolbar = view.findViewById(R.id.toolbar);
        }
        carouselView = view.findViewById(R.id.carouselView);
        navigationView = view.findViewById(R.id.navigationDrawer);
        drawerLayout = view.findViewById(R.id.drawer);
        ///cardViewDefine///

        createEventCard = view.findViewById(R.id.createEventsLayout);
        onGoingEventCard = view.findViewById(R.id.onGoingEventsLayout);
        eventExpensesCard = view.findViewById(R.id.expenseOfEventsLayout);
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };

    private void initNavigationViewDrawer() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch ((item.getItemId())) {

                    case R.id.settings:
                       /* getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frameLayoutID, new SettingsFragment())
                                .addToBackStack(null)
                                .commit();*/
                        Toast.makeText(context, "Settings Under Construction be Patient!", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.feedback:
                        /*Intent intent = new Intent(context, FeedBackActivity.class);
                        startActivity(intent);*/
                        Toast.makeText(context, "FeedBack Under Construction be Patient!", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.adminpanel:
                        /*Intent intent1 = new Intent(context, AdminActivity.class);
                        startActivity(intent1);*/
                        Toast.makeText(context, "Admin Under Construction be Patient!", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.about:
                       /* Intent intent3 = new Intent(context, AboutUsActivity.class);
                        startActivity(intent3);*/
                        Toast.makeText(context, "About Under Construction be Patient!", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.logOut:
                        FirebaseAuth.getInstance().signOut();
                        getActivity().finish();
                        Intent intent4 = new Intent(context, MainActivity.class);
                        startActivity(intent4);
                        Toast.makeText(context, "Successfully Log Out", Toast.LENGTH_LONG).show();
                        break;

                    default:
                        break;
                }
                return false;
            }
        });

    }
}