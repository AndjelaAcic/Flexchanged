package com.example.bottomnavigationview;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mGoogleMap;
    private MapView mapView;
    private View mView;

    private BottomSheetBehavior mBottomSheetBehavior;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private RequestQueue mQueue;
    ArrayList<CardItem> arrayList,beograd,kraljevo,kragujevac,uzice,apatin,vranje,nis,noviSad,subotica,
                                    leskovac,kikinda,paracin,pancevo,becej,kladovo,pozega,jagodina;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView= inflater.inflate(R.layout.fragment_search, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View bottomSheet = view.findViewById(R.id.bottom_sheet);
        arrayList=new ArrayList<>();
        beograd=new ArrayList<>();
        kragujevac=new ArrayList<>();
        kraljevo=new ArrayList<>();
        uzice=new ArrayList<>();
        apatin=new ArrayList<>();
        vranje=new ArrayList<>();
        nis=new ArrayList<>();
        noviSad=new ArrayList<>();
        subotica=new ArrayList<>();
        leskovac=new ArrayList<>();
        kikinda=new ArrayList<>();
        paracin=new ArrayList<>();
        pancevo=new ArrayList<>();
        becej=new ArrayList<>();
        kladovo=new ArrayList<>();
        pozega=new ArrayList<>();
        jagodina=new ArrayList<>();
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        mQueue = Volley.newRequestQueue(getContext());

        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        mLayoutManager = new LinearLayoutManager(getContext());


        mapView=(MapView)mView.findViewById(R.id.map);
        if(mapView!=null)
        {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        if(getActivity()!=null) {
            SupportMapFragment mapFragment = (SupportMapFragment) getActivity().getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            if (mapFragment != null) {
                mapFragment.getMapAsync(this);

                //code here?

            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        jsonParse();
        MapsInitializer.initialize(getContext());
        mGoogleMap=googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        zoom();
        noviMarker(44.7866,20.4489,"Beograd");
        noviMarker(45.2671,19.8335,"Novi Sad");
        noviMarker(43.7238, 20.6873,"Kraljevo");
        noviMarker(43.3209, 21.8958,"Niš");
        noviMarker(43.8556, 19.8425,"Užice");
        noviMarker(42.5450, 21.9003,"Vranje");
        noviMarker(45.6729, 18.9843,"Apatin");
        noviMarker(44.0128, 20.9114,"Kragujevac");
        noviMarker(46.1005, 19.6651,"Subotica");

        noviMarker(42.9964,  21.9440,"Leskovac");
        noviMarker(45.8273,  20.4615,"Kikinda");
        noviMarker(43.8586,  21.4039,"Paracin");
        noviMarker(44.8740,  20.6476,"Pančevo");


        noviMarker(45.6138,  20.0460,"Bečej");
        noviMarker(44.6073,  22.6122,"Kladovo");
        noviMarker(43.8461,  20.0356,"Požega");
        noviMarker(43.9777,  21.2573,"Jagodina");
        mGoogleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                switch (marker.getTitle().trim())
                {
                    case"Beograd":
                        populateRecyclerView(beograd);
                        break;
                    case "Kraljevo":
                        populateRecyclerView(kraljevo);
                        break;
                    case "Niš": case "Nis":
                    populateRecyclerView(nis);
                    break;
                    case "Kragujevac":
                        populateRecyclerView(kragujevac);
                        break;
                    case "Apatin":
                        populateRecyclerView(apatin);
                        break;
                    case "Novi Sad":
                        populateRecyclerView(noviSad);
                        break;
                    case "Vranje":
                        populateRecyclerView(vranje);
                        break;
                    case "Uzice": case "Užice":
                    populateRecyclerView(uzice);
                    break;
                    case "Leskovac":
                        populateRecyclerView(leskovac);
                     break;
                    case "Kikinda":
                        populateRecyclerView(kikinda);
                        break;
                    case "Subotica":
                        populateRecyclerView(subotica);
                        break;
                    case "Paracin":case "Paraćin":
                        populateRecyclerView(paracin);
                        break;
                    case "Pancevo": case "Pančevo":
                        populateRecyclerView(pancevo);
                        break;
                    case "Becej":case "Bečej":
                        populateRecyclerView(becej);
                        break;
                    case "Kladovo":
                        populateRecyclerView(kladovo);
                        break;
                    case "Pozega":case "Požega":
                        populateRecyclerView(pozega);
                        break;
                    case "Jagodina":
                        populateRecyclerView(jagodina);
                        break;


                    default:
                        break;
                }
                //populateRecyclerView(arrayList);
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                return false;
            }
        });
        mGoogleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                emptyRecyclerView();
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                zoom();
            }
        });
      /*  mGoogleMap = googleMap;

        //Do your stuff here




        Toast.makeText(getContext(), "done", Toast.LENGTH_SHORT).show();*/
        // Add a marker in Sydney and move the camera
     /*   LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
       /* */
    }
    private void noviMarker(double x,double y,String text)
    {
        LatLng sydney = new LatLng(x, y);
        mGoogleMap.addMarker(new MarkerOptions().position(sydney).title(text));
    }
    private void populateRecyclerView(ArrayList<CardItem>lista) {
        mAdapter = new CardItemAdapter(lista);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void emptyRecyclerView() {
        ArrayList<CardItem> empty=new ArrayList<>();
        mAdapter = new CardItemAdapter(empty);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void zoom() {

        float f= (float) 6.5;
        LatLng pos = new LatLng(44.0165, 21.0059);
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pos, f));


    }
    private void jsonParse() {

       // String url="https://api.myjson.com/bins/hmvi5";
        String url="https://api.myjson.com/bins/1gby6p";
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try
                        {
                            JSONArray jsonArray=response.getJSONArray("Alumni"); //passing the name of the group
                            for(int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject alumni=jsonArray.getJSONObject(i);
                                /*String firstName=alumni.getString("Ime i prezime");
                                //int age=alumni.getInt("age");
                                String mail=alumni.getString("Email");
                                String phone=alumni.getString("Broj telefona");
                                String gen=alumni.getString("FLEX/A-SMYLE generacija");
                                String grad=alumni.getString("Grad");
                                String opstina=alumni.getString("Opština");*/

                                String firstName=alumni.getString("Ime");
                                //int age=alumni.getInt("age");
                                String mail=alumni.getString("Email");
                                String phone=alumni.getString("Broj");
                                String gen=alumni.getString("Generacija");
                                String grad=alumni.getString("Grad:");
                                String opstina=alumni.getString("Opština");

                                switch (grad.trim())
                                {
                                    case"Beograd":
                                        beograd.add(new CardItem(firstName,gen,mail,phone));
                                        break;
                                    case "Kraljevo":
                                        kraljevo.add(new CardItem(firstName,gen,mail,phone));
                                        break;
                                    case "Niš": case "Nis":
                                    nis.add(new CardItem(firstName,gen,mail,phone));
                                    break;
                                    case "Kragujevac":
                                        kragujevac.add(new CardItem(firstName,gen,mail,phone));
                                        break;
                                    case "Apatin":
                                        apatin.add(new CardItem(firstName,gen,mail,phone));
                                        break;
                                    case "Novi Sad":
                                        noviSad.add(new CardItem(firstName,gen,mail,phone));
                                        break;
                                    case "Vranje":
                                        vranje.add(new CardItem(firstName,gen,mail,phone));
                                        break;
                                    case "Uzice": case "Užice":
                                        uzice.add(new CardItem(firstName,gen,mail,phone));
                                    break;
                                    case "Subotica":
                                        subotica.add(new CardItem(firstName,gen,mail,phone));
                                        break;
                                    case "Leskovac":
                                        leskovac.add(new CardItem(firstName,gen,mail,phone));
                                        break;
                                    case "Kikinda":
                                        kikinda.add(new CardItem(firstName,gen,mail,phone));
                                        break;
                                    case "Paracin":case "Paraćin":
                                        paracin.add(new CardItem(firstName,gen,mail,phone));
                                    break;
                                    case "Pancevo": case "Pančevo":
                                        pancevo.add(new CardItem(firstName,gen,mail,phone));
                                    break;
                                    case "Becej":case "Bečej":
                                        becej.add(new CardItem(firstName,gen,mail,phone));
                                    break;
                                    case "Kladovo":
                                        kladovo.add(new CardItem(firstName,gen,mail,phone));
                                        break;
                                    case "Pozega":case "Požega":
                                        pozega.add(new CardItem(firstName,gen,mail,phone));
                                    break;
                                    case "Jagodina":
                                        jagodina.add(new CardItem(firstName,gen,mail,phone));
                                        break;
                                    default:
                                        break;
                                }
                                arrayList.add(new CardItem(firstName,gen,mail,phone));
                                // mTextViewResult.append(firstName + ", " + String.valueOf(age) + ", " + mail + "\n\n");
                            }
                            mAdapter = new CardItemAdapter(arrayList);

                            mRecyclerView.setLayoutManager(mLayoutManager);
                            mRecyclerView.setAdapter(mAdapter);
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(getContext(),"Fail read",Toast.LENGTH_LONG).show();
                    }
                });
        mQueue.add(request);

    }
}