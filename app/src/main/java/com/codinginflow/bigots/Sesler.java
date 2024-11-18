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

/* loaded from: classes3.dex */
public class Sesler extends AppCompatActivity {
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

    /* renamed from: artı, reason: contains not printable characters */
    public static boolean arti = false;
    public static boolean eksi = false;
    static int toplamsesler = 120;
    static SeekBar[] seeks = new SeekBar[120];
    static MediaPlayer[] sesler = new MediaPlayer[120];

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Ayarlar");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sesler);
        this.kontrola = (CheckBox) findViewById(R.id.artı);
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
        sesler[0] = MainActivity.dots;
        sesler[1] = MainActivity.avax;
        sesler[2] = MainActivity.eos;
        sesler[3] = MainActivity.tron;
        sesler[4] = MainActivity.xrp;
        sesler[5] = MainActivity.xlm;
        sesler[6] = MainActivity.ltc;
        sesler[7] = MainActivity.ont;
        sesler[8] = MainActivity.hot;
        sesler[9] = MainActivity.ada;
        sesler[10] = MainActivity.dogem;
        sesler[11] = MainActivity.atom;
        sesler[12] = MainActivity.btt;
        sesler[13] = MainActivity.neo;
        sesler[14] = MainActivity.etherfi;
        sesler[15] = MainActivity.rvn;
        sesler[16] = MainActivity.xtz;
        sesler[17] = MainActivity.bat;
        sesler[18] = MainActivity.chz;
        sesler[19] = MainActivity.uni;
        sesler[20] = MainActivity.bal;
        sesler[21] = MainActivity.aave;
        sesler[22] = MainActivity.link;
        sesler[23] = MainActivity.maker;
        sesler[24] = MainActivity.w;
        sesler[25] = MainActivity.reef;
        sesler[26] = MainActivity.lrc;
        sesler[27] = MainActivity.band;
        sesler[28] = MainActivity.algo;
        sesler[29] = MainActivity.zil;
        sesler[30] = MainActivity.grt;
        sesler[31] = MainActivity.enj;
        sesler[32] = MainActivity.theta;
        sesler[33] = MainActivity.matic;
        sesler[34] = MainActivity.oxt;
        sesler[35] = MainActivity.psg;
        sesler[36] = MainActivity.atm;
        sesler[37] = MainActivity.asr;
        sesler[38] = MainActivity.bar;
        sesler[39] = MainActivity.juv;
        sesler[40] = MainActivity.acm;
        sesler[41] = MainActivity.crv;
        sesler[42] = MainActivity.ogn;
        sesler[43] = MainActivity.mana;
        sesler[44] = MainActivity.iota;
        sesler[45] = MainActivity.sol;
        sesler[46] = MainActivity.mina;
        sesler[47] = MainActivity.ape;
        sesler[48] = MainActivity.vet;
        sesler[49] = MainActivity.ankr;
        sesler[50] = MainActivity.shib;
        sesler[51] = MainActivity.lpt;
        sesler[52] = MainActivity.inj;
        sesler[53] = MainActivity.icp;
        sesler[54] = MainActivity.ftm;
        sesler[55] = MainActivity.axs;
        sesler[56] = MainActivity.bch;
        sesler[57] = MainActivity.ens;
        sesler[58] = MainActivity.alice;
        sesler[59] = MainActivity.tlm;
        sesler[60] = MainActivity.sand;
        sesler[61] = MainActivity.audio;
        sesler[62] = MainActivity.clv;
        sesler[63] = MainActivity.gala;
        sesler[64] = MainActivity.vanry;
        sesler[65] = MainActivity.uma;
        sesler[66] = MainActivity.storj;
        sesler[67] = MainActivity.city;
        sesler[68] = MainActivity.rad;
        sesler[69] = MainActivity.inch;
        sesler[70] = MainActivity.comp;
        sesler[71] = MainActivity.snx;
        sesler[72] = MainActivity.fet;
        sesler[73] = MainActivity.spell;
        sesler[74] = MainActivity.stg;
        sesler[75] = MainActivity.qnt;
        sesler[76] = MainActivity.apt;
        sesler[77] = MainActivity.rndr;
        sesler[78] = MainActivity.arb;
        sesler[79] = MainActivity.sui;
        sesler[80] = MainActivity.ctsi;
        sesler[81] = MainActivity.op;
        sesler[82] = MainActivity.pepe;
        sesler[83] = MainActivity.ocean;
        sesler[84] = MainActivity.rlc;
        sesler[85] = MainActivity.skl;
        sesler[86] = MainActivity.agix;
        sesler[87] = MainActivity.tia;
        sesler[88] = MainActivity.dash;
        sesler[89] = MainActivity.fil;
        sesler[90] = MainActivity.stx;
        sesler[91] = MainActivity.amp;
        sesler[92] = MainActivity.lunc;
        sesler[93] = MainActivity.bnt;
        sesler[94] = MainActivity.glm;
        sesler[95] = MainActivity.luna;
        sesler[96] = MainActivity.paxg;
        sesler[97] = MainActivity.imx;
        sesler[98] = MainActivity.cvc;
        sesler[99] = MainActivity.etc;
        sesler[100] = MainActivity.flow;
        sesler[101] = MainActivity.mask;
        sesler[102] = MainActivity.api3;
        sesler[103] = MainActivity.t;
        sesler[104] = MainActivity.arpa;
        sesler[105] = MainActivity.ldo;
        sesler[106] = MainActivity.sushi;
        sesler[107] = MainActivity.magic;
        sesler[108] = MainActivity.sei;
        sesler[109] = MainActivity.rdnt;
        sesler[110] = MainActivity.floki;
        sesler[111] = MainActivity.dydx;
        sesler[112] = MainActivity.jasmy;
        sesler[113] = MainActivity.zrx;
        sesler[114] = MainActivity.nmr;
        sesler[115] = MainActivity.blur;
        sesler[116] = MainActivity.bonk;
        sesler[117] = MainActivity.arkm;
        sesler[118] = MainActivity.tnsr;
        sesler[119] = MainActivity.axl;
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
        this.seektum.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.codinginflow.bigots.Sesler.6
            int progressChangedValue = 0;

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                this.progressChangedValue = progress;
                for (int i = 0; i < Sesler.toplamsesler; i++) {
                    Sesler.sesler[i].setVolume(progress / 15.0f, progress / 15.0f);
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
        this.seekveri.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.codinginflow.bigots.Sesler.7
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
}