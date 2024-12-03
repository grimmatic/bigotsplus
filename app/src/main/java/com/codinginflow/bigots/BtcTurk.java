package com.codinginflow.bigots;


import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import java.lang.ref.WeakReference;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

import static com.codinginflow.bigots.MainActivity.saniye;
import static com.codinginflow.bigots.MainActivity.seslerBtcTurk;
import static com.codinginflow.bigots.R.menu.search;

public class BtcTurk extends AppCompatActivity {
    public  TextView BinanceAnaText;
    public  TextView BinanceTlAnaText;
    static FloatingActionButton asagi;
    static FloatingActionButton sol;
    static FloatingActionButton ayarlar;
    static String coinName;
    static float[] btcturk;
    public  TextView btcturkAnaText;
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
    private NestedScrollView scrollView;
    private int currentPosition = 0;
    private Button binancetradebutton;
    private Button binancewalletbutton;
    private Button paributradebutton;
    private Button paribuwalletbutton;
    static FloatingActionButton start;
    static FloatingActionButton stop;
    static FloatingActionButton yukari;
    private RecyclerView btcturkRecyclerView;
    private RecyclerView binanceRecyclerView;
    private RecyclerView binanceTlRecyclerView;
    private CoinAdapter btcturkAdapter;
    private CoinAdapter binanceAdapter;
    private CoinAdapter binanceTlAdapter;
    MenuItem binancecheck;
    private AlertDialog dialog;
    private AlertDialog.Builder dialogBuilder;
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
    static int btctotal = 106;
    static int btctotal2 = 0;
    static int p = 0;
    static boolean calistiMi = false;
    static boolean otuyorMu = false;
    static boolean girdi = false;
    private static WeakReference<BtcTurk> instanceRef;
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


    private MediaPlayerManager mediaPlayerManager;
    private void setupRecyclerViews() {
        btcturkRecyclerView.setHasFixedSize(true);
        binanceRecyclerView.setHasFixedSize(true);
        binanceTlRecyclerView.setHasFixedSize(true);

        // Tüm decorationları temizle
        while (btcturkRecyclerView.getItemDecorationCount() > 0) {
            btcturkRecyclerView.removeItemDecorationAt(0);
        }
        while (binanceRecyclerView.getItemDecorationCount() > 0) {
            binanceRecyclerView.removeItemDecorationAt(0);
        }
        while (binanceTlRecyclerView.getItemDecorationCount() > 0) {
            binanceTlRecyclerView.removeItemDecorationAt(0);
        }

        // Yeni divider decoration
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider));

        // Decorationları ekle
        btcturkRecyclerView.addItemDecoration(dividerItemDecoration);
        binanceRecyclerView.addItemDecoration(dividerItemDecoration);
        binanceTlRecyclerView.addItemDecoration(dividerItemDecoration);
    }

    private class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {
        private final GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context) {
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    View childView = btcturkRecyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (childView != null && calistiMi) {
                        int position = btcturkRecyclerView.getChildAdapterPosition(childView);
                        if (position != RecyclerView.NO_POSITION) {
                            if (btcturkAdapter != null) {
                                btcturkAdapter.onItemClick(position);
                            }
                            return true;
                        }
                    }
                    return false;
                }

                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                    float deltaX = e2.getX() - e1.getX();
                    if (Math.abs(deltaX) > 100 && Math.abs(velocityX) > 100) {
                        if (deltaX > 0) { // Soldan sağa kaydırma
                            Intent openMainActivity = new Intent(BtcTurk.this, MainActivity.class);
                            openMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                            startActivityIfNeeded(openMainActivity, 0);
                            return true;
                        }
                    }
                    return false;
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
            View childView = rv.findChildViewUnder(e.getX(), e.getY());
            if (childView != null) {
                return gestureDetector.onTouchEvent(e);
            }
            return false;
        }

        @Override
        public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instanceRef = new WeakReference<>(this);

        setContentView(R.layout.btcturk);
        ayarlar = findViewById(R.id.ayarlarbtcturk);
        yukari=findViewById(R.id.yukaribtcturk);
        asagi=findViewById(R.id.asagibtcturk);
        asagi.setVisibility(View.GONE);
        yukari.setVisibility(View.GONE);
        sol=findViewById(R.id.mainacitivy);
        start=findViewById(R.id.baslatbtcturk);
        stop=findViewById(R.id.durdurbtcturk);
        duzenle=findViewById(R.id.duzenlebtcturk);
        btcturkAnaText=findViewById(R.id.baslikbtcturk);
        BinanceTlAnaText=findViewById(R.id.baslikbinancetlbtcturk);
        BinanceAnaText=findViewById(R.id.baslikbinancebtcturk);
        scrollView = findViewById(R.id.ekran6);
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
        binancetradebutton =popupView.findViewById(R.id.binanceTrade);
        binancewalletbutton =popupView.findViewById(R.id.binanceWallet);
        paributradebutton =popupView.findViewById(R.id.paribuTrade);
        paribuwalletbutton =popupView.findViewById(R.id.paribuWallet);
        dialog=dialogBuilder.create();
        mQueue = Volley.newRequestQueue(this);
        dot = findViewById(R.id.DOTT);
        ekran6=findViewById(R.id.ekran6);
        if (dot != null && MainActivity.dot != null) {
            dot.setText(MainActivity.dot.getText().toString(), TextView.BufferType.EDITABLE);
        }
        mbottomappbar=findViewById(R.id.bottom_app_barbtcturk);
        toolbar=findViewById(R.id.toolbarbtcturk);
        setSupportActionBar(toolbar);
        if (UnifiedService.isBtcTurkServiceRunning() && calistiMi) {
            start.setVisibility(View.GONE);
            updateUI(true);
        }
        try {
            Double.parseDouble(dot.getText().toString());
            start.setVisibility(View.VISIBLE);
        } catch (NumberFormatException e) {
        }

        btcturkRecyclerView = findViewById(R.id.listview0btcturk); // XML'de id'yi güncelle
        binanceRecyclerView = findViewById(R.id.listview1btcturk);
        binanceTlRecyclerView = findViewById(R.id.listview2btcturk);
        btcturkRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binanceRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binanceTlRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        btcturkAdapter = new CoinAdapter();
        binanceAdapter = new CoinAdapter();
        binanceTlAdapter = new CoinAdapter();
        btcturkRecyclerView.setAdapter(btcturkAdapter);
        binanceRecyclerView.setAdapter(binanceAdapter);
        binanceTlRecyclerView.setAdapter(binanceTlAdapter);
        BtcTurk.RecyclerTouchListener touchListener = new BtcTurk.RecyclerTouchListener(this);
        btcturkRecyclerView.addOnItemTouchListener(touchListener);
        binanceRecyclerView.addOnItemTouchListener(touchListener);
        binanceTlRecyclerView.addOnItemTouchListener(touchListener);
        setupRecyclerViews();

        // RecyclerView'ların padding ve clipToPadding ayarlarını programmatik olarak da set et
        btcturkRecyclerView.setPadding(0, 0, 0, 0);
        btcturkRecyclerView.setClipToPadding(false);
        binanceRecyclerView.setPadding(0, 0, 0, 0);
        binanceRecyclerView.setClipToPadding(false);
        binanceTlRecyclerView.setPadding(0, 0, 0, 0);
        binanceTlRecyclerView.setClipToPadding(false);

        mediaPlayerManager = MediaPlayerManager.getInstance(this);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        start.setX((width - 178) / 2);
        start.setY((height - 800) / 2);
        ayarlar.setOnClickListener(new View.OnClickListener() {
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                Intent openMainActivity = new Intent((Context) BtcTurk.this, (Class<?>) Sesler.class);
                openMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                BtcTurk.this.startActivityIfNeeded(openMainActivity, 2);
            }
        });
        yukari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPosition > 0) {
                    currentPosition--;
                    scrollToPosition(currentPosition);
                }
            }
        });

        asagi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPosition < 2) {
                    currentPosition++;
                    scrollToPosition(currentPosition);
                }
            }
        });


        sol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openMainActivity = new Intent(BtcTurk.this, MainActivity.class);
                openMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivityIfNeeded(openMainActivity, 0);
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



        btcturkAdapter.setOnItemClickListener(position -> {

                if (BtcTurk.calistiMi) {
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                    BtcTurk.this.dialog.show();
                    if (!BtcTurk.girdi) {
                        BtcTurk.tiklanansira = MainActivity.siralamaBtcTurk[position];
                        BtcTurk.pop.setText(MainActivity.btcturkisim[position]);
                        paributradebutton.setText("BTCTurk PARİTE");
                        paributradebutton.setBackgroundColor(Color.parseColor("#1e88e5"));
                        paribuwalletbutton.setText("BTCTurk CÜZDANI");
                        paribuwalletbutton.setBackgroundColor(Color.parseColor("#1e88e5"));

                        coinName = MainActivity.btcturkisim[position];
                        BtcTurk.this.edit.setText("" + MainActivity.oranlarbtcturk[position]);
                        BtcTurk.oran.setText("" + MainActivity.oranlarbtcturk[position]);
                        BtcTurk.this.seek.setProgress((int) (MainActivity.oranlarbtcturk[position].doubleValue() * 10.0d));
                        BtcTurk.this.seekses.setProgress(MainActivity.sesSeviyesiBtcTurk[position]);
                        BtcTurk.this.seekses.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                            @Override
                            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                for (int i = 0; i < BtcTurk.btctotal; i++) {
                                    if (MainActivity.btcturkisim[i].contains(BtcTurk.pop.getText())) {
                                        MainActivity.getInstance().updateSoundLevel(i, progress, true);
                                        return;
                                    }
                                }
                            }

                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar) {
                            }

                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar) {
                            }
                        });
                        binancetradebutton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                try {
                                    Intent intent = new Intent(Intent.ACTION_VIEW);
                                    intent.setData(Uri.parse("bnc://app.binance.com/trade/trade?at=spot&symbol=" + coinName.toLowerCase().replace("_try", "").replace("_", "") + "usdt"));
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                } catch (ActivityNotFoundException e) {
                                    Toast.makeText(BtcTurk.this, "Uygulama bulunamadı", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        binancewalletbutton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                try {
                                    Intent intent = new Intent(Intent.ACTION_VIEW);
                                    intent.setData(Uri.parse("bnc://app.binance.com/funds/withdrawChooseCoin"));
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                } catch (ActivityNotFoundException e) {
                                    Toast.makeText(BtcTurk.this, "Uygulama bulunamadı", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }); paributradebutton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                try {
                                    Intent intent = new Intent(Intent.ACTION_VIEW);
                                    intent.setData(Uri.parse("btcturkpro://host/trade/" + coinName.toLowerCase()));
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                } catch (ActivityNotFoundException e) {
                                    Toast.makeText(BtcTurk.this, "Uygulama bulunamadı", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        paribuwalletbutton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                try {
                                    Intent intent = new Intent(Intent.ACTION_VIEW);
                                    intent.setData(Uri.parse("btcturkpro://host/wallet"));
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                } catch (ActivityNotFoundException e) {
                                    Toast.makeText(BtcTurk.this, "Uygulama bulunamadı", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        return ;
                    }
                    BtcTurk.tiklanansira = BtcTurk.aramaindexler.get(position).intValue();
                    BtcTurk.this.isim = MainActivity.btcturkisim[BtcTurk.tiklanansira];
                    BtcTurk.pop.setText(MainActivity.btcturkisim[BtcTurk.tiklanansira]);
                    coinName = MainActivity.btcturkisim[tiklanansira];
                    BtcTurk.this.edit.setText("" + MainActivity.oranlarbtcturk[BtcTurk.tiklanansira]);
                    BtcTurk.oran.setText("" + MainActivity.oranlarbtcturk[BtcTurk.tiklanansira]);
                    BtcTurk.this.seek.setProgress((int) (MainActivity.oranlarbtcturk[BtcTurk.tiklanansira].doubleValue() * 10.0d));
                    BtcTurk.this.seekses.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                            for (int i = 0; i < BtcTurk.btctotal; i++) {
                                if (MainActivity.btcturkisim[i].contains(BtcTurk.pop.getText())) {
                                    MainActivity.getInstance().updateSoundLevel(i, progress, true);
                                    return;
                                }
                            }
                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {
                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {
                        }
                    });
                }
            binancetradebutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("bnc://app.binance.com/trade/trade?at=spot&symbol=" + coinName.toLowerCase().replace("_try", "").replace("_", "") + "usdt"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(BtcTurk.this, "Uygulama bulunamadı", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            binancewalletbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("bnc://app.binance.com/funds/withdrawChooseCoin"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(BtcTurk.this, "Uygulama bulunamadı", Toast.LENGTH_SHORT).show();
                    }
                }
            }); paributradebutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("btcturkpro://host/trade/" + coinName.toLowerCase()));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(BtcTurk.this, "Uygulama bulunamadı", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            paribuwalletbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("btcturkpro://host/wallet"));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(BtcTurk.this, "Uygulama bulunamadı", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        });


    }
    public static BtcTurk getInstance() {
        return instanceRef != null ? instanceRef.get() : null;
    }
    public void scrollToPosition(int position) {
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                // Scroll yapılacak view'ı seç
                LinearLayoutCompat layout = scrollView.findViewById(R.id.ekran6)
                        .findViewById(R.id.linear_layout_compat);
                View targetView;

                switch (position) {
                    case 0:
                        targetView = layout.getChildAt(layout.indexOfChild(btcturkAnaText));
                        break;
                    case 1:
                        targetView = layout.getChildAt(layout.indexOfChild(BinanceTlAnaText));
                        break;
                    case 2:
                        targetView = layout.getChildAt(layout.indexOfChild(BinanceAnaText));
                        break;
                    default:
                        return;
                }

                if (targetView != null) {
                    // View'ın üst kenarının scrollview içindeki pozisyonunu hesapla
                    int targetTop = 0;
                    View current = targetView;

                    while (current != scrollView) {
                        targetTop += current.getTop();
                        if (current.getParent() instanceof View) {
                            current = (View) current.getParent();
                        } else {
                            break;
                        }
                    }

                    // Toolbar yüksekliğini hesapla
                    int toolbarHeight = toolbar != null ? toolbar.getHeight() : 0;

                    // Status bar yüksekliğini al
                    int statusBarHeight = 0;
                    int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
                    if (resourceId > 0) {
                        statusBarHeight = getResources().getDimensionPixelSize(resourceId);
                    }

                    // Son scroll pozisyonunu hesapla
                    final int scrollTo = targetTop - toolbarHeight - statusBarHeight;

                    // Smooth scroll yap
                    scrollView.smoothScrollTo(0, scrollTo);

                    // Debug için log
                    Log.d("ScrollDebug", "Position: " + position +
                            ", TargetTop: " + targetTop +
                            ", ScrollTo: " + scrollTo);
                }
            }
        });
    }

    private void updateRecyclerViewHeights(int itemCount) {
        // Ekran density'sini al
        Resources resources = getResources();
        float density = resources.getDisplayMetrics().density;

        // Her bir item için minimum yükseklik (dp olarak)
        int itemHeightDp = 37;

        // Her item için toplam yükseklik hesaplama (pixel olarak)
        int itemHeightPx = (int) (itemHeightDp * density);
        int totalHeight = itemHeightPx * itemCount;

        // Layout parametrelerini güncelle
        ViewGroup.LayoutParams paramsP = btcturkRecyclerView.getLayoutParams();
        ViewGroup.LayoutParams paramsB = binanceRecyclerView.getLayoutParams();
        ViewGroup.LayoutParams paramsBT = binanceTlRecyclerView.getLayoutParams();

        if (paramsP instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginParamsP = (ViewGroup.MarginLayoutParams) paramsP;
            ViewGroup.MarginLayoutParams marginParamsB = (ViewGroup.MarginLayoutParams) paramsB;
            ViewGroup.MarginLayoutParams marginParamsBT = (ViewGroup.MarginLayoutParams) paramsBT;

            // Margin'leri ayarla
            marginParamsP.setMargins(0, 2, 0, 2);
            marginParamsB.setMargins(0, 2, 0, 2);
            marginParamsBT.setMargins(0, 2, 0, 2);

            // Yükseklikleri ayarla
            marginParamsP.height = totalHeight;
            marginParamsB.height = totalHeight;
            marginParamsBT.height = totalHeight;

            btcturkRecyclerView.setLayoutParams(marginParamsP);
            binanceRecyclerView.setLayoutParams(marginParamsB);
            binanceTlRecyclerView.setLayoutParams(marginParamsBT);
        }

        // RecyclerView'ları yeniden çiz
        btcturkRecyclerView.requestLayout();
        binanceRecyclerView.requestLayout();
        binanceTlRecyclerView.requestLayout();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (isFinishing()) {
            // Uygulama gerçekten kapatılıyorsa
            cleanupAndKill();
        }
    }

    private void cleanupAndKill() {
        try {
            // Dialog'u temizle
            if (dialog != null) {
                dialog.dismiss();
                dialog = null;
            }

            // MediaPlayer'ı temizle
            if (mediaPlayerManager != null) {
                mediaPlayerManager.releaseAll();
                mediaPlayerManager = null;
            }

            // Volley isteklerini iptal et
            if (mQueue != null) {
                mQueue.cancelAll(request -> true);
                mQueue = null;
            }

            // RecyclerView adaptörlerini temizle
            if (btcturkAdapter != null) {
                btcturkAdapter.updateData(null);
                btcturkAdapter = null;
            }
            if (binanceAdapter != null) {
                binanceAdapter.updateData(null);
                binanceAdapter = null;
            }
            if (binanceTlAdapter != null) {
                binanceTlAdapter.updateData(null);
                binanceTlAdapter = null;
            }

            // Service'i durdur
            Intent serviceIntent = new Intent(this, UnifiedService.class);
            stopService(serviceIntent);

            // WeakReference'ı temizle
            instanceRef = null;

            // Tüm aktiviteleri kapat
            finishAffinity();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void startService(View v) {
        if (!UnifiedService.isMainServiceRunning()) {
            Toast.makeText(getApplicationContext(), "Önce hızlı servisini başlatmanız gerekir!", Toast.LENGTH_SHORT).show();
            return;
        }
        saniye.start();
        calistiMi = true;
        updateUI(true);
        start.setVisibility(View.GONE);
        btcturkAnaText.setVisibility(View.VISIBLE);
        BinanceAnaText.setVisibility(View.VISIBLE);
        BinanceTlAnaText.setVisibility(View.VISIBLE);
        btcturkRecyclerView.setVisibility(View.VISIBLE);
        binanceRecyclerView.setVisibility(View.VISIBLE);
        binanceTlRecyclerView.setVisibility(View.VISIBLE);
        asagi.setVisibility(View.VISIBLE);
        yukari.setVisibility(View.VISIBLE);
        /*this.paribucheck.setChecked(true);
        this.tlcheck.setChecked(true);
        this.binancecheck.setChecked(true);*/
        AppBarLayout.LayoutParams paramst = (AppBarLayout.LayoutParams) MainActivity.toolbar.getLayoutParams();

        ViewGroup.MarginLayoutParams layoutParams = (LinearLayoutCompat.LayoutParams) dot.getLayoutParams();
        layoutParams.setMargins(40, paramst.height + 10, 40, 5);
        dot.setLayoutParams(layoutParams);
        stop.setVisibility(View.VISIBLE);
        this.dotusd = Double.valueOf(dot.getText().toString());
        for (int i = 0; i < btctotal; i++) {
            MainActivity.oranlarbtcturk[i] = this.dotusd;
        }
        btcturkAdapter.updateData(Arrays.asList(metinlerBtcTurk));
        binanceAdapter.updateData(Arrays.asList(metinlerBinance));
        binanceTlAdapter.updateData(Arrays.asList(metinlerBinanceTl));
        updateRecyclerViewHeights(btctotal);


        this.dotusd = Double.valueOf(dot.getText().toString());
        Toast.makeText(getApplicationContext(), "Servis Başlatıldı", Toast.LENGTH_SHORT).show();
        Intent serviceIntent = new Intent(this, UnifiedService.class);
        serviceIntent.putExtra("service_type", "btcturk");
        ContextCompat.startForegroundService(this, serviceIntent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (UnifiedService.isBtcTurkServiceRunning()&& calistiMi) {
            updateUI(true);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (!isFinishing() && calistiMi) {
            updateUI(false);
        }

    }

    public static boolean isVisible = true;
    private void updateUI(boolean show) {
        SharedPreferences prefs = getSharedPreferences("UIState", MODE_PRIVATE);
        prefs.edit().putBoolean("isVisible", show).apply();
        isVisible = show;
        if (show) {btcturkAnaText.setVisibility(View.VISIBLE);
            BinanceAnaText.setVisibility(View.VISIBLE);
            BinanceTlAnaText.setVisibility(View.VISIBLE);
            btcturkRecyclerView.setVisibility(View.VISIBLE);
            binanceRecyclerView.setVisibility(View.VISIBLE);
            binanceTlRecyclerView.setVisibility(View.VISIBLE);

            if (paribucheck != null) paribucheck.setChecked(true);
            if (tlcheck != null) tlcheck.setChecked(true);
            if (binancecheck != null) binancecheck.setChecked(true);
        } else {
            btcturkAnaText.setVisibility(View.GONE);
            BinanceTlAnaText.setVisibility(View.GONE);
            BinanceAnaText.setVisibility(View.GONE);
            btcturkRecyclerView.setVisibility(View.GONE);
            binanceRecyclerView.setVisibility(View.GONE);
            binanceTlRecyclerView.setVisibility(View.GONE);
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
        // Dialog'u kapat
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }

        calistiMi = false;
        updateUI(false);

        Intent serviceIntent = new Intent(this, UnifiedService.class);
        stopService(serviceIntent);

        finishAffinity();
    }

    public String Dot() {


        for (int i = 0; i < btctotal; i++) {
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

                    mediaPlayerManager.playSound(seslerBtcTurk[a2], MainActivity.soundPrefs.getInt("btcturk_sound_" + a2, 15) / 15.0f);
                    otuyorMu = true;
                } else if (!Sesler.arti && !Sesler.eksi && (MainActivity.farklarBtcTurk[a2]/5 < MainActivity.oranlarbtcturk[a2].doubleValue() * (-1.0d) || MainActivity.farklarBtcTurk[a2] > MainActivity.oranlarbtcturk[a2].doubleValue())) {

                     SpannableString spannableString4 = metinlerBtcTurk[a2];
                    spannableString4.setSpan(this.fscgreen, 0, spannableString4.length(), 33);
                    SpannableString spannableString5 = metinlerBinance[a2];
                    spannableString5.setSpan(this.yellow, 0, spannableString5.length(), 33);
                    SpannableString spannableString6 = metinlerBinanceTl[a2];
                    spannableString6.setSpan(this.red, 0, spannableString6.length(), 33);

                    mediaPlayerManager.playSound(seslerBtcTurk[a2], MainActivity.soundPrefs.getInt("btcturk_sound_" + a2, 15) / 15.0f);

                    otuyorMu = true;
                } else if (!Sesler.arti && Sesler.eksi && MainActivity.farklarBtcTurk[a2] /5< MainActivity.oranlarbtcturk[a2].doubleValue() * (-1.0d)) {
                    SpannableString spannableString7 = metinlerBtcTurk[a2];
                    spannableString7.setSpan(this.fscgreen, 0, spannableString7.length(), 33);
                    SpannableString spannableString8 = metinlerBinance[a2];
                    spannableString8.setSpan(this.yellow, 0, spannableString8.length(), 33);
                    SpannableString spannableString9 = metinlerBinanceTl[a2];
                    spannableString9.setSpan(this.red, 0, spannableString9.length(), 33);
                    mediaPlayerManager.playSound(seslerBtcTurk[a2], MainActivity.soundPrefs.getInt("btcturk_sound_" + a2, 15) / 15.0f);
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
                btcturkAdapter.updateData(Arrays.asList(metinlerBtcTurk));
                binanceAdapter.updateData(Arrays.asList(metinlerBinance));
                binanceTlAdapter.updateData(Arrays.asList(metinlerBinanceTl));
                updateRecyclerViewHeights(btctotal);

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
                        this.otuyorMuArama = true;
                    } else if (!Sesler.arti && !Sesler.eksi && (MainActivity.farklarBtcTurk[index] < MainActivity.oranlarbtcturk[index].doubleValue() * (-1.0d) || MainActivity.farklarBtcTurk[index] > MainActivity.oranlarbtcturk[index].doubleValue())) {
                        SpannableString spannableString17 = metinlerBtcTurkarama[a3];
                        spannableString17.setSpan(this.fscgreen, 0, spannableString17.length(), 33);
                        SpannableString spannableString18 = metinlerBinancearama[a3];
                        spannableString18.setSpan(this.yellow, 0, spannableString18.length(), 33);
                        SpannableString spannableString19 = metinlerBinanceTlarama[a3];
                        spannableString19.setSpan(this.red, 0, spannableString19.length(), 33);
                        this.otuyorMuArama = true;
                    } else {
                        if (!Sesler.arti && Sesler.eksi) {
                            if (MainActivity.farklarBtcTurk[index] < MainActivity.oranlarbtcturk[index].doubleValue() * (-1.0d)) {
                                SpannableString spannableString20 = metinlerBtcTurkarama[a3];
                                spannableString20.setSpan(this.fscgreen, 0, spannableString20.length(), 33);
                                SpannableString spannableString21 = metinlerBinancearama[a3];
                                spannableString21.setSpan(this.yellow, 0, spannableString21.length(), 33);
                                SpannableString spannableString22 = metinlerBinanceTlarama[a3];
                                spannableString22.setSpan(this.red, 0, spannableString22.length(), 33);
                                this.otuyorMuArama = true;
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


                }
                btcturkAdapter.updateData(Arrays.asList(metinlerBtcTurkarama));
                binanceAdapter.updateData(Arrays.asList(metinlerBinancearama));
                binanceTlAdapter.updateData(Arrays.asList(metinlerBinanceTlarama));
                updateRecyclerViewHeights(btctotal2);
            }
        }
        return "";
    }

  /*  public boolean onOptionsItemSelected(MenuItem item) {
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
*/
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater ().inflate (search,menu);
        MenuItem menuItem = menu.findItem(R.id.arama);
       /* paribucheck = menu.findItem(R.id.checkboxhizliparibu);
        tlcheck = menu.findItem(R.id.checkboxhizlitl);
        binancecheck = menu.findItem(R.id.checkboxhizlibinance);*/
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
                    if (BtcTurk.this.aramaMetni.length() > 0 && MainActivity.isimlerBtcTurk[a].toLowerCase().contains(BtcTurk.this.aramaMetni.toLowerCase())) {
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
                    btcturkAdapter.updateData(Arrays.asList(metinlerBtcTurk));
                    binanceAdapter.updateData(Arrays.asList(metinlerBinance));
                    binanceTlAdapter.updateData(Arrays.asList(metinlerBinanceTl));
                    updateRecyclerViewHeights(btctotal);
                }
                return false;
            }
        });
        return true;
    }
}