package com.nestle.plantpowermonitoring

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val navy = Color.rgb(6,24,42)
    private val white = Color.rgb(244,248,252)
    private val muted = Color.rgb(169,190,210)
    private val blue = Color.rgb(21,151,255)
    private val green = Color.rgb(33,230,154)
    private val yellow = Color.rgb(255,197,51)
    private val red = Color.rgb(255,75,85)
    private fun dp(v:Int)= (v*resources.displayMetrics.density).toInt()
    private fun text(s:String, size:Number, color:Int=white, bold:Boolean=false)=TextView(this).apply{ text=s; textSize=size.toFloat(); setTextColor(color); gravity=Gravity.CENTER_VERTICAL; if(bold)setTypeface(typeface,1) }
    private fun card():LinearLayout=LinearLayout(this).apply{orientation=LinearLayout.VERTICAL;setPadding(dp(12),dp(12),dp(12),dp(12));setBackgroundResource(R.drawable.bg_card)}
    override fun onCreate(b:Bundle?){super.onCreate(b);window.statusBarColor=Color.rgb(4,17,30);window.navigationBarColor=Color.rgb(4,17,30);build()}
    private fun build(){
        val outer=ScrollView(this).apply{setBackgroundColor(navy);isFillViewport=true}
        val page=LinearLayout(this).apply{orientation=LinearLayout.VERTICAL;setPadding(dp(12),dp(10),dp(12),dp(16))}; outer.addView(page)
        val header=LinearLayout(this).apply{orientation=LinearLayout.HORIZONTAL;gravity=Gravity.CENTER_VERTICAL}
        header.addView(text("☰",28),LinearLayout.LayoutParams(dp(40),dp(52)))
        header.addView(text("Nestlé  |  Factory Power Monitoring",18,white,true),LinearLayout.LayoutParams(0,dp(52),1f))
        header.addView(text("● Online",13,green,true),LinearLayout.LayoutParams(dp(72),dp(52)))
        page.addView(header)
        val usage=card();usage.addView(text("⚡  Realtime Plant Usage",18,white,true));usage.addView(text("2,842 kW",34,white,true));usage.addView(text("PF 0.96                 72% of 3,950 kW",13,muted));page.addView(usage,LinearLayout.LayoutParams(-1,dp(132)).apply{bottomMargin=dp(8)})
        val dist=card();dist.addView(text("◉  Usage Distribution",16,white,true));listOf("Process                 38%     1,077 kW","Utilities                22%       625 kW","HVAC                     15%       427 kW","Packaging              12%       341 kW","Other                     13%       372 kW").forEach{dist.addView(text(it,12))}
        val source=card();source.addView(text("⚡  Source",16,white,true));source.addView(text("Generator     1,420 kW",14,white,true));source.addView(text("● ONLINE",12,green,true));source.addView(text("Utility          1,422 kW",14,white,true));source.addView(text("● ONLINE",12,green,true))
        val two=LinearLayout(this).apply{orientation=LinearLayout.HORIZONTAL};two.addView(dist,LinearLayout.LayoutParams(0,dp(190),1f).apply{rightMargin=dp(4)});two.addView(source,LinearLayout.LayoutParams(0,dp(190),1f).apply{leftMargin=dp(4)});page.addView(two,LinearLayout.LayoutParams(-1,dp(190)).apply{bottomMargin=dp(8)})
        val feeder=card();val fh=LinearLayout(this).apply{orientation=LinearLayout.HORIZONTAL;gravity=Gravity.CENTER_VERTICAL};fh.addView(text("☷  Feeders",18,white,true),LinearLayout.LayoutParams(0,dp(42),1f));fh.addView(text("16 / 16 Online",12,muted,true),LinearLayout.LayoutParams(dp(100),dp(42)));feeder.addView(fh)
        val scroll=ScrollView(this).apply{setBackgroundResource(R.drawable.bg_inner)};val list=LinearLayout(this).apply{orientation=LinearLayout.VERTICAL}
        val names=arrayOf("Boiler House","Packaging Line 1","Packaging Line 2","Processing Line 1","Processing Line 2","Utilities","HVAC 1","HVAC 2","Compressor 1","Compressor 2","Water Treatment","Waste Treatment","Lighting","Cold Storage","Laboratory","Administration")
        val kw=intArrayOf(185,210,198,320,319,260,180,175,140,138,98,80,65,120,75,60)
        for(i in 0 until 16){val st=when(i){5,13->"Warning";15->"Alarm";else->"Normal"};val c=when(st){"Warning"->yellow;"Alarm"->red;else->green};val line=LinearLayout(this).apply{orientation=LinearLayout.HORIZONTAL;gravity=Gravity.CENTER_VERTICAL;setPadding(dp(6),dp(4),dp(6),dp(4))};val n=text("${i+1}",14,Color.WHITE,true);n.gravity=Gravity.CENTER;line.addView(n,LinearLayout.LayoutParams(dp(34),dp(34)));val info=LinearLayout(this).apply{orientation=LinearLayout.VERTICAL;addView(text(names[i],13,white,true));addView(text("${kw[i]} kW    PF 0.${if(i==5)92 else 96}",11,muted))};line.addView(info,LinearLayout.LayoutParams(0,dp(48),1f));line.addView(text("● $st",11,c,true));list.addView(line,LinearLayout.LayoutParams(-1,dp(54)))}
        scroll.addView(list);feeder.addView(scroll,LinearLayout.LayoutParams(-1,dp(520)));page.addView(feeder,LinearLayout.LayoutParams(-1,dp(570)).apply{bottomMargin=dp(8)})
        val trend=card();trend.addView(text("↗  Load Trend",17,white,true));trend.addView(text("4,000 kW",11,muted));trend.addView(text("00:00      04:00      08:00      12:00      16:00      20:00",9,muted));val bar=View(this).apply{setBackgroundColor(blue)};trend.addView(bar,LinearLayout.LayoutParams(-1,dp(44)));page.addView(trend,LinearLayout.LayoutParams(-1,dp(125)).apply{bottomMargin=dp(8)})
        val energy=card();energy.addView(text("⚡  Total Energy Usage",17,white,true));energy.addView(text("48,320 kWh",30,white,true));energy.addView(text("▲ 6.8% vs yesterday",12,green,true));energy.addView(text("Mon   Tue   Wed   Thu   Fri   Sat   Sun",10,muted));page.addView(energy,LinearLayout.LayoutParams(-1,dp(150)))
        setContentView(outer)
    }
}
