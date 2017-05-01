package cl.duoc.innovacion.easyshopping.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import cl.duoc.innovacion.easyshopping.R;

/**
 * Clase principal
 *
 * @author Beto
 */
public class MainActivity extends AppCompatActivity {

    private AutoCompleteTextView autoCompleteSearchProduct;
    private TextView mTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destacados);
        btnProductSearchListener();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    /**
     * Botón encargado de buscar un producto específico
     */
    private void btnProductSearchListener() {
        Button button = (Button) findViewById(R.id.btn_search_product);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                autoCompleteSearchProduct = (AutoCompleteTextView) findViewById(R.id.autocomplete_search_product);
                String product = autoCompleteSearchProduct.getText().toString();
                Toast.makeText(MainActivity.this, "Buscando producto: " + product, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     *
     */
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_featured_products:
                    return true;
                case R.id.navigation_dashboard:
                    return true;
                case R.id.navigation_notifications:
                    return true;
                case R.id.navigation_help:
                    return true;
            }
            return false;
        }

    };

}
