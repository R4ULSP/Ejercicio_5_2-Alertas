package es.travelworld.ejercicio52_alertas;

import static es.travelworld.ejercicio52_alertas.tools.References.KEY_USER;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import es.travelworld.ejercicio52_alertas.databinding.ActivityHomeBinding;
import es.travelworld.ejercicio52_alertas.fragments.WipFragment;
import es.travelworld.ejercicio52_alertas.tools.User;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        user = (User)getIntent().getSerializableExtra(KEY_USER);

        setSupportActionBar(binding.toolbar);

        Log.i("---Datos usuario","Nombre: " + user.getName() + "  Apellidos: " + user.getLastname() + "  Edad:" + user.getAgeGroup());
        Snackbar.make(binding.getRoot(), "Nombre: " + user.getName() + "  Apellidos: " + user.getLastname() + "  Edad:" + user.getAgeGroup(), BaseTransientBottomBar.LENGTH_LONG).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == R.id.eurodisney) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.disneylandparis.com/es-es/"));
            startActivity(intent);
        }

        if (item.getItemId() == R.id.rentacar) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            WipFragment wipFragment = WipFragment.newInstance();
            wipFragment.show(fragmentManager,null);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.home_menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

}