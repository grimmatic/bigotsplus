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
    public static MediaPlayer saniye;

    public static EditText dot;
    public static int MainservisSayac;
    static ArrayList<Integer> aramaindexler;
    static String binanceDuyuruText;
    static String binanceGeciciDuyuruText;
    static ListView binanceL;
    static ListView binanceTlL;
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
    static ListView paribuL;
    static String[] paribuisim;
    static int[] sesSeviyesi;
    public static int[] sesSeviyesiBtcTurk;
    static int[] siralama;
    static int[] siralamaBtcTurk;
    static int[] sesler;
    public static int[] seslerBtcTurk;
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
    private static MainActivity instance;
    private MediaPlayerManager mediaPlayerManager;
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
        saniye = MediaPlayer.create(this, R.raw.saniye);
        mediaPlayerManager = MediaPlayerManager.getInstance(this);


        sesler[0] = SOUND_DOT;
        sesler[1] =SOUND_AVAX;
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
        seslerBtcTurk[55] = SOUND_RAD;
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
        seslerBtcTurk[70] = SOUND_RLC;
        seslerBtcTurk[71] = SOUND_ARPA;
        seslerBtcTurk[72] = SOUND_LDO;
        seslerBtcTurk[73] = SOUND_SUSHI;
        seslerBtcTurk[74] = SOUND_MAGIC;
        seslerBtcTurk[75] = SOUND_JASMY;
        seslerBtcTurk[76] = SOUND_ZRX;
        seslerBtcTurk[77] = SOUND_PEPE;
        seslerBtcTurk[78] = SOUND_NMR;
        seslerBtcTurk[79] = SOUND_FLOKI;
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
                                        mediaPlayerManager.updateVolume(sesler[i3], progress / 15.0f);
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
                                    mediaPlayerManager.updateVolume(sesler[i3], progress / 15.0f);
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

    public void updateVolume(int soundResourceId, float volume) {
        if (mediaPlayerManager != null) {
            mediaPlayerManager.updateVolume(soundResourceId, volume);
        }
    }

    public static MainActivity getInstance() {
        return instance;
    }
    public void startService(View v) {
        saniye.start();
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
    @Override
    protected void onDestroy() {
        if (mediaPlayerManager != null) {
            mediaPlayerManager.releaseAll();
        }
        super.onDestroy();
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
                    mediaPlayerManager.playSound(sesler[a], sesSeviyesi[a] / 15.0f);
                    otuyorMu = true;
                } else if (!Sesler.arti && !Sesler.eksi && (farklar[a] < oranlar[a].doubleValue() * (-1.0d) || farklar[a] > oranlar[a].doubleValue())) {
                    SpannableString spannableString4 = metinlerParibu[a];
                    spannableString4.setSpan(fscgreen, 0, spannableString4.length(), 33);
                    SpannableString spannableString5 = metinlerBinance[a];
                    spannableString5.setSpan(yellow, 0, spannableString5.length(), 33);
                    SpannableString spannableString6 = metinlerBinanceTl[a];
                    spannableString6.setSpan(red, 0, spannableString6.length(), 33);
                    mediaPlayerManager.playSound(sesler[a], sesSeviyesi[a] / 15.0f);
                    otuyorMu = true;
                } else if (!Sesler.arti && Sesler.eksi && farklar[a] < oranlar[a].doubleValue() * (-1.0d)) {
                    SpannableString spannableString7 = metinlerParibu[a];
                    spannableString7.setSpan(fscgreen, 0, spannableString7.length(), 33);
                    SpannableString spannableString8 = metinlerBinance[a];
                    spannableString8.setSpan(yellow, 0, spannableString8.length(), 33);
                    SpannableString spannableString9 = metinlerBinanceTl[a];
                    spannableString9.setSpan(red, 0, spannableString9.length(), 33);
                    mediaPlayerManager.playSound(sesler[a], sesSeviyesi[a] / 15.0f);
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