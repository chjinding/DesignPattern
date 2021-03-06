package com.design.pattern.designpattern;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements
		NavigationView.OnNavigationItemSelectedListener {

	private Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.setTitle("设计模式");
		setSupportActionBar(toolbar);


		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,
				toolbar, R.string.navigation_drawer_open,
				R.string.navigation_drawer_close);
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

	@SuppressWarnings("StatementWithEmptyBody")
	@Override
	public boolean onNavigationItemSelected(MenuItem item) {
		// Handle navigation view item clicks here.
		int id = item.getItemId();

		if (id == R.id.nav_item1) {
			switchItem1();
		} else if (id == R.id.nav_item2) {

		} else if (id == R.id.nav_item3) {
			switchItem3();
		} else if (id == R.id.nav_item4) {

		} else if (id == R.id.nav_item5) {

		} else if (id == R.id.nav_item6) {

		} else if (id == R.id.nav_item7) {

		}

		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawer.closeDrawer(GravityCompat.START);
		return true;
	}

	private void switchItem1() {
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.frame_content, new Item1Fragment()).commit();
		toolbar.setTitle("单例模式");
	}

	private void switchItem3() {
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.frame_content, new Item3Fragment()).commit();
		toolbar.setTitle("观察者模式");
	}
}
