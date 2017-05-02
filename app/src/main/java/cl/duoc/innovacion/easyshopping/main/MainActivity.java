package cl.duoc.innovacion.easyshopping.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cl.duoc.innovacion.easyshopping.R;
import cl.duoc.innovacion.easyshopping.adapters.ExpandableListAdapter;

/**
 * Clase principal
 *
 * @author Beto
 */
public class MainActivity extends AppCompatActivity {

    private AutoCompleteTextView autoCompleteSearchProduct;
    private Button btnSearchProduct;
    private Button btnFilters;
    private Spinner spnStores;
    private ListView featuredProductsList;

    List<String> groupList;
    List<String> childList;
    Map<String, List<String>> laptopCollection;
    ExpandableListView expListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destacados);
        spnStores = (Spinner) findViewById(R.id.spn_stores);
        String[] arraySpinner = {"Seleccione una tienda...", "Paris", "Ripley", "Falabella", "Dijon"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        spnStores.setAdapter(adapter);
        btnProductSearchListener();
        createGroupList();
        createCollection();
        expListView = (ExpandableListView) findViewById(R.id.featured_products_list);
        final ExpandableListAdapter expListAdapter = new ExpandableListAdapter(
                this, groupList, laptopCollection);
        expListView.setAdapter(expListAdapter);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    /**
     * Botón encargado de buscar un producto específico
     */
    private void btnProductSearchListener() {
        btnSearchProduct = (Button) findViewById(R.id.btn_search_product);

        btnSearchProduct.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                autoCompleteSearchProduct = (AutoCompleteTextView) findViewById(R.id.autocomplete_search_product);
                String product = autoCompleteSearchProduct.getText().toString();
                Toast.makeText(MainActivity.this, "Buscando producto: " + product, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Opciones del menú de abajo
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

    /**
     * Simulamos la lista de categorias
     */
    private void createGroupList() {
        groupList = new ArrayList<String>();
        groupList.add("Paris");
        groupList.add("Ripley");
        groupList.add("Falabella");
        groupList.add("Dijon");
    }

    /**
     * Simulamos la lista de ofertas por cada categoria
     */
    private void createCollection() {
        String[] parisProducts = {"Oferta 1 Paris"};
        String[] ripleyProducts = {"Oferta 1 Ripley"};
        String[] falabellaProducts = {"Oferta 1 Falabella"};
        String[] dijonProducts = {"Oferta 1 Dijon"};

        laptopCollection = new LinkedHashMap<String, List<String>>();

        for (String group : groupList) {
            if (group.equals("Paris")) {
                loadChild(parisProducts);
            } else if (group.equals("Ripley"))
                loadChild(ripleyProducts);
            else if (group.equals("Falabella"))
                loadChild(falabellaProducts);
            else
                loadChild(dijonProducts);

            laptopCollection.put(group, childList);
        }
    }

    /**
     * Cargamos los productos de cada categoria
     * @param products
     */
    private void loadChild(String[] products) {
        childList = new ArrayList<String>();
        for (String model : products)
            childList.add(model);
    }

    /**
     *
     * Dibujamos el indicar en el menú
     */
    private void setGroupIndicatorToRight() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;

        expListView.setIndicatorBounds(width - getDipsFromPixel(35), width
                - getDipsFromPixel(5));
    }

    public int getDipsFromPixel(float pixels) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (pixels * scale + 0.5f);
    }



}
