package com.ace.vishal.helpinghands;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    ToggleButton deaf,dumb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Feedback", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Thread timer=new Thread(){
                    public void run(){
                        try{
                            sleep(1200);
                        }catch (    InterruptedException e){
                            e.printStackTrace();
                        }finally{
                            String[] emails = new String[]{
                                    "vishalmago16@gmail.com",
                                    "ace.codroid@gmail.com"
                            };
                            String mailSubject = "Feedback to Developer";
                            Intent intent = new Intent(Intent.ACTION_SENDTO);
                            intent.putExtra(Intent.EXTRA_EMAIL,emails);
                            intent.putExtra(Intent.EXTRA_SUBJECT,mailSubject);
                            intent.setData(Uri.parse("mailto:"));

                            // Try to start the activity
                            if (intent.resolveActivity(getPackageManager()) != null) {
                                startActivity(intent);
                            }else{
                                // If there are no email client installed in this device
                                Toast.makeText(MainActivity.this,"No email client installed in this device.",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                };
                timer.start();
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent i;
        if(id==R.id.nav_contact){
            i=new Intent(MainActivity.this,ContactUs.class);
            startActivity(i);
        }else if(id==R.id.nav_about){
            i=new Intent(MainActivity.this,AboutUs.class);
            startActivity(i);
        } else if (id == R.id.nav_share) {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Download Helping Hands App from play store");
            sharingIntent.putExtra(Intent.EXTRA_TEXT, "Download Locator app for free"+"\t"+"http://play.google.com/store/apps/details?id=com.ace.vishal.helpinghands");
            startActivity(Intent.createChooser(sharingIntent, "Share via"));
        }else if(id==R.id.nav_rate){
            try{
                Uri uri=Uri.parse("market://details?id=com.ace.vishal.helpinghands");
                Intent goToMarket=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(goToMarket);
            }catch(ActivityNotFoundException e){
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://play.google.com/store/apps/details?id=com.ace.vishal.helpinghands")));
            }
        }else if(id==R.id.nav_feedback){
            String[] emails = new String[]{
                    "vishalmago16@gmail.com",
                    "ace.codroid@gmail.com"
            };
            String mailSubject = "Feedback to Developer";
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.putExtra(Intent.EXTRA_EMAIL,emails);
            intent.putExtra(Intent.EXTRA_SUBJECT,mailSubject);
            intent.setData(Uri.parse("mailto:"));

            // Try to start the activity
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }else{
                // If there are no email client installed in this device
                Toast.makeText(MainActivity.this,"No email client installed in this device.",Toast.LENGTH_SHORT).show();
            }
        }else if(id==R.id.nav_orgdeaf){
            i=new Intent(MainActivity.this,DeafOrganisation.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            Fragment f=null;
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch(position){
                case 0:
                    f=new deaffragment();
                    break;
                case 1:
                    f=new dumbfragment();
                    break;
            }
            return f;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Dumb";
                case 1:
                    return "Deaf";
            }
            return null;
        }
    }
}


