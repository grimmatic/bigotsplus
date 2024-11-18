package com.codinginflow.bigots;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.lang.ref.WeakReference;
import java.text.DecimalFormat;
import java.util.ArrayList;
import static com.codinginflow.bigots.R.menu.search;

/* loaded from: C:\Users\semad\Downloads\classes3.dex */
public class BtcTurk extends AppCompatActivity {
    public static TextView BinanceAnaText;
    public static TextView BinanceTlAnaText;
    static FloatingActionButton asagi;
    static FloatingActionButton ayarlar;
    static ListView binanceL;
    static ListView binanceTlL;
    static float[] btcturk;
    public static TextView btcturkAnaText;
    static ListView btcturkL;
    public static EditText dot;
    static FloatingActionButton duzenle;
    static float[] huobibtcturk;
    static SpannableString[] metinlerBinance;
    static SpannableString[] metinlerBinanceTl;
    static SpannableString[] metinlerBinanceTlarama;
    static SpannableString[] metinlerBinancearama;
    static SpannableString[] metinlerBtcTurk;
    static SpannableString[] metinlerBtcTurkarama;
    public static TextView oran;
    public static TextView pop;
    public static MediaPlayer saniye;
    static FloatingActionButton start;
    static FloatingActionButton stop;
    static FloatingActionButton yukari;
    ArrayAdapter<SpannableString> arrayAdapter;
    ArrayAdapter<SpannableString> arrayAdapter1;
    ArrayAdapter<SpannableString> arrayAdapter2;
    MenuItem binancecheck;
    private AlertDialog dialog;
    private AlertDialog.Builder dialogBuilder;
    float downX;
    private EditText edit;
    NestedScrollView ekran6;
    private Button geributton;
    private RequestQueue mQueue;
    private BottomAppBar mbottomappbar;
    MenuItem paribucheck;
    private Button popupButon;
    private SeekBar seek;
    private SeekBar seekses;
    MenuItem tlcheck;
    Toolbar toolbar;
    float upX;
    static int btctotal = 80;
    static int btctotal2 = 0;
    static int p = 0;
    static boolean calistiMi = false;
    static boolean otuyorMu = false;
    static boolean girdi = false;
    static ArrayList<Integer> aramaindexler = new ArrayList<>();
    static int tiklanansira = 0;
    Double dotusd = Double.valueOf(2.5d);
    private String isim = "";
    String aramaMetni="";
    ForegroundColorSpan fscgreen= new ForegroundColorSpan(0XFF005EFF);
    ForegroundColorSpan fsccyan= new ForegroundColorSpan(0XFF0090FF);
    ForegroundColorSpan fscdarkyellow= new ForegroundColorSpan(0XFFEBA65F);
    ForegroundColorSpan yellow= new ForegroundColorSpan(Color.YELLOW);
    ForegroundColorSpan red= new ForegroundColorSpan(0xFFF83E3A);
    ForegroundColorSpan darkred= new ForegroundColorSpan(0xFFDC743C);
    boolean otuyorMuArama=false;
    DecimalFormat df = new DecimalFormat("#.###");

    static {
        int r0 = btctotal;
        huobibtcturk = new float[r0];
        btcturk = new float[r0];
        metinlerBtcTurk = new SpannableString[r0];
        metinlerBinance = new SpannableString[r0];
        metinlerBinanceTl = new SpannableString[r0];
    }

    private static BtcTurk instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        setContentView(R.layout.btcturk);
        btcturkL=findViewById(R.id.listview0btcturk);
        binanceL=findViewById(R.id.listview2btcturk);
        binanceTlL=findViewById(R.id.listview1btcturk);
        ayarlar = findViewById(R.id.ayarlarbtcturk);
        yukari=findViewById(R.id.yukaribtcturk);
        asagi=findViewById(R.id.asagibtcturk);
        start=findViewById(R.id.baslatbtcturk);
        stop=findViewById(R.id.durdurbtcturk);
        duzenle=findViewById(R.id.duzenlebtcturk);
        btcturkAnaText=findViewById(R.id.baslikbtcturk);
        BinanceTlAnaText=findViewById(R.id.baslikbinancetlbtcturk);
        BinanceAnaText=findViewById(R.id.baslikbinancebtcturk);
        dialogBuilder = new AlertDialog.Builder(BtcTurk.this);
        View popupView = getLayoutInflater().inflate(R.layout.popup, (ViewGroup) null);
        pop=popupView.findViewById(R.id.textView665);
        edit=popupView.findViewById(R.id.edittext);
        seek=popupView.findViewById(R.id.seekbar665);
        seekses=popupView.findViewById(R.id.seekbar666);
        popupButon=popupView.findViewById(R.id.button665);
        geributton=popupView.findViewById(R.id.button6650);
        oran=popupView.findViewById(R.id.textView6650);
        dialogBuilder.setView(popupView);
        dialog=dialogBuilder.create();
        mQueue = Volley.newRequestQueue(this);
        dot = findViewById(R.id.DOTT);
        ekran6=findViewById(R.id.ekran6);
        dot.setText(MainActivity.dot.getText().toString(),TextView.BufferType.EDITABLE);
        mbottomappbar=findViewById(R.id.bottom_app_barbtcturk);
        toolbar=findViewById(R.id.toolbarbtcturk);
        setSupportActionBar(toolbar);
        if (Service.isRunning() && calistiMi) {
            start.setVisibility(View.GONE);
            updateUI(true);
        }
        try {
            Double.parseDouble(dot.getText().toString());
            start.setVisibility(View.VISIBLE);
        } catch (NumberFormatException e) {
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        start.setX((width - 178) / 2);
        start.setY((height - 800) / 2);
        ayarlar.setOnClickListener(new View.OnClickListener() { // from class: com.codinginflow.bigots.BtcTurk.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                Intent openMainActivity = new Intent((Context) BtcTurk.this, (Class<?>) Sesler.class);
                openMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                BtcTurk.this.startActivityIfNeeded(openMainActivity, 2);
            }
        });
        yukari.setOnClickListener(new View.OnClickListener() { // from class: com.codinginflow.bigots.BtcTurk.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                BtcTurk.this.ekran6.scrollTo(0, 0);
            }
        });
        asagi.setOnClickListener(new View.OnClickListener() { // from class: com.codinginflow.bigots.BtcTurk.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                BtcTurk.this.ekran6.scrollTo(99000, 99000);
            }
        });
        TextWatcher tt = new TextWatcher() { // from class: com.codinginflow.bigots.BtcTurk.4
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                if (BtcTurk.calistiMi) {
                    try {
                        Double.parseDouble(BtcTurk.dot.getText().toString());
                        if (Double.parseDouble(BtcTurk.dot.getText().toString()) >= 0.0d) {
                            BtcTurk.duzenle.setVisibility(View.VISIBLE);
                            return;
                        }
                        return;
                    } catch (NumberFormatException e2) {
                        BtcTurk.duzenle.setVisibility(View.GONE);
                        return;
                    }
                }
                try {
                    Double.parseDouble(BtcTurk.dot.getText().toString());
                    if (Double.parseDouble(BtcTurk.dot.getText().toString()) >= 0.0d) {
                        BtcTurk.start.setVisibility(View.VISIBLE);
                    }
                } catch (NumberFormatException e3) {
                    BtcTurk.start.setVisibility(View.GONE);
                }
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start2, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start2, int before, int count) {
            }
        };
        dot.addTextChangedListener(tt);
        dot.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.codinginflow.bigots.BtcTurk.5
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    BtcTurk.duzenle.setVisibility(View.GONE);
                }
            }
        });
        duzenle.setOnClickListener(new View.OnClickListener() { // from class: com.codinginflow.bigots.BtcTurk.6
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                BtcTurk.this.dotusd = Double.valueOf(BtcTurk.dot.getText().toString());
                for (int i = 0; i < BtcTurk.btctotal; i++) {
                    MainActivity.oranlarbtcturk[i] = BtcTurk.this.dotusd;
                }
                Snackbar snackbar = Snackbar.make(v, "Tüm oranlar " + BtcTurk.this.dotusd + "'a ayarlandı", 0);
                snackbar.show();
            }
        });
        this.geributton.setOnClickListener(new View.OnClickListener() { // from class: com.codinginflow.bigots.BtcTurk.7
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                BtcTurk.this.dialog.cancel();
                BtcTurk.this.dialog.dismiss();
            }
        });
        this.seek.setMax(100);
        this.seekses.setMax(15);
        this.seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.codinginflow.bigots.BtcTurk.8
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                BtcTurk.this.edit.setText("" + (progress / 10.0d));
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        this.popupButon.setOnClickListener(new View.OnClickListener() { // from class: com.codinginflow.bigots.BtcTurk.9
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                try {
                    Double.parseDouble(BtcTurk.this.edit.getText().toString());
                    for (int x = 0; x < BtcTurk.btctotal; x++) {
                        if (!BtcTurk.girdi && MainActivity.siralamaBtcTurk[x] == BtcTurk.tiklanansira) {
                            MainActivity.oranlarbtcturk[x] = Double.valueOf(BtcTurk.this.edit.getText().toString());
                            BtcTurk.oran.setText("" + MainActivity.oranlarbtcturk[x]);
                        } else if (BtcTurk.girdi && MainActivity.btcturkisim[x] == BtcTurk.this.isim) {
                            MainActivity.oranlarbtcturk[x] = Double.valueOf(BtcTurk.this.edit.getText().toString());
                            BtcTurk.oran.setText("" + MainActivity.oranlarbtcturk[x]);
                        }
                    }
                } catch (NumberFormatException e2) {
                    Toast.makeText(BtcTurk.this.getApplicationContext(), "Lütfen değer gir", Toast.LENGTH_SHORT).show();
                }
            }
        });
        this.mbottomappbar.setOnClickListener(new View.OnClickListener() { // from class: com.codinginflow.bigots.BtcTurk.10
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                BtcTurk.p++;
                if (BtcTurk.p % 2 == 1) {
                    BtcTurk.this.mbottomappbar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
                } else {
                    BtcTurk.this.mbottomappbar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);
                }
            }
        });
        btcturkL.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.codinginflow.bigots.BtcTurk.11
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (BtcTurk.calistiMi) {
                    BtcTurk.this.dialog.show();
                    if (!BtcTurk.girdi) {
                        BtcTurk.tiklanansira = MainActivity.siralamaBtcTurk[position];
                        BtcTurk.pop.setText(MainActivity.btcturkisim[position]);
                        BtcTurk.this.edit.setText("" + MainActivity.oranlarbtcturk[position]);
                        BtcTurk.oran.setText("" + MainActivity.oranlarbtcturk[position]);
                        BtcTurk.this.seek.setProgress((int) (MainActivity.oranlarbtcturk[position].doubleValue() * 10.0d));
                        BtcTurk.this.seekses.setProgress(MainActivity.sesSeviyesiBtcTurk[position]);
                        BtcTurk.this.seekses.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.codinginflow.bigots.BtcTurk.11.1
                            @Override // android.widget.SeekBar.OnSeekBarChangeListener
                            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                for (int i = 0; i < BtcTurk.btctotal; i++) {
                                    if (MainActivity.btcturkisim[i].contains(BtcTurk.pop.getText())) {
                                        MainActivity.sesSeviyesiBtcTurk[i] = progress;
                                        MainActivity.seslerBtcTurk[i].setVolume(progress / 15.0f, progress / 15.0f);
                                        return;
                                    }
                                }
                            }

                            @Override // android.widget.SeekBar.OnSeekBarChangeListener
                            public void onStartTrackingTouch(SeekBar seekBar) {
                            }

                            @Override // android.widget.SeekBar.OnSeekBarChangeListener
                            public void onStopTrackingTouch(SeekBar seekBar) {
                            }
                        });
                        return;
                    }
                    BtcTurk.tiklanansira = BtcTurk.aramaindexler.get(position).intValue();
                    BtcTurk.this.isim = MainActivity.btcturkisim[BtcTurk.tiklanansira];
                    BtcTurk.pop.setText(MainActivity.btcturkisim[BtcTurk.tiklanansira]);
                    BtcTurk.this.edit.setText("" + MainActivity.oranlarbtcturk[BtcTurk.tiklanansira]);
                    BtcTurk.oran.setText("" + MainActivity.oranlarbtcturk[BtcTurk.tiklanansira]);
                    BtcTurk.this.seek.setProgress((int) (MainActivity.oranlarbtcturk[BtcTurk.tiklanansira].doubleValue() * 10.0d));
                    BtcTurk.this.seekses.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.codinginflow.bigots.BtcTurk.11.2
                        @Override // android.widget.SeekBar.OnSeekBarChangeListener
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                            for (int i = 0; i < BtcTurk.btctotal; i++) {
                                if (MainActivity.btcturkisim[i].contains(BtcTurk.pop.getText())) {
                                    MainActivity.sesSeviyesiBtcTurk[i] = progress;
                                    MainActivity.seslerBtcTurk[i].setVolume(progress / 15.0f, progress / 15.0f);
                                    return;
                                }
                            }
                        }

                        @Override // android.widget.SeekBar.OnSeekBarChangeListener
                        public void onStartTrackingTouch(SeekBar seekBar) {
                        }

                        @Override // android.widget.SeekBar.OnSeekBarChangeListener
                        public void onStopTrackingTouch(SeekBar seekBar) {
                        }
                    });
                }
            }
        });
        btcturkL.setOnTouchListener(new View.OnTouchListener() { // from class: com.codinginflow.bigots.BtcTurk.12
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                BtcTurk.dot.clearFocus();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        BtcTurk.this.downX = event.getX();
                    case MotionEvent.ACTION_UP /* 1 */:
                        BtcTurk.this.upX = event.getX();
                        float deltaX = BtcTurk.this.downX - BtcTurk.this.upX;
                        if (Math.abs(deltaX) > 0.0f && deltaX < -300.0f) {
                            Intent openMainActivity = new Intent((Context) BtcTurk.this, (Class<?>) MainActivity.class);
                            openMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                            BtcTurk.this.startActivityIfNeeded(openMainActivity, 0);
                            return true;
                        }
                        break;
                    default:
                        return false;
                }
                return false;
            }
        });
        binanceL.setOnTouchListener(new View.OnTouchListener() { // from class: com.codinginflow.bigots.BtcTurk.13
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                BtcTurk.dot.clearFocus();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        BtcTurk.this.downX = event.getX();
                    case MotionEvent.ACTION_UP /* 1 */:
                        BtcTurk.this.upX = event.getX();
                        float deltaX = BtcTurk.this.downX - BtcTurk.this.upX;
                        if (Math.abs(deltaX) > 0.0f && deltaX < -300.0f) {
                            Intent openMainActivity = new Intent((Context) BtcTurk.this, (Class<?>) MainActivity.class);
                            openMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                            BtcTurk.this.startActivityIfNeeded(openMainActivity, 0);
                            return true;
                        }
                        break;
                    default:
                        return false;
                }
                return false;
            }
        });
        binanceTlL.setOnTouchListener(new View.OnTouchListener() { // from class: com.codinginflow.bigots.BtcTurk.14
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                BtcTurk.dot.clearFocus();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        BtcTurk.this.downX = event.getX();
                    case MotionEvent.ACTION_UP /* 1 */:
                        BtcTurk.this.upX = event.getX();
                        float deltaX = BtcTurk.this.downX - BtcTurk.this.upX;
                        if (Math.abs(deltaX) > 0.0f && deltaX < -300.0f) {
                            Intent openMainActivity = new Intent((Context) BtcTurk.this, (Class<?>) MainActivity.class);
                            openMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                            BtcTurk.this.startActivityIfNeeded(openMainActivity, 0);
                            return true;
                        }
                        break;
                    default:
                        return false;
                }
                return false;
            }
        });
        this.ekran6.setOnTouchListener(new View.OnTouchListener() { // from class: com.codinginflow.bigots.BtcTurk.15
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        BtcTurk.this.downX = event.getX();
                    case MotionEvent.ACTION_UP /* 1 */:
                        BtcTurk.this.upX = event.getX();
                        float deltaX = BtcTurk.this.downX - BtcTurk.this.upX;
                        if (Math.abs(deltaX) > 0.0f && deltaX < -300.0f) {
                            Intent openMainActivity = new Intent((Context) BtcTurk.this, (Class<?>) MainActivity.class);
                            openMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                            BtcTurk.this.startActivityIfNeeded(openMainActivity, 0);
                            return true;
                        }
                        break;
                    default:
                        return false;
                }
                return false;
            }
        });
    }
    public static BtcTurk getInstance() {
        return instance;
    }
    public void startService(View v) {
        if (MainActivity.MainservisSayac == 0) {
            Toast.makeText(getApplicationContext(), "Önce hızlı servisini başlatmanız gerekir!", Toast.LENGTH_SHORT).show();
            return;
        }
        calistiMi = true;
        updateUI(true);
        start.setVisibility(View.GONE);
        btcturkAnaText.setVisibility(View.VISIBLE);
        BinanceAnaText.setVisibility(View.VISIBLE);
        BinanceTlAnaText.setVisibility(View.VISIBLE);
        btcturkL.setVisibility(View.VISIBLE);
        binanceTlL.setVisibility(View.VISIBLE);
        binanceL.setVisibility(View.VISIBLE);
        this.paribucheck.setChecked(true);
        this.tlcheck.setChecked(true);
        this.binancecheck.setChecked(true);
        AppBarLayout.LayoutParams paramst = (AppBarLayout.LayoutParams) MainActivity.toolbar.getLayoutParams();

        ViewGroup.MarginLayoutParams layoutParams = (LinearLayoutCompat.LayoutParams) dot.getLayoutParams();
        layoutParams.setMargins(40, paramst.height + 10, 40, 5);
        dot.setLayoutParams(layoutParams);
        stop.setVisibility(View.VISIBLE);
        this.dotusd = Double.valueOf(dot.getText().toString());
        for (int i = 0; i < btctotal; i++) {
            MainActivity.oranlarbtcturk[i] = this.dotusd;
        }
        this.dotusd = Double.valueOf(dot.getText().toString());
        Toast.makeText(getApplicationContext(), "Servis Başlatıldı", Toast.LENGTH_SHORT).show();
        Intent serviceIntent = new Intent(this, Service2.class);
        ContextCompat.startForegroundService(this, serviceIntent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Service2.isRunning() && calistiMi) {
            updateUI(true);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Activity arka planda iken gereksiz UI güncellemelerini durdur
        if (!isFinishing() && calistiMi) {
            updateUI(false);
        }
    }

    // UI güncellemelerini yönetmek için yardımcı metod
    private void updateUI(boolean show) {
        SharedPreferences prefs = getSharedPreferences("UIState", MODE_PRIVATE);
        prefs.edit().putBoolean("isVisible", show).apply();

        if (show) {
            btcturkAnaText.setVisibility(View.VISIBLE);
            BinanceAnaText.setVisibility(View.VISIBLE);
            BinanceTlAnaText.setVisibility(View.VISIBLE);
            btcturkL.setVisibility(View.VISIBLE);
            binanceTlL.setVisibility(View.VISIBLE);
            binanceL.setVisibility(View.VISIBLE);

            if (paribucheck != null) paribucheck.setChecked(true);
            if (tlcheck != null) tlcheck.setChecked(true);
            if (binancecheck != null) binancecheck.setChecked(true);
        } else {
            btcturkAnaText.setVisibility(View.GONE);
            BinanceTlAnaText.setVisibility(View.GONE);
            BinanceAnaText.setVisibility(View.GONE);
            btcturkL.setVisibility(View.GONE);
            binanceL.setVisibility(View.GONE);
            binanceTlL.setVisibility(View.GONE);
        }
    }

    private WeakReference<MainActivity> mainActivityRef;

    public void setMainActivity(MainActivity activity) {
        mainActivityRef = new WeakReference<>(activity);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("calistiMi", calistiMi);
    }
    public void stopService(View v) {
        calistiMi = false; // Flag'i false yap
        updateUI(false); // UI'ı gizle
        Intent serviceIntent = new Intent( this, Service2.class);
        stopService(serviceIntent);
        Intent serviceIntent0 = new Intent( this,  Service.class);
        stopService(serviceIntent0);
        onDestroy();
        finishAffinity();
        System.exit(0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v2, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r1v9 */
    public String Dot() {


        for (int i = 0; i < btctotal; i++) {
            // Her SpannableString'i başlangıç değerleriyle oluştur
            metinlerBtcTurk[i] = new SpannableString(MainActivity.isimlerBtcTurk[i] + btcturk[i] + " %" + df.format(MainActivity.farklarBtcTurk[i]));
            metinlerBinance[i] = new SpannableString(MainActivity.isimlerBtcTurk[i] + huobibtcturk[i]);
            metinlerBinanceTl[i] = new SpannableString(MainActivity.isimlerBtcTurk[i] + (huobibtcturk[i] * MainActivity.tsatisb));
        }

        boolean z;
        int a;
        aramaindexler.clear();
        btctotal2 = 0;
        otuyorMu = false;
        btcturk = MainActivity.btcturk;
        huobibtcturk = MainActivity.huobibtcturk;
        this.df = new DecimalFormat("#.###");
        int a2 = 0;
        while (true) {
            z = true;
            if (a2 >= btctotal) {
                break;
            }
            if (calistiMi) {
                if (Sesler.arti && !Sesler.eksi && MainActivity.farklarBtcTurk[a2] > MainActivity.oranlarbtcturk[a2].doubleValue()) {
                    SpannableString spannableString = metinlerBtcTurk[a2];
                    spannableString.setSpan(this.fscgreen, 0, spannableString.length(), 33);
                    SpannableString spannableString2 = metinlerBinance[a2];
                    spannableString2.setSpan(this.yellow, 0, spannableString2.length(), 33);
                    SpannableString spannableString3 = metinlerBinanceTl[a2];
                    spannableString3.setSpan(this.red, 0, spannableString3.length(), 33);
                    MainActivity.seslerBtcTurk[a2].start();
                    otuyorMu = true;
                } else if (!Sesler.arti && !Sesler.eksi && (MainActivity.farklarBtcTurk[a2] < MainActivity.oranlarbtcturk[a2].doubleValue() * (-1.0d) || MainActivity.farklarBtcTurk[a2] > MainActivity.oranlarbtcturk[a2].doubleValue())) {
                    SpannableString spannableString4 = metinlerBtcTurk[a2];
                    spannableString4.setSpan(this.fscgreen, 0, spannableString4.length(), 33);
                    SpannableString spannableString5 = metinlerBinance[a2];
                    spannableString5.setSpan(this.yellow, 0, spannableString5.length(), 33);
                    SpannableString spannableString6 = metinlerBinanceTl[a2];
                    spannableString6.setSpan(this.red, 0, spannableString6.length(), 33);
                    MainActivity.seslerBtcTurk[a2].start();
                    otuyorMu = true;
                } else if (!Sesler.arti && Sesler.eksi && MainActivity.farklarBtcTurk[a2] < MainActivity.oranlarbtcturk[a2].doubleValue() * (-1.0d)) {
                    SpannableString spannableString7 = metinlerBtcTurk[a2];
                    spannableString7.setSpan(this.fscgreen, 0, spannableString7.length(), 33);
                    SpannableString spannableString8 = metinlerBinance[a2];
                    spannableString8.setSpan(this.yellow, 0, spannableString8.length(), 33);
                    SpannableString spannableString9 = metinlerBinanceTl[a2];
                    spannableString9.setSpan(this.red, 0, spannableString9.length(), 33);
                    MainActivity.seslerBtcTurk[a2].start();
                    otuyorMu = true;
                } else {
                    SpannableString spannableString10 = metinlerBtcTurk[a2];
                    spannableString10.setSpan(this.fsccyan, 0, spannableString10.length(), 33);
                    SpannableString spannableString11 = metinlerBinance[a2];
                    spannableString11.setSpan(this.fscdarkyellow, 0, spannableString11.length(), 33);
                    SpannableString spannableString12 = metinlerBinanceTl[a2];
                    spannableString12.setSpan(this.darkred, 0, spannableString12.length(), 33);
                    otuyorMu = false;
                }
                if (otuyorMu) {
                    String sayac = btcturk[a2] + "";
                    int sayac0 = sayac.length();
                    int sayi = MainActivity.isimlerBtcTurk[a2].length() + this.df.format(MainActivity.farklarBtcTurk[a2]).length() + sayac0 + " %".length();
                    metinlerBtcTurk[a2] = new SpannableString(MainActivity.isimlerBtcTurk[a2] + btcturk[a2] + " %" + this.df.format(MainActivity.farklarBtcTurk[a2]) + " || " + (huobibtcturk[a2] * MainActivity.tsatisb));
                    metinlerBtcTurk[a2].setSpan(this.fscgreen, 0, sayi, 33);
                    SpannableString spannableString13 = metinlerBtcTurk[a2];
                    spannableString13.setSpan(this.darkred, sayi + 1, spannableString13.length(), 33);
                }
                String sayac2 = this.aramaMetni;
                if (sayac2.length() > 0 && MainActivity.isimlerBtcTurk[a2].matches(this.aramaMetni.toUpperCase() + "(.*)")) {
                    aramaindexler.add(Integer.valueOf(a2));
                    btctotal2++;
                }
            }
            a2++;
        }
        if (calistiMi) {

            if (btctotal2 == 0) {
                arrayAdapter = new ArrayAdapter<>(BtcTurk.this, R.layout.listview, metinlerBtcTurk);
                arrayAdapter2 = new ArrayAdapter<>(BtcTurk.this, R.layout.listview, metinlerBinance);
                arrayAdapter1 = new ArrayAdapter<>(BtcTurk.this, R.layout.listview, metinlerBinanceTl);
                ViewGroup.MarginLayoutParams layoutParams = (LinearLayoutCompat.LayoutParams) btcturkL.getLayoutParams();
                layoutParams.setMargins(0, 0, 0, -190);
                binanceL.setLayoutParams(layoutParams);
                binanceTlL.setLayoutParams(layoutParams);
                btcturkL.setLayoutParams(layoutParams);
                btcturkL.setAdapter((ListAdapter) this.arrayAdapter);
                Helper.getListViewSize(btcturkL, false);
                binanceL.setAdapter((ListAdapter) this.arrayAdapter2);
                Helper.getListViewSize(binanceL, false);
                binanceTlL.setAdapter((ListAdapter) this.arrayAdapter1);
                Helper.getListViewSize(binanceTlL, false);
            } else {
                metinlerBtcTurkarama = new SpannableString[btctotal2];
                metinlerBinancearama = new SpannableString[btctotal2];
                metinlerBinanceTlarama = new SpannableString[btctotal2];
                int a3 = 0;
                while (a3 < btctotal2) {
                    int index = aramaindexler.get(a3).intValue();
                    this.otuyorMuArama = false;
                    metinlerBtcTurkarama[a3] = new SpannableString(MainActivity.isimlerBtcTurk[index] + btcturk[index] + " %" + this.df.format(MainActivity.farklarBtcTurk[index]));
                    metinlerBinancearama[a3] = new SpannableString(MainActivity.isimlerBtcTurk[index] + huobibtcturk[index]);
                    metinlerBinanceTlarama[a3] = new SpannableString(MainActivity.isimlerBtcTurk[index] + (huobibtcturk[index] * MainActivity.tsatisb));
                    if (Sesler.arti && !Sesler.eksi && MainActivity.farklarBtcTurk[index] > MainActivity.oranlarbtcturk[index].doubleValue()) {
                        SpannableString spannableString14 = metinlerBtcTurkarama[a3];
                        spannableString14.setSpan(this.fscgreen, 0, spannableString14.length(), 33);
                        SpannableString spannableString15 = metinlerBinancearama[a3];
                        spannableString15.setSpan(this.yellow, 0, spannableString15.length(), 33);
                        SpannableString spannableString16 = metinlerBinanceTlarama[a3];
                        spannableString16.setSpan(this.red, 0, spannableString16.length(), 33);
                        this.otuyorMuArama = z;
                    } else if (!Sesler.arti && !Sesler.eksi && (MainActivity.farklarBtcTurk[index] < MainActivity.oranlarbtcturk[index].doubleValue() * (-1.0d) || MainActivity.farklarBtcTurk[index] > MainActivity.oranlarbtcturk[index].doubleValue())) {
                        SpannableString spannableString17 = metinlerBtcTurkarama[a3];
                        spannableString17.setSpan(this.fscgreen, 0, spannableString17.length(), 33);
                        SpannableString spannableString18 = metinlerBinancearama[a3];
                        spannableString18.setSpan(this.yellow, 0, spannableString18.length(), 33);
                        SpannableString spannableString19 = metinlerBinanceTlarama[a3];
                        spannableString19.setSpan(this.red, 0, spannableString19.length(), 33);
                        this.otuyorMuArama = z;
                    } else {
                        if (!Sesler.arti && Sesler.eksi) {
                            if (MainActivity.farklarBtcTurk[index] < MainActivity.oranlarbtcturk[index].doubleValue() * (-1.0d)) {
                                SpannableString spannableString20 = metinlerBtcTurkarama[a3];
                                spannableString20.setSpan(this.fscgreen, 0, spannableString20.length(), 33);
                                SpannableString spannableString21 = metinlerBinancearama[a3];
                                spannableString21.setSpan(this.yellow, 0, spannableString21.length(), 33);
                                SpannableString spannableString22 = metinlerBinanceTlarama[a3];
                                spannableString22.setSpan(this.red, 0, spannableString22.length(), 33);
                                this.otuyorMuArama = z;
                            }
                        }
                        SpannableString spannableString23 = metinlerBtcTurkarama[a3];
                        spannableString23.setSpan(this.fsccyan, 0, spannableString23.length(), 33);
                        SpannableString spannableString24 = metinlerBinancearama[a3];
                        spannableString24.setSpan(this.fscdarkyellow, 0, spannableString24.length(), 33);
                        SpannableString spannableString25 = metinlerBinanceTlarama[a3];
                        spannableString25.setSpan(this.darkred, 0, spannableString25.length(), 33);
                        this.otuyorMuArama = false;
                    }
                    if (!this.otuyorMuArama) {
                        a = a3;
                    } else {
                        String sayac3 = btcturk[index] + "";
                        int sayac02 = sayac3.length();
                        int sayi2 = MainActivity.isimlerBtcTurk[index].length() + this.df.format(MainActivity.farklarBtcTurk[index]).length() + sayac02 + " %".length();
                        a = a3;
                        metinlerBtcTurkarama[a] = new SpannableString(MainActivity.isimlerBtcTurk[index] + btcturk[index] + " %" + this.df.format(MainActivity.farklarBtcTurk[index]) + " || " + (huobibtcturk[index] * MainActivity.tsatisb));
                        metinlerBtcTurkarama[a].setSpan(this.fscgreen, 0, sayi2, 33);
                        SpannableString spannableString26 = metinlerBtcTurkarama[a];
                        spannableString26.setSpan(this.darkred, sayi2 + 1, spannableString26.length(), 33);
                    }
                    a3 = a + 1;

                    z = true;
                }
                arrayAdapter = new ArrayAdapter<>(BtcTurk.this, R.layout.listview, metinlerBtcTurkarama);
                arrayAdapter2 = new ArrayAdapter<>(BtcTurk.this, R.layout.listview, metinlerBinancearama);
                arrayAdapter1 = new ArrayAdapter<>(BtcTurk.this, R.layout.listview, metinlerBinanceTlarama);
                ViewGroup.MarginLayoutParams layoutParams2 = (LinearLayoutCompat.LayoutParams) binanceL.getLayoutParams();
                layoutParams2.setMargins(0, 0, 0, 45);
                binanceL.setLayoutParams(layoutParams2);
                btcturkL.setLayoutParams(layoutParams2);
                binanceTlL.setLayoutParams(layoutParams2);
                btcturkL.setAdapter((ListAdapter) this.arrayAdapter);
                Helper.getListViewSize(btcturkL, false);
                binanceL.setAdapter((ListAdapter) this.arrayAdapter2);
                Helper.getListViewSize(binanceL, false);
                binanceTlL.setAdapter((ListAdapter) this.arrayAdapter1);
                Helper.getListViewSize(binanceTlL, false);
                this.arrayAdapter.getFilter().filter(this.aramaMetni);
                this.arrayAdapter2.getFilter().filter(this.aramaMetni);
                this.arrayAdapter1.getFilter().filter(this.aramaMetni);
            }
        }
        return "";
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.checkboxhizliparibu:
                if (calistiMi) {
                    if (!item.isChecked()) {
                        BinanceAnaText.setVisibility(View.VISIBLE);
                        binanceL.setVisibility(View.VISIBLE);
                        if (calistiMi) {
                            binanceL.setVisibility(View.VISIBLE);
                            BinanceAnaText.setVisibility(View.VISIBLE);
                        }
                        item.setChecked(true);
                    } else {
                        if (calistiMi) {
                            binanceL.setVisibility(View.GONE);
                            BinanceAnaText.setVisibility(View.GONE);
                        }
                        binanceL.setVisibility(View.GONE);
                        BinanceAnaText.setVisibility(View.GONE);
                        item.setChecked(false);
                    }
                }
                return true;
            case R.id.checkboxhizlibinance:
                if (calistiMi) {
                    if (!item.isChecked()) {
                        btcturkAnaText.setVisibility(View.VISIBLE);
                        btcturkL.setVisibility(View.VISIBLE);
                        if (calistiMi) {
                            btcturkAnaText.setVisibility(View.VISIBLE);
                            btcturkL.setVisibility(View.VISIBLE);
                        }
                        item.setChecked(true);
                    } else {
                        btcturkAnaText.setVisibility(View.GONE);
                        btcturkL.setVisibility(View.GONE);
                        if (calistiMi) {
                            btcturkAnaText.setVisibility(View.GONE);
                            btcturkL.setVisibility(View.GONE);
                        }
                        item.setChecked(false);
                    }
                }
                return true;
            case R.id.checkboxhizlitl:
                if (calistiMi) {
                    if (!item.isChecked()) {
                        BinanceTlAnaText.setVisibility(View.VISIBLE);
                        binanceTlL.setVisibility(View.VISIBLE);
                        if (calistiMi) {
                            binanceTlL.setVisibility(View.VISIBLE);
                            BinanceTlAnaText.setVisibility(View.VISIBLE);
                        }
                        item.setChecked(true);
                    } else {
                        binanceTlL.setVisibility(View.GONE);
                        BinanceTlAnaText.setVisibility(View.GONE);
                        if (calistiMi) {
                            binanceTlL.setVisibility(View.GONE);
                            BinanceTlAnaText.setVisibility(View.GONE);
                        }
                        item.setChecked(false);
                    }
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater ().inflate (search,menu);
        MenuItem menuItem = menu.findItem(R.id.arama);
        paribucheck = menu.findItem(R.id.checkboxhizliparibu);
        tlcheck = menu.findItem(R.id.checkboxhizlitl);
        binancecheck = menu.findItem(R.id.checkboxhizlibinance);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Arama yapmak için yazın");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() { // from class: com.codinginflow.bigots.BtcTurk.17
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            public boolean onQueryTextChange(String newText) {
                BtcTurk.girdi = false;
                BtcTurk.this.aramaMetni = newText;
                BtcTurk.aramaindexler.clear();
                BtcTurk.btctotal2 = 0;
                for (int a = 0; a < BtcTurk.btctotal; a++) {
                    if (BtcTurk.this.aramaMetni.length() > 0 && MainActivity.isimlerBtcTurk[a].matches(BtcTurk.this.aramaMetni.toUpperCase() + "(.*)")) {
                        BtcTurk.aramaindexler.add(Integer.valueOf(a));
                        BtcTurk.btctotal2++;
                    }
                }
                if (BtcTurk.calistiMi && newText.length() > 0) {
                    BtcTurk.girdi = true;
                    BtcTurk.this.Dot();
                } else {
                    BtcTurk.aramaindexler.clear();
                    BtcTurk.btctotal2 = 0;
                }
                if (BtcTurk.this.aramaMetni.length() == 0 && BtcTurk.btctotal2 == 0 && BtcTurk.calistiMi) {
                    arrayAdapter = new ArrayAdapter<>(BtcTurk.this, R.layout.listview, metinlerBtcTurk);
                    arrayAdapter2 = new ArrayAdapter<>(BtcTurk.this, R.layout.listview, metinlerBinance);
                    arrayAdapter1 = new ArrayAdapter<>(BtcTurk.this, R.layout.listview, metinlerBinanceTl);
                    BtcTurk.btcturkL.setAdapter((ListAdapter) BtcTurk.this.arrayAdapter);
                    Helper.getListViewSize(BtcTurk.btcturkL, false);
                    BtcTurk.binanceL.setAdapter((ListAdapter) BtcTurk.this.arrayAdapter2);
                    Helper.getListViewSize(BtcTurk.binanceL, false);
                    BtcTurk.binanceTlL.setAdapter((ListAdapter) BtcTurk.this.arrayAdapter1);
                    Helper.getListViewSize(BtcTurk.binanceTlL, false);
                }
                return false;
            }
        });
        return true;
    }
}