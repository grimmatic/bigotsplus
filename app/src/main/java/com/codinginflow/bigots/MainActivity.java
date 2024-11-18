package com.codinginflow.bigots;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.MediaPlayer;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
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
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.lang.ref.WeakReference;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import static com.codinginflow.bigots.BtcTurk.btctotal;
import static com.codinginflow.bigots.R.menu.search;

public class MainActivity extends AppCompatActivity {
    public static int MainservisSayac;
    public static MediaPlayer aave;
    public static MediaPlayer acm;
    public static MediaPlayer ada;
    public static MediaPlayer agix;
    public static MediaPlayer alarm;
    public static MediaPlayer algo;
    public static MediaPlayer alice;
    public static MediaPlayer amp;
    public static MediaPlayer ankr;
    public static MediaPlayer ape;
    public static MediaPlayer api3;
    public static MediaPlayer apt;
    static ArrayList<Integer> aramaindexler;
    public static MediaPlayer arb;
    public static MediaPlayer arkm;
    public static MediaPlayer arpa;
    public static MediaPlayer asr;
    public static MediaPlayer atm;
    public static MediaPlayer atom;
    public static MediaPlayer audio;
    public static MediaPlayer avax;
    public static MediaPlayer axl;
    public static MediaPlayer axs;
    public static MediaPlayer bal;
    public static MediaPlayer band;
    public static MediaPlayer bar;
    public static MediaPlayer bat;
    public static MediaPlayer bch;
    public static MediaPlayer beam;
    static String binanceDuyuruText;
    static String binanceGeciciDuyuruText;
    static ListView binanceL;
    static ListView binanceTlL;
    public static MediaPlayer blur;
    public static MediaPlayer bnt;
    public static MediaPlayer bonk;
    static float[] btcturk;
    static String[] btcturkisim;

    public static MediaPlayer btt;
    public static MediaPlayer chz;
    public static MediaPlayer city;
    public static MediaPlayer clv;
    public static MediaPlayer comp;
    public static MediaPlayer crv;
    public static MediaPlayer ctsi;
    public static MediaPlayer cvc;
    public static MediaPlayer dash;
    public static MediaPlayer dogem;
    public static EditText dot;
    public static MediaPlayer dots;
    public static MediaPlayer dydx;
    public static MediaPlayer dym;
    public static MediaPlayer enj;
    public static MediaPlayer ens;
    public static MediaPlayer eos;
    public static MediaPlayer etc;
    public static MediaPlayer etherfi;
    static float[] farklar;
    static float[] farklarBtcTurk;
    public static MediaPlayer fet;
    public static MediaPlayer fil;
    public static MediaPlayer floki;
    public static MediaPlayer flow;
    public static MediaPlayer ftm;
    public static MediaPlayer gala;
    static boolean girdi;
    public static MediaPlayer glm;
    public static MediaPlayer grt;
    public static MediaPlayer hot;
    static float[] huobi;
    static float[] huobibtcturk;
    public static MediaPlayer icp;
    public static MediaPlayer imx;
    public static MediaPlayer inch;
    static int[] indexler;
    static int[] indexlerbtcturk;
    public static MediaPlayer inj;
    public static MediaPlayer iota;
    static String[] isimler;
    static String[] isimlerBtcTurk;
    public static MediaPlayer jasmy;
    public static MediaPlayer joe;
    public static MediaPlayer jto;
    public static MediaPlayer jup;
    public static MediaPlayer juv;
    public static MediaPlayer ldo;
    public static MediaPlayer link;
    static boolean listGeldiMi;
    public static MediaPlayer lpt;
    public static MediaPlayer lrc;
    public static MediaPlayer ltc;
    public static MediaPlayer luna;
    public static MediaPlayer lunc;
    public static MediaPlayer magic;
    public static MediaPlayer maker;
    public static MediaPlayer mana;
    public static MediaPlayer manta;
    public static MediaPlayer mask;
    public static MediaPlayer matic;
    public static MediaPlayer meme;
    static SpannableString[] metinlerBinance;
    static SpannableString[] metinlerBinanceTl;
    static SpannableString[] metinlerBinanceTlarama;
    static SpannableString[] metinlerBinancearama;
    static SpannableString[] metinlerParibu;
    static SpannableString[] metinlerParibuarama;
    public static MediaPlayer mina;
    public static MediaPlayer neo;
    public static MediaPlayer nmr;
    public static MediaPlayer ocean;
    public static MediaPlayer ogn;
    public static MediaPlayer ont;
    public static MediaPlayer op;
    static boolean otuyorMu;
    public static MediaPlayer oxt;
    static int p;
    static float[] paribu;
    static ListView paribuL;
    static String[] paribuisim;
    public static MediaPlayer paxg;
    public static MediaPlayer pendle;
    public static MediaPlayer pepe;
    public static MediaPlayer psg;
    public static MediaPlayer pyth;
    public static MediaPlayer qnt;
    public static MediaPlayer rad;
    public static MediaPlayer rdnt;
    public static MediaPlayer reef;
    public static MediaPlayer rlc;
    public static MediaPlayer rndr;
    public static MediaPlayer rvn;
    public static MediaPlayer sand;
    public static MediaPlayer saniye;
    public static MediaPlayer sei;
    static int[] sesSeviyesi;
    public static int[] sesSeviyesiBtcTurk;
    static MediaPlayer[] sesler;
    public static MediaPlayer[] seslerBtcTurk;
    public static MediaPlayer shib;
    static int[] siralama;
    static int[] siralamaBtcTurk;
    public static MediaPlayer skl;
    public static MediaPlayer snx;
    public static MediaPlayer sol;
    public static MediaPlayer spell;
    public static MediaPlayer stg;
    public static MediaPlayer storj;
    public static MediaPlayer strk;
    public static MediaPlayer stx;
    public static MediaPlayer sui;
    public static MediaPlayer sushi;
    public static MediaPlayer t;
    public static MediaPlayer theta;
    public static MediaPlayer tia;
    public static MediaPlayer tlm;
    public static MediaPlayer waves;
    public static MediaPlayer omg;
    public static MediaPlayer pla;
    public static MediaPlayer tnsr;
    static Toolbar toolbar;
    public static MediaPlayer tron;
    public static MediaPlayer uma;
    public static MediaPlayer uni;
    static String url;
    public static MediaPlayer vanry;
    public static MediaPlayer vet;
    public static MediaPlayer w;
    public static MediaPlayer xlm;
    public static MediaPlayer xrp;
    public static MediaPlayer xtz;
    public static MediaPlayer zil;
    public static MediaPlayer zrx;
    public static MediaPlayer eigen;
    public static MediaPlayer ray;
    public static MediaPlayer wif;
    public static MediaPlayer io;
    public static MediaPlayer aevo;
    public static MediaPlayer ena;
    public static MediaPlayer zk;
    public static MediaPlayer alt;
    public static MediaPlayer syn;
    public static MediaPlayer pda;
    public static MediaPlayer zro;
    public static MediaPlayer super0;
    private TextView BinanceAnaText;
    private TextView BinanceTlAnaText;
    ArrayAdapter<SpannableString> arrayAdapter;
    ArrayAdapter<SpannableString> arrayAdapter2;
    ArrayAdapter<SpannableString> arrayAdapter3;
    FloatingActionButton asagi;
    FloatingActionButton ayarlar;
    MenuItem binancecheck;
    private AlertDialog dialog;
    private AlertDialog.Builder dialogBuilder;
    float downX;
    FloatingActionButton duzenle;
    private EditText edit;
    NestedScrollView ekran3;
    private Button geributton;
    private RequestQueue mQueue;
    private BottomAppBar mbottomappbar;
    private TextView oran;
    private TextView paribuAnaText;
    MenuItem paribucheck;
    private TextView pop;
    private Button popupButon;
    private SeekBar seek;
    private SeekBar seekses;
    FloatingActionButton start;
    FloatingActionButton stop;
    MenuItem tlcheck;
    float upX;
    FloatingActionButton yukari;
    static float tsatis = 0.0f;
    static float tsatisb = 0.0f;
    static float btc = 0.0f;
    static int hizli = 125;
    static int hizli2 = 0;
    static Double[] oranlar = new Double[hizli];
    static Double[] oranlarbtcturk = new Double[btctotal];
    static int tiklanansira = 0;
    Double dotusd = Double.valueOf(2.5d);
    private String isim = "";
    private int sayac = 0;
    ForegroundColorSpan fscgreen= new ForegroundColorSpan(Color.GREEN);
    ForegroundColorSpan fsccyan= new ForegroundColorSpan(0XFF6A9C1E);
    ForegroundColorSpan fscdarkyellow= new ForegroundColorSpan(0XFFEBA65F);
    ForegroundColorSpan yellow= new ForegroundColorSpan(Color.YELLOW);
    ForegroundColorSpan red= new ForegroundColorSpan(0xFFF83E3A);
    ForegroundColorSpan darkred= new ForegroundColorSpan(0xFFDC743C);
    boolean calistiMi = false;
    boolean otuyorMuArama = false;
    String aramaMetni = "";
    DecimalFormat df = new DecimalFormat("#.###");

    static {

        indexler = new int[hizli];
        huobi = new float[hizli];
        paribu = new float[hizli];
        paribuisim = new String[hizli];
        farklar = new float[hizli];
        farklarBtcTurk = new float[btctotal];
        sesler = new MediaPlayer[hizli];
        isimler = new String[hizli];
        isimlerBtcTurk = new String[btctotal];
        siralama = new int[hizli];
        siralamaBtcTurk = new int[btctotal];
        sesSeviyesi = new int[hizli];
        seslerBtcTurk = new MediaPlayer[btctotal];
        sesSeviyesiBtcTurk = new int[btctotal];
        metinlerParibu = new SpannableString[hizli];
        metinlerBinance = new SpannableString[hizli];
        metinlerBinanceTl = new SpannableString[hizli];
        aramaindexler = new ArrayList<>();
        girdi = false;
        listGeldiMi = false;
        binanceDuyuruText = "";
        binanceGeciciDuyuruText = "";
        p = 0;
        btcturk = new float[btctotal];
        huobibtcturk = new float[btctotal];
        btcturkisim = new String[btctotal];
        indexlerbtcturk = new int[btctotal];
        MainservisSayac = 0;
        otuyorMu = false;
    }

    public static void mergeSort(float[] arr, int left, int right, float[] f1, float[] f2, String[] s, String[] s1, int[] i0, int[] i1, int[] i2, Double[] d, MediaPlayer[] mP) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(arr, left, middle, f1, f2, s, s1, i0, i1, i2, d, mP);
            mergeSort(arr, middle + 1, right, f1, f2, s, s1, i0, i1, i2, d, mP);
            merge(arr, left, middle, right, f1, f2, s, s1, i0, i1, i2, d, mP);
        }
    }

    public static void merge(float[] arr, int left, int middle, int right, float[] f1, float[] f2, String[] s, String[] s1, int[] i0, int[] i1, int[] i2, Double[] d, MediaPlayer[] mP) {
        int n1 = (middle - left) + 1;
        int n2 = right - middle;
        float[] L = new float[n1];
        float[] R = new float[n2];
        float[] floatL1 = new float[n1];
        float[] floatR1 = new float[n2];
        float[] floatL2 = new float[n1];
        float[] floatR2 = new float[n2];
        String[] strL = new String[n1];
        String[] strR = new String[n2];
        String[] strL1 = new String[n1];
        String[] strR1 = new String[n2];
        int[] intL0 = new int[n1];
        int[] intR0 = new int[n2];
        int[] intL1 = new int[n1];
        int[] intR1 = new int[n2];
        int[] intR12 = new int[n1];
        int[] intR02 = new int[n2];
        double[] doubleL = new double[n1];
        double[] doubleR = new double[n2];
        MediaPlayer[] mPL = new MediaPlayer[n1];
        MediaPlayer[] mPR = new MediaPlayer[n2];
        for (int i = 0; i < n1; i++) {
            L[i] = arr[left + i];
            floatL1[i] = f1[left + i];
            floatL2[i] = f2[left + i];
            strL[i] = s[left + i];
            strL1[i] = s1[left + i];
            intL0[i] = i0[left + i];
            intL1[i] = i1[left + i];
            intR12[i] = i2[left + i];
            doubleL[i] = d[left + i].doubleValue();
            mPL[i] = mP[left + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = arr[middle + 1 + j];
            floatR1[j] = f1[middle + 1 + j];
            floatR2[j] = f2[middle + 1 + j];
            strR[j] = s[middle + 1 + j];
            strR1[j] = s1[middle + 1 + j];
            intR0[j] = i0[middle + 1 + j];
            intR1[j] = i1[middle + 1 + j];
            intR02[j] = i2[middle + 1 + j];
            doubleR[j] = d[middle + 1 + j].doubleValue();
            mPR[j] = mP[middle + 1 + j];
        }
        int i3 = 0;
        int k = left;
        int j2 = 0;
        while (i3 < n1 && j2 < n2) {
            if (L[i3] >= R[j2]) {
                arr[k] = L[i3];
                f1[k] = floatL1[i3];
                f2[k] = floatL2[i3];
                s[k] = strL[i3];
                s1[k] = strL1[i3];
                i0[k] = intL0[i3];
                i1[k] = intL1[i3];
                i2[k] = intR12[i3];
                d[k] = Double.valueOf(doubleL[i3]);
                mP[k] = mPL[i3];
                i3++;
            } else {
                arr[k] = R[j2];
                f1[k] = floatR1[j2];
                f2[k] = floatR2[j2];
                s[k] = strR[j2];
                s1[k] = strR1[j2];
                i0[k] = intR0[j2];
                i1[k] = intR1[j2];
                i2[k] = intR02[j2];
                d[k] = Double.valueOf(doubleR[j2]);
                mP[k] = mPR[j2];
                j2++;
            }
            k++;
        }
        while (i3 < n1) {
            arr[k] = L[i3];
            f1[k] = floatL1[i3];
            f2[k] = floatL2[i3];
            s[k] = strL[i3];
            s1[k] = strL1[i3];
            i0[k] = intL0[i3];
            i1[k] = intL1[i3];
            i2[k] = intR12[i3];
            d[k] = Double.valueOf(doubleL[i3]);
            mP[k] = mPL[i3];
            i3++;
            k++;
        }
        while (j2 < n2) {
            arr[k] = R[j2];
            f1[k] = floatR1[j2];
            f2[k] = floatR2[j2];
            s[k] = strR[j2];
            s1[k] = strR1[j2];
            i0[k] = intR0[j2];
            i1[k] = intR1[j2];
            i2[k] = intR02[j2];
            d[k] = Double.valueOf(doubleR[j2]);
            mP[k] = mPR[j2];
            j2++;
            k++;
        }
    }

    public static void mergeSortBtcTurk(float[] arr, int left, int right, float[] f1, float[] f2,
                                        String[] s, String[] s1, int[] i0, int[] i1, int[] i2, Double[] d, MediaPlayer[] mP) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSortBtcTurk(arr, left, middle, f1, f2, s, s1, i0, i1, i2, d, mP);
            mergeSortBtcTurk(arr, middle + 1, right, f1, f2, s, s1, i0, i1, i2, d, mP);
            mergeBtcTurk(arr, left, middle, right, f1, f2, s, s1, i0, i1, i2, d, mP);
        }
    }

    public static void mergeBtcTurk(float[] arr, int left, int middle, int right, float[] f1, float[] f2,
                                    String[] s, String[] s1, int[] i0, int[] i1, int[] i2, Double[] d, MediaPlayer[] mP) {
        int n1 = middle - left + 1;
        int n2 = right - middle;

        float[] L = new float[n1];
        float[] R = new float[n2];
        float[] floatL1 = new float[n1];
        float[] floatR1 = new float[n2];
        float[] floatL2 = new float[n1];
        float[] floatR2 = new float[n2];
        String[] strL = new String[n1];
        String[] strR = new String[n2];
        String[] strL1 = new String[n1];
        String[] strR1 = new String[n2];
        int[] intL0 = new int[n1];
        int[] intR0 = new int[n2];
        int[] intL1 = new int[n1];
        int[] intR1 = new int[n2];
        int[] intR12 = new int[n1];
        int[] intR02 = new int[n2];
        double[] doubleL = new double[n1];
        double[] doubleR = new double[n2];
        MediaPlayer[] mPL = new MediaPlayer[n1];
        MediaPlayer[] mPR = new MediaPlayer[n2];

        // Sol ve sağ alt dizileri doldur
        for (int i = 0; i < n1; i++) {
            L[i] = arr[left + i];
            floatL1[i] = f1[left + i];
            floatL2[i] = f2[left + i];
            strL[i] = s[left + i];
            strL1[i] = s1[left + i];
            intL0[i] = i0[left + i];
            intL1[i] = i1[left + i];
            intR12[i] = i2[left + i];
            doubleL[i] = d[left + i];
            mPL[i] = mP[left + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = arr[middle + 1 + j];
            floatR1[j] = f1[middle + 1 + j];
            floatR2[j] = f2[middle + 1 + j];
            strR[j] = s[middle + 1 + j];
            strR1[j] = s1[middle + 1 + j];
            intR0[j] = i0[middle + 1 + j];
            intR1[j] = i1[middle + 1 + j];
            intR02[j] = i2[middle + 1 + j];
            doubleR[j] = d[middle + 1 + j];
            mPR[j] = mP[middle + 1 + j];
        }

        // Birleştirme işlemi
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] >= R[j]) {
                arr[k] = L[i];
                f1[k] = floatL1[i];
                f2[k] = floatL2[i];
                s[k] = strL[i];
                s1[k] = strL1[i];
                i0[k] = intL0[i];
                i1[k] = intL1[i];
                i2[k] = intR12[i];
                d[k] = doubleL[i];
                mP[k] = mPL[i];
                i++;
            } else {
                arr[k] = R[j];
                f1[k] = floatR1[j];
                f2[k] = floatR2[j];
                s[k] = strR[j];
                s1[k] = strR1[j];
                i0[k] = intR0[j];
                i1[k] = intR1[j];
                i2[k] = intR02[j];
                d[k] = doubleR[j];
                mP[k] = mPR[j];
                j++;
            }
            k++;
        }

        // Kalan elemanları kopyala
        while (i < n1) {
            arr[k] = L[i];
            f1[k] = floatL1[i];
            f2[k] = floatL2[i];
            s[k] = strL[i];
            s1[k] = strL1[i];
            i0[k] = intL0[i];
            i1[k] = intL1[i];
            i2[k] = intR12[i];
            d[k] = doubleL[i];
            mP[k] = mPL[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = R[j];
            f1[k] = floatR1[j];
            f2[k] = floatR2[j];
            s[k] = strR[j];
            s1[k] = strR1[j];
            i0[k] = intR0[j];
            i1[k] = intR1[j];
            i2[k] = intR02[j];
            d[k] = doubleR[j];
            mP[k] = mPR[j];
            j++;
            k++;
        }
    }
    private static MainActivity instance;
    protected void onCreate(Bundle savedInstanceState) {
        Sesler.arti = true;
        instance = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 1);
            }
        }
        if (Service.isRunning() && calistiMi) {
            start.setVisibility(View.GONE);
            updateUI(true);
        }

        if (savedInstanceState != null) {
            calistiMi = savedInstanceState.getBoolean("calistiMi", false);
            if (calistiMi && Service.isRunning()) {
                start.setVisibility(View.GONE);
                updateUI(true);
            }
        }
        paribuL = findViewById(R.id.listview);
        binanceL =  findViewById(R.id.listview2);
        binanceTlL =  findViewById(R.id.listview3);
        mQueue = Volley.newRequestQueue(this);
        dot =  findViewById(R.id.DOT);
        ayarlar = findViewById(R.id.ayarlar);
        yukari = findViewById(R.id.yukari);
        asagi = findViewById(R.id.asagi);
        start = findViewById(R.id.baslat);
        stop = findViewById(R.id.durdur);
        duzenle = findViewById(R.id.duzenle);
        paribuAnaText = findViewById(R.id.baslikparibuhizli);
        BinanceTlAnaText =  findViewById(R.id.baslikbinancetlhizli);
        BinanceAnaText =  findViewById(R.id.baslikbinancehizli);
        BottomAppBar findViewById = findViewById(R.id.bottom_app_bar);
        mbottomappbar = findViewById;
        setSupportActionBar(findViewById);
        ekran3 = findViewById(R.id.ekran1);
        Toolbar findViewById2 = findViewById(R.id.toolbar);
        toolbar = findViewById2;
        setSupportActionBar(findViewById2);
        dialogBuilder = new AlertDialog.Builder(this);
        View popupView = getLayoutInflater().inflate(R.layout.popup, (ViewGroup) null);
        pop = (TextView) popupView.findViewById(R.id.textView665);
        edit = (EditText) popupView.findViewById(R.id.edittext);
        seek = (SeekBar) popupView.findViewById(R.id.seekbar665);
        seekses = (SeekBar) popupView.findViewById(R.id.seekbar666);
        popupButon = (Button) popupView.findViewById(R.id.button665);
        geributton = (Button) popupView.findViewById(R.id.button6650);
        oran = (TextView) popupView.findViewById(R.id.textView6650);
        dialogBuilder.setView(popupView);
        AlertDialog create = dialogBuilder.create();
        dialog = create;
        create.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dots = MediaPlayer.create(this, R.raw.dot);
        avax = MediaPlayer.create(this, R.raw.avax);
        tron = MediaPlayer.create(this, R.raw.tron);
        eos = MediaPlayer.create(this, R.raw.eos);
        saniye = MediaPlayer.create(this, R.raw.saniye);
        xlm = MediaPlayer.create(this, R.raw.sitellar);
        xrp = MediaPlayer.create(this, R.raw.ripple);
        ont = MediaPlayer.create(this, R.raw.ont);
        btt = MediaPlayer.create(this, R.raw.btt);
        atom = MediaPlayer.create(this, R.raw.atom);
        hot = MediaPlayer.create(this, R.raw.hot);
        neo = MediaPlayer.create(this, R.raw.neo);
        bat = MediaPlayer.create(this, R.raw.bat);
        chz = MediaPlayer.create(this, R.raw.chz);
        uni = MediaPlayer.create(this, R.raw.uni);
        bal = MediaPlayer.create(this, R.raw.bal);
        ada = MediaPlayer.create(this, R.raw.ada);
        dogem = MediaPlayer.create(this, R.raw.doge);
        ltc = MediaPlayer.create(this, R.raw.lite);
        rvn = MediaPlayer.create(this, R.raw.rvn);
        waves = MediaPlayer.create(this, R.raw.waves);
        xtz = MediaPlayer.create(this, R.raw.xtz);
        aave = MediaPlayer.create(this, R.raw.aave);
        link = MediaPlayer.create(this, R.raw.link);
        maker = MediaPlayer.create(this, R.raw.maker);
        omg = MediaPlayer.create(this, R.raw.omg);
        reef = MediaPlayer.create(this, R.raw.reef);
        lrc = MediaPlayer.create(this, R.raw.lrc);
        band= MediaPlayer.create(this, R.raw.band);
        algo= MediaPlayer.create(this, R.raw.algo);
        zil= MediaPlayer.create(this, R.raw.zil);
        grt= MediaPlayer.create(this, R.raw.grt);
        enj= MediaPlayer.create(this, R.raw.enj);
        theta= MediaPlayer.create(this, R.raw.theta);
        matic= MediaPlayer.create(this, R.raw.matic);
        oxt= MediaPlayer.create(this, R.raw.oxt);
        psg= MediaPlayer.create(this, R.raw.psg);
        atm= MediaPlayer.create(this, R.raw.atm);
        asr= MediaPlayer.create(this, R.raw.asr);
        bar= MediaPlayer.create(this, R.raw.bar);
        juv= MediaPlayer.create(this, R.raw.juv);
        acm= MediaPlayer.create(this, R.raw.acm);
        crv= MediaPlayer.create(this, R.raw.crv);
        ogn= MediaPlayer.create(this, R.raw.ogn);
        mana= MediaPlayer.create(this, R.raw.mana);
        iota= MediaPlayer.create(this, R.raw.iota);
        sol= MediaPlayer.create(this, R.raw.sol);
        mina= MediaPlayer.create(this, R.raw.mina);
        ape= MediaPlayer.create(this, R.raw.ape);
        vet= MediaPlayer.create(this, R.raw.vet);
        ankr= MediaPlayer.create(this, R.raw.ankr);
        shib= MediaPlayer.create(this, R.raw.shib);
        lpt=MediaPlayer.create(this, R.raw.lpt);
        inj=MediaPlayer.create(this, R.raw.inj);
        icp=MediaPlayer.create(this, R.raw.icp);
        ftm=MediaPlayer.create(this, R.raw.ftm);
        axs=MediaPlayer.create(this, R.raw.axs);
        bch=MediaPlayer.create(this, R.raw.bch);
        ens=MediaPlayer.create(this, R.raw.ens);
        alice=MediaPlayer.create(this, R.raw.alice);
        tlm=MediaPlayer.create(this, R.raw.tlm);
        sand=MediaPlayer.create(this, R.raw.sand);
        audio=MediaPlayer.create(this, R.raw.audio);
        clv=MediaPlayer.create(this, R.raw.clv);
        gala=MediaPlayer.create(this, R.raw.gala);
        vanry=MediaPlayer.create(this, R.raw.vanry);
        uma=MediaPlayer.create(this, R.raw.uma);
        storj=MediaPlayer.create(this, R.raw.storj);
        city=MediaPlayer.create(this, R.raw.city);
        rad=MediaPlayer.create(this, R.raw.rad);
        inch=MediaPlayer.create(this, R.raw.inch);
        comp=MediaPlayer.create(this, R.raw.comp);
        snx=MediaPlayer.create(this, R.raw.snx);
        fet=MediaPlayer.create(this, R.raw.fet);
        spell=MediaPlayer.create(this, R.raw.spell);
        stg=MediaPlayer.create(this, R.raw.stg);
        qnt=MediaPlayer.create(this, R.raw.qnt);
        apt=MediaPlayer.create(this, R.raw.apt);
        rndr=MediaPlayer.create(this, R.raw.rndr);
        arb=MediaPlayer.create(this, R.raw.arb);
        sui=MediaPlayer.create(this, R.raw.sui);
        ctsi=MediaPlayer.create(this, R.raw.ctsi);
        op=MediaPlayer.create(this, R.raw.op);
        pepe=MediaPlayer.create(this, R.raw.pepe);
        ocean=MediaPlayer.create(this, R.raw.ocean);
        rlc=MediaPlayer.create(this, R.raw.rlc);
        skl=MediaPlayer.create(this, R.raw.skl);
        agix=MediaPlayer.create(this, R.raw.agix);
        t=MediaPlayer.create(this, R.raw.t);
        dash=MediaPlayer.create(this, R.raw.dash);
        fil=MediaPlayer.create(this, R.raw.fil);
        stx=MediaPlayer.create(this, R.raw.stx);
        amp=MediaPlayer.create(this, R.raw.amp);
        lunc=MediaPlayer.create(this, R.raw.lunc);
        bnt=MediaPlayer.create(this, R.raw.bnt);
        glm=MediaPlayer.create(this, R.raw.glm);
        luna=MediaPlayer.create(this, R.raw.luna);
        paxg=MediaPlayer.create(this, R.raw.paxg);
        imx=MediaPlayer.create(this, R.raw.imx);
        pla=MediaPlayer.create(this, R.raw.pla);
        cvc=MediaPlayer.create(this, R.raw.cvc);
        etc=MediaPlayer.create(this, R.raw.etc);
        flow=MediaPlayer.create(this, R.raw.flow);
        mask=MediaPlayer.create(this, R.raw.mask);
        api3=MediaPlayer.create(this, R.raw.api3);
        t=MediaPlayer.create(this, R.raw.t);
        arpa=MediaPlayer.create(this, R.raw.arpa);
        ldo=MediaPlayer.create(this, R.raw.ldo);
        sushi=MediaPlayer.create(this, R.raw.sushi);
        magic=MediaPlayer.create(this, R.raw.magic);
        sei=MediaPlayer.create(this, R.raw.sei);
        rdnt=MediaPlayer.create(this, R.raw.rdnt);
        floki=MediaPlayer.create(this, R.raw.floki);
        dydx=MediaPlayer.create(this, R.raw.dydx);
        jasmy=MediaPlayer.create(this, R.raw.jasmy);
        zrx=MediaPlayer.create(this, R.raw.zrx);
        nmr=MediaPlayer.create(this, R.raw.nmr);
        tia=MediaPlayer.create(this, R.raw.tia);
        blur=MediaPlayer.create(this, R.raw.blur);
        bonk=MediaPlayer.create(this, R.raw.bonk);
        beam=MediaPlayer.create(this, R.raw.beam);
        pyth=MediaPlayer.create(this, R.raw.pyth);
        joe=MediaPlayer.create(this, R.raw.joe);
        jto=MediaPlayer.create(this, R.raw.jto);
        meme=MediaPlayer.create(this, R.raw.meme);
        strk=MediaPlayer.create(this, R.raw.strk);
        dym=MediaPlayer.create(this, R.raw.dym);
        jup=MediaPlayer.create(this, R.raw.jup);
        pendle=MediaPlayer.create(this, R.raw.pendle);
        manta=MediaPlayer.create(this, R.raw.manta);
        alarm=MediaPlayer.create(this, R.raw.alarm);
        w = MediaPlayer.create(this, R.raw.w);
        etherfi = MediaPlayer.create(this, R.raw.etherfi);
        tnsr = MediaPlayer.create(this, R.raw.tnsr);
        arkm = MediaPlayer.create( this, R.raw.arkm);
        axl = MediaPlayer.create( this, R.raw.axl);
        ray = MediaPlayer.create( this, R.raw.ray);
        eigen = MediaPlayer.create( this, R.raw.eigen);
        wif = MediaPlayer.create( this, R.raw.wif);
        io = MediaPlayer.create( this, R.raw.io);
        aevo = MediaPlayer.create( this, R.raw.aevo);
        ena = MediaPlayer.create( this, R.raw.ena);
        zk = MediaPlayer.create( this, R.raw.zk);
        alt = MediaPlayer.create( this, R.raw.alt);
        syn = MediaPlayer.create( this, R.raw.syn);
        pda = MediaPlayer.create( this, R.raw.pda);
        zro = MediaPlayer.create( this, R.raw.zro);
        super0 = MediaPlayer.create( this, R.raw.super0);



        indexler[0] = 954;
        indexler[1] = 1052;
        indexler[2] = 350;
        indexler[3] = 323;
        indexler[4] = 1908;
        indexler[5] = 306;
        indexler[6] = 334;
        indexler[7] = 348;
        indexler[8] = 502;
        indexler[9] = 466;
        indexler[10] = 141;
        indexler[11] = 473;
        indexler[12] = 609;
        indexler[13] = 1042;
        indexler[14] = 915;
        indexler[15] = 1092;
        indexler[16] = 431;
        indexler[17] = 869;
        indexler[18] = 2568;
        indexler[19] = 1566;
        indexler[20] = 816;
        indexler[21] = 612;
        indexler[22] = 541;
        indexler[23] = 1212;
        indexler[24] = 495;
        indexler[25] = 494;
        indexler[26] = 2736;
        indexler[27] = 1046;
        indexler[28] = 939;
        indexler[29] = 697;
        indexler[30] = 902;
        indexler[31] = 333;
        indexler[32] = 779;
        indexler[33] = 1969;
        indexler[34] = 377;
        indexler[35] = 569;
        indexler[36] = 1419;
        indexler[37] = 1448;
        indexler[38] = 1113;
        indexler[39] = 1424;
        indexler[40] = 531;
        indexler[41] = 1139;
        indexler[42] = 1742;
        indexler[43] = 942;
        indexler[44] = 1121;
        indexler[45] = 1547;
        indexler[46] = 1010;
        indexler[47] = 883;
        indexler[48] = 1670;
        indexler[49] = 1222;
        indexler[50] = 833;
        indexler[51] = 849;
        indexler[52] = 472;
        indexler[53] = 2088;
        indexler[54] = 1551;
        indexler[55] = 2111;
        indexler[56] = 2693;
        indexler[57] = 2170;
        indexler[58] = 2205;
        indexler[59] = 786;
        indexler[60] = 2060;
        indexler[61] = 2755;
        indexler[62] = 652;
        indexler[63] = 1186;
        indexler[64] = 2511;
        indexler[65] = 1944;
        indexler[66] = 2280;
        indexler[67] = 2186;
        indexler[68] = 2215;
        indexler[69] = 1611;
        indexler[70] = 2329;
        indexler[71] = 1334;
        indexler[72] = 1385;
        indexler[73] = 1626;
        indexler[74] = 2374;
        indexler[75] = 1849;
        indexler[76] = 2213;
        indexler[77] = 1907;
        indexler[78] = 1444;
        indexler[79] = 2162;
        indexler[80] = 1878;
        indexler[81] = 2367;
        indexler[82] = 2396;
        indexler[83] = 296;
        indexler[84] = 558;
        indexler[85] = 190;
        indexler[86] = 623;
        indexler[87] = 2543;
        indexler[88] = 621;
        indexler[89] = 467;
        indexler[90] = 1563;
        indexler[91] = 661;
        indexler[92] = 1218;
        indexler[93] = 1228;
        indexler[94] = 1230;
        indexler[95] = 1401;
        indexler[96] = 1215;
        indexler[97] = 1298;
        indexler[98] = 1738;
        indexler[99] = 2349;
        indexler[100] = 2463;
        indexler[101] = 1856;
        indexler[102] = 2384;
        indexler[103] = 2332;
        indexler[104] = 2482;
        indexler[105] = 2471;
        indexler[106] = 2456;
        indexler[107] = 2135;
        indexler[108] = 469;
        indexler[109] = 950;
        indexler[110] = 2237;
        indexler[111] = 2439;
        indexler[112] = 2575;
        indexler[113] = 2246;
        indexler[114] = 2501;
        indexler[115] = 2657;
        indexler[116] = 2038;
        indexler[117] = 2531;
        indexler[118] = 2559;
        indexler[119] = 2665;
        indexler[120] = 2450;
        indexler[121] = 2151;
        indexler[122] = 2485;
        indexler[123] = 2499;
        indexler[124] = 2673;

        paribuisim[0] = "DOT_TL";
        paribuisim[1] = "AVAX_TL";
        paribuisim[2] = "TRX_TL";
        paribuisim[3] = "EOS_TL";
        paribuisim[4] = "BTTC_TL";
        paribuisim[5] = "XRP_TL";
        paribuisim[6] = "XLM_TL";
        paribuisim[7] = "ONT_TL";
        paribuisim[8] = "ATOM_TL";
        paribuisim[9] = "HOT_TL";
        paribuisim[10] = "NEO_TL";
        paribuisim[11] = "BAT_TL";
        paribuisim[12] = "CHZ_TL";
        paribuisim[13] = "UNI_TL";
        paribuisim[14] = "BAL_TL";
        paribuisim[15] = "AAVE_TL";
        paribuisim[16] = "LINK_TL";
        paribuisim[17] = "MKR_TL";
        paribuisim[18] = "W_TL";
        paribuisim[19] = "RAY_TL";
        paribuisim[20] = "LRC_TL";
        paribuisim[21] = "BAND_TL";
        paribuisim[22] = "ALGO_TL";
        paribuisim[23] = "GRT_TL";
        paribuisim[24] = "ENJ_TL";
        paribuisim[25] = "THETA_TL";
        paribuisim[26] = "MATIC_TL";
        paribuisim[27] = "OXT_TL";
        paribuisim[28] = "CRV_TL";
        paribuisim[29] = "OGN_TL";
        paribuisim[30] = "MANA_TL";
        paribuisim[31] = "MIOTA_TL";
        paribuisim[32] = "SOL_TL";
        paribuisim[33] = "APE_TL";
        paribuisim[34] = "VET_TL";
        paribuisim[35] = "ANKR_TL";
        paribuisim[36] = "SHIB_TL";
        paribuisim[37] = "LPT_TL";
        paribuisim[38] = "INJ_TL";
        paribuisim[39] = "ICP_TL";
        paribuisim[40] = "FTM_TL";
        paribuisim[41] = "AXS_TL";
        paribuisim[42] = "ENS_TL";
        paribuisim[43] = "SAND_TL";
        paribuisim[44] = "AUDIO_TL";
        paribuisim[45] = "CLV_TL";
        paribuisim[46] = "UMA_TL";
        paribuisim[47] = "STORJ_TL";
        paribuisim[48] = "RAD_TL";
        paribuisim[49] = "1INCH_TL";
        paribuisim[50] = "COMP_TL";
        paribuisim[51] = "SNX_TL";
        paribuisim[52] = "FET_TL";
        paribuisim[53] = "STG_TL";
        paribuisim[54] = "QNT_TL";
        paribuisim[55] = "APT_TL";
        paribuisim[56] = "RNDR_TL";
        paribuisim[57] = "ARB_TL";
        paribuisim[58] = "SUI_TL";
        paribuisim[59] = "CTSI_TL";
        paribuisim[60] = "OP_TL";
        paribuisim[61] = "EIGEN_TL";
        paribuisim[62] = "RLC_TL";
        paribuisim[63] = "SKL_TL";
        paribuisim[64] = "WIF_TL";
        paribuisim[65] = "T_TL";
        paribuisim[66] = "SEI_TL";
        paribuisim[67] = "RDNT_TL";
        paribuisim[68] = "FLOKI_TL";
        paribuisim[69] = "DYDX_TL";
        paribuisim[70] = "TIA_TL";
        paribuisim[71] = "ALICE_TL";
        paribuisim[72] = "TLM_TL";
        paribuisim[73] = "GALA_TL";
        paribuisim[74] = "VANRY_TL";
        paribuisim[75] = "SPELL_TL";
        paribuisim[76] = "PEPE_TL";
        paribuisim[77] = "API3_TL";
        paribuisim[78] = "MASK_TL";
        paribuisim[79] = "GLM_TL";
        paribuisim[80] = "IMX_TL";
        paribuisim[81] = "BLUR_TL";
        paribuisim[82] = "BONK_TL";
        paribuisim[83] = "ADA_TL";
        paribuisim[84] = "DOGE_TL";
        paribuisim[85] = "LTC_TL";
        paribuisim[86] = "RVN_TL";
        paribuisim[87] = "ETHFI_TL";
        paribuisim[88] = "XTZ_TL";
        paribuisim[89] = "ZIL_TL";
        paribuisim[90] = "MINA_TL";
        paribuisim[91] = "BCH_TL";
        paribuisim[92] = "PSG_TL";
        paribuisim[93] = "ATM_TL";
        paribuisim[94] = "ASR_TL";
        paribuisim[95] = "BAR_TL";
        paribuisim[96] = "JUV_TL";
        paribuisim[97] = "ACM_TL";
        paribuisim[98] = "CITY_TL";
        paribuisim[99] = "BEAM_TL";
        paribuisim[100] = "PYTH_TL";
        paribuisim[101] = "JOE_TL";
        paribuisim[102] = "JTO_TL";
        paribuisim[103] = "MEME_TL";
        paribuisim[104] = "STRK_TL";
        paribuisim[105] = "DYM_TL";
        paribuisim[106] = "JUP_TL";
        paribuisim[107] = "MAGIC_TL";
        paribuisim[108] = "ZRX_TL";
        paribuisim[109] = "NMR_TL";
        paribuisim[110] = "PENDLE_TL";
        paribuisim[111] = "MANTA_TL";
        paribuisim[112] = "TNSR_TL";
        paribuisim[113] = "ARKM_TL";
        paribuisim[114] = "AXL_TL";
        paribuisim[115] = "IO_TL";
        paribuisim[116] = "LDO_TL";
        paribuisim[117] = "AEVO_TL";
        paribuisim[118] = "ENA_TL";
        paribuisim[119] = "ZK_TL";
        paribuisim[120] = "ALT_TL";
        paribuisim[121] = "SYN_TL";
        paribuisim[122] = "FIL_TL";
        paribuisim[123] = "PDA_TL";
        paribuisim[124] = "ZRO_TL";

        sesler[0] = dots;
        sesler[1] = avax;
        sesler[2] = tron;
        sesler[3] = eos;
        sesler[4] = btt;
        sesler[5] = xrp;
        sesler[6] = xlm;
        sesler[7] = ont;
        sesler[8] = atom;
        sesler[9] = hot;
        sesler[10] = neo;
        sesler[11] = bat;
        sesler[12] = chz;
        sesler[13] = uni;
        sesler[14] = bal;
        sesler[15] = aave;
        sesler[16] = link;
        sesler[17] = maker;
        sesler[18] = w;
        sesler[19] = ray;
        sesler[20] = lrc;
        sesler[21] = band;
        sesler[22] = algo;
        sesler[23] = grt;
        sesler[24] = enj;
        sesler[25] = theta;
        sesler[26] = matic;
        sesler[27] = oxt;
        sesler[28] = crv;
        sesler[29] = ogn;
        sesler[30] = mana;
        sesler[31] = iota;
        sesler[32] = sol;
        sesler[33] = ape;
        sesler[34] = vet;
        sesler[35] = ankr;
        sesler[36] = shib;
        sesler[37] = lpt;
        sesler[38] = inj;
        sesler[39] = icp;
        sesler[40] = ftm;
        sesler[41] = axs;
        sesler[42] = ens;
        sesler[43] = sand;
        sesler[44] = audio;
        sesler[45] = clv;
        sesler[46] = uma;
        sesler[47] = storj;
        sesler[48] = rad;
        sesler[49] = inch;
        sesler[50] = comp;
        sesler[51] = snx;
        sesler[52] = fet;
        sesler[53] = stg;
        sesler[54] = qnt;
        sesler[55] = apt;
        sesler[56] = rndr;
        sesler[57] = arb;
        sesler[58] = sui;
        sesler[59] = ctsi;
        sesler[60] = op;
        sesler[61] = eigen;
        sesler[62] = rlc;
        sesler[63] = skl;
        sesler[64] = wif;
        sesler[65] = t;
        sesler[66] = sei;
        sesler[67] = rdnt;
        sesler[68] = floki;
        sesler[69] = dydx;
        sesler[70] = tia;
        sesler[71] = alice;
        sesler[72] = tlm;
        sesler[73] = gala;
        sesler[74] = vanry;
        sesler[75] = spell;
        sesler[76] = pepe;
        sesler[77] = api3;
        sesler[78] = mask;
        sesler[79] = glm;
        sesler[80] = imx;
        sesler[81] = blur;
        sesler[82] = bonk;
        sesler[83] = ada;
        sesler[84] = dogem;
        sesler[85] = ltc;
        sesler[86] = rvn;
        sesler[87] = etherfi;
        sesler[88] = xtz;
        sesler[89] = zil;
        sesler[90] = mina;
        sesler[91] = bch;
        sesler[92] = psg;
        sesler[93] = atm;
        sesler[94] = asr;
        sesler[95] = bar;
        sesler[96] = juv;
        sesler[97] = acm;
        sesler[98] = city;
        sesler[99] = beam;
        sesler[100] = pyth;
        sesler[101] = joe;
        sesler[102] = jto;
        sesler[103] = meme;
        sesler[104] = strk;
        sesler[105] = dym;
        sesler[106] = jup;
        sesler[107] = magic;
        sesler[108] = zrx;
        sesler[109] = nmr;
        sesler[110] = pendle;
        sesler[111] = manta;
        sesler[112] = tnsr;
        sesler[113] = arkm;
        sesler[114] = axl;
        sesler[115] = io;
        sesler[116] = ldo;
        sesler[117] = aevo;
        sesler[118] = ena;
        sesler[119] = zk;
        sesler[120] = alt;
        sesler[121] = syn;
        sesler[122] = fil;
        sesler[123] = pda;
        sesler[124] = zro;

        btcturkisim[0] = "XRP_TRY";
        btcturkisim[1] = "LTC_TRY";
        btcturkisim[2] = "XLM_TRY";
        btcturkisim[3] = "NEO_TRY";
        btcturkisim[4] = "EOS_TRY";
        btcturkisim[5] = "DASH_TRY";
        btcturkisim[6] = "LINK_TRY";
        btcturkisim[7] = "ATOM_TRY";
        btcturkisim[8] = "XTZ_TRY";
        btcturkisim[9] = "TRX_TRY";
        btcturkisim[10] = "ADA_TRY";
        btcturkisim[11] = "DOT_TRY";
        btcturkisim[12] = "UNI_TRY";
        btcturkisim[13] = "ANKR_TRY";
        btcturkisim[14] = "MKR_TRY";
        btcturkisim[15] = "ENJ_TRY";
        btcturkisim[16] = "COMP_TRY";
        btcturkisim[17] = "GRT_TRY";
        btcturkisim[18] = "MANA_TRY";
        btcturkisim[19] = "POL_TRY";
        btcturkisim[20] = "SNX_TRY";
        btcturkisim[21] = "BAT_TRY";
        btcturkisim[22] = "AVAX_TRY";
        btcturkisim[23] = "FIL_TRY";
        btcturkisim[24] = "DOGE_TRY";
        btcturkisim[25] = "CHZ_TRY";
        btcturkisim[26] = "SOL_TRY";
        btcturkisim[27] = "STX_TRY";
        btcturkisim[28] = "AXS_TRY";
        btcturkisim[29] = "SHIB_TRY";
        btcturkisim[30] = "FTM_TRY";
        btcturkisim[31] = "LRC_TRY";
        btcturkisim[32] = "UMA_TRY";
        btcturkisim[33] = "FET_TRY";
        btcturkisim[34] = "STORJ_TRY";
        btcturkisim[35] = "AAVE_TRY";
        btcturkisim[36] = "GALA_TRY";
        btcturkisim[37] = "SAND_TRY";
        btcturkisim[38] = "AMP_TRY";
        btcturkisim[39] = "AUDIO_TRY";
        btcturkisim[40] = "SPELL_TRY";
        btcturkisim[41] = "ALGO_TRY";
        btcturkisim[42] = "APE_TRY";
        btcturkisim[43] = "BNT_TRY";
        btcturkisim[44] = "CRV_TRY";
        btcturkisim[45] = "QNT_TRY";
        btcturkisim[46] = "SKL_TRY";
        btcturkisim[47] = "GLM_TRY";
        btcturkisim[48] = "LUNA_TRY";
        btcturkisim[49] = "PAXG_TRY";
        btcturkisim[50] = "ENS_TRY";
        btcturkisim[51] = "IMX_TRY";
        btcturkisim[52] = "LPT_TRY";
        btcturkisim[53] = "RENDER_TRY";
        btcturkisim[54] = "CVC_TRY";
        btcturkisim[55] = "RLC_TRY";
        btcturkisim[56] = "ETC_TRY";
        btcturkisim[57] = "HOT_TRY";
        btcturkisim[58] = "APT_TRY";
        btcturkisim[59] = "FLOW_TRY";
        btcturkisim[60] = "CTSI_TRY";
        btcturkisim[61] = "MASK_TRY";
        btcturkisim[62] = "API3_TRY";
        btcturkisim[63] = "BAND_TRY";
        btcturkisim[64] = "T_TRY";
        btcturkisim[65] = "ARB_TRY";
        btcturkisim[66] = "STRK_TRY";
        btcturkisim[67] = "SUPER_TRY";
        btcturkisim[68] = "INJ_TRY";
        btcturkisim[69] = "OGN_TRY";
        btcturkisim[70] = "RAD_TRY";
        btcturkisim[71] = "ARPA_TRY";
        btcturkisim[72] = "LDO_TRY";
        btcturkisim[73] = "SUSHI_TRY";
        btcturkisim[74] = "MAGIC_TRY";
        btcturkisim[75] = "JASMY_TRY";
        btcturkisim[76] = "ZRX_TRY";
        btcturkisim[77] = "PEPE_TRY";
        btcturkisim[78] = "NMR_TRY";
        btcturkisim[79] = "FLOKI_TRY";

        indexlerbtcturk[0] = 306;
        indexlerbtcturk[1] = 190;
        indexlerbtcturk[2] = 334;
        indexlerbtcturk[3] = 141;
        indexlerbtcturk[4] = 323;
        indexlerbtcturk[5] = 490;
        indexlerbtcturk[6] = 431;
        indexlerbtcturk[7] = 502;
        indexlerbtcturk[8] = 621;
        indexlerbtcturk[9] = 350;
        indexlerbtcturk[10] = 296;
        indexlerbtcturk[11] = 954;
        indexlerbtcturk[12] = 1042;
        indexlerbtcturk[13] = 569;
        indexlerbtcturk[14] = 869;
        indexlerbtcturk[15] = 495;
        indexlerbtcturk[16] = 833;
        indexlerbtcturk[17] = 1212;
        indexlerbtcturk[18] = 902;
        indexlerbtcturk[19] = 2736;
        indexlerbtcturk[20] = 849;
        indexlerbtcturk[21] = 473;
        indexlerbtcturk[22] = 1052;
        indexlerbtcturk[23] = 1105;
        indexlerbtcturk[24] = 558;
        indexlerbtcturk[25] = 609;
        indexlerbtcturk[26] = 779;
        indexlerbtcturk[27] = 639;
        indexlerbtcturk[28] = 1139;
        indexlerbtcturk[29] = 1419;
        indexlerbtcturk[30] = 531;
        indexlerbtcturk[31] = 816;
        indexlerbtcturk[32] = 1010;
        indexlerbtcturk[33] = 472;
        indexlerbtcturk[34] = 883;
        indexlerbtcturk[35] = 1092;
        indexlerbtcturk[36] = 1626;
        indexlerbtcturk[37] = 942;
        indexlerbtcturk[38] = 1775;
        indexlerbtcturk[39] = 1121;
        indexlerbtcturk[40] = 1849;
        indexlerbtcturk[41] = 541;
        indexlerbtcturk[42] = 1969;
        indexlerbtcturk[43] = 719;
        indexlerbtcturk[44] = 939;
        indexlerbtcturk[45] = 1551;
        indexlerbtcturk[46] = 1186;
        indexlerbtcturk[47] = 2162;
        indexlerbtcturk[48] = 958;
        indexlerbtcturk[49] = 968;
        indexlerbtcturk[50] = 1742;
        indexlerbtcturk[51] = 1878;
        indexlerbtcturk[52] = 1448;
        indexlerbtcturk[53] = 2693;
        indexlerbtcturk[54] = 604;
        indexlerbtcturk[55] = 652;
        indexlerbtcturk[56] = 351;
        indexlerbtcturk[57] = 466;
        indexlerbtcturk[58] = 2111;
        indexlerbtcturk[59] = 1555;
        indexlerbtcturk[60] = 786;
        indexlerbtcturk[61] = 1444;
        indexlerbtcturk[62] = 1907;
        indexlerbtcturk[63] = 612;
        indexlerbtcturk[64] = 1944;
        indexlerbtcturk[65] = 2170;
        indexlerbtcturk[66] = 2482;
        indexlerbtcturk[67] = 1356;
        indexlerbtcturk[68] = 1113;
        indexlerbtcturk[69] = 697;
        indexlerbtcturk[70] = 1670;
        indexlerbtcturk[71] = 648;
        indexlerbtcturk[72] = 2038;
        indexlerbtcturk[73] = 990;
        indexlerbtcturk[74] = 2135;
        indexlerbtcturk[75] = 1768;
        indexlerbtcturk[76] = 469;
        indexlerbtcturk[77] = 2213;
        indexlerbtcturk[78] = 950;
        indexlerbtcturk[79] = 2215;

        seslerBtcTurk[0] = xrp;
        seslerBtcTurk[1] = ltc;
        seslerBtcTurk[2] = xlm;
        seslerBtcTurk[3] = neo;
        seslerBtcTurk[4] = eos;
        seslerBtcTurk[5] = dash;
        seslerBtcTurk[6] = link;
        seslerBtcTurk[7] = atom;
        seslerBtcTurk[8] = xtz;
        seslerBtcTurk[9] = tron;
        seslerBtcTurk[10] = ada;
        seslerBtcTurk[11] = dots;
        seslerBtcTurk[12] = uni;
        seslerBtcTurk[13] = ankr;
        seslerBtcTurk[14] = maker;
        seslerBtcTurk[15] = enj;
        seslerBtcTurk[16] = comp;
        seslerBtcTurk[17] = grt;
        seslerBtcTurk[18] = mana;
        seslerBtcTurk[19] = matic;
        seslerBtcTurk[20] = snx;
        seslerBtcTurk[21] = bat;
        seslerBtcTurk[22] = avax;
        seslerBtcTurk[23] = fil;
        seslerBtcTurk[24] = dogem;
        seslerBtcTurk[25] = chz;
        seslerBtcTurk[26] = sol;
        seslerBtcTurk[27] = stx;
        seslerBtcTurk[28] = axs;
        seslerBtcTurk[29] = shib;
        seslerBtcTurk[30] = ftm;
        seslerBtcTurk[31] = lrc;
        seslerBtcTurk[32] = uma;
        seslerBtcTurk[33] = fet;
        seslerBtcTurk[34] = storj;
        seslerBtcTurk[35] = uni;
        seslerBtcTurk[36] = gala;
        seslerBtcTurk[37] = vet;
        seslerBtcTurk[38] = amp;
        seslerBtcTurk[39] = audio;
        seslerBtcTurk[40] = spell;
        seslerBtcTurk[41] = algo;
        seslerBtcTurk[42] = ape;
        seslerBtcTurk[43] = bnt;
        seslerBtcTurk[44] = crv;
        seslerBtcTurk[45] = qnt;
        seslerBtcTurk[46] = skl;
        seslerBtcTurk[47] = glm;
        seslerBtcTurk[48] = luna;
        seslerBtcTurk[49] = paxg;
        seslerBtcTurk[50] = ens;
        seslerBtcTurk[51] = imx;
        seslerBtcTurk[52] = lpt;
        seslerBtcTurk[53] = rndr;
        seslerBtcTurk[54] = cvc;
        seslerBtcTurk[55] = rad;
        seslerBtcTurk[56] = etc;
        seslerBtcTurk[57] = hot;
        seslerBtcTurk[58] = sand;
        seslerBtcTurk[59] = flow;
        seslerBtcTurk[60] = ctsi;
        seslerBtcTurk[61] = mask;
        seslerBtcTurk[62] = api3;
        seslerBtcTurk[63] = band;
        seslerBtcTurk[64] = t;
        seslerBtcTurk[65] = arb;
        seslerBtcTurk[66] = strk;
        seslerBtcTurk[67] = super0;
        seslerBtcTurk[68] = inj;
        seslerBtcTurk[69] = ogn;
        seslerBtcTurk[70] = rlc;
        seslerBtcTurk[71] = arpa;
        seslerBtcTurk[72] = ldo;
        seslerBtcTurk[73] = sushi;
        seslerBtcTurk[74] = magic;
        seslerBtcTurk[75] = jasmy;
        seslerBtcTurk[76] = zrx;
        seslerBtcTurk[77] = pepe;
        seslerBtcTurk[78] = nmr;
        seslerBtcTurk[79] = floki;
        for (int i = 0; i < hizli; i++) {
            String[] strArr3 = isimler;
            StringBuilder sb = new StringBuilder();
            String str = paribuisim[i];
            strArr3[i] = sb.append(str.substring(0, str.indexOf("_"))).append(":").toString();
            siralama[i] = i;
            oranlar[i] = Double.valueOf(0.0d);
            sesSeviyesi[i] = 15;
        }
        for (int i2 = 0; i2 < btctotal; i2++) {
            String[] strArr4 = isimlerBtcTurk;
            StringBuilder sb2 = new StringBuilder();
            String str2 = btcturkisim[i2];
            strArr4[i2] = sb2.append(str2.substring(0, str2.indexOf("_"))).append(":").toString();
            siralamaBtcTurk[i2] = i2;
            oranlarbtcturk[i2] = Double.valueOf(0.0d);
            sesSeviyesiBtcTurk[i2] = 15;
        }
        ayarlar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openMainActivity = new Intent((Context) MainActivity.this, (Class<?>) Sesler.class);
                openMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                MainActivity.this.startActivityIfNeeded(openMainActivity, 2);
            }
        });
        yukari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.ekran3.scrollTo(0, 0);
            }
        });
        asagi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.ekran3.scrollTo(99000, 99000);
            }
        });
        paribuL.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (MainActivity.this.calistiMi) {
                    MainActivity.this.dialog.show();
                    if (!MainActivity.girdi) {
                        MainActivity.tiklanansira = MainActivity.siralama[position];
                        MainActivity.this.pop.setText(MainActivity.paribuisim[position]);
                        MainActivity.this.edit.setText("" + MainActivity.oranlar[position]);
                        MainActivity.this.oran.setText("" + MainActivity.oranlar[position]);
                        MainActivity.this.seek.setProgress((int) (MainActivity.oranlar[position].doubleValue() * 10.0d));
                        MainActivity.this.seekses.setProgress(MainActivity.sesSeviyesi[position]);
                        MainActivity.this.seekses.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                            @Override
                            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                for (int i3 = 0; i3 < MainActivity.hizli; i3++) {
                                    if (MainActivity.paribuisim[i3].contains(MainActivity.this.pop.getText())) {
                                        MainActivity.sesSeviyesi[i3] = progress;
                                        MainActivity.sesler[i3].setVolume(progress / 15.0f, progress / 15.0f);
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
                        return;
                    }
                    MainActivity.tiklanansira = MainActivity.aramaindexler.get(position).intValue();
                    MainActivity.this.isim = MainActivity.paribuisim[MainActivity.tiklanansira];
                    MainActivity.this.pop.setText(MainActivity.paribuisim[MainActivity.tiklanansira]);
                    MainActivity.this.edit.setText("" + MainActivity.oranlar[MainActivity.tiklanansira]);
                    MainActivity.this.oran.setText("" + MainActivity.oranlar[MainActivity.tiklanansira]);
                    MainActivity.this.seek.setProgress((int) (MainActivity.oranlar[MainActivity.tiklanansira].doubleValue() * 10.0d));
                    MainActivity.this.seekses.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                            for (int i3 = 0; i3 < MainActivity.hizli; i3++) {
                                if (MainActivity.paribuisim[i3].contains(MainActivity.this.pop.getText())) {
                                    MainActivity.sesSeviyesi[i3] = progress;
                                    MainActivity.sesler[i3].setVolume(progress / 15.0f, progress / 15.0f);
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
            }
        });
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        final int height = displayMetrics.heightPixels;
        final int width = displayMetrics.widthPixels;
        TextWatcher tt = new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if (MainActivity.this.calistiMi) {
                    try {
                        Double.parseDouble(MainActivity.dot.getText().toString());
                        if (Double.parseDouble(MainActivity.dot.getText().toString()) >= 0.0d) {
                            MainActivity.this.duzenle.setVisibility(View.VISIBLE);
                            return;
                        }
                    } catch (NumberFormatException e) {
                        MainActivity.this.duzenle.setVisibility(View.GONE);
                        return;
                    }
                } else {
                    try {
                        Double.parseDouble(MainActivity.dot.getText().toString());
                        if (Double.parseDouble(MainActivity.dot.getText().toString()) >= 0.0d) {
                            MainActivity.this.start.setX((width - 178) / 2);
                            MainActivity.this.start.setY((height - 800) / 2);
                            MainActivity.this.start.setVisibility(View.VISIBLE);
                        }
                    } catch (NumberFormatException e2) {
                        MainActivity.this.start.setVisibility(View.INVISIBLE);
                        return;
                    }
                }
                if (MainActivity.this.sayac == 1) {
                    MainActivity.this.Dot();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        };
        dot.addTextChangedListener(tt);
        dot.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    MainActivity.this.duzenle.setVisibility(View.GONE);
                }
            }
        });
        duzenle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.dotusd = Double.valueOf(MainActivity.dot.getText().toString());
                for (int i3 = 0; i3 < MainActivity.hizli; i3++) {
                    MainActivity.oranlar[i3] = MainActivity.this.dotusd;
                }
                Snackbar snackbar = Snackbar.make(v, "Tüm oranlar " + MainActivity.this.dotusd + "'a ayarlandı", 0);
                snackbar.show();
            }
        });
        popupButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Double.parseDouble(MainActivity.this.edit.getText().toString());
                    for (int x = 0; x < MainActivity.hizli; x++) {
                        if (!MainActivity.girdi && MainActivity.siralama[x] == MainActivity.tiklanansira) {
                            MainActivity.oranlar[x] = Double.valueOf(MainActivity.this.edit.getText().toString());
                            MainActivity.this.oran.setText("" + MainActivity.oranlar[x]);
                        } else if (girdi && MainActivity.paribuisim[x] == isim) {
                            MainActivity.oranlar[x] = Double.valueOf(MainActivity.this.edit.getText().toString());
                            MainActivity.this.oran.setText("" + MainActivity.oranlar[x]);
                        }
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this.getApplicationContext(), "Lütfen değer gir", Toast.LENGTH_SHORT).show();
                }
            }
        });
        geributton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.dialog.cancel();
                MainActivity.this.dialog.dismiss();
            }
        });
        seek.setMax(100);
        seekses.setMax(15);
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                MainActivity.this.edit.setText("" + (progress / 10.0d));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        mbottomappbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.p++;
                if (MainActivity.p % 2 == 1) {
                    MainActivity.this.mbottomappbar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
                } else {
                    MainActivity.this.mbottomappbar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);
                }
            }
        });
        paribuL.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                MainActivity.dot.clearFocus();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        MainActivity.this.downX = event.getX();
                        break;
                    case MotionEvent.ACTION_UP :
                        break;
                    default:
                        return false;
                }
                MainActivity.this.upX = event.getX();
                float deltaX = MainActivity.this.downX - MainActivity.this.upX;
                if (Math.abs(deltaX) > 0.0f && deltaX >= 300.0f) {
                    Intent openMainActivity = new Intent( MainActivity.this,  BtcTurk.class);
                    openMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    MainActivity.this.startActivityIfNeeded(openMainActivity, 1);
                    return true;
                }
                return false;
            }
        });
        binanceL.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                MainActivity.dot.clearFocus();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        MainActivity.this.downX = event.getX();
                        break;
                    case MotionEvent.ACTION_UP :
                        break;
                    default:
                        return false;
                }
                MainActivity.this.upX = event.getX();
                float deltaX = MainActivity.this.downX - MainActivity.this.upX;
                if (Math.abs(deltaX) > 0.0f && deltaX >= 300.0f) {
                    Intent openMainActivity = new Intent( MainActivity.this,  BtcTurk.class);
                    openMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    MainActivity.this.startActivityIfNeeded(openMainActivity, 1);
                    return true;
                }
                return false;
            }
        });
        binanceTlL.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                MainActivity.dot.clearFocus();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        MainActivity.this.downX = event.getX();
                        break;
                    case MotionEvent.ACTION_UP :
                        break;
                    default:
                        return false;
                }
                MainActivity.this.upX = event.getX();
                float deltaX = MainActivity.this.downX - MainActivity.this.upX;
                if (Math.abs(deltaX) > 0.0f && deltaX >= 300.0f) {
                    Intent openMainActivity = new Intent(MainActivity.this,  BtcTurk.class);
                    openMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    MainActivity.this.startActivityIfNeeded(openMainActivity, 1);
                    return true;
                }
                return false;
            }
        });
        ekran3.setOnTouchListener(new View.OnTouchListener() {
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                MainActivity.dot.clearFocus();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        MainActivity.this.downX = event.getX();
                        break;
                    case MotionEvent.ACTION_UP :
                        break;
                    default:
                        return false;
                }
                MainActivity.this.upX = event.getX();
                float deltaX = MainActivity.this.downX - MainActivity.this.upX;
                if (Math.abs(deltaX) > 0.0f && deltaX >= 300.0f) {
                    Intent openMainActivity = new Intent( MainActivity.this, BtcTurk.class);
                    openMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    MainActivity.this.startActivityIfNeeded(openMainActivity, 1);
                    return true;
                }
                return false;
            }
        });
        Dot();
    }
    public static MainActivity getInstance() {
        return instance;
    }
    public void startService(View v) {
        start.setVisibility(View.GONE);
        updateUI(true);
        paribuAnaText.setVisibility(View.VISIBLE);
        BinanceAnaText.setVisibility(View.VISIBLE);
        BinanceTlAnaText.setVisibility(View.VISIBLE);
        paribuL.setVisibility(View.VISIBLE);
        binanceTlL.setVisibility(View.VISIBLE);
        binanceL.setVisibility(View.VISIBLE);
        paribucheck.setChecked(true);
        tlcheck.setChecked(true);
        binancecheck.setChecked(true);
        calistiMi = true;
        AppBarLayout.LayoutParams paramst = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
        ViewGroup.MarginLayoutParams layoutParams = (LinearLayoutCompat.LayoutParams) dot.getLayoutParams();
        layoutParams.setMargins(40, paramst.height + 10, 40, 5);
        dot.setLayoutParams(layoutParams);
        stop.setVisibility(View.VISIBLE);
        MainservisSayac++;
        dotusd = Double.valueOf(dot.getText().toString());
        for (int i = 0; i < hizli; i++) {
            oranlar[i] = dotusd;
        }
        Toast.makeText(getApplicationContext(), "Servis Başlatıldı", Toast.LENGTH_SHORT).show();
        Intent serviceIntent = new Intent(this, Service.class);
        ContextCompat.startForegroundService(this, serviceIntent);
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (Service.isRunning()&&calistiMi) {
            updateUI(true);
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (!isFinishing()&&calistiMi) {
            updateUI(false);
        }
    }
    private WeakReference<MainActivity> mainActivityRef;

    public void setMainActivity(MainActivity activity) {
        mainActivityRef = new WeakReference<>(activity);
    }
    private void updateUI(boolean show) {
        SharedPreferences prefs = getSharedPreferences("UIState", MODE_PRIVATE);
        prefs.edit().putBoolean("isVisible", show).apply();

        if (show) {
            paribuAnaText.setVisibility(View.VISIBLE);
            BinanceTlAnaText.setVisibility(View.VISIBLE);
            BinanceAnaText.setVisibility(View.VISIBLE);
            paribuL.setVisibility(View.VISIBLE);
            binanceL.setVisibility(View.VISIBLE);
            binanceTlL.setVisibility(View.VISIBLE);
            if (paribucheck != null) paribucheck.setChecked(true);
            if (tlcheck != null) tlcheck.setChecked(true);
            if (binancecheck != null) binancecheck.setChecked(true);
        } else {
            paribuAnaText.setVisibility(View.GONE);
            BinanceTlAnaText.setVisibility(View.GONE);
            BinanceAnaText.setVisibility(View.GONE);
            paribuL.setVisibility(View.GONE);
            binanceL.setVisibility(View.GONE);
            binanceTlL.setVisibility(View.GONE);

        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("calistiMi", calistiMi);
    }
    public void stopService(View v) {
        calistiMi = false;
        updateUI(false);
        Intent serviceIntent = new Intent( this, Service2.class);
        stopService(serviceIntent);
        Intent serviceIntent0 = new Intent( this,  Service.class);
        stopService(serviceIntent0);
        finishAffinity();
        onDestroy();
        System.exit(0);
    }


    public String Dot() {
        JsonObjectRequest request3;

        otuyorMu = false;
        sayac++;
        JsonObjectRequest request32 = new JsonObjectRequest(0, "https://www.paribu.com/ticker", (JSONObject) null, new Response.Listener<JSONObject>() {
            public void onResponse(JSONObject response) {
                try {
                    JSONObject jsonArray2 = response.getJSONObject("USDT_TL");
                    MainActivity.tsatis = (float) jsonArray2.getDouble("lowestAsk");
                    for (int i = 0; i < MainActivity.hizli; i++) {
                        MainActivity.paribu[i] = (float) response.getJSONObject(MainActivity.paribuisim[i]).getDouble("highestBid");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() { // from class: com.codinginflow.bigots.MainActivity.18
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request32);
        JsonArrayRequest request0 = new JsonArrayRequest(0, "https://www.binance.com/api/v3/ticker/bookTicker", (JSONArray) null, new Response.Listener<JSONArray>() {
            public void onResponse(JSONArray response) {
                try {
                    MainActivity.btc = (float) response.getJSONObject(11).getDouble("bidPrice");
                    for (int i = 0; i < MainActivity.hizli; i++) {
                        MainActivity.huobi[i] = (float) response.getJSONObject(MainActivity.indexler[i]).getDouble("bidPrice");
                    }
                    for (int i2 = 0; i2 < btctotal; i2++) {
                        MainActivity.huobibtcturk[i2] = (float) response.getJSONObject(MainActivity.indexlerbtcturk[i2]).getDouble("bidPrice");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request0);
        for (int i = 0; i < hizli; i++) {
            float[] fArr = farklar;
            float f = paribu[i];
            fArr[i] = ((f - (huobi[i] * tsatis)) * 100.0f) / f;
        }
        for (int i2 = 0; i2 < btctotal; i2++) {
            float[] fArr2 = farklarBtcTurk;
            float f2 = btcturk[i2];
            fArr2[i2] = ((f2 - (huobibtcturk[i2] * tsatisb)) * 100.0f) / f2;
        }

        JsonObjectRequest request2 = new JsonObjectRequest(0, "https://api.btcturk.com/api/v2/ticker", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // USDT/TRY paritesini bul
                            JSONArray data = response.getJSONArray("data");
                            for (int i = 0; i < data.length(); i++) {
                                JSONObject pair = data.getJSONObject(i);
                                if (pair.getString("pair").equals("USDTTRY")) {
                                    tsatisb = (float) pair.getDouble("ask");
                                    break;
                                }
                            }

                            // Her bir btcturkisim için eşleşen pair'i bul
                            for (int i = 0; i < btctotal; i++) {
                                String targetPair = btcturkisim[i];
                                boolean found = false;

                                for (int j = 0; j < data.length(); j++) {
                                    JSONObject pair = data.getJSONObject(j);
                                    if (pair.getString("pair").equals(targetPair.replace("_", ""))) {
                                        btcturk[i] = (float) pair.getDouble("bid");
                                        found = true;
                                        break;
                                    }
                                }

                                if (!found) {
                                    // Eğer pair bulunamadıysa 0 ata veya hata işle
                                    btcturk[i] = 0f;
                                    System.out.println("Pair not found: " + targetPair);
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );
        mQueue.add(request2);
        setTitle(btc + " | " + tsatis);
        aramaindexler.clear();
        hizli2 = 0;
        mergeSort(farklar, 0, farklar.length - 1, paribu, huobi, paribuisim, isimler, sesSeviyesi, indexler, siralama, oranlar, sesler);
        int a = 0;
        while (a < hizli) {
            otuyorMu = false;
            JsonArrayRequest request02 = request0;
            metinlerParibu[a] = new SpannableString(isimler[a] + paribu[a] + " %" + df.format(farklar[a]));
            metinlerBinance[a] = new SpannableString(isimler[a] + huobi[a]);
            metinlerBinanceTl[a] = new SpannableString(isimler[a] + (huobi[a] * tsatis));
            if (calistiMi) {
                if (Sesler.arti && !Sesler.eksi && farklar[a] > oranlar[a].doubleValue()) {
                    SpannableString spannableString = metinlerParibu[a];
                    spannableString.setSpan(fscgreen, 0, spannableString.length(), 33);
                    SpannableString spannableString2 = metinlerBinance[a];
                    spannableString2.setSpan(yellow, 0, spannableString2.length(), 33);
                    SpannableString spannableString3 = metinlerBinanceTl[a];
                    spannableString3.setSpan(red, 0, spannableString3.length(), 33);
                    sesler[a].start();
                    otuyorMu = true;
                } else if (!Sesler.arti && !Sesler.eksi && (farklar[a] < oranlar[a].doubleValue() * (-1.0d) || farklar[a] > oranlar[a].doubleValue())) {
                    SpannableString spannableString4 = metinlerParibu[a];
                    spannableString4.setSpan(fscgreen, 0, spannableString4.length(), 33);
                    SpannableString spannableString5 = metinlerBinance[a];
                    spannableString5.setSpan(yellow, 0, spannableString5.length(), 33);
                    SpannableString spannableString6 = metinlerBinanceTl[a];
                    spannableString6.setSpan(red, 0, spannableString6.length(), 33);
                    sesler[a].start();
                    otuyorMu = true;
                } else if (!Sesler.arti && Sesler.eksi && farklar[a] < oranlar[a].doubleValue() * (-1.0d)) {
                    SpannableString spannableString7 = metinlerParibu[a];
                    spannableString7.setSpan(fscgreen, 0, spannableString7.length(), 33);
                    SpannableString spannableString8 = metinlerBinance[a];
                    spannableString8.setSpan(yellow, 0, spannableString8.length(), 33);
                    SpannableString spannableString9 = metinlerBinanceTl[a];
                    spannableString9.setSpan(red, 0, spannableString9.length(), 33);
                    sesler[a].start();
                    otuyorMu = true;
                } else {
                    SpannableString spannableString10 = metinlerParibu[a];
                    spannableString10.setSpan(fsccyan, 0, spannableString10.length(), 33);
                    SpannableString spannableString11 = metinlerBinance[a];
                    spannableString11.setSpan(fscdarkyellow, 0, spannableString11.length(), 33);
                    SpannableString spannableString12 = metinlerBinanceTl[a];
                    spannableString12.setSpan(darkred, 0, spannableString12.length(), 33);
                    otuyorMu = false;
                }
                if (otuyorMu) {
                    String sayac = paribu[a] + "";
                    int sayac0 = sayac.length();
                    int sayi = isimler[a].length() + df.format(farklar[a]).length() + sayac0 + " %".length();
                    metinlerParibu[a] = new SpannableString(isimler[a] + paribu[a] + " %" + df.format(farklar[a]) + " || " + (huobi[a] * tsatis));
                    metinlerParibu[a].setSpan(fscgreen, 0, sayi, 33);
                    SpannableString spannableString13 = metinlerParibu[a];
                    spannableString13.setSpan(darkred, sayi + 1, spannableString13.length(), 33);
                }
                String sayac2 = aramaMetni;
                if (sayac2.length() > 0 && isimler[a].matches(aramaMetni.toUpperCase() + "(.*)")) {
                    aramaindexler.add(Integer.valueOf(a));
                    hizli2++;
                }
            }
            a++;
            request0 = request02;
        }
        mergeSortBtcTurk(farklarBtcTurk, 0, farklarBtcTurk.length - 1, btcturk, huobibtcturk,
                btcturkisim, isimlerBtcTurk, sesSeviyesiBtcTurk, indexlerbtcturk, siralamaBtcTurk,
                oranlarbtcturk, seslerBtcTurk);
        if (calistiMi) {

            if (hizli2 == 0) {
                arrayAdapter = new ArrayAdapter<>(MainActivity.this, R.layout.listview, metinlerParibu);
                arrayAdapter3 = new ArrayAdapter<>(MainActivity.this, R.layout.listview, metinlerBinance);
                arrayAdapter2 = new ArrayAdapter<>(MainActivity.this, R.layout.listview, metinlerBinanceTl);
                ViewGroup.MarginLayoutParams layoutParams = (LinearLayoutCompat.LayoutParams) paribuL.getLayoutParams();
                layoutParams.setMargins(0, 0, 0, -190);
                binanceL.setLayoutParams(layoutParams);
                binanceTlL.setLayoutParams(layoutParams);
                paribuL.setLayoutParams(layoutParams);
                paribuL.setAdapter((ListAdapter) arrayAdapter);
                Helper.getListViewSize(paribuL, false);
                binanceL.setAdapter((ListAdapter) arrayAdapter3);
                Helper.getListViewSize(binanceL, false);
                binanceTlL.setAdapter((ListAdapter) arrayAdapter2);
                Helper.getListViewSize(binanceTlL, false);
            } else {
                metinlerParibuarama = new SpannableString[hizli2];
                metinlerBinancearama = new SpannableString[hizli2];
                metinlerBinanceTlarama = new SpannableString[hizli2];
                int a3 = 0;
                while (a3 < hizli2) {
                    int index = aramaindexler.get(a3).intValue();
                    otuyorMuArama = false;
                    JsonObjectRequest request22 = request2;
                    metinlerParibuarama[a3] = new SpannableString(isimler[index] + paribu[index] + " %" + df.format(farklar[index]));
                    metinlerBinancearama[a3] = new SpannableString(isimler[index] + huobi[index]);
                    metinlerBinanceTlarama[a3] = new SpannableString(isimler[index] + (huobi[index] * tsatis));
                    if (Sesler.arti && !Sesler.eksi && farklar[index] > oranlar[index].doubleValue()) {
                        metinlerParibuarama[a3].setSpan(fscgreen, 0, metinlerParibuarama[a3].length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        metinlerBinancearama[a3].setSpan(yellow, 0, metinlerBinancearama[a3].length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        metinlerBinanceTlarama[a3].setSpan(red, 0, metinlerBinanceTlarama[a3].length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        otuyorMuArama = true;
                    } else if (!Sesler.arti && !Sesler.eksi && (farklar[index] < oranlar[index].doubleValue() * (-1.0d) || farklar[index] > oranlar[index].doubleValue())) {
                        metinlerParibuarama[a3].setSpan(fscgreen,  0, metinlerParibuarama[a3].length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        metinlerBinancearama[a3].setSpan(yellow, 0, metinlerBinancearama[a3].length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        metinlerBinanceTlarama[a3].setSpan(red, 0, metinlerBinanceTlarama[a3].length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        otuyorMuArama = true;
                    } else {
                        if (!Sesler.arti && Sesler.eksi) {
                            if (farklar[index] < oranlar[index].doubleValue() * (-1.0d)) {
                                metinlerParibuarama[a3].setSpan(fscgreen, 0, metinlerParibuarama[a3].length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                metinlerBinancearama[a3].setSpan(yellow, 0, metinlerBinancearama[a3].length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                metinlerBinanceTlarama[a3].setSpan(red, 0, metinlerBinanceTlarama[a3].length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                                otuyorMuArama = true;
                            }
                        }

                        metinlerParibuarama[a3].setSpan(fsccyan, 0, metinlerParibuarama[a3].length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        metinlerBinancearama[a3].setSpan(fscdarkyellow, 0, metinlerBinancearama[a3].length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        metinlerBinanceTlarama[a3].setSpan(darkred, 0, metinlerBinanceTlarama[a3].length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        otuyorMuArama = false;
                    }
                    if (!otuyorMuArama) {
                        request3 = request32;
                    } else {
                        String sayac3 = paribu[index] + "";
                        int sayac02 = sayac3.length();
                        int sayi2 = isimler[index].length() + df.format(farklar[index]).length() + sayac02 + " %".length();
                        request3 = request32;
                        metinlerParibuarama[a3] = new SpannableString(isimler[index] + paribu[index] + " %" + df.format(farklar[index]) + " || " + huobi[index] * tsatis);
                        metinlerParibuarama[a3].setSpan(fscgreen, 0, sayi2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        metinlerParibuarama[a3].setSpan(darkred, sayi2 + 1, metinlerParibuarama[a3].length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                    a3++;
                    request32 = request3;
                    request2 = request22;

                }
                arrayAdapter = new ArrayAdapter<>(MainActivity.this, R.layout.listview, metinlerParibuarama);
                arrayAdapter3 = new ArrayAdapter<>(MainActivity.this, R.layout.listview, metinlerBinancearama);
                arrayAdapter2 = new ArrayAdapter<>(MainActivity.this, R.layout.listview, metinlerBinanceTlarama);
                ViewGroup.MarginLayoutParams layoutParams2 = (LinearLayoutCompat.LayoutParams) binanceL.getLayoutParams();
                layoutParams2.setMargins(0, 0, 0, 45);
                binanceL.setLayoutParams(layoutParams2);
                paribuL.setLayoutParams(layoutParams2);
                binanceTlL.setLayoutParams(layoutParams2);
                paribuL.setAdapter( arrayAdapter);
                Helper.getListViewSize(paribuL, false);
                binanceL.setAdapter((ListAdapter) arrayAdapter3);
                Helper.getListViewSize(binanceL, false);
                binanceTlL.setAdapter((ListAdapter) arrayAdapter2);
                Helper.getListViewSize(binanceTlL, false);
                arrayAdapter.getFilter().filter(aramaMetni);
                arrayAdapter2.getFilter().filter(aramaMetni);
                arrayAdapter3.getFilter().filter(aramaMetni);
            }
        }
        return "";
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.checkboxhizlibinance:
                if (calistiMi) {
                    if (!item.isChecked()) {
                        BinanceAnaText.setVisibility(View.VISIBLE);
                        binanceL.setVisibility(View.VISIBLE);
                        if (BtcTurk.calistiMi) {
                            BtcTurk.binanceL.setVisibility(View.VISIBLE);
                            BtcTurk.BinanceAnaText.setVisibility(View.VISIBLE);
                        }
                        item.setChecked(true);
                    } else {
                        if (BtcTurk.calistiMi) {
                            BtcTurk.binanceL.setVisibility(View.GONE);
                            BtcTurk.BinanceAnaText.setVisibility(View.GONE);
                        }
                        binanceL.setVisibility(View.GONE);
                        BinanceAnaText.setVisibility(View.GONE);
                        item.setChecked(false);
                    }
                }
                return true;
            case R.id.checkboxhizliparibu:
                if (calistiMi) {
                    if (!item.isChecked()) {
                        paribuAnaText.setVisibility(View.VISIBLE);
                        paribuL.setVisibility(View.VISIBLE);
                        if (BtcTurk.calistiMi) {
                            BtcTurk.btcturkAnaText.setVisibility(View.VISIBLE);
                            BtcTurk.btcturkL.setVisibility(View.VISIBLE);
                        }
                        item.setChecked(true);
                    } else {
                        paribuAnaText.setVisibility(View.GONE);
                        paribuL.setVisibility(View.GONE);
                        if (BtcTurk.calistiMi) {
                            BtcTurk.btcturkAnaText.setVisibility(View.GONE);
                            BtcTurk.btcturkL.setVisibility(View.GONE);
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
                        if (BtcTurk.calistiMi) {
                            BtcTurk.binanceTlL.setVisibility(View.VISIBLE);
                            BtcTurk.BinanceTlAnaText.setVisibility(View.VISIBLE);
                        }
                        item.setChecked(true);
                    } else {
                        binanceTlL.setVisibility(View.GONE);
                        BinanceTlAnaText.setVisibility(View.GONE);
                        if (BtcTurk.calistiMi) {
                            BtcTurk.binanceTlL.setVisibility(View.GONE);
                            BtcTurk.BinanceTlAnaText.setVisibility(View.GONE);
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
        getMenuInflater().inflate(search, menu);
        MenuItem menuItem = menu.findItem(R.id.arama);
        paribucheck = menu.findItem(R.id.checkboxhizliparibu);
        tlcheck = menu.findItem(R.id.checkboxhizlitl);
        binancecheck = menu.findItem(R.id.checkboxhizlibinance);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Arama yapmak için yazın");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            public boolean onQueryTextChange(String newText) {
                MainActivity.girdi = false;
                MainActivity.this.aramaMetni = newText;
                MainActivity.aramaindexler.clear();
                MainActivity.hizli2 = 0;
                for (int a = 0; a < MainActivity.hizli; a++) {
                    if (MainActivity.this.aramaMetni.length() > 0 && MainActivity.isimler[a].matches(MainActivity.this.aramaMetni.toUpperCase() + "(.*)")) {
                        MainActivity.aramaindexler.add(Integer.valueOf(a));
                        MainActivity.hizli2++;
                    }
                }
                if (MainActivity.this.calistiMi && newText.length() > 0) {
                    MainActivity.girdi = true;
                    MainActivity.this.Dot();
                } else {
                    MainActivity.aramaindexler.clear();
                    MainActivity.hizli2 = 0;
                }
                if (MainActivity.this.aramaMetni.length() == 0 && MainActivity.hizli2 == 0 && MainActivity.this.calistiMi) {
                    arrayAdapter = new ArrayAdapter<>(MainActivity.this, R.layout.listview, metinlerParibu);
                    arrayAdapter3 = new ArrayAdapter<>(MainActivity.this, R.layout.listview, metinlerBinance);
                    arrayAdapter2 = new ArrayAdapter<>(MainActivity.this, R.layout.listview, metinlerBinanceTl);
                    MainActivity.paribuL.setAdapter((ListAdapter) MainActivity.this.arrayAdapter);
                    Helper.getListViewSize(MainActivity.paribuL, false);
                    MainActivity.binanceL.setAdapter((ListAdapter) MainActivity.this.arrayAdapter3);
                    Helper.getListViewSize(MainActivity.binanceL, false);
                    MainActivity.binanceTlL.setAdapter((ListAdapter) MainActivity.this.arrayAdapter2);
                    Helper.getListViewSize(MainActivity.binanceTlL, false);
                }
                return false;
            }
        });
        return true;
    }
}