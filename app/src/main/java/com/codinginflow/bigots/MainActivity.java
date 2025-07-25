package com.codinginflow.bigots;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
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
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
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
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import java.lang.ref.WeakReference;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import static com.codinginflow.bigots.BtcTurk.btctotal;
import static com.codinginflow.bigots.R.menu.search;

public class MainActivity extends AppCompatActivity {
    public static MediaPlayer saniye;

    public static EditText dot;
    public static int MainservisSayac;
    static ArrayList<Integer> aramaindexler;
    static String binanceDuyuruText;
    static String binanceGeciciDuyuruText;

    static float[] btcturk;
    static String[] btcturkisim;
    static float[] farklar;
    static float[] farklarBtcTurk;
    static boolean girdi;
    static float[] huobi;
    static float[] huobibtcturk;
    static int[] indexler;
    static int[] indexlerbtcturk;
    static String[] isimler;
    static String[] isimlerBtcTurk;
    static boolean listGeldiMi;
    static SpannableString[] metinlerBinance;
    static SpannableString[] metinlerBinanceTl;
    static SpannableString[] metinlerBinanceTlarama;
    static SpannableString[] metinlerBinancearama;
    static SpannableString[] metinlerParibu;
    static SpannableString[] metinlerParibuarama;
    static Toolbar toolbar;
    static int p;
    static boolean otuyorMu;
    static float[] paribu;
    private RecyclerView paribuRecyclerView;
    private RecyclerView binanceRecyclerView;
    private RecyclerView binanceTlRecyclerView;
    private CoinAdapter paribuAdapter;
    private CoinAdapter binanceAdapter;
    private CoinAdapter binanceTlAdapter;
    static String[] paribuisim;
    static int[] sesSeviyesi;
    public static int[] sesSeviyesiBtcTurk;
    static int[] siralama;
    static int[] siralamaBtcTurk;
    static int[] sesler;
    public static int[] seslerBtcTurk;
    private TextView BinanceAnaText;
    private TextView BinanceTlAnaText;

    FloatingActionButton asagi;
    FloatingActionButton sag;
    FloatingActionButton ayarlar;
    private AlertDialog dialog;
    private AlertDialog.Builder dialogBuilder;
    public static SharedPreferences soundPrefs;
    FloatingActionButton duzenle;
    private EditText edit;
    NestedScrollView ekran3;
    private NestedScrollView scrollView;
    private int currentPosition = 0;
    private final ExecutorService executorService = Executors.newFixedThreadPool(3);
    private Button geributton;
    private Button binancetradebutton;
    private Button binancewalletbutton;
    private Button paributradebutton;
    private Button paribuwalletbutton;
    private RequestQueue mQueue;
    private BottomAppBar mbottomappbar;
    private TextView oran;
    private TextView paribuAnaText;
    private TextView pop;
    private Button popupButon;
    private SeekBar seek;
    private SeekBar seekses;
    FloatingActionButton start;
    FloatingActionButton stop;
    FloatingActionButton yukari;
    static float tsatis = 0.0f;
    static float tsatisb = 0.0f;
    static float btc = 0.0f;
    static int hizli = 130;
    static int hizli2 = 0;
    static Double[] oranlar = new Double[hizli];
    static Double[] oranlarbtcturk = new Double[btctotal];
    static int tiklanansira = 0;
    Double dotusd = Double.valueOf(2.5d);
    private String isim = "";
    private int sayac = 0;
    static String coinName;
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
    public static void initSoundPrefs(Context context) {
        if (soundPrefs == null) {
            soundPrefs = context.getSharedPreferences("SoundSettings", MODE_PRIVATE);
        }
    }

    public static void updateAllSoundLevels(int level) {
        if (soundPrefs != null) {
            SharedPreferences.Editor editor = soundPrefs.edit();

            // Paribu ses seviyeleri
            for (int i = 0; i < hizli; i++) {
                sesSeviyesi[i] = level;
                editor.putInt("paribu_sound_" + i, level);
            }

            // BTCTurk ses seviyeleri
            for (int i = 0; i < btctotal; i++) {
                sesSeviyesiBtcTurk[i] = level;
                editor.putInt("btcturk_sound_" + i, level);
            }

            editor.apply();
        }
    }
    static {
        // Önce dizileri initialize et
        indexler = new int[hizli];
        huobi = new float[hizli];
        paribu = new float[hizli];
        paribuisim = new String[hizli];
        farklar = new float[hizli];
        farklarBtcTurk = new float[btctotal];
        sesler = new int[hizli];
        isimler = new String[hizli];
        isimlerBtcTurk = new String[btctotal];
        siralama = new int[hizli];
        siralamaBtcTurk = new int[btctotal];
        sesSeviyesi = new int[hizli];
        seslerBtcTurk = new int[btctotal];
        sesSeviyesiBtcTurk = new int[btctotal];
        metinlerParibu = new SpannableString[hizli];
        metinlerBinance = new SpannableString[hizli];
        metinlerBinanceTl = new SpannableString[hizli];
        aramaindexler = new ArrayList<>();
        btcturk = new float[btctotal];
        huobibtcturk = new float[btctotal];
        btcturkisim = new String[btctotal];
        indexlerbtcturk = new int[btctotal];
        oranlar = new Double[hizli];
        oranlarbtcturk = new Double[btctotal];

        // Sonra varsayılan değerleri ayarla
        girdi = false;
        listGeldiMi = false;
        binanceDuyuruText = "";
        binanceGeciciDuyuruText = "";
        p = 0;
        MainservisSayac = 0;
        otuyorMu = false;
    }


    public static final int SOUND_DOT = R.raw.dot;
    public static final int SOUND_AVAX = R.raw.avax;
    public static final int SOUND_TRON = R.raw.tron;
    public static final int SOUND_EOS = R.raw.eos;
    public static final int SOUND_BTT = R.raw.btt;
    public static final int SOUND_RIPPLE = R.raw.ripple;
    public static final int SOUND_SITELLAR = R.raw.sitellar;
    public static final int SOUND_ONT = R.raw.ont;
    public static final int SOUND_ATOM = R.raw.atom;
    public static final int SOUND_HOT = R.raw.hot;
    public static final int SOUND_NEO = R.raw.neo;
    public static final int SOUND_BAT = R.raw.bat;
    public static final int SOUND_CHZ = R.raw.chz;
    public static final int SOUND_UNI = R.raw.uni;
    public static final int SOUND_BAL = R.raw.bal;
    public static final int SOUND_AAVE = R.raw.aave;
    public static final int SOUND_LINK = R.raw.link;
    public static final int SOUND_MAKER = R.raw.maker;
    public static final int SOUND_W = R.raw.w;
    public static final int SOUND_RAY = R.raw.ray;
    public static final int SOUND_LRC = R.raw.lrc;
    public static final int SOUND_BAND = R.raw.band;
    public static final int SOUND_ALGO = R.raw.algo;
    public static final int SOUND_GRT = R.raw.grt;
    public static final int SOUND_ENJ = R.raw.enj;
    public static final int SOUND_THETA = R.raw.theta;
    public static final int SOUND_MATIC = R.raw.matic;
    public static final int SOUND_OXT = R.raw.oxt;
    public static final int SOUND_CRV = R.raw.crv;
    public static final int SOUND_OGN = R.raw.ogn;
    public static final int SOUND_MANA = R.raw.mana;
    public static final int SOUND_IOTA = R.raw.iota;
    public static final int SOUND_SOL = R.raw.sol;
    public static final int SOUND_APE = R.raw.ape;
    public static final int SOUND_VET = R.raw.vet;
    public static final int SOUND_ANKR = R.raw.ankr;
    public static final int SOUND_SHIB = R.raw.shib;
    public static final int SOUND_LPT = R.raw.lpt;
    public static final int SOUND_INJ = R.raw.inj;
    public static final int SOUND_ICP = R.raw.icp;
    public static final int SOUND_FTM = R.raw.ftm;
    public static final int SOUND_AXS = R.raw.axs;
    public static final int SOUND_ENS = R.raw.ens;
    public static final int SOUND_SAND = R.raw.sand;
    public static final int SOUND_AUDIO = R.raw.audio;
    public static final int SOUND_CLV = R.raw.clv;
    public static final int SOUND_UMA = R.raw.uma;
    public static final int SOUND_STORJ = R.raw.storj;
    public static final int SOUND_RAD = R.raw.rad;
    public static final int SOUND_INCH = R.raw.inch;
    public static final int SOUND_COMP = R.raw.comp;
    public static final int SOUND_SNX = R.raw.snx;
    public static final int SOUND_FET = R.raw.fet;
    public static final int SOUND_STG = R.raw.stg;
    public static final int SOUND_QNT = R.raw.qnt;
    public static final int SOUND_APT = R.raw.apt;
    public static final int SOUND_RNDR = R.raw.rndr;
    public static final int SOUND_ARB = R.raw.arb;
    public static final int SOUND_SUI = R.raw.sui;
    public static final int SOUND_CTSI = R.raw.ctsi;
    public static final int SOUND_OP = R.raw.op;
    public static final int SOUND_EIGEN = R.raw.eigen;
    public static final int SOUND_RLC = R.raw.rlc;
    public static final int SOUND_SKL = R.raw.skl;
    public static final int SOUND_WIF = R.raw.wif;
    public static final int SOUND_T = R.raw.t;
    public static final int SOUND_SEI = R.raw.sei;
    public static final int SOUND_RDNT = R.raw.rdnt;
    public static final int SOUND_FLOKI = R.raw.floki;
    public static final int SOUND_DYDX = R.raw.dydx;
    public static final int SOUND_TIA = R.raw.tia;
    public static final int SOUND_ALICE = R.raw.alice;
    public static final int SOUND_TLM = R.raw.tlm;
    public static final int SOUND_GALA = R.raw.gala;
    public static final int SOUND_VANRY = R.raw.vanry;
    public static final int SOUND_SPELL = R.raw.spell;
    public static final int SOUND_PEPE = R.raw.pepe;
    public static final int SOUND_API3 = R.raw.api3;
    public static final int SOUND_MASK = R.raw.mask;
    public static final int SOUND_GLM = R.raw.glm;
    public static final int SOUND_IMX = R.raw.imx;
    public static final int SOUND_BLUR = R.raw.blur;
    public static final int SOUND_BONK = R.raw.bonk;
    public static final int SOUND_ADA = R.raw.ada;
    public static final int SOUND_DOGE = R.raw.doge;
    public static final int SOUND_LITE = R.raw.lite;
    public static final int SOUND_RVN = R.raw.rvn;
    public static final int SOUND_ETHERFI = R.raw.etherfi;
    public static final int SOUND_XTZ = R.raw.xtz;
    public static final int SOUND_ZIL = R.raw.zil;
    public static final int SOUND_MINA = R.raw.mina;
    public static final int SOUND_BCH = R.raw.bch;
    public static final int SOUND_PSG = R.raw.psg;
    public static final int SOUND_ATM = R.raw.atm;
    public static final int SOUND_ASR = R.raw.asr;
    public static final int SOUND_BAR = R.raw.bar;
    public static final int SOUND_JUV = R.raw.juv;
    public static final int SOUND_ACM = R.raw.acm;
    public static final int SOUND_CITY = R.raw.city;
    public static final int SOUND_BEAM = R.raw.beam;
    public static final int SOUND_PYTH = R.raw.pyth;
    public static final int SOUND_JOE = R.raw.joe;
    public static final int SOUND_JTO = R.raw.jto;
    public static final int SOUND_MEME = R.raw.meme;
    public static final int SOUND_STRK = R.raw.strk;
    public static final int SOUND_DYM = R.raw.dym;
    public static final int SOUND_JUP = R.raw.jup;
    public static final int SOUND_MAGIC = R.raw.magic;
    public static final int SOUND_ZRX = R.raw.zrx;
    public static final int SOUND_NMR = R.raw.nmr;
    public static final int SOUND_PENDLE = R.raw.pendle;
    public static final int SOUND_MANTA = R.raw.manta;
    public static final int SOUND_TNSR = R.raw.tnsr;
    public static final int SOUND_ARKM = R.raw.arkm;
    public static final int SOUND_AXL = R.raw.axl;
    public static final int SOUND_IO = R.raw.io;
    public static final int SOUND_LDO = R.raw.ldo;
    public static final int SOUND_AEVO = R.raw.aevo;
    public static final int SOUND_ENA = R.raw.ena;
    public static final int SOUND_ZK = R.raw.zk;
    public static final int SOUND_ALT = R.raw.alt;
    public static final int SOUND_SYN = R.raw.syn;
    public static final int SOUND_FIL = R.raw.fil;
    public static final int SOUND_PDA = R.raw.pda;
    public static final int SOUND_ZRO = R.raw.zro;
    public static final int SOUND_DASH = R.raw.dash;
    public static final int SOUND_STX = R.raw.stx;
    public static final int SOUND_AMP = R.raw.amp;
    public static final int SOUND_BNT = R.raw.bnt;
    public static final int SOUND_LUNA = R.raw.luna;
    public static final int SOUND_PAXG = R.raw.paxg;
    public static final int SOUND_CVC = R.raw.cvc;
    public static final int SOUND_ETC = R.raw.etc;
    public static final int SOUND_FLOW = R.raw.flow;
    public static final int SOUND_SUPER0 = R.raw.super0;
    public static final int SOUND_ARPA = R.raw.arpa;
    public static final int SOUND_SUSHI = R.raw.sushi;
    public static final int SOUND_JASMY = R.raw.jasmy;
    public static final int SOUND_TON = R.raw.ton;
    public static final int SOUND_WLD = R.raw.wld;
    public static final int SOUND_XAI = R.raw.xai;
    public static final int SOUND_BTC = R.raw.btc;
    public static final int SOUND_ETH = R.raw.eth;
    public static final int SOUND_NEAR = R.raw.near;
    public static final int SOUND_HBAR = R.raw.hbar;
    public static final int SOUND_KSM = R.raw.ksm;
    public static final int SOUND_RARE = R.raw.rare;
    public static final int SOUND_GMT = R.raw.gmt;
    public static final int SOUND_GLMR = R.raw.glmr;
    public static final int SOUND_RUNE = R.raw.rune;
    public static final int SOUND_KAVA = R.raw.kava;


    public static void mergeSort(float[] arr, int left, int right, float[] f1, float[] f2, String[] s, String[] s1, int[] i0, int[] i1, int[] i2, Double[] d, int[] soundIds) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(arr, left, middle, f1, f2, s, s1, i0, i1, i2, d, soundIds);
            mergeSort(arr, middle + 1, right, f1, f2, s, s1, i0, i1, i2, d, soundIds);
            merge(arr, left, middle, right, f1, f2, s, s1, i0, i1, i2, d, soundIds);
        }
    }

    public static void merge(float[] arr, int left, int middle, int right, float[] f1, float[] f2, String[] s, String[] s1, int[] i0, int[] i1, int[] i2, Double[] d, int[] soundIds) {
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
        int[] soundL = new int[n1];
        int[] soundR = new int[n2];
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
            soundL[i] = soundIds[left + i];
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
            soundR[j] = soundIds[middle + 1 + j];
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
                soundIds[k] = soundL[i3];
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
                soundIds[k] = soundR[j2];
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
            soundIds[k] = soundL[i3];
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
            soundIds[k] = soundR[j2];
            j2++;
            k++;
        }
    }

    public static void mergeSortBtcTurk(float[] arr, int left, int right, float[] f1, float[] f2,
                                        String[] s, String[] s1, int[] i0, int[] i1, int[] i2, Double[] d,int[] soundIds) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSortBtcTurk(arr, left, middle, f1, f2, s, s1, i0, i1, i2, d, soundIds);
            mergeSortBtcTurk(arr, middle + 1, right, f1, f2, s, s1, i0, i1, i2, d, soundIds);
            mergeBtcTurk(arr, left, middle, right, f1, f2, s, s1, i0, i1, i2, d, soundIds);
        }
    }

    public static void mergeBtcTurk(float[] arr, int left, int middle, int right, float[] f1, float[] f2,
                                    String[] s, String[] s1, int[] i0, int[] i1, int[] i2, Double[] d,int[] soundIds) {
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
        int[] soundL = new int[n1];
        int[] soundR = new int[n2];

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
            soundL[i] = soundIds[left + i];
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
            soundR[j] = soundIds[middle + 1 + j];
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
                soundIds[k] = soundL[i];
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
                soundIds[k] = soundR[j];
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
            soundIds[k] = soundL[i];
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
            soundIds[k] = soundR[j];
            j++;
            k++;
        }
    }

    private MediaPlayerManager mediaPlayerManager;

    private void setupRecyclerViews() {
        paribuRecyclerView.setHasFixedSize(true);
        binanceRecyclerView.setHasFixedSize(true);
        binanceTlRecyclerView.setHasFixedSize(true);

        // Tüm decorationları temizle
        while (paribuRecyclerView.getItemDecorationCount() > 0) {
            paribuRecyclerView.removeItemDecorationAt(0);
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
        paribuRecyclerView.addItemDecoration(dividerItemDecoration);
        binanceRecyclerView.addItemDecoration(dividerItemDecoration);
        binanceTlRecyclerView.addItemDecoration(dividerItemDecoration);
    }


    private class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {
        private final GestureDetector gestureDetector;
        private float downX;

        public RecyclerTouchListener(Context context) {
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    View childView = paribuRecyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (childView != null && calistiMi) {
                        int position = paribuRecyclerView.getChildAdapterPosition(childView);
                        if (position != RecyclerView.NO_POSITION) {
                            if (paribuAdapter != null) {
                                paribuAdapter.onItemClick(position);
                            }
                            return true;
                        }
                    }
                    return false;
                }


            });
        }

        @Override
        public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

            return false;
        }

        @Override
        public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        }
    }










    protected void onCreate(Bundle savedInstanceState) {
      //  Sesler.arti = true;

        super.onCreate(savedInstanceState);
        soundPrefs = getSharedPreferences("SoundSettings", MODE_PRIVATE);
        instanceRef = new WeakReference<>(this);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 1);
            }
        }
        if (UnifiedService.isMainServiceRunning() && calistiMi) {
            start.setVisibility(View.GONE);
            updateUI(true);
        }

        if (savedInstanceState != null) {
            calistiMi = savedInstanceState.getBoolean("calistiMi", false);
            if (calistiMi && UnifiedService.isMainServiceRunning()) {
                start.setVisibility(View.GONE);
                updateUI(true);
            }
        }
        if (!DeviceAuth.isDeviceAuthorized(this)) {
            Toast.makeText(this, "Bu uygulama yalnızca yetkili cihazlarda çalışır",
                    Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        paribuRecyclerView = findViewById(R.id.recyclerview); // XML'de id'yi güncelle
        binanceRecyclerView = findViewById(R.id.recyclerview2);
        binanceTlRecyclerView = findViewById(R.id.recyclerview3);
        paribuRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binanceRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binanceTlRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        paribuAdapter = new CoinAdapter();
        binanceAdapter = new CoinAdapter();
        binanceTlAdapter = new CoinAdapter();
        paribuRecyclerView.setAdapter(paribuAdapter);
        binanceRecyclerView.setAdapter(binanceAdapter);
        binanceTlRecyclerView.setAdapter(binanceTlAdapter);
        RecyclerTouchListener touchListener = new RecyclerTouchListener(this);
        paribuRecyclerView.addOnItemTouchListener(touchListener);
        binanceRecyclerView.addOnItemTouchListener(touchListener);
        binanceTlRecyclerView.addOnItemTouchListener(touchListener);
        setupRecyclerViews();

        // RecyclerView'ların padding ve clipToPadding ayarlarını programmatik olarak da set et
        paribuRecyclerView.setPadding(0, 0, 0, 0);
        paribuRecyclerView.setClipToPadding(false);
        binanceRecyclerView.setPadding(0, 0, 0, 0);
        binanceRecyclerView.setClipToPadding(false);
        binanceTlRecyclerView.setPadding(0, 0, 0, 0);
        binanceTlRecyclerView.setClipToPadding(false);


        mQueue = Volley.newRequestQueue(this);
        dot = findViewById(R.id.DOT);
        ayarlar = findViewById(R.id.ayarlar);
        yukari = findViewById(R.id.yukari);
        asagi = findViewById(R.id.asagi);
        asagi.setVisibility(View.GONE);
        yukari.setVisibility(View.GONE);
        sag = findViewById(R.id.btcturkactivity);
        start = findViewById(R.id.baslat);
        stop = findViewById(R.id.durdur);
        duzenle = findViewById(R.id.duzenle);
        paribuAnaText = findViewById(R.id.baslikparibuhizli);
        BinanceTlAnaText = findViewById(R.id.baslikbinancetlhizli);
        BinanceAnaText = findViewById(R.id.baslikbinancehizli);
        scrollView = findViewById(R.id.ekran1);
        BottomAppBar findViewById = findViewById(R.id.bottom_app_bar);
        mbottomappbar = findViewById;
        setSupportActionBar(findViewById);
        ekran3 = findViewById(R.id.ekran1);
        Toolbar findViewById2 = findViewById(R.id.toolbar);
        toolbar = findViewById2;
        setSupportActionBar(findViewById2);
        dialogBuilder = new AlertDialog.Builder(this);
        View popupView = getLayoutInflater().inflate(R.layout.popup, null);
        pop = popupView.findViewById(R.id.textView665);
        edit = popupView.findViewById(R.id.edittext);
        seek = popupView.findViewById(R.id.seekbar665);
        seekses = popupView.findViewById(R.id.seekbar666);
        popupButon = popupView.findViewById(R.id.button665);
        geributton = popupView.findViewById(R.id.button6650);
        binancetradebutton = popupView.findViewById(R.id.binanceTrade);
        binancewalletbutton = popupView.findViewById(R.id.binanceWallet);
        paributradebutton = popupView.findViewById(R.id.paribuTrade);
        paribuwalletbutton = popupView.findViewById(R.id.paribuWallet);
        oran = popupView.findViewById(R.id.textView6650);
        dialogBuilder.setView(popupView);
        AlertDialog create = dialogBuilder.create();
        dialog = create;
        create.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        saniye = MediaPlayer.create(this, R.raw.saniye);
        mediaPlayerManager = MediaPlayerManager.getInstance(this);


        sesler[0] = SOUND_DOT;
        sesler[1] = SOUND_AVAX;
        sesler[2] = SOUND_TRON;
        sesler[3] = SOUND_EOS;
        sesler[4] = SOUND_BTT;
        sesler[5] = SOUND_RIPPLE;
        sesler[6] = SOUND_SITELLAR;
        sesler[7] = SOUND_ONT;
        sesler[8] = SOUND_ATOM;
        sesler[9] = SOUND_HOT;
        sesler[10] = SOUND_NEO;
        sesler[11] = SOUND_BAT;
        sesler[12] = SOUND_CHZ;
        sesler[13] = SOUND_UNI;
        sesler[14] = SOUND_BAL;
        sesler[15] = SOUND_AAVE;
        sesler[16] = SOUND_LINK;
        sesler[17] = SOUND_MAKER;
        sesler[18] = SOUND_W;
        sesler[19] = SOUND_RAY;
        sesler[20] = SOUND_LRC;
        sesler[21] = SOUND_BAND;
        sesler[22] = SOUND_ALGO;
        sesler[23] = SOUND_GRT;
        sesler[24] = SOUND_ENJ;
        sesler[25] = SOUND_THETA;
        sesler[26] = SOUND_MATIC;
        sesler[27] = SOUND_OXT;
        sesler[28] = SOUND_CRV;
        sesler[29] = SOUND_OGN;
        sesler[30] = SOUND_MANA;
        sesler[31] = SOUND_IOTA;
        sesler[32] = SOUND_SOL;
        sesler[33] = SOUND_APE;
        sesler[34] = SOUND_VET;
        sesler[35] = SOUND_ANKR;
        sesler[36] = SOUND_SHIB;
        sesler[37] = SOUND_LPT;
        sesler[38] = SOUND_INJ;
        sesler[39] = SOUND_ICP;
        sesler[40] = SOUND_FTM;
        sesler[41] = SOUND_AXS;
        sesler[42] = SOUND_ENS;
        sesler[43] = SOUND_SAND;
        sesler[44] = SOUND_AUDIO;
        sesler[45] = SOUND_CLV;
        sesler[46] = SOUND_UMA;
        sesler[47] = SOUND_STORJ;
        sesler[48] = SOUND_RAD;
        sesler[49] = SOUND_INCH;
        sesler[50] = SOUND_COMP;
        sesler[51] = SOUND_SNX;
        sesler[52] = SOUND_FET;
        sesler[53] = SOUND_STG;
        sesler[54] = SOUND_QNT;
        sesler[55] = SOUND_APT;
        sesler[56] = SOUND_RNDR;
        sesler[57] = SOUND_ARB;
        sesler[58] = SOUND_SUI;
        sesler[59] = SOUND_CTSI;
        sesler[60] = SOUND_OP;
        sesler[61] = SOUND_EIGEN;
        sesler[62] = SOUND_RLC;
        sesler[63] = SOUND_SKL;
        sesler[64] = SOUND_WIF;
        sesler[65] = SOUND_T;
        sesler[66] = SOUND_SEI;
        sesler[67] = SOUND_RDNT;
        sesler[68] = SOUND_FLOKI;
        sesler[69] = SOUND_DYDX;
        sesler[70] = SOUND_TIA;
        sesler[71] = SOUND_ALICE;
        sesler[72] = SOUND_TLM;
        sesler[73] = SOUND_GALA;
        sesler[74] = SOUND_VANRY;
        sesler[75] = SOUND_SPELL;
        sesler[76] = SOUND_PEPE;
        sesler[77] = SOUND_API3;
        sesler[78] = SOUND_MASK;
        sesler[79] = SOUND_GLM;
        sesler[80] = SOUND_IMX;
        sesler[81] = SOUND_BLUR;
        sesler[82] = SOUND_BONK;
        sesler[83] = SOUND_ADA;
        sesler[84] = SOUND_DOGE;
        sesler[85] = SOUND_LITE;
        sesler[86] = SOUND_RVN;
        sesler[87] = SOUND_ETHERFI;
        sesler[88] = SOUND_XTZ;
        sesler[89] = SOUND_ZIL;
        sesler[90] = SOUND_MINA;
        sesler[91] = SOUND_BCH;
        sesler[92] = SOUND_PSG;
        sesler[93] = SOUND_ATM;
        sesler[94] = SOUND_ASR;
        sesler[95] = SOUND_BAR;
        sesler[96] = SOUND_JUV;
        sesler[97] = SOUND_ACM;
        sesler[98] = SOUND_CITY;
        sesler[99] = SOUND_BEAM;
        sesler[100] = SOUND_PYTH;
        sesler[101] = SOUND_JOE;
        sesler[102] = SOUND_JTO;
        sesler[103] = SOUND_MEME;
        sesler[104] = SOUND_STRK;
        sesler[105] = SOUND_DYM;
        sesler[106] = SOUND_JUP;
        sesler[107] = SOUND_MAGIC;
        sesler[108] = SOUND_ZRX;
        sesler[109] = SOUND_NMR;
        sesler[110] = SOUND_PENDLE;
        sesler[111] = SOUND_MANTA;
        sesler[112] = SOUND_TNSR;
        sesler[113] = SOUND_ARKM;
        sesler[114] = SOUND_AXL;
        sesler[115] = SOUND_IO;
        sesler[116] = SOUND_LDO;
        sesler[117] = SOUND_AEVO;
        sesler[118] = SOUND_ENA;
        sesler[119] = SOUND_ZK;
        sesler[120] = SOUND_ALT;
        sesler[121] = SOUND_SYN;
        sesler[122] = SOUND_FIL;
        sesler[123] = SOUND_PDA;
        sesler[124] = SOUND_ZRO;
        sesler[125] = SOUND_TON;
        sesler[126] = SOUND_WLD;
        sesler[127] = SOUND_XAI;
        sesler[128] = SOUND_BTC;
        sesler[129] = SOUND_ETH;

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
        indexler[125] = 2700;
        indexler[126] = 2258;
        indexler[127] = 2430;
        indexler[128] = 11;
        indexler[129] = 12;

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
        paribuisim[125] = "TON_TL";
        paribuisim[126] = "WLD_TL";
        paribuisim[127] = "XAI_TL";
        paribuisim[128] = "BTC_TL";
        paribuisim[129] = "ETH_TL";


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
        btcturkisim[80] = "EIGEN_TRY";
        btcturkisim[81] = "W_TRY";
        btcturkisim[82] = "AXL_TRY";
        btcturkisim[83] = "BTC_TRY";
        btcturkisim[84] = "ETH_TRY";
        btcturkisim[85] = "NEAR_TRY";
        btcturkisim[86] = "HBAR_TRY";
        btcturkisim[87] = "ZRO_TRY";
        btcturkisim[88] = "WLD_TRY";
        btcturkisim[89] = "BLUR_TRY";
        btcturkisim[90] = "KSM_TRY";
        btcturkisim[91] = "TIA_TRY";
        btcturkisim[92] = "ARKM_TRY";
        btcturkisim[93] = "JUP_TRY";
        btcturkisim[94] = "RARE_TRY";
        btcturkisim[95] = "OP_TRY";
        btcturkisim[96] = "PYTH_TRY";
        btcturkisim[97] = "TON_TRY";
        btcturkisim[98] = "GMT_TRY";
        btcturkisim[99] = "GLMR_TRY";
        btcturkisim[100] = "RUNE_TRY";
        btcturkisim[101] = "JUV_TRY";
        btcturkisim[102] = "KAVA_TRY";
        btcturkisim[103] = "CITY_TRY";
        btcturkisim[104] = "ACM_TRY";
        btcturkisim[105] = "ATM_TRY";


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
        indexlerbtcturk[80] = 2755;
        indexlerbtcturk[81] = 2568;
        indexlerbtcturk[82] = 2501;
        indexlerbtcturk[83] = 11;
        indexlerbtcturk[84] = 12;
        indexlerbtcturk[85] = 1097;
        indexlerbtcturk[86] = 627;
        indexlerbtcturk[87] = 2673;
        indexlerbtcturk[88] = 2258;
        indexlerbtcturk[89] = 2367;
        indexlerbtcturk[90] = 998;
        indexlerbtcturk[91] = 2329;
        indexlerbtcturk[92] = 2246;
        indexlerbtcturk[93] = 2456;
        indexlerbtcturk[94] = 1682;
        indexlerbtcturk[95] = 2060;
        indexlerbtcturk[96] = 2463;
        indexlerbtcturk[97] = 2700;
        indexlerbtcturk[98] = 1960;
        indexlerbtcturk[99] = 1881;
        indexlerbtcturk[100] = 1007;
        indexlerbtcturk[101] = 1215;
        indexlerbtcturk[102] = 642;
        indexlerbtcturk[103] = 1738;
        indexlerbtcturk[104] = 1298;
        indexlerbtcturk[105] = 1228;

        seslerBtcTurk[0] = SOUND_RIPPLE;
        seslerBtcTurk[1] = SOUND_LITE;
        seslerBtcTurk[2] = SOUND_SITELLAR;
        seslerBtcTurk[3] = SOUND_NEO;
        seslerBtcTurk[4] = SOUND_EOS;
        seslerBtcTurk[5] = SOUND_DASH;
        seslerBtcTurk[6] = SOUND_LINK;
        seslerBtcTurk[7] = SOUND_ATOM;
        seslerBtcTurk[8] = SOUND_XTZ;
        seslerBtcTurk[9] = SOUND_TRON;
        seslerBtcTurk[10] = SOUND_ADA;
        seslerBtcTurk[11] = SOUND_DOT;
        seslerBtcTurk[12] = SOUND_UNI;
        seslerBtcTurk[13] = SOUND_ANKR;
        seslerBtcTurk[14] = SOUND_MAKER;
        seslerBtcTurk[15] = SOUND_ENJ;
        seslerBtcTurk[16] = SOUND_COMP;
        seslerBtcTurk[17] = SOUND_GRT;
        seslerBtcTurk[18] = SOUND_MANA;
        seslerBtcTurk[19] = SOUND_MATIC;
        seslerBtcTurk[20] = SOUND_SNX;
        seslerBtcTurk[21] = SOUND_BAT;
        seslerBtcTurk[22] = SOUND_AVAX;
        seslerBtcTurk[23] = SOUND_FIL;
        seslerBtcTurk[24] = SOUND_DOGE;
        seslerBtcTurk[25] = SOUND_CHZ;
        seslerBtcTurk[26] = SOUND_SOL;
        seslerBtcTurk[27] = SOUND_STX;
        seslerBtcTurk[28] = SOUND_AXS;
        seslerBtcTurk[29] = SOUND_SHIB;
        seslerBtcTurk[30] = SOUND_FTM;
        seslerBtcTurk[31] = SOUND_LRC;
        seslerBtcTurk[32] = SOUND_UMA;
        seslerBtcTurk[33] = SOUND_FET;
        seslerBtcTurk[34] = SOUND_STORJ;
        seslerBtcTurk[35] = SOUND_UNI;
        seslerBtcTurk[36] = SOUND_GALA;
        seslerBtcTurk[37] = SOUND_VET;
        seslerBtcTurk[38] = SOUND_AMP;
        seslerBtcTurk[39] = SOUND_AUDIO;
        seslerBtcTurk[40] = SOUND_SPELL;
        seslerBtcTurk[41] = SOUND_ALGO;
        seslerBtcTurk[42] = SOUND_APE;
        seslerBtcTurk[43] = SOUND_BNT;
        seslerBtcTurk[44] = SOUND_CRV;
        seslerBtcTurk[45] = SOUND_QNT;
        seslerBtcTurk[46] = SOUND_SKL;
        seslerBtcTurk[47] = SOUND_GLM;
        seslerBtcTurk[48] = SOUND_LUNA;
        seslerBtcTurk[49] = SOUND_PAXG;
        seslerBtcTurk[50] = SOUND_ENS;
        seslerBtcTurk[51] = SOUND_IMX;
        seslerBtcTurk[52] = SOUND_LPT;
        seslerBtcTurk[53] = SOUND_RNDR;
        seslerBtcTurk[54] = SOUND_CVC;
        seslerBtcTurk[55] = SOUND_RLC;
        seslerBtcTurk[56] = SOUND_ETC;
        seslerBtcTurk[57] = SOUND_HOT;
        seslerBtcTurk[58] = SOUND_SAND;
        seslerBtcTurk[59] = SOUND_FLOW;
        seslerBtcTurk[60] = SOUND_CTSI;
        seslerBtcTurk[61] = SOUND_MASK;
        seslerBtcTurk[62] = SOUND_API3;
        seslerBtcTurk[63] = SOUND_BAND;
        seslerBtcTurk[64] = SOUND_T;
        seslerBtcTurk[65] = SOUND_ARB;
        seslerBtcTurk[66] = SOUND_STRK;
        seslerBtcTurk[67] = SOUND_SUPER0;
        seslerBtcTurk[68] = SOUND_INJ;
        seslerBtcTurk[69] = SOUND_OGN;
        seslerBtcTurk[70] = SOUND_RAD;
        seslerBtcTurk[71] = SOUND_ARPA;
        seslerBtcTurk[72] = SOUND_LDO;
        seslerBtcTurk[73] = SOUND_SUSHI;
        seslerBtcTurk[74] = SOUND_MAGIC;
        seslerBtcTurk[75] = SOUND_JASMY;
        seslerBtcTurk[76] = SOUND_ZRX;
        seslerBtcTurk[77] = SOUND_PEPE;
        seslerBtcTurk[78] = SOUND_NMR;
        seslerBtcTurk[79] = SOUND_FLOKI;
        seslerBtcTurk[80] = SOUND_EIGEN;
        seslerBtcTurk[81] = SOUND_W;
        seslerBtcTurk[82] = SOUND_AXL;
        seslerBtcTurk[83] = SOUND_BTC;
        seslerBtcTurk[84] = SOUND_ETH;
        seslerBtcTurk[85] = SOUND_NEAR;
        seslerBtcTurk[86] = SOUND_HBAR;
        seslerBtcTurk[87] = SOUND_ZRO;
        seslerBtcTurk[88] = SOUND_WLD;
        seslerBtcTurk[89] = SOUND_BLUR;
        seslerBtcTurk[90] = SOUND_KSM;
        seslerBtcTurk[91] = SOUND_TIA;
        seslerBtcTurk[92] = SOUND_ARKM;
        seslerBtcTurk[93] = SOUND_JUP;
        seslerBtcTurk[94] = SOUND_RARE;
        seslerBtcTurk[95] = SOUND_OP;
        seslerBtcTurk[96] = SOUND_PYTH;
        seslerBtcTurk[97] = SOUND_TON;
        seslerBtcTurk[98] = SOUND_GMT;
        seslerBtcTurk[99] = SOUND_GLMR;
        seslerBtcTurk[100] = SOUND_RUNE;
        seslerBtcTurk[101] = SOUND_JUV;
        seslerBtcTurk[102] = SOUND_KAVA;
        seslerBtcTurk[103] = SOUND_CITY;
        seslerBtcTurk[104] = SOUND_ACM;
        seslerBtcTurk[105] = SOUND_ATM;
        for (int i = 0; i < hizli; i++) {
            String[] strArr3 = isimler;
            StringBuilder sb = new StringBuilder();
            String str = paribuisim[i];
            strArr3[i] = sb.append(str.substring(0, str.indexOf("_"))).append(":").toString();
            siralama[i] = i;
            oranlar[i] = Double.valueOf(0.0d);
            sesSeviyesi[i] = soundPrefs.getInt("paribu_sound_" + i, 15);
        }
        for (int i2 = 0; i2 < btctotal; i2++) {
            String[] strArr4 = isimlerBtcTurk;
            StringBuilder sb2 = new StringBuilder();
            String str2 = btcturkisim[i2];
            strArr4[i2] = sb2.append(str2.substring(0, str2.indexOf("_"))).append(":").toString();
            siralamaBtcTurk[i2] = i2;
            oranlarbtcturk[i2] = Double.valueOf(0.0d);
            sesSeviyesiBtcTurk[i2] = soundPrefs.getInt("btcturk_sound_" + i2, 15);
        }
        ayarlar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openMainActivity = new Intent( MainActivity.this, Sesler.class);
                openMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                MainActivity.this.startActivityIfNeeded(openMainActivity, 2);
            }
        });
        setupScrollButtons();
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

        sag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openBtcTurk = new Intent(MainActivity.this, BtcTurk.class);
                openBtcTurk.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivityIfNeeded(openBtcTurk, 1);
            }
        });



        paribuAdapter.setOnItemClickListener(position -> {

                if (calistiMi) {
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                    dialog.show();
                    if (!girdi) {
                        tiklanansira = MainActivity.siralama[position];
                        pop.setText(MainActivity.paribuisim[position]);
                        coinName = MainActivity.paribuisim[position];
                        edit.setText("" + MainActivity.oranlar[position]);
                        oran.setText("" + MainActivity.oranlar[position]);
                        seek.setProgress((int) (MainActivity.oranlar[position].doubleValue() * 10.0d));
                        seekses.setProgress(MainActivity.sesSeviyesi[position]);
                        seekses.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                            @Override
                            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                for (int i3 = 0; i3 < MainActivity.hizli; i3++) {
                                    if (paribuisim[i3].contains(pop.getText())) {
                                        updateSoundLevel(i3, progress, false);
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
                                    String binanceSymbol = CoinMapper.getBinanceSymbol(coinName.replace("_TL", ""));
                                    Intent intent = new Intent(Intent.ACTION_VIEW);
                                    intent.setData(Uri.parse("bnc://app.binance.com/trade/trade?at=spot&symbol=" +
                                            binanceSymbol.toLowerCase() + "usdt"));
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                                    startActivity(intent);
                                } catch (ActivityNotFoundException e) {
                                    Toast.makeText(MainActivity.this, "Uygulama bulunamadı", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        binancewalletbutton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                try {
                                    Intent intent = new Intent(Intent.ACTION_VIEW);
                                    intent.setData(Uri.parse("bnc://app.binance.com/funds/withdrawChooseCoin"));
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK  );
                                    startActivity(intent);
                                } catch (ActivityNotFoundException e) {
                                    Toast.makeText(MainActivity.this, "Uygulama bulunamadı", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });paributradebutton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                try {
                                    String paribuPath = CoinMapper.getParibTradeUrl(coinName);
                                    Intent intent = new Intent(Intent.ACTION_VIEW);
                                    intent.setData(Uri.parse("paribu://markets/" + paribuPath));
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK  );
                                    startActivity(intent);
                                } catch (ActivityNotFoundException e) {
                                    Toast.makeText(MainActivity.this, "Uygulama bulunamadı", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        paribuwalletbutton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                try {
                                    String paribuPath = CoinMapper.getParibWalletUrl(coinName);
                                    Intent intent = new Intent(Intent.ACTION_VIEW);
                                    intent.setData(Uri.parse("paribu://wallet/" + paribuPath + "/deposit"));
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK  );
                                    startActivity(intent);
                                } catch (ActivityNotFoundException e) {
                                    Toast.makeText(MainActivity.this, "Uygulama bulunamadı", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        return;
                    }
                    MainActivity.tiklanansira = MainActivity.aramaindexler.get(position).intValue();
                    MainActivity.this.isim = MainActivity.paribuisim[MainActivity.tiklanansira];
                    MainActivity.this.pop.setText(MainActivity.paribuisim[MainActivity.tiklanansira]);
                    coinName = MainActivity.paribuisim[tiklanansira];
                    MainActivity.this.edit.setText("" + MainActivity.oranlar[MainActivity.tiklanansira]);
                    MainActivity.this.oran.setText("" + MainActivity.oranlar[MainActivity.tiklanansira]);
                    MainActivity.this.seek.setProgress((int) (MainActivity.oranlar[MainActivity.tiklanansira].doubleValue() * 10.0d));
                    MainActivity.this.seekses.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                            for (int i3 = 0; i3 < MainActivity.hizli; i3++) {
                                if (paribuisim[i3].contains(pop.getText())) {
                                    updateSoundLevel(i3, progress, false);
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
                                String binanceSymbol = CoinMapper.getBinanceSymbol(coinName.replace("_TL", ""));
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                intent.setData(Uri.parse("bnc://app.binance.com/trade/trade?at=spot&symbol=" +
                                        binanceSymbol.toLowerCase() + "usdt"));
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK  );
                                startActivity(intent);
                            } catch (ActivityNotFoundException e) {
                                Toast.makeText(MainActivity.this, "Uygulama bulunamadı", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    binancewalletbutton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                intent.setData(Uri.parse("bnc://app.binance.com/funds/withdrawChooseCoin"));
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK  );
                                startActivity(intent);
                            } catch (ActivityNotFoundException e) {
                                Toast.makeText(MainActivity.this, "Uygulama bulunamadı", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });paributradebutton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                String paribuPath = CoinMapper.getParibTradeUrl(coinName);
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                intent.setData(Uri.parse("paribu://markets/" + paribuPath));
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK  );
                                startActivity(intent);
                            } catch (ActivityNotFoundException e) {
                                Toast.makeText(MainActivity.this, "Uygulama bulunamadı", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    paribuwalletbutton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                String paribuPath = CoinMapper.getParibWalletUrl(coinName);
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                intent.setData(Uri.parse("paribu://wallet/" + paribuPath + "/deposit"));
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK  );
                                startActivity(intent);
                            } catch (ActivityNotFoundException e) {
                                Toast.makeText(MainActivity.this, "Uygulama bulunamadı", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

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
        scrollView.getViewTreeObserver().addOnScrollChangedListener(() -> {
            // Scroll sırasında butonları güncelle
            int totalHeight = scrollView.getChildAt(0).getHeight();
            int scrollViewHeight = scrollView.getHeight();
            int scrollY = scrollView.getScrollY();

            yukari.setEnabled(scrollY > 0);
            asagi.setEnabled(scrollY < (totalHeight - scrollViewHeight));
        });

        Dot();
    }
    public void updateSoundLevel(int index, int progress, boolean isBtcTurk) {
        if (isBtcTurk) {
            sesSeviyesiBtcTurk[index] = progress;
            soundPrefs.edit().putInt("btcturk_sound_" + index, progress).apply();
        } else {
            sesSeviyesi[index] = progress;
            soundPrefs.edit().putInt("paribu_sound_" + index, progress).apply();
        }
        // MediaPlayer ses seviyesini güncelle
        mediaPlayerManager.updateVolume(
                isBtcTurk ? seslerBtcTurk[index] : sesler[index],
                progress / 15.0f
        );
    }
    private void scrollToPosition(int position) {
        scrollView.post(() -> {
            // Her RecyclerView'ın üstündeki başlık (TextView) kullanılacak
            View targetView = null;
            String debugInfo = "";

            // Önce scroll view'ın mevcut scroll pozisyonunu al
            int currentScrollY = scrollView.getScrollY();

            switch (position) {
                case 0: // Paribu
                    targetView = paribuAnaText;
                    debugInfo = "Scrolling to Paribu";
                    break;
                case 1: // Binance TL
                    targetView = BinanceTlAnaText;
                    debugInfo = "Scrolling to Binance TL";
                    break;
                case 2: // Binance
                    targetView = BinanceAnaText;
                    debugInfo = "Scrolling to Binance";
                    break;
            }

            if (targetView == null || !targetView.isShown()) {
                Log.e("ScrollDebug", "Target view not found or not visible");
                return;
            }

            // View'ın ScrollView içindeki mutlak pozisyonunu bul
            int targetY = 0;
            View current = targetView;
            while (current != scrollView) {
                targetY += current.getTop();
                if (current.getParent() instanceof View) {
                    current = (View) current.getParent();
                } else {
                    break;
                }
            }

            // Toolbar offset'ini hesapla
            int toolbarOffset = toolbar.getHeight();

            // Final scroll pozisyonunu hesapla
            final int scrollTo = Math.max(0, targetY - toolbarOffset);

            Log.d("ScrollDebug", debugInfo +
                    "\nCurrent Scroll Y: " + currentScrollY +
                    "\nTarget Y: " + targetY +
                    "\nToolbar Offset: " + toolbarOffset +
                    "\nFinal Scroll Position: " + scrollTo);

            // Smooth scroll yerine programatik scroll kullanalım
            ObjectAnimator scrollAnimator = ObjectAnimator.ofInt(scrollView, "scrollY",
                    currentScrollY, scrollTo);
            scrollAnimator.setDuration(500); // 500ms
            scrollAnimator.setInterpolator(new DecelerateInterpolator());
            scrollAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    // Scroll tamamlandığında pozisyonu kontrol et
                    scrollView.postDelayed(() -> {
                        int finalY = scrollView.getScrollY();
                        if (Math.abs(finalY - scrollTo) > 5) {
                            scrollView.scrollTo(0, scrollTo);
                        }
                    }, 50);
                }
            });
            scrollAnimator.start();
        });
    }

    // Butonların click listener'larını da güncelleyelim
    private void setupScrollButtons() {
        yukari.setOnClickListener(v -> {
            if (currentPosition > 0) {
                currentPosition--;
                scrollToPosition(currentPosition);
                updateScrollButtons(); // Scroll sonrası butonları güncelle
            }
        });

        asagi.setOnClickListener(v -> {
            if (currentPosition < 2) {
                currentPosition++;
                scrollToPosition(currentPosition);
                updateScrollButtons(); // Scroll sonrası butonları güncelle
            }
        });

        // ScrollView listener'ı güncelle
        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                updateScrollButtons();
            }
        });

        // İlk yüklemede butonları güncelle
        scrollView.post(() -> updateScrollButtons());
    }

    private void updateButtonStates() {
        yukari.setEnabled(currentPosition > 0);
        asagi.setEnabled(currentPosition < 2);
    }



    // Scroll durumuna göre butonları güncelle
    private void updateScrollButtons() {
        int scrollY = scrollView.getScrollY();
        int maxScroll = scrollView.getChildAt(0).getHeight() - scrollView.getHeight();

        // Yukarı buton durumu
        if (scrollY > 0) {
            yukari.setEnabled(true);
            yukari.setAlpha(1f);
        } else {
            yukari.setEnabled(false);
            yukari.setAlpha(0.5f);
        }

        // Aşağı buton durumu
        if (scrollY < maxScroll) {
            asagi.setEnabled(true);
            asagi.setAlpha(1f);
        } else {
            asagi.setEnabled(false);
            asagi.setAlpha(0.5f);
        }
    }
    public static MainActivity getInstance() {
        return instanceRef != null ? instanceRef.get() : null;
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
        ViewGroup.LayoutParams paramsP = paribuRecyclerView.getLayoutParams();
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

            paribuRecyclerView.setLayoutParams(marginParamsP);
            binanceRecyclerView.setLayoutParams(marginParamsB);
            binanceTlRecyclerView.setLayoutParams(marginParamsBT);
        }

        // RecyclerView'ları yeniden çiz
        paribuRecyclerView.requestLayout();
        binanceRecyclerView.requestLayout();
        binanceTlRecyclerView.requestLayout();
    }
    public void startService(View v) {
        saniye.start();
        start.setVisibility(View.GONE);
        updateUI(true);
        paribuAnaText.setVisibility(View.VISIBLE);
        BinanceAnaText.setVisibility(View.VISIBLE);
        BinanceTlAnaText.setVisibility(View.VISIBLE);
        paribuRecyclerView.setVisibility(View.VISIBLE);
        binanceRecyclerView.setVisibility(View.VISIBLE);
        binanceTlRecyclerView.setVisibility(View.VISIBLE);
        asagi.setVisibility(View.VISIBLE);
        yukari.setVisibility(View.VISIBLE);
     /*   paribucheck.setChecked(true);
        tlcheck.setChecked(true);
        binancecheck.setChecked(true);*/
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

        paribuAdapter.updateData(Arrays.asList(metinlerParibu));
        binanceAdapter.updateData(Arrays.asList(metinlerBinance));
        binanceTlAdapter.updateData(Arrays.asList(metinlerBinanceTl));
        updateRecyclerViewHeights(hizli);
        Toast.makeText(getApplicationContext(), "Servis Başlatıldı", Toast.LENGTH_SHORT).show();
        Intent serviceIntent = new Intent(this, UnifiedService.class);
        serviceIntent.putExtra("service_type", "main");
        ContextCompat.startForegroundService(this, serviceIntent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (UnifiedService.isMainServiceRunning()&&calistiMi) {
            updateUI(true);
            scrollView.post(() -> updateScrollButtons());
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (!isFinishing() && calistiMi) {
            updateUI(false);
        }

    }

    private static WeakReference<MainActivity> instanceRef;


    private boolean isVisible = true;
    private void updateUI(boolean show) {
        SharedPreferences prefs = getSharedPreferences("UIState", MODE_PRIVATE);
        prefs.edit().putBoolean("isVisible", show).apply();
        isVisible = show;
        if (show) {
            paribuAnaText.setVisibility(View.VISIBLE);
            BinanceTlAnaText.setVisibility(View.VISIBLE);
            BinanceAnaText.setVisibility(View.VISIBLE);
            paribuRecyclerView.setVisibility(View.VISIBLE);
            binanceRecyclerView.setVisibility(View.VISIBLE);
            binanceTlRecyclerView.setVisibility(View.VISIBLE);
           /* if (paribucheck != null) paribucheck.setChecked(true);
            if (tlcheck != null) tlcheck.setChecked(true);
            if (binancecheck != null) binancecheck.setChecked(true);*/

        } else {

            paribuAnaText.setVisibility(View.GONE);
            BinanceTlAnaText.setVisibility(View.GONE);
            BinanceAnaText.setVisibility(View.GONE);
            paribuRecyclerView.setVisibility(View.GONE);
            binanceRecyclerView.setVisibility(View.GONE);
            binanceTlRecyclerView.setVisibility(View.GONE);

        }
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

    @Override
    protected void onDestroy() {
        // Önce servis durdurulmalı
        Intent serviceIntent = new Intent(this, UnifiedService.class);
        stopService(serviceIntent);

        // Diğer kaynakları temizle
        if (mediaPlayerManager != null) {
            mediaPlayerManager.releaseAll();
        }

        // En son instanceRef null yapılmalı
        instanceRef = null;

        super.onDestroy();
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
            if (paribuAdapter != null) {
                paribuAdapter.updateData(null);
                paribuAdapter = null;
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
    private void fetchData() {
        executorService.execute(() -> {
            JsonObjectRequest request32 = new JsonObjectRequest(0, "https://www.paribu.com/ticker", (JSONObject) null, new Response.Listener<JSONObject>() {
                public void onResponse(JSONObject response) {
                    try {
                        JSONObject jsonArray2 = response.getJSONObject("USDT_TL");
                        tsatis = (float) jsonArray2.getDouble("lowestAsk");
                        for (int i = 0; i < MainActivity.hizli; i++) {
                            paribu[i] = (float) response.getJSONObject(paribuisim[i]).getDouble("highestBid");
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
            mQueue.add(request32);
            JsonArrayRequest request0 = new JsonArrayRequest(0, "https://www.binance.com/api/v3/ticker/bookTicker", (JSONArray) null, new Response.Listener<JSONArray>() {
                public void onResponse(JSONArray response) {
                    try {
                        MainActivity.btc = (float) response.getJSONObject(11).getDouble("askPrice");
                        for (int i = 0; i < MainActivity.hizli; i++) {
                            MainActivity.huobi[i] = (float) response.getJSONObject(MainActivity.indexler[i]).getDouble("askPrice");
                        }
                        for (int i2 = 0; i2 < btctotal; i2++) {
                            MainActivity.huobibtcturk[i2] = (float) response.getJSONObject(MainActivity.indexlerbtcturk[i2]).getDouble("askPrice");
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


            if (BtcTurk.calistiMi) {
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
            }     });
    }
    public void Dot() {
        otuyorMu = false;
        sayac++;

       fetchData();

        for (int i = 0; i < hizli; i++) {
            farklar[i] = ((paribu[i] - (huobi[i] * tsatis)) * 100.0f) / paribu[i];
        }
        if(BtcTurk.calistiMi) {
            for (int i2 = 0; i2 < btctotal; i2++) {
                farklarBtcTurk[i2] = ((btcturk[i2] - (huobibtcturk[i2] * tsatisb)) * 100.0f) / btcturk[i2];
            }
        }

        if (isVisible) {
            setTitle(btc + " | " + tsatis);
        }
        aramaindexler.clear();
        hizli2 = 0;

        if(isVisible){
        mergeSort(farklar, 0, farklar.length - 1, paribu, huobi, paribuisim, isimler, sesSeviyesi, indexler, siralama, oranlar, sesler);}
        int a = 0;
        while (a < hizli) {
            otuyorMu = false;

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

                    mediaPlayerManager.playSound(sesler[a], soundPrefs.getInt("paribu_sound_" + a, 15) / 15.0f);
                    otuyorMu = true;
                } else if (!Sesler.arti && !Sesler.eksi && (farklar[a]/6 < oranlar[a].doubleValue() * (-1.0d) || farklar[a] > oranlar[a].doubleValue())) {

                    SpannableString spannableString4 = metinlerParibu[a];
                    spannableString4.setSpan(fscgreen, 0, spannableString4.length(), 33);
                    SpannableString spannableString5 = metinlerBinance[a];
                    spannableString5.setSpan(yellow, 0, spannableString5.length(), 33);
                    SpannableString spannableString6 = metinlerBinanceTl[a];
                    spannableString6.setSpan(red, 0, spannableString6.length(), 33);

                    mediaPlayerManager.playSound(sesler[a], soundPrefs.getInt("paribu_sound_" + a, 15) / 15.0f);
                    otuyorMu = true;
                } else if (!Sesler.arti && Sesler.eksi && farklar[a]/6 < oranlar[a].doubleValue() * (-1.0d)) {

                    SpannableString spannableString7 = metinlerParibu[a];
                    spannableString7.setSpan(fscgreen, 0, spannableString7.length(), 33);
                    SpannableString spannableString8 = metinlerBinance[a];
                    spannableString8.setSpan(yellow, 0, spannableString8.length(), 33);
                    SpannableString spannableString9 = metinlerBinanceTl[a];
                    spannableString9.setSpan(red, 0, spannableString9.length(), 33);

                    mediaPlayerManager.playSound(sesler[a], soundPrefs.getInt("paribu_sound_" + a, 15) / 15.0f);
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

        }
        if(BtcTurk.calistiMi&&BtcTurk.isVisible){     mergeSortBtcTurk(farklarBtcTurk, 0, farklarBtcTurk.length - 1, btcturk, huobibtcturk,
                btcturkisim, isimlerBtcTurk, sesSeviyesiBtcTurk, indexlerbtcturk, siralamaBtcTurk,
                oranlarbtcturk, seslerBtcTurk);}

        if (calistiMi) {

            if (hizli2 == 0) {
                // Normal veriler için
                paribuAdapter.updateData(Arrays.asList(metinlerParibu));
                binanceAdapter.updateData(Arrays.asList(metinlerBinance));
                binanceTlAdapter.updateData(Arrays.asList(metinlerBinanceTl));
                updateRecyclerViewHeights(hizli);
               // notifyAllAdapters();

            } else {
                metinlerParibuarama = new SpannableString[hizli2];
                metinlerBinancearama = new SpannableString[hizli2];
                metinlerBinanceTlarama = new SpannableString[hizli2];
                int a3 = 0;
                while (a3 < hizli2) {
                    int index = aramaindexler.get(a3).intValue();
                    otuyorMuArama = false;

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

                    } else {
                        String sayac3 = paribu[index] + "";
                        int sayac02 = sayac3.length();
                        int sayi2 = isimler[index].length() + df.format(farklar[index]).length() + sayac02 + " %".length();

                        metinlerParibuarama[a3] = new SpannableString(isimler[index] + paribu[index] + " %" + df.format(farklar[index]) + " || " + huobi[index] * tsatis);
                        metinlerParibuarama[a3].setSpan(fscgreen, 0, sayi2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        metinlerParibuarama[a3].setSpan(darkred, sayi2 + 1, metinlerParibuarama[a3].length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    }
                    a3++;



                }

                paribuAdapter.updateData(Arrays.asList(metinlerParibuarama));
                binanceAdapter.updateData(Arrays.asList(metinlerBinancearama));
                binanceTlAdapter.updateData(Arrays.asList(metinlerBinanceTlarama));
                updateRecyclerViewHeights(hizli2);

            }
        }
    }

  /*  public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.checkboxhizlibinance:
                if (calistiMi) {
                    if (!item.isChecked()) {
                        BinanceAnaText.setVisibility(View.VISIBLE);
                        binanceRecyclerView.setVisibility(View.VISIBLE);
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
                        binanceRecyclerView.setVisibility(View.GONE);
                        BinanceAnaText.setVisibility(View.GONE);
                        item.setChecked(false);
                    }
                }
                return true;
            case R.id.checkboxhizliparibu:
                if (calistiMi) {
                    if (!item.isChecked()) {
                        paribuAnaText.setVisibility(View.VISIBLE);
                        paribuRecyclerView.setVisibility(View.VISIBLE);
                        if (BtcTurk.calistiMi) {
                            BtcTurk.btcturkAnaText.setVisibility(View.VISIBLE);
                            BtcTurk.btcturkL.setVisibility(View.VISIBLE);
                        }
                        item.setChecked(true);
                    } else {
                        paribuAnaText.setVisibility(View.GONE);
                        paribuRecyclerView.setVisibility(View.GONE);
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
    }*/

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(search, menu);
        MenuItem menuItem = menu.findItem(R.id.arama);
       /* paribucheck = menu.findItem(R.id.checkboxhizliparibu);
        tlcheck = menu.findItem(R.id.checkboxhizlitl);
        binancecheck = menu.findItem(R.id.checkboxhizlibinance);*/
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
                    if (MainActivity.this.aramaMetni.length() > 0 && MainActivity.isimler[a].toLowerCase().contains(MainActivity.this.aramaMetni.toLowerCase())) {
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
                    paribuAdapter.updateData(Arrays.asList(metinlerParibu));
                    binanceAdapter.updateData(Arrays.asList(metinlerBinance));
                    binanceTlAdapter.updateData(Arrays.asList(metinlerBinanceTl));
                    updateRecyclerViewHeights(hizli);
                }
                return false;
            }
        });
        return true;
    }

}