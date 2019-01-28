package com.todo.task.todo;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;
    Toolbar toolbar;

    public static final String SHOW_INVENTORY_URL = "https://webswap.000webhostapp.com/PHPScripts/showInventory.php";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<InventoryModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)this.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        drawerLayout = (DrawerLayout)this.findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_drawer, R.string.close_drawer);

        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        navigationView = (NavigationView)this.findViewById(R.id.navigationView);

        recyclerView = (RecyclerView)this.findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);

        list = new ArrayList<>();
        getInventory();
    }

    public void getInventory()
    {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Loading Data...");
        dialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, SHOW_INVENTORY_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dialog.dismiss();
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++)
                    {
                        JSONObject object = jsonArray.getJSONObject(i);
                        InventoryModel model = new InventoryModel(
                                object.getString("image_path"),
                                object.getString("name"),
                                object.getString("status_img_path"),
                                object.getString("status"),
                                object.getString("request_name"),
                                object.getString("person_name")
                        );
                        list.add(model);
                    }
                    adapter = new InventoryAdapter(getBaseContext(), list);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
                Toast.makeText(getBaseContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menus, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(drawerToggle.onOptionsItemSelected(item))
            return true;

        switch (item.getItemId())
        {
            case R.id.addnew:

        }
        return true;
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }
}
