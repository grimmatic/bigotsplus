package com.codinginflow.bigots;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class Sesler extends AppCompatActivity {
    private MediaPlayerManager mediaPlayerManager;
    FloatingActionButton asagi;
    AudioManager audiomanager;
    FloatingActionButton geri;
    private CheckBox kontrola;
    private CheckBox kontrole;
    TextView saniye;
    SeekBar seektum;
    SeekBar seekveri;
    NestedScrollView sesEkran;
    FloatingActionButton yukari;
    public static float saniyed = 2000.0f;


    public static boolean arti = false;
    public static boolean eksi = false;
    static int toplamsesler = 139;
    static int[] tumSesler = new int[toplamsesler];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Ayarlar");
        super.onCreate(savedInstanceState);
        MainActivity.initSoundPrefs(this); // SharedPreferences'ı initialize et
        mediaPlayerManager = MediaPlayerManager.getInstance(this);
        setContentView(R.layout.sesler);
        this.kontrola = (CheckBox) findViewById(R.id.arti);
        this.kontrole = (CheckBox) findViewById(R.id.eksi);
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        this.audiomanager = audioManager;
        int max = audioManager.getStreamMaxVolume(3);
        this.geri = (FloatingActionButton) findViewById(R.id.floatactiongeri);
        this.yukari = (FloatingActionButton) findViewById(R.id.floatactionyukari);
        this.asagi = (FloatingActionButton) findViewById(R.id.floatactionasagi);
        this.sesEkran = (NestedScrollView) findViewById(R.id.scrollView2);
        this.kontrola.setChecked(true);
        this.saniye = (TextView) findViewById(R.id.saniye);
        this.seekveri = (SeekBar) findViewById(R.id.seekBar32);
        this.seektum = (SeekBar) findViewById(R.id.seekBar30);
        tumSesler[0] = MainActivity.SOUND_DOT;
        tumSesler[1] = MainActivity.SOUND_AVAX;
        tumSesler[2] = MainActivity.SOUND_EOS;
        tumSesler[3] = MainActivity.SOUND_TRON;
        tumSesler[4] = MainActivity.SOUND_RIPPLE;
        tumSesler[5] = MainActivity.SOUND_SITELLAR;
        tumSesler[6] = MainActivity.SOUND_LITE;
        tumSesler[7] = MainActivity.SOUND_ONT;
        tumSesler[8] = MainActivity.SOUND_HOT;
        tumSesler[9] = MainActivity.SOUND_ADA;
        tumSesler[10] = MainActivity.SOUND_DOGE;
        tumSesler[11] = MainActivity.SOUND_ATOM;
        tumSesler[12] = MainActivity.SOUND_BTT;
        tumSesler[13] = MainActivity.SOUND_NEO;
        tumSesler[14] = MainActivity.SOUND_ETHERFI;
        tumSesler[15] = MainActivity.SOUND_RVN;
        tumSesler[16] = MainActivity.SOUND_XTZ;
        tumSesler[17] = MainActivity.SOUND_BAT;
        tumSesler[18] = MainActivity.SOUND_CHZ;
        tumSesler[19] = MainActivity.SOUND_UNI;
        tumSesler[20] = MainActivity.SOUND_BAL;
        tumSesler[21] = MainActivity.SOUND_AAVE;
        tumSesler[22] = MainActivity.SOUND_LINK;
        tumSesler[23] = MainActivity.SOUND_MAKER;
        tumSesler[24] = MainActivity.SOUND_W;
        tumSesler[25] = MainActivity.SOUND_BEAM;
        tumSesler[26] = MainActivity.SOUND_LRC;
        tumSesler[27] = MainActivity.SOUND_BAND;
        tumSesler[28] = MainActivity.SOUND_ALGO;
        tumSesler[29] = MainActivity.SOUND_ZIL;
        tumSesler[30] = MainActivity.SOUND_GRT;
        tumSesler[31] = MainActivity.SOUND_ENJ;
        tumSesler[32] = MainActivity.SOUND_THETA;
        tumSesler[33] = MainActivity.SOUND_MATIC;
        tumSesler[34] = MainActivity.SOUND_OXT;
        tumSesler[35] = MainActivity.SOUND_PSG;
        tumSesler[36] = MainActivity.SOUND_ATM;
        tumSesler[37] = MainActivity.SOUND_ASR;
        tumSesler[38] = MainActivity.SOUND_BAR;
        tumSesler[39] = MainActivity.SOUND_JUV;
        tumSesler[40] = MainActivity.SOUND_ACM;
        tumSesler[41] = MainActivity.SOUND_CRV;
        tumSesler[42] = MainActivity.SOUND_OGN;
        tumSesler[43] = MainActivity.SOUND_MANA;
        tumSesler[44] = MainActivity.SOUND_IOTA;
        tumSesler[45] = MainActivity.SOUND_SOL;
        tumSesler[46] = MainActivity.SOUND_MINA;
        tumSesler[47] = MainActivity.SOUND_APE;
        tumSesler[48] = MainActivity.SOUND_VET;
        tumSesler[49] = MainActivity.SOUND_ANKR;
        tumSesler[50] = MainActivity.SOUND_SHIB;
        tumSesler[51] = MainActivity.SOUND_LPT;
        tumSesler[52] = MainActivity.SOUND_INJ;
        tumSesler[53] = MainActivity.SOUND_ICP;
        tumSesler[54] = MainActivity.SOUND_FTM;
        tumSesler[55] = MainActivity.SOUND_AXS;
        tumSesler[56] = MainActivity.SOUND_BCH;
        tumSesler[57] = MainActivity.SOUND_ENS;
        tumSesler[58] = MainActivity.SOUND_ALICE;
        tumSesler[59] = MainActivity.SOUND_TLM;
        tumSesler[60] = MainActivity.SOUND_SAND;
        tumSesler[61] = MainActivity.SOUND_AUDIO;
        tumSesler[62] = MainActivity.SOUND_CLV;
        tumSesler[63] = MainActivity.SOUND_GALA;
        tumSesler[64] = MainActivity.SOUND_VANRY;
        tumSesler[65] = MainActivity.SOUND_UMA;
        tumSesler[66] = MainActivity.SOUND_STORJ;
        tumSesler[67] = MainActivity.SOUND_CITY;
        tumSesler[68] = MainActivity.SOUND_RAD;
        tumSesler[69] = MainActivity.SOUND_INCH;
        tumSesler[70] = MainActivity.SOUND_COMP;
        tumSesler[71] = MainActivity.SOUND_SNX;
        tumSesler[72] = MainActivity.SOUND_FET;
        tumSesler[73] = MainActivity.SOUND_SPELL;
        tumSesler[74] = MainActivity.SOUND_STG;
        tumSesler[75] = MainActivity.SOUND_QNT;
        tumSesler[76] = MainActivity.SOUND_APT;
        tumSesler[77] = MainActivity.SOUND_RNDR;
        tumSesler[78] = MainActivity.SOUND_ARB;
        tumSesler[79] = MainActivity.SOUND_SUI;
        tumSesler[80] = MainActivity.SOUND_CTSI;
        tumSesler[81] = MainActivity.SOUND_OP;
        tumSesler[82] = MainActivity.SOUND_PEPE;
        tumSesler[83] = MainActivity.SOUND_DYM;
        tumSesler[84] = MainActivity.SOUND_RLC;
        tumSesler[85] = MainActivity.SOUND_SKL;
        tumSesler[86] = MainActivity.SOUND_JOE;
        tumSesler[87] = MainActivity.SOUND_TIA;
        tumSesler[88] = MainActivity.SOUND_DASH;
        tumSesler[89] = MainActivity.SOUND_FIL;
        tumSesler[90] = MainActivity.SOUND_STX;
        tumSesler[91] = MainActivity.SOUND_AMP;
        tumSesler[92] = MainActivity.SOUND_JTO;
        tumSesler[93] = MainActivity.SOUND_BNT;
        tumSesler[94] = MainActivity.SOUND_GLM;
        tumSesler[95] = MainActivity.SOUND_LUNA;
        tumSesler[96] = MainActivity.SOUND_PAXG;
        tumSesler[97] = MainActivity.SOUND_IMX;
        tumSesler[98] = MainActivity.SOUND_CVC;
        tumSesler[99] = MainActivity.SOUND_ETC;
        tumSesler[100] = MainActivity.SOUND_FLOW;
        tumSesler[101] = MainActivity.SOUND_MASK;
        tumSesler[102] = MainActivity.SOUND_API3;
        tumSesler[103] = MainActivity.SOUND_T;
        tumSesler[104] = MainActivity.SOUND_ARPA;
        tumSesler[105] = MainActivity.SOUND_LDO;
        tumSesler[106] = MainActivity.SOUND_SUSHI;
        tumSesler[107] = MainActivity.SOUND_MAGIC;
        tumSesler[108] = MainActivity.SOUND_SEI;
        tumSesler[109] = MainActivity.SOUND_RDNT;
        tumSesler[110] = MainActivity.SOUND_FLOKI;
        tumSesler[111] = MainActivity.SOUND_DYDX;
        tumSesler[112] = MainActivity.SOUND_JASMY;
        tumSesler[113] = MainActivity.SOUND_ZRX;
        tumSesler[114] = MainActivity.SOUND_NMR;
        tumSesler[115] = MainActivity.SOUND_BLUR;
        tumSesler[116] = MainActivity.SOUND_BONK;
        tumSesler[117] = MainActivity.SOUND_ARKM;
        tumSesler[118] = MainActivity.SOUND_TNSR;
        tumSesler[120] = MainActivity.SOUND_AXL;
        tumSesler[121] = MainActivity.SOUND_JUP;
        tumSesler[122] = MainActivity.SOUND_MANTA;
        tumSesler[123] = MainActivity.SOUND_MEME;
        tumSesler[124] = MainActivity.SOUND_PENDLE;
        tumSesler[125] = MainActivity.SOUND_PYTH;
        tumSesler[126] = MainActivity.SOUND_STRK;
        tumSesler[127] = MainActivity.SOUND_EIGEN;
        tumSesler[128] = MainActivity.SOUND_RAY;
        tumSesler[129] = MainActivity.SOUND_WIF;
        tumSesler[130] = MainActivity.SOUND_IO;
        tumSesler[131] = MainActivity.SOUND_AEVO;
        tumSesler[132] = MainActivity.SOUND_ENA;
        tumSesler[133] = MainActivity.SOUND_ZK;
        tumSesler[134] = MainActivity.SOUND_ALT;
        tumSesler[135] = MainActivity.SOUND_SYN;
        tumSesler[136] = MainActivity.SOUND_PDA;
        tumSesler[137] = MainActivity.SOUND_ZRO;
        tumSesler[138] = MainActivity.SOUND_SUPER0;
        this.seektum.setMax(max);
        this.seekveri.setMax(15);
        this.seektum.setProgress(max);
        this.seekveri.setProgress(2);
        Drawable tempDrawable = getResources().getDrawable(R.drawable.daire0);
        LayerDrawable bubble = (LayerDrawable) tempDrawable;
        GradientDrawable solidColor = (GradientDrawable) bubble.findDrawableByLayerId(R.id.outerRectangle);
        solidColor.setColor(Color.rgb(0, 145, 0));
        this.seekveri.setThumb(tempDrawable);
        Drawable tempDrawable1 = getResources().getDrawable(R.drawable.daire0);
        LayerDrawable bubble1 = (LayerDrawable) tempDrawable1;
        GradientDrawable solidColor1 = (GradientDrawable) bubble1.findDrawableByLayerId(R.id.outerRectangle);
        solidColor1.setColor(Color.rgb(0, 255, 0));
        this.seektum.setThumb(tempDrawable1);
        this.kontrola.setOnClickListener(new View.OnClickListener() { // from class: com.codinginflow.bigots.Sesler.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (Sesler.this.kontrola.isChecked()) {
                    Sesler.arti = true;
                    Sesler.this.kontrole.setChecked(false);
                    Sesler.eksi = false;
                    return;
                }
                Sesler.arti = false;
            }
        });
        this.kontrole.setOnClickListener(new View.OnClickListener() { // from class: com.codinginflow.bigots.Sesler.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (Sesler.this.kontrole.isChecked()) {
                    Sesler.eksi = true;
                    Sesler.this.kontrola.setChecked(false);
                    Sesler.arti = false;
                    return;
                }
                Sesler.eksi = false;
            }
        });
        this.yukari.setOnClickListener(new View.OnClickListener() { // from class: com.codinginflow.bigots.Sesler.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                Sesler.this.sesEkran.scrollTo(0, 0);
            }
        });
        this.asagi.setOnClickListener(new View.OnClickListener() { // from class: com.codinginflow.bigots.Sesler.4
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                Sesler.this.sesEkran.scrollTo(990000, 990000);
            }
        });
        this.geri.setOnClickListener(new View.OnClickListener() { // from class: com.codinginflow.bigots.Sesler.5
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                Intent openMainActivity = new Intent(Sesler.this, (Class<?>) MainActivity.class);
                openMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                Sesler.this.startActivityIfNeeded(openMainActivity, 0);
            }
        });
        this.seektum.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                this.progressChangedValue = progress;
                MainActivity.updateAllSoundLevels(progress);
                for (int i = 0; i < Sesler.toplamsesler; i++) {
                    if (tumSesler[i] != 0) { // 0 olmayan (geçerli) ses ID'leri için
                        mediaPlayerManager.updateVolume(tumSesler[i], progress / 15.0f);
                    }
                }
                if (progress == 0) {
                    Drawable tempDrawable2 = Sesler.this.getResources().getDrawable(R.drawable.daire0);
                    LayerDrawable bubble2 = (LayerDrawable) tempDrawable2;
                    GradientDrawable solidColor2 = (GradientDrawable) bubble2.findDrawableByLayerId(R.id.outerRectangle);
                    solidColor2.setColor(Color.rgb(255, 0, 0));
                    Sesler.this.seektum.setThumb(tempDrawable2);
                } else if (progress == 1) {
                    Drawable tempDrawable3 = Sesler.this.getResources().getDrawable(R.drawable.daire0);
                    LayerDrawable bubble3 = (LayerDrawable) tempDrawable3;
                    GradientDrawable solidColor3 = (GradientDrawable) bubble3.findDrawableByLayerId(R.id.outerRectangle);
                    solidColor3.setColor(Color.rgb(190, 0, 0));
                    Sesler.this.seektum.setThumb(tempDrawable3);
                } else if (progress == 2) {
                    Drawable tempDrawable4 = Sesler.this.getResources().getDrawable(R.drawable.daire0);
                    LayerDrawable bubble4 = (LayerDrawable) tempDrawable4;
                    GradientDrawable solidColor4 = (GradientDrawable) bubble4.findDrawableByLayerId(R.id.outerRectangle);
                    solidColor4.setColor(Color.rgb(125, 0, 0));
                    Sesler.this.seektum.setThumb(tempDrawable4);
                } else if (progress == 3) {
                    Drawable tempDrawable5 = Sesler.this.getResources().getDrawable(R.drawable.daire0);
                    LayerDrawable bubble5 = (LayerDrawable) tempDrawable5;
                    GradientDrawable solidColor5 = (GradientDrawable) bubble5.findDrawableByLayerId(R.id.outerRectangle);
                    solidColor5.setColor(Color.rgb(64, 45, 0));
                    Sesler.this.seektum.setThumb(tempDrawable5);
                } else if (progress == 4) {
                    Drawable tempDrawable6 = Sesler.this.getResources().getDrawable(R.drawable.daire0);
                    LayerDrawable bubble6 = (LayerDrawable) tempDrawable6;
                    GradientDrawable solidColor6 = (GradientDrawable) bubble6.findDrawableByLayerId(R.id.outerRectangle);
                    solidColor6.setColor(Color.rgb(47, 54, 0));
                    Sesler.this.seektum.setThumb(tempDrawable6);
                } else if (progress == 5) {
                    Drawable tempDrawable7 = Sesler.this.getResources().getDrawable(R.drawable.daire0);
                    LayerDrawable bubble7 = (LayerDrawable) tempDrawable7;
                    GradientDrawable solidColor7 = (GradientDrawable) bubble7.findDrawableByLayerId(R.id.outerRectangle);
                    solidColor7.setColor(Color.rgb(37, 54, 0));
                    Sesler.this.seektum.setThumb(tempDrawable7);
                } else if (progress == 6) {
                    Drawable tempDrawable8 = Sesler.this.getResources().getDrawable(R.drawable.daire0);
                    LayerDrawable bubble8 = (LayerDrawable) tempDrawable8;
                    GradientDrawable solidColor8 = (GradientDrawable) bubble8.findDrawableByLayerId(R.id.outerRectangle);
                    solidColor8.setColor(Color.rgb(25, 54, 0));
                    Sesler.this.seektum.setThumb(tempDrawable8);
                } else if (progress == 7) {
                    Drawable tempDrawable9 = Sesler.this.getResources().getDrawable(R.drawable.daire0);
                    LayerDrawable bubble9 = (LayerDrawable) tempDrawable9;
                    GradientDrawable solidColor9 = (GradientDrawable) bubble9.findDrawableByLayerId(R.id.outerRectangle);
                    solidColor9.setColor(Color.rgb(15, 54, 0));
                    Sesler.this.seektum.setThumb(tempDrawable9);
                } else if (progress == 8) {
                    Drawable tempDrawable10 = Sesler.this.getResources().getDrawable(R.drawable.daire0);
                    LayerDrawable bubble10 = (LayerDrawable) tempDrawable10;
                    GradientDrawable solidColor10 = (GradientDrawable) bubble10.findDrawableByLayerId(R.id.outerRectangle);
                    solidColor10.setColor(Color.rgb(5, 61, 0));
                    Sesler.this.seektum.setThumb(tempDrawable10);
                } else if (progress == 9) {
                    Drawable tempDrawable11 = Sesler.this.getResources().getDrawable(R.drawable.daire0);
                    LayerDrawable bubble11 = (LayerDrawable) tempDrawable11;
                    GradientDrawable solidColor11 = (GradientDrawable) bubble11.findDrawableByLayerId(R.id.outerRectangle);
                    solidColor11.setColor(Color.rgb(0, 70, 0));
                    Sesler.this.seektum.setThumb(tempDrawable11);
                } else if (progress == 10) {
                    Drawable tempDrawable12 = Sesler.this.getResources().getDrawable(R.drawable.daire0);
                    LayerDrawable bubble12 = (LayerDrawable) tempDrawable12;
                    GradientDrawable solidColor12 = (GradientDrawable) bubble12.findDrawableByLayerId(R.id.outerRectangle);
                    solidColor12.setColor(Color.rgb(0, 85, 0));
                    Sesler.this.seektum.setThumb(tempDrawable12);
                } else if (progress == 11) {
                    Drawable tempDrawable13 = Sesler.this.getResources().getDrawable(R.drawable.daire0);
                    LayerDrawable bubble13 = (LayerDrawable) tempDrawable13;
                    GradientDrawable solidColor13 = (GradientDrawable) bubble13.findDrawableByLayerId(R.id.outerRectangle);
                    solidColor13.setColor(Color.rgb(0, 100, 0));
                    Sesler.this.seektum.setThumb(tempDrawable13);
                } else if (progress == 12) {
                    Drawable tempDrawable14 = Sesler.this.getResources().getDrawable(R.drawable.daire0);
                    LayerDrawable bubble14 = (LayerDrawable) tempDrawable14;
                    GradientDrawable solidColor14 = (GradientDrawable) bubble14.findDrawableByLayerId(R.id.outerRectangle);
                    solidColor14.setColor(Color.rgb(0, 120, 0));
                    Sesler.this.seektum.setThumb(tempDrawable14);
                } else if (progress == 13) {
                    Drawable tempDrawable15 = Sesler.this.getResources().getDrawable(R.drawable.daire0);
                    LayerDrawable bubble15 = (LayerDrawable) tempDrawable15;
                    GradientDrawable solidColor15 = (GradientDrawable) bubble15.findDrawableByLayerId(R.id.outerRectangle);
                    solidColor15.setColor(Color.rgb(0, 145, 0));
                    Sesler.this.seektum.setThumb(tempDrawable15);
                } else if (progress == 14) {
                    Drawable tempDrawable16 = Sesler.this.getResources().getDrawable(R.drawable.daire0);
                    LayerDrawable bubble16 = (LayerDrawable) tempDrawable16;
                    GradientDrawable solidColor16 = (GradientDrawable) bubble16.findDrawableByLayerId(R.id.outerRectangle);
                    solidColor16.setColor(Color.rgb(0, 196, 0));
                    Sesler.this.seektum.setThumb(tempDrawable16);
                } else if (progress == 15) {
                    Drawable tempDrawable17 = Sesler.this.getResources().getDrawable(R.drawable.daire0);
                    LayerDrawable bubble17 = (LayerDrawable) tempDrawable17;
                    GradientDrawable solidColor17 = (GradientDrawable) bubble17.findDrawableByLayerId(R.id.outerRectangle);
                    solidColor17.setColor(Color.rgb(0, 255, 0));
                    Sesler.this.seektum.setThumb(tempDrawable17);
                }
                Sesler.this.seektum.setProgress(progress);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(Sesler.this, "Ses: %" + Math.round(this.progressChangedValue * 6.66d),  Toast.LENGTH_SHORT).show();
            }
        });
        this.seekveri.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress == 15) {
                    Sesler.this.saniye.setText("15");
                    Sesler.saniyed = 15000.0f;
                    Drawable tempDrawable2 = Sesler.this.getResources().getDrawable(R.drawable.daire0);
                    LayerDrawable bubble2 = (LayerDrawable) tempDrawable2;
                    GradientDrawable solidColor2 = (GradientDrawable) bubble2.findDrawableByLayerId(R.id.outerRectangle);
                    solidColor2.setColor(Color.rgb(255, 0, 0));
                    Sesler.this.seekveri.setThumb(tempDrawable2);
                } else if (progress == 14) {
                    Sesler.this.saniye.setText("10");
                    Sesler.saniyed = 10000.0f;
                    Drawable tempDrawable3 = Sesler.this.getResources().getDrawable(R.drawable.daire0);
                    LayerDrawable bubble3 = (LayerDrawable) tempDrawable3;
                    GradientDrawable solidColor3 = (GradientDrawable) bubble3.findDrawableByLayerId(R.id.outerRectangle);
                    solidColor3.setColor(Color.rgb(190, 0, 0));
                    Sesler.this.seekveri.setThumb(tempDrawable3);
                } else if (progress == 13) {
                    Sesler.this.saniye.setText("8.5");
                    Sesler.saniyed = 8500.0f;
                    Drawable tempDrawable4 = Sesler.this.getResources().getDrawable(R.drawable.daire0);
                    LayerDrawable bubble4 = (LayerDrawable) tempDrawable4;
                    GradientDrawable solidColor4 = (GradientDrawable) bubble4.findDrawableByLayerId(R.id.outerRectangle);
                    solidColor4.setColor(Color.rgb(125, 0, 0));
                    Sesler.this.seekveri.setThumb(tempDrawable4);
                } else if (progress == 12) {
                    Sesler.this.saniye.setText("7");
                    Sesler.saniyed = 7000.0f;
                    Drawable tempDrawable5 = Sesler.this.getResources().getDrawable(R.drawable.daire0);
                    LayerDrawable bubble5 = (LayerDrawable) tempDrawable5;
                    GradientDrawable solidColor5 = (GradientDrawable) bubble5.findDrawableByLayerId(R.id.outerRectangle);
                    solidColor5.setColor(Color.rgb(64, 45, 0));
                    Sesler.this.seekveri.setThumb(tempDrawable5);
                } else if (progress == 11) {
                    Sesler.this.saniye.setText("6.5");
                    Sesler.saniyed = 6500.0f;
                    Drawable tempDrawable6 = Sesler.this.getResources().getDrawable(R.drawable.daire0);
                    LayerDrawable bubble6 = (LayerDrawable) tempDrawable6;
                    GradientDrawable solidColor6 = (GradientDrawable) bubble6.findDrawableByLayerId(R.id.outerRectangle);
                    solidColor6.setColor(Color.rgb(47, 54, 0));
                    Sesler.this.seekveri.setThumb(tempDrawable6);
                } else if (progress == 10) {
                    Sesler.this.saniye.setText("6");
                    Sesler.saniyed = 6000.0f;
                    Drawable tempDrawable7 = Sesler.this.getResources().getDrawable(R.drawable.daire0);
                    LayerDrawable bubble7 = (LayerDrawable) tempDrawable7;
                    GradientDrawable solidColor7 = (GradientDrawable) bubble7.findDrawableByLayerId(R.id.outerRectangle);
                    solidColor7.setColor(Color.rgb(37, 54, 0));
                    Sesler.this.seekveri.setThumb(tempDrawable7);
                } else if (progress == 9) {
                    Sesler.this.saniye.setText("5.5");
                    Sesler.saniyed = 5500.0f;
                    Drawable tempDrawable8 = Sesler.this.getResources().getDrawable(R.drawable.daire0);
                    LayerDrawable bubble8 = (LayerDrawable) tempDrawable8;
                    GradientDrawable solidColor8 = (GradientDrawable) bubble8.findDrawableByLayerId(R.id.outerRectangle);
                    solidColor8.setColor(Color.rgb(25, 54, 0));
                    Sesler.this.seekveri.setThumb(tempDrawable8);
                } else if (progress == 8) {
                    Sesler.this.saniye.setText("5");
                    Sesler.saniyed = 5000.0f;
                    Drawable tempDrawable9 = Sesler.this.getResources().getDrawable(R.drawable.daire0);
                    LayerDrawable bubble9 = (LayerDrawable) tempDrawable9;
                    GradientDrawable solidColor9 = (GradientDrawable) bubble9.findDrawableByLayerId(R.id.outerRectangle);
                    solidColor9.setColor(Color.rgb(15, 54, 0));
                    Sesler.this.seekveri.setThumb(tempDrawable9);
                } else if (progress == 7) {
                    Sesler.this.saniye.setText("4.5");
                    Sesler.saniyed = 4500.0f;
                    Drawable tempDrawable10 = Sesler.this.getResources().getDrawable(R.drawable.daire0);
                    LayerDrawable bubble10 = (LayerDrawable) tempDrawable10;
                    GradientDrawable solidColor10 = (GradientDrawable) bubble10.findDrawableByLayerId(R.id.outerRectangle);
                    solidColor10.setColor(Color.rgb(5, 61, 0));
                    Sesler.this.seekveri.setThumb(tempDrawable10);
                } else if (progress == 6) {
                    Sesler.this.saniye.setText("4");
                    Sesler.saniyed = 4000.0f;
                    Drawable tempDrawable11 = Sesler.this.getResources().getDrawable(R.drawable.daire0);
                    LayerDrawable bubble11 = (LayerDrawable) tempDrawable11;
                    GradientDrawable solidColor11 = (GradientDrawable) bubble11.findDrawableByLayerId(R.id.outerRectangle);
                    solidColor11.setColor(Color.rgb(0, 70, 0));
                    Sesler.this.seekveri.setThumb(tempDrawable11);
                } else if (progress == 5) {
                    Sesler.this.saniye.setText("3.5");
                    Sesler.saniyed = 3500.0f;
                    Drawable tempDrawable12 = Sesler.this.getResources().getDrawable(R.drawable.daire0);
                    LayerDrawable bubble12 = (LayerDrawable) tempDrawable12;
                    GradientDrawable solidColor12 = (GradientDrawable) bubble12.findDrawableByLayerId(R.id.outerRectangle);
                    solidColor12.setColor(Color.rgb(0, 85, 0));
                    Sesler.this.seekveri.setThumb(tempDrawable12);
                } else if (progress == 4) {
                    Sesler.this.saniye.setText("3");
                    Sesler.saniyed = 3000.0f;
                    Drawable tempDrawable13 = Sesler.this.getResources().getDrawable(R.drawable.daire0);
                    LayerDrawable bubble13 = (LayerDrawable) tempDrawable13;
                    GradientDrawable solidColor13 = (GradientDrawable) bubble13.findDrawableByLayerId(R.id.outerRectangle);
                    solidColor13.setColor(Color.rgb(0, 100, 0));
                    Sesler.this.seekveri.setThumb(tempDrawable13);
                } else if (progress == 3) {
                    Sesler.this.saniye.setText("2.5");
                    Sesler.saniyed = 2500.0f;
                    Drawable tempDrawable14 = Sesler.this.getResources().getDrawable(R.drawable.daire0);
                    LayerDrawable bubble14 = (LayerDrawable) tempDrawable14;
                    GradientDrawable solidColor14 = (GradientDrawable) bubble14.findDrawableByLayerId(R.id.outerRectangle);
                    solidColor14.setColor(Color.rgb(0, 120, 0));
                    Sesler.this.seekveri.setThumb(tempDrawable14);
                } else if (progress == 2) {
                    Sesler.this.saniye.setText("2");
                    Sesler.saniyed = 2000.0f;
                    Drawable tempDrawable15 = Sesler.this.getResources().getDrawable(R.drawable.daire0);
                    LayerDrawable bubble15 = (LayerDrawable) tempDrawable15;
                    GradientDrawable solidColor15 = (GradientDrawable) bubble15.findDrawableByLayerId(R.id.outerRectangle);
                    solidColor15.setColor(Color.rgb(0, 145, 0));
                    Sesler.this.seekveri.setThumb(tempDrawable15);
                } else if (progress == 1) {
                    Sesler.this.saniye.setText("1.85");
                    Sesler.saniyed = 1850.0f;
                    Drawable tempDrawable16 = Sesler.this.getResources().getDrawable(R.drawable.daire0);
                    LayerDrawable bubble16 = (LayerDrawable) tempDrawable16;
                    GradientDrawable solidColor16 = (GradientDrawable) bubble16.findDrawableByLayerId(R.id.outerRectangle);
                    solidColor16.setColor(Color.rgb(0, 196, 0));
                    Sesler.this.seekveri.setThumb(tempDrawable16);
                } else if (progress == 0) {
                    Sesler.this.saniye.setText("1.7");
                    Sesler.saniyed = 1700.0f;
                    Drawable tempDrawable17 = Sesler.this.getResources().getDrawable(R.drawable.daire0);
                    LayerDrawable bubble17 = (LayerDrawable) tempDrawable17;
                    GradientDrawable solidColor17 = (GradientDrawable) bubble17.findDrawableByLayerId(R.id.outerRectangle);
                    solidColor17.setColor(Color.rgb(0, 255, 0));
                    Sesler.this.seekveri.setThumb(tempDrawable17);
                }
                Sesler.this.seekveri.setProgress(progress);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(Sesler.this, "Hız (per): " + (Sesler.saniyed / 1000.0f) + " sn",  Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected void onDestroy() {
        if (mediaPlayerManager != null) {
            mediaPlayerManager.releaseAll();
        }
        super.onDestroy();
    }
}