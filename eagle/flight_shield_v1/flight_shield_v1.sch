<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE eagle SYSTEM "eagle.dtd">
<eagle version="7.7.0">
<drawing>
<settings>
<setting alwaysvectorfont="no"/>
<setting verticaltext="up"/>
</settings>
<grid distance="0.1" unitdist="inch" unit="inch" style="lines" multiple="1" display="no" altdistance="0.01" altunitdist="inch" altunit="inch"/>
<layers>
<layer number="1" name="Top" color="4" fill="1" visible="no" active="no"/>
<layer number="16" name="Bottom" color="1" fill="1" visible="no" active="no"/>
<layer number="17" name="Pads" color="2" fill="1" visible="no" active="no"/>
<layer number="18" name="Vias" color="2" fill="1" visible="no" active="no"/>
<layer number="19" name="Unrouted" color="6" fill="1" visible="no" active="no"/>
<layer number="20" name="Dimension" color="15" fill="1" visible="no" active="no"/>
<layer number="21" name="tPlace" color="16" fill="1" visible="no" active="no"/>
<layer number="22" name="bPlace" color="14" fill="1" visible="no" active="no"/>
<layer number="23" name="tOrigins" color="15" fill="1" visible="no" active="no"/>
<layer number="24" name="bOrigins" color="15" fill="1" visible="no" active="no"/>
<layer number="25" name="tNames" color="7" fill="1" visible="no" active="no"/>
<layer number="26" name="bNames" color="7" fill="1" visible="no" active="no"/>
<layer number="27" name="tValues" color="7" fill="1" visible="no" active="no"/>
<layer number="28" name="bValues" color="7" fill="1" visible="no" active="no"/>
<layer number="29" name="tStop" color="7" fill="3" visible="no" active="no"/>
<layer number="30" name="bStop" color="7" fill="6" visible="no" active="no"/>
<layer number="31" name="tCream" color="7" fill="4" visible="no" active="no"/>
<layer number="32" name="bCream" color="7" fill="5" visible="no" active="no"/>
<layer number="33" name="tFinish" color="6" fill="3" visible="no" active="no"/>
<layer number="34" name="bFinish" color="6" fill="6" visible="no" active="no"/>
<layer number="35" name="tGlue" color="7" fill="4" visible="no" active="no"/>
<layer number="36" name="bGlue" color="7" fill="5" visible="no" active="no"/>
<layer number="37" name="tTest" color="7" fill="1" visible="no" active="no"/>
<layer number="38" name="bTest" color="7" fill="1" visible="no" active="no"/>
<layer number="39" name="tKeepout" color="4" fill="11" visible="no" active="no"/>
<layer number="40" name="bKeepout" color="1" fill="11" visible="no" active="no"/>
<layer number="41" name="tRestrict" color="4" fill="10" visible="no" active="no"/>
<layer number="42" name="bRestrict" color="1" fill="10" visible="no" active="no"/>
<layer number="43" name="vRestrict" color="2" fill="10" visible="no" active="no"/>
<layer number="44" name="Drills" color="7" fill="1" visible="no" active="no"/>
<layer number="45" name="Holes" color="7" fill="1" visible="no" active="no"/>
<layer number="46" name="Milling" color="3" fill="1" visible="no" active="no"/>
<layer number="47" name="Measures" color="7" fill="1" visible="no" active="no"/>
<layer number="48" name="Document" color="7" fill="1" visible="no" active="no"/>
<layer number="49" name="Reference" color="7" fill="1" visible="no" active="no"/>
<layer number="50" name="dxf" color="7" fill="1" visible="no" active="no"/>
<layer number="51" name="tDocu" color="7" fill="1" visible="no" active="no"/>
<layer number="52" name="bDocu" color="7" fill="1" visible="no" active="no"/>
<layer number="53" name="tGND_GNDA" color="7" fill="9" visible="no" active="no"/>
<layer number="54" name="bGND_GNDA" color="1" fill="9" visible="no" active="no"/>
<layer number="56" name="wert" color="7" fill="1" visible="no" active="no"/>
<layer number="57" name="tCAD" color="7" fill="1" visible="no" active="no"/>
<layer number="59" name="tCarbon" color="7" fill="1" visible="no" active="no"/>
<layer number="60" name="bCarbon" color="7" fill="1" visible="no" active="no"/>
<layer number="90" name="Modules" color="5" fill="1" visible="yes" active="yes"/>
<layer number="91" name="Nets" color="2" fill="1" visible="yes" active="yes"/>
<layer number="92" name="Busses" color="1" fill="1" visible="yes" active="yes"/>
<layer number="93" name="Pins" color="2" fill="1" visible="no" active="yes"/>
<layer number="94" name="Symbols" color="4" fill="1" visible="yes" active="yes"/>
<layer number="95" name="Names" color="7" fill="1" visible="yes" active="yes"/>
<layer number="96" name="Values" color="7" fill="1" visible="yes" active="yes"/>
<layer number="97" name="Info" color="7" fill="1" visible="yes" active="yes"/>
<layer number="98" name="Guide" color="6" fill="1" visible="yes" active="yes"/>
<layer number="99" name="SpiceOrder" color="7" fill="1" visible="no" active="no"/>
<layer number="100" name="Muster" color="7" fill="1" visible="no" active="no"/>
<layer number="101" name="Patch_Top" color="12" fill="4" visible="yes" active="yes"/>
<layer number="102" name="Vscore" color="7" fill="1" visible="yes" active="yes"/>
<layer number="103" name="tMap" color="7" fill="1" visible="yes" active="yes"/>
<layer number="104" name="Name" color="16" fill="1" visible="yes" active="yes"/>
<layer number="105" name="tPlate" color="7" fill="1" visible="yes" active="yes"/>
<layer number="106" name="bPlate" color="7" fill="1" visible="yes" active="yes"/>
<layer number="107" name="Crop" color="7" fill="1" visible="yes" active="yes"/>
<layer number="108" name="tplace-old" color="10" fill="1" visible="yes" active="yes"/>
<layer number="109" name="ref-old" color="11" fill="1" visible="yes" active="yes"/>
<layer number="110" name="fp0" color="7" fill="1" visible="yes" active="yes"/>
<layer number="111" name="LPC17xx" color="7" fill="1" visible="yes" active="yes"/>
<layer number="112" name="tSilk" color="7" fill="1" visible="yes" active="yes"/>
<layer number="113" name="IDFDebug" color="7" fill="1" visible="yes" active="yes"/>
<layer number="114" name="Badge_Outline" color="11" fill="1" visible="no" active="no"/>
<layer number="115" name="ReferenceISLANDS" color="7" fill="1" visible="yes" active="yes"/>
<layer number="116" name="Patch_BOT" color="9" fill="4" visible="yes" active="yes"/>
<layer number="118" name="Rect_Pads" color="7" fill="1" visible="yes" active="yes"/>
<layer number="121" name="_tsilk" color="7" fill="1" visible="yes" active="yes"/>
<layer number="122" name="_bsilk" color="7" fill="1" visible="yes" active="yes"/>
<layer number="123" name="tTestmark" color="7" fill="1" visible="yes" active="yes"/>
<layer number="124" name="bTestmark" color="7" fill="1" visible="yes" active="yes"/>
<layer number="125" name="_tNames" color="7" fill="1" visible="yes" active="yes"/>
<layer number="126" name="_bNames" color="7" fill="1" visible="yes" active="yes"/>
<layer number="127" name="_tValues" color="7" fill="1" visible="yes" active="yes"/>
<layer number="128" name="_bValues" color="7" fill="1" visible="yes" active="yes"/>
<layer number="129" name="Mask" color="7" fill="1" visible="yes" active="yes"/>
<layer number="131" name="tAdjust" color="7" fill="1" visible="yes" active="yes"/>
<layer number="132" name="bAdjust" color="7" fill="1" visible="yes" active="yes"/>
<layer number="144" name="Drill_legend" color="7" fill="1" visible="yes" active="yes"/>
<layer number="150" name="Notes" color="7" fill="1" visible="yes" active="yes"/>
<layer number="151" name="HeatSink" color="7" fill="1" visible="yes" active="yes"/>
<layer number="152" name="_bDocu" color="7" fill="1" visible="yes" active="yes"/>
<layer number="153" name="FabDoc1" color="7" fill="1" visible="yes" active="yes"/>
<layer number="154" name="FabDoc2" color="7" fill="1" visible="yes" active="yes"/>
<layer number="155" name="FabDoc3" color="7" fill="1" visible="yes" active="yes"/>
<layer number="199" name="Contour" color="7" fill="1" visible="yes" active="yes"/>
<layer number="200" name="200bmp" color="1" fill="10" visible="yes" active="yes"/>
<layer number="201" name="201bmp" color="2" fill="10" visible="yes" active="yes"/>
<layer number="202" name="202bmp" color="3" fill="10" visible="yes" active="yes"/>
<layer number="203" name="203bmp" color="4" fill="10" visible="yes" active="yes"/>
<layer number="204" name="204bmp" color="5" fill="10" visible="yes" active="yes"/>
<layer number="205" name="205bmp" color="6" fill="10" visible="yes" active="yes"/>
<layer number="206" name="206bmp" color="7" fill="10" visible="yes" active="yes"/>
<layer number="207" name="207bmp" color="8" fill="10" visible="yes" active="yes"/>
<layer number="208" name="208bmp" color="9" fill="10" visible="yes" active="yes"/>
<layer number="209" name="209bmp" color="7" fill="1" visible="yes" active="yes"/>
<layer number="210" name="210bmp" color="7" fill="1" visible="yes" active="yes"/>
<layer number="211" name="211bmp" color="7" fill="1" visible="yes" active="yes"/>
<layer number="212" name="212bmp" color="7" fill="1" visible="yes" active="yes"/>
<layer number="213" name="213bmp" color="7" fill="1" visible="yes" active="yes"/>
<layer number="214" name="214bmp" color="7" fill="1" visible="yes" active="yes"/>
<layer number="215" name="215bmp" color="7" fill="1" visible="yes" active="yes"/>
<layer number="216" name="216bmp" color="7" fill="1" visible="yes" active="yes"/>
<layer number="217" name="217bmp" color="18" fill="1" visible="no" active="no"/>
<layer number="218" name="218bmp" color="19" fill="1" visible="no" active="no"/>
<layer number="219" name="219bmp" color="20" fill="1" visible="no" active="no"/>
<layer number="220" name="220bmp" color="21" fill="1" visible="no" active="no"/>
<layer number="221" name="221bmp" color="22" fill="1" visible="no" active="no"/>
<layer number="222" name="222bmp" color="23" fill="1" visible="no" active="no"/>
<layer number="223" name="223bmp" color="24" fill="1" visible="no" active="no"/>
<layer number="224" name="224bmp" color="25" fill="1" visible="no" active="no"/>
<layer number="225" name="225bmp" color="7" fill="1" visible="yes" active="yes"/>
<layer number="226" name="226bmp" color="7" fill="1" visible="yes" active="yes"/>
<layer number="227" name="227bmp" color="7" fill="1" visible="yes" active="yes"/>
<layer number="228" name="228bmp" color="7" fill="1" visible="yes" active="yes"/>
<layer number="229" name="229bmp" color="7" fill="1" visible="yes" active="yes"/>
<layer number="230" name="230bmp" color="7" fill="1" visible="yes" active="yes"/>
<layer number="231" name="231bmp" color="7" fill="1" visible="yes" active="yes"/>
<layer number="248" name="Housing" color="7" fill="1" visible="yes" active="yes"/>
<layer number="249" name="Edge" color="7" fill="1" visible="yes" active="yes"/>
<layer number="250" name="Descript" color="3" fill="1" visible="no" active="no"/>
<layer number="251" name="SMDround" color="12" fill="11" visible="no" active="no"/>
<layer number="254" name="cooling" color="7" fill="1" visible="yes" active="yes"/>
<layer number="255" name="routoute" color="7" fill="1" visible="yes" active="yes"/>
</layers>
<schematic xreflabel="%F%N/%S.%C%R" xrefpart="/%S.%C%R">
<libraries>
<library name="SparkFun-Boards">
<description>&lt;h3&gt;SparkFun Electronics' preferred foot prints&lt;/h3&gt;
This library contains footprints for SparkFun breakout boards, microcontrollers (Arduino, Particle, Teensy, etc.),  breadboards, non-RF modules, etc.
&lt;br&gt;
&lt;br&gt;
We've spent an enormous amount of time creating and checking these footprints and parts, but it is &lt;b&gt; the end user's responsibility&lt;/b&gt; to ensure correctness and suitablity for a given componet or application. 
&lt;br&gt;
&lt;br&gt;If you enjoy using this library, please buy one of our products at &lt;a href=" www.sparkfun.com"&gt;SparkFun.com&lt;/a&gt;.
&lt;br&gt;
&lt;br&gt;
&lt;b&gt;Licensing:&lt;/b&gt; Creative Commons ShareAlike 4.0 International - https://creativecommons.org/licenses/by-sa/4.0/ 
&lt;br&gt;
&lt;br&gt;
You are welcome to use this library for commercial purposes. For attribution, we ask that when you begin to sell your device using our footprint, you email us with a link to the product being sold. We want bragging rights that we helped (in a very small part) to create your 8th world wonder. We would like the opportunity to feature your device on our homepage.</description>
<packages>
<package name="UNO_R3_SHIELD">
<description>&lt;h3&gt;Arduino Uno-Compatible Footprint&lt;/h3&gt;
No holes, no ICSP connections.
&lt;p&gt;Specifications:
&lt;ul&gt;&lt;li&gt;Pin count: 32&lt;/li&gt;
&lt;li&gt;Pin pitch: 0.1"&lt;/li&gt;
&lt;li&gt;Area:2.1x2.35"&lt;/li&gt;
&lt;/ul&gt;&lt;/p&gt;
&lt;p&gt;Example device(s):
&lt;ul&gt;&lt;li&gt;Arduino Uno R3 Shield&lt;/li&gt;
&lt;/ul&gt;&lt;/p&gt;</description>
<wire qx="-24.13" y1="-29.21" qy="-17.17" y2="-29.21" width="0.254" layer="51"/>
<wire qx="-17.17" y1="-29.21" qy="-4.97" y2="-29.21" width="0.254" layer="51"/>
<wire qx="-4.97" y1="-29.21" qy="24.13" y2="-29.21" width="0.254" layer="51"/>
<wire qx="24.13" y1="-29.21" qy="26.67" y2="-26.67" width="0.254" layer="51"/>
<wire qx="26.67" y1="27.94" qy="24.13" y2="27.94" width="0.254" layer="51"/>
<wire qx="24.13" y1="27.94" qy="21.59" y2="30.48" width="0.254" layer="51"/>
<wire qx="-26.67" y1="26.67" qy="-26.67" y2="-26.67" width="0.254" layer="51"/>
<wire qx="-26.67" y1="-26.67" qy="-24.13" y2="-29.21" width="0.254" layer="51"/>
<wire qx="21.59" y1="30.48" qy="-11.43" y2="30.48" width="0.254" layer="51"/>
<wire qx="-11.43" y1="30.48" qy="-13.97" y2="27.94" width="0.254" layer="51"/>
<wire qx="-13.97" y1="27.94" qy="-25.4" y2="27.94" width="0.254" layer="51"/>
<wire qx="-25.4" y1="27.94" qy="-26.67" y2="26.67" width="0.254" layer="51"/>
<wire qx="26.67" y1="-26.67" qy="26.67" y2="27.94" width="0.254" layer="51"/>
<wire qx="-17.17" y1="-44.71" qy="-4.97" y2="-44.71" width="0.254" layer="51"/>
<wire qx="13.53" y1="-39.51" qy="22.53" y2="-39.51" width="0.254" layer="51"/>
<wire qx="-17.17" y1="-44.71" qy="-17.17" y2="-29.21" width="0.254" layer="51"/>
<wire qx="-4.97" y1="-44.71" qy="-4.97" y2="-29.21" width="0.254" layer="51"/>
<wire qx="13.53" y1="-39.51" qy="13.53" y2="-29.31" width="0.254" layer="51"/>
<wire qx="22.53" y1="-39.51" qy="22.53" y2="-29.31" width="0.254" layer="51"/>
<wire qx="-25.4" y1="26.67" qy="-22.86" y2="26.67" width="0.127" layer="51"/>
<wire qx="-22.86" y1="26.67" qy="-22.86" y2="6.35" width="0.127" layer="51"/>
<wire qx="-22.86" y1="6.35" qy="-25.4" y2="6.35" width="0.127" layer="51"/>
<wire qx="-25.4" y1="6.35" qy="-25.4" y2="26.67" width="0.127" layer="51"/>
<wire qx="-25.4" y1="5.08" qy="-22.86" y2="5.08" width="0.127" layer="51"/>
<wire qx="-22.86" y1="5.08" qy="-22.86" y2="-20.32" width="0.127" layer="51"/>
<wire qx="-22.86" y1="-20.32" qy="-25.4" y2="-20.32" width="0.127" layer="51"/>
<wire qx="-25.4" y1="-20.32" qy="-25.4" y2="5.08" width="0.127" layer="51"/>
<wire qx="22.86" y1="-11.43" qy="25.4" y2="-11.43" width="0.127" layer="51"/>
<wire qx="25.4" y1="-11.43" qy="25.4" y2="8.89" width="0.127" layer="51"/>
<wire qx="25.4" y1="8.89" qy="22.86" y2="8.89" width="0.127" layer="51"/>
<wire qx="22.86" y1="8.89" qy="22.86" y2="-11.43" width="0.127" layer="51"/>
<wire qx="25.4" y1="11.43" qy="22.86" y2="11.43" width="0.127" layer="51"/>
<wire qx="22.86" y1="11.43" qy="22.86" y2="26.67" width="0.127" layer="51"/>
<wire qx="22.86" y1="26.67" qy="25.4" y2="26.67" width="0.127" layer="51"/>
<wire qx="25.4" y1="26.67" qy="25.4" y2="11.43" width="0.127" layer="51"/>
<pad name="RES" x="24.13" y="-5.08" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="3.3V" x="24.13" y="-2.54" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="5V" x="24.13" y="0" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="GND@0" x="24.13" y="2.54" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="GND@1" x="24.13" y="5.08" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="VIN" x="24.13" y="7.62" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="A0" x="24.13" y="12.7" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="A1" x="24.13" y="15.24" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="A2" x="24.13" y="17.78" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="A3" x="24.13" y="20.32" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="A4" x="24.13" y="22.86" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="A5" x="24.13" y="25.4" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="RX" x="-24.13" y="25.4" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="TX" x="-24.13" y="22.86" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="D2" x="-24.13" y="20.32" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="D3" x="-24.13" y="17.78" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="D4" x="-24.13" y="15.24" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="D5" x="-24.13" y="12.7" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="D6" x="-24.13" y="10.16" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="D7" x="-24.13" y="7.62" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="D8" x="-24.13" y="3.81" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="D9" x="-24.13" y="1.27" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="D10" x="-24.13" y="-1.27" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="D11" x="-24.13" y="-3.81" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="D12" x="-24.13" y="-6.35" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="D13" x="-24.13" y="-8.89" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="GND@2" x="-24.13" y="-11.43" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="AREF" x="-24.13" y="-13.97" drill="1.016" diameter="1.8796" rot="R90"/>
<text x="22.7457" y="3.048" size="1.016" layer="21" font="vector" ratio="15" rot="R180">GND</text>
<text x="22.7457" y="5.588" size="1.016" layer="21" font="vector" ratio="15" rot="R180">GND</text>
<text x="22.7457" y="0.508" size="1.016" layer="21" font="vector" ratio="15" rot="R180">+5V</text>
<text x="22.7457" y="-4.572" size="1.016" layer="21" font="vector" ratio="15" rot="R180">RST</text>
<text x="22.7457" y="8.128" size="1.016" layer="21" font="vector" ratio="15" rot="R180">VIN</text>
<text x="22.7457" y="-2.032" size="1.016" layer="21" font="vector" ratio="15" rot="R180">+3.3V</text>
<text x="22.7457" y="13.208" size="1.016" layer="21" font="vector" ratio="15" rot="R180">0</text>
<text x="22.7457" y="15.748" size="1.016" layer="21" font="vector" ratio="15" rot="R180">1</text>
<text x="22.7457" y="18.288" size="1.016" layer="21" font="vector" ratio="15" rot="R180">2</text>
<text x="22.7457" y="20.828" size="1.016" layer="21" font="vector" ratio="15" rot="R180">3</text>
<text x="22.7457" y="23.368" size="1.016" layer="21" font="vector" ratio="15" rot="R180">4</text>
<text x="22.7457" y="25.908" size="1.016" layer="21" font="vector" ratio="15" rot="R180">5</text>
<text x="-22.86" y="-11.938" size="1.016" layer="21" font="vector" ratio="15">GND</text>
<text x="-22.86" y="-9.398" size="1.016" layer="21" font="vector" ratio="15">13</text>
<text x="-22.86" y="-6.858" size="1.016" layer="21" font="vector" ratio="15">12</text>
<text x="-22.86" y="-4.318" size="1.016" layer="21" font="vector" ratio="15">11</text>
<text x="-22.86" y="-14.478" size="1.016" layer="21" font="vector" ratio="15">AREF</text>
<text x="-22.86" y="-1.778" size="1.016" layer="21" font="vector" ratio="15">10</text>
<text x="-22.86" y="0.762" size="1.016" layer="21" font="vector" ratio="15">9</text>
<text x="-22.86" y="3.302" size="1.016" layer="21" font="vector" ratio="15">8</text>
<text x="-22.86" y="7.112" size="1.016" layer="21" font="vector" ratio="15">7</text>
<text x="-22.86" y="9.652" size="1.016" layer="21" font="vector" ratio="15">6</text>
<text x="-22.86" y="12.192" size="1.016" layer="21" font="vector" ratio="15">5</text>
<text x="-22.86" y="14.732" size="1.016" layer="21" font="vector" ratio="15">4</text>
<text x="-22.86" y="17.272" size="1.016" layer="21" font="vector" ratio="15">3</text>
<text x="-22.86" y="19.812" size="1.016" layer="21" font="vector" ratio="15">2</text>
<text x="-22.86" y="22.352" size="1.016" layer="21" font="vector" ratio="15">TX</text>
<text x="-22.86" y="24.892" size="1.016" layer="21" font="vector" ratio="15">RX</text>
<pad name="SDA" x="-24.13" y="-16.51" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="SCL" x="-24.13" y="-19.05" drill="1.016" diameter="1.8796" rot="R90"/>
<text x="-22.86" y="-17.018" size="1.016" layer="21" font="vector" ratio="15">SDA</text>
<text x="-22.86" y="-19.558" size="1.016" layer="21" font="vector" ratio="15">SCL</text>
<pad name="IOREF" x="24.13" y="-7.62" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="NC" x="24.13" y="-10.16" drill="1.016" diameter="1.8796" rot="R90"/>
<text x="22.7457" y="-7.112" size="1.016" layer="21" font="vector" ratio="15" rot="R180">IOREF</text>
<wire qx="-4.445" y1="24.13" qy="-5.08" y2="24.765" width="0.254" layer="51"/>
<wire qx="-5.08" y1="24.765" qy="-5.08" y2="28.575" width="0.254" layer="51"/>
<wire qx="-5.08" y1="28.575" qy="-4.445" y2="29.21" width="0.254" layer="51"/>
<wire qx="-4.445" y1="29.21" qy="1.905" y2="29.21" width="0.254" layer="51"/>
<wire qx="1.905" y1="29.21" qy="2.54" y2="28.575" width="0.254" layer="51"/>
<wire qx="2.54" y1="28.575" qy="2.54" y2="24.765" width="0.254" layer="51"/>
<wire qx="2.54" y1="24.765" qy="1.905" y2="24.13" width="0.254" layer="51"/>
<wire qx="1.905" y1="24.13" qy="-4.445" y2="24.13" width="0.254" layer="51"/>
<text x="0.635" y="23.241" size="0.508" layer="51" font="vector" ratio="15">RST</text>
<text x="-1.778" y="26.416" size="0.508" layer="51" font="vector" ratio="15">ISP</text>
<wire qx="-3.175" y1="23.622" qy="-4.445" y2="23.622" width="0.2032" layer="51"/>
<text x="-12.065" y="-44.069" size="0.508" layer="51" font="vector" ratio="15">USB</text>
<text x="15.875" y="-38.989" size="0.508" layer="51" font="vector" ratio="15">POWER JACK</text>
<text x="0" y="30.734" size="0.6096" layer="25" font="vector" ratio="20" align="bottom-center">&gt;NAME</text>
<text x="0" y="-29.718" size="0.6096" layer="27" font="vector" ratio="20" align="top-center">&gt;VALUE</text>
</package>
<package name="UNO_R3_SHIELD_NOLABELS">
<description>&lt;h3&gt;Arduino Uno-Compatible Footprint&lt;/h3&gt;
No holes, no ICSP connections, no silk labels. 
&lt;p&gt;Specifications:
&lt;ul&gt;&lt;li&gt;Pin count: 32&lt;/li&gt;
&lt;li&gt;Pin pitch: 0.1"&lt;/li&gt;
&lt;li&gt;Area:2.1x2.35"&lt;/li&gt;
&lt;/ul&gt;&lt;/p&gt;
&lt;p&gt;Example device(s):
&lt;ul&gt;&lt;li&gt;Arduino Uno R3 Shield&lt;/li&gt;
&lt;/ul&gt;&lt;/p&gt;</description>
<wire qx="-24.13" y1="-30.48" qy="-17.17" y2="-30.48" width="0.254" layer="51"/>
<wire qx="-17.17" y1="-30.48" qy="-4.97" y2="-30.48" width="0.254" layer="51"/>
<wire qx="-4.97" y1="-30.48" qy="24.13" y2="-30.48" width="0.254" layer="51"/>
<wire qx="24.13" y1="-30.48" qy="26.67" y2="-27.94" width="0.254" layer="51"/>
<wire qx="26.67" y1="26.67" qy="24.13" y2="26.67" width="0.254" layer="51"/>
<wire qx="24.13" y1="26.67" qy="21.59" y2="29.21" width="0.254" layer="51"/>
<wire qx="-26.67" y1="25.4" qy="-26.67" y2="-27.94" width="0.254" layer="51"/>
<wire qx="-26.67" y1="-27.94" qy="-24.13" y2="-30.48" width="0.254" layer="51"/>
<wire qx="21.59" y1="29.21" qy="-11.43" y2="29.21" width="0.254" layer="51"/>
<wire qx="-11.43" y1="29.21" qy="-13.97" y2="26.67" width="0.254" layer="51"/>
<wire qx="-13.97" y1="26.67" qy="-25.4" y2="26.67" width="0.254" layer="51"/>
<wire qx="-25.4" y1="26.67" qy="-26.67" y2="25.4" width="0.254" layer="51"/>
<wire qx="26.67" y1="-27.94" qy="26.67" y2="26.67" width="0.254" layer="51"/>
<wire qx="-17.17" y1="-45.98" qy="-4.97" y2="-45.98" width="0.254" layer="51"/>
<wire qx="13.53" y1="-40.78" qy="22.53" y2="-40.78" width="0.254" layer="51"/>
<wire qx="-17.17" y1="-45.98" qy="-17.17" y2="-30.48" width="0.254" layer="51"/>
<wire qx="-4.97" y1="-45.98" qy="-4.97" y2="-30.48" width="0.254" layer="51"/>
<wire qx="13.53" y1="-40.78" qy="13.53" y2="-30.58" width="0.254" layer="51"/>
<wire qx="22.53" y1="-40.78" qy="22.53" y2="-30.58" width="0.254" layer="51"/>
<wire qx="-25.4" y1="25.4" qy="-22.86" y2="25.4" width="0.127" layer="51"/>
<wire qx="-22.86" y1="25.4" qy="-22.86" y2="5.08" width="0.127" layer="51"/>
<wire qx="-22.86" y1="5.08" qy="-25.4" y2="5.08" width="0.127" layer="51"/>
<wire qx="-25.4" y1="5.08" qy="-25.4" y2="25.4" width="0.127" layer="51"/>
<wire qx="-25.4" y1="3.81" qy="-22.86" y2="3.81" width="0.127" layer="51"/>
<wire qx="-22.86" y1="3.81" qy="-22.86" y2="-21.59" width="0.127" layer="51"/>
<wire qx="-22.86" y1="-21.59" qy="-25.4" y2="-21.59" width="0.127" layer="51"/>
<wire qx="-25.4" y1="-21.59" qy="-25.4" y2="3.81" width="0.127" layer="51"/>
<wire qx="22.86" y1="-12.7" qy="25.4" y2="-12.7" width="0.127" layer="51"/>
<wire qx="25.4" y1="-12.7" qy="25.4" y2="7.62" width="0.127" layer="51"/>
<wire qx="25.4" y1="7.62" qy="22.86" y2="7.62" width="0.127" layer="51"/>
<wire qx="25.4" y1="10.16" qy="22.86" y2="10.16" width="0.127" layer="51"/>
<wire qx="22.86" y1="10.16" qy="22.86" y2="25.4" width="0.127" layer="51"/>
<wire qx="22.86" y1="25.4" qy="25.4" y2="25.4" width="0.127" layer="51"/>
<wire qx="25.4" y1="25.4" qy="25.4" y2="10.16" width="0.127" layer="51"/>
<pad name="RES" x="24.13" y="-6.35" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="3.3V" x="24.13" y="-3.81" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="5V" x="24.13" y="-1.27" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="GND@0" x="24.13" y="1.27" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="GND@1" x="24.13" y="3.81" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="VIN" x="24.13" y="6.35" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="A0" x="24.13" y="11.43" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="A1" x="24.13" y="13.97" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="A2" x="24.13" y="16.51" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="A3" x="24.13" y="19.05" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="A4" x="24.13" y="21.59" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="A5" x="24.13" y="24.13" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="RX" x="-24.13" y="24.13" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="TX" x="-24.13" y="21.59" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="D2" x="-24.13" y="19.05" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="D3" x="-24.13" y="16.51" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="D4" x="-24.13" y="13.97" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="D5" x="-24.13" y="11.43" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="D6" x="-24.13" y="8.89" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="D7" x="-24.13" y="6.35" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="D8" x="-24.13" y="2.54" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="D9" x="-24.13" y="0" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="D10" x="-24.13" y="-2.54" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="D11" x="-24.13" y="-5.08" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="D12" x="-24.13" y="-7.62" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="D13" x="-24.13" y="-10.16" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="GND@2" x="-24.13" y="-12.7" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="AREF" x="-24.13" y="-15.24" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="SDA" x="-24.13" y="-17.78" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="SCL" x="-24.13" y="-20.32" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="IOREF" x="24.13" y="-8.89" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="NC" x="24.13" y="-11.43" drill="1.016" diameter="1.8796" rot="R90"/>
<wire qx="-4.445" y1="22.86" qy="-5.08" y2="23.495" width="0.254" layer="51"/>
<wire qx="-5.08" y1="23.495" qy="-5.08" y2="27.305" width="0.254" layer="51"/>
<wire qx="-5.08" y1="27.305" qy="-4.445" y2="27.94" width="0.254" layer="51"/>
<wire qx="-4.445" y1="27.94" qy="1.905" y2="27.94" width="0.254" layer="51"/>
<wire qx="1.905" y1="27.94" qy="2.54" y2="27.305" width="0.254" layer="51"/>
<wire qx="2.54" y1="27.305" qy="2.54" y2="23.495" width="0.254" layer="51"/>
<wire qx="2.54" y1="23.495" qy="1.905" y2="22.86" width="0.254" layer="51"/>
<wire qx="1.905" y1="22.86" qy="-4.445" y2="22.86" width="0.254" layer="51"/>
<text x="0.635" y="21.971" size="0.508" layer="51" font="vector" ratio="15">RST</text>
<text x="-1.778" y="25.146" size="0.508" layer="51" ratio="15">ISP</text>
<wire qx="-3.175" y1="22.352" qy="-4.445" y2="22.352" width="0.2032" layer="51"/>
<wire qx="22.86" y1="7.62" qy="22.86" y2="-12.7" width="0.127" layer="51"/>
<text x="0" y="29.464" size="0.6096" layer="25" font="vector" ratio="20" align="bottom-center">&gt;NAME</text>
<text x="0" y="-30.734" size="0.6096" layer="25" font="vector" ratio="20" align="top-center">&gt;VALUE</text>
<text x="-11.43" y="-44.45" size="0.8128" layer="51" font="vector" ratio="20" align="top-center">USB</text>
<text x="17.78" y="-39.37" size="0.8128" layer="51" font="vector" ratio="20" align="top-center">PWR JACK</text>
</package>
<package name="UNO_R3_SHIELD_LOCK">
<description>&lt;h3&gt;Arduino Uno-Compatible Footprint&lt;/h3&gt;
No holes, no ICSP connections.
Locking footprint for headers.
&lt;p&gt;Specifications:
&lt;ul&gt;&lt;li&gt;Pin count: 32&lt;/li&gt;
&lt;li&gt;Pin pitch: 0.1"&lt;/li&gt;
&lt;li&gt;Area:2.1x2.35"&lt;/li&gt;
&lt;/ul&gt;&lt;/p&gt;
&lt;p&gt;Example device(s):
&lt;ul&gt;&lt;li&gt;Arduino Uno R3 Shield&lt;/li&gt;
&lt;/ul&gt;&lt;/p&gt;</description>
<wire qx="-24.13" y1="-30.48" qy="-17.17" y2="-30.48" width="0.254" layer="51"/>
<wire qx="-17.17" y1="-30.48" qy="-4.97" y2="-30.48" width="0.254" layer="51"/>
<wire qx="-4.97" y1="-30.48" qy="24.13" y2="-30.48" width="0.254" layer="51"/>
<wire qx="24.13" y1="-30.48" qy="26.67" y2="-27.94" width="0.254" layer="51"/>
<wire qx="26.67" y1="26.67" qy="24.13" y2="26.67" width="0.254" layer="51"/>
<wire qx="24.13" y1="26.67" qy="21.59" y2="29.21" width="0.254" layer="51"/>
<wire qx="-26.67" y1="25.4" qy="-26.67" y2="-27.94" width="0.254" layer="51"/>
<wire qx="-26.67" y1="-27.94" qy="-24.13" y2="-30.48" width="0.254" layer="51"/>
<wire qx="21.59" y1="29.21" qy="-11.43" y2="29.21" width="0.254" layer="51"/>
<wire qx="-11.43" y1="29.21" qy="-13.97" y2="26.67" width="0.254" layer="51"/>
<wire qx="-13.97" y1="26.67" qy="-25.4" y2="26.67" width="0.254" layer="51"/>
<wire qx="-25.4" y1="26.67" qy="-26.67" y2="25.4" width="0.254" layer="51"/>
<wire qx="26.67" y1="-27.94" qy="26.67" y2="26.67" width="0.254" layer="51"/>
<wire qx="-17.17" y1="-45.98" qy="-4.97" y2="-45.98" width="0.254" layer="51"/>
<wire qx="13.53" y1="-40.78" qy="22.53" y2="-40.78" width="0.254" layer="51"/>
<wire qx="-17.17" y1="-45.98" qy="-17.17" y2="-30.48" width="0.254" layer="51"/>
<wire qx="-4.97" y1="-45.98" qy="-4.97" y2="-30.48" width="0.254" layer="51"/>
<wire qx="13.53" y1="-40.78" qy="13.53" y2="-30.58" width="0.254" layer="51"/>
<wire qx="22.53" y1="-40.78" qy="22.53" y2="-30.58" width="0.254" layer="51"/>
<wire qx="-25.4" y1="25.4" qy="-22.86" y2="25.4" width="0.127" layer="51"/>
<wire qx="-22.86" y1="25.4" qy="-22.86" y2="5.08" width="0.127" layer="51"/>
<wire qx="-22.86" y1="5.08" qy="-25.4" y2="5.08" width="0.127" layer="51"/>
<wire qx="-25.4" y1="5.08" qy="-25.4" y2="25.4" width="0.127" layer="51"/>
<wire qx="-25.4" y1="3.81" qy="-22.86" y2="3.81" width="0.127" layer="51"/>
<wire qx="-22.86" y1="3.81" qy="-22.86" y2="-21.59" width="0.127" layer="51"/>
<wire qx="-22.86" y1="-21.59" qy="-25.4" y2="-21.59" width="0.127" layer="51"/>
<wire qx="-25.4" y1="-21.59" qy="-25.4" y2="3.81" width="0.127" layer="51"/>
<wire qx="22.86" y1="-12.7" qy="25.4" y2="-12.7" width="0.127" layer="51"/>
<wire qx="25.4" y1="-12.7" qy="25.4" y2="7.62" width="0.127" layer="51"/>
<wire qx="25.4" y1="7.62" qy="22.86" y2="7.62" width="0.127" layer="51"/>
<wire qx="22.86" y1="7.62" qy="22.86" y2="-12.7" width="0.127" layer="51"/>
<wire qx="25.4" y1="10.16" qy="22.86" y2="10.16" width="0.127" layer="51"/>
<wire qx="22.86" y1="10.16" qy="22.86" y2="25.4" width="0.127" layer="51"/>
<wire qx="22.86" y1="25.4" qy="25.4" y2="25.4" width="0.127" layer="51"/>
<wire qx="25.4" y1="25.4" qy="25.4" y2="10.16" width="0.127" layer="51"/>
<pad name="RES" x="24.384" y="-6.35" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="3.3V" x="24.13" y="-3.81" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="5V" x="24.384" y="-1.27" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="GND@0" x="24.13" y="1.27" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="GND@1" x="24.384" y="3.81" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="VIN" x="24.13" y="6.35" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="A0" x="24.384" y="11.43" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="A1" x="24.13" y="13.97" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="A2" x="24.384" y="16.51" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="A3" x="24.13" y="19.05" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="A4" x="24.384" y="21.59" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="A5" x="24.13" y="24.13" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="RX" x="-24.13" y="24.13" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="TX" x="-24.384" y="21.59" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="D2" x="-24.13" y="19.05" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="D3" x="-24.384" y="16.51" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="D4" x="-24.13" y="13.97" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="D5" x="-24.384" y="11.43" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="D6" x="-24.13" y="8.89" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="D7" x="-24.384" y="6.35" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="D8" x="-24.13" y="2.54" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="D9" x="-24.384" y="0" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="D10" x="-24.13" y="-2.54" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="D11" x="-24.384" y="-5.08" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="D12" x="-24.13" y="-7.62" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="D13" x="-24.384" y="-10.16" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="GND@2" x="-24.13" y="-12.7" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="AREF" x="-24.384" y="-15.24" drill="1.016" diameter="1.8796" rot="R90"/>
<text x="22.7457" y="1.778" size="1.016" layer="21" font="vector" ratio="15" rot="R180">GND</text>
<text x="22.7457" y="4.318" size="1.016" layer="21" font="vector" ratio="15" rot="R180">GND</text>
<text x="22.7457" y="-0.762" size="1.016" layer="21" font="vector" ratio="15" rot="R180">+5V</text>
<text x="22.7457" y="-5.842" size="1.016" layer="21" font="vector" ratio="15" rot="R180">RST</text>
<text x="22.7457" y="6.858" size="1.016" layer="21" font="vector" ratio="15" rot="R180">VIN</text>
<text x="22.7457" y="-3.302" size="1.016" layer="21" font="vector" ratio="15" rot="R180">+3.3V</text>
<text x="22.7457" y="11.938" size="1.016" layer="21" font="vector" ratio="15" rot="R180">0</text>
<text x="22.7457" y="14.478" size="1.016" layer="21" font="vector" ratio="15" rot="R180">1</text>
<text x="22.7457" y="17.018" size="1.016" layer="21" font="vector" ratio="15" rot="R180">2</text>
<text x="22.7457" y="19.558" size="1.016" layer="21" font="vector" ratio="15" rot="R180">3</text>
<text x="22.7457" y="22.098" size="1.016" layer="21" font="vector" ratio="15" rot="R180">4</text>
<text x="22.7457" y="24.638" size="1.016" layer="21" font="vector" ratio="15" rot="R180">5</text>
<text x="20.2057" y="21.717" size="1.016" layer="21" font="vector" ratio="15" rot="R270">Analog In</text>
<text x="-22.86" y="-13.208" size="1.016" layer="21" font="vector" ratio="15">GND</text>
<text x="-22.86" y="-10.668" size="1.016" layer="21" font="vector" ratio="15">13</text>
<text x="-22.86" y="-8.128" size="1.016" layer="21" font="vector" ratio="15">12</text>
<text x="-22.86" y="-5.588" size="1.016" layer="21" font="vector" ratio="15">11</text>
<text x="-22.86" y="-15.748" size="1.016" layer="21" font="vector" ratio="15">AREF</text>
<text x="-22.86" y="-3.048" size="1.016" layer="21" font="vector" ratio="15">10</text>
<text x="-22.86" y="-0.508" size="1.016" layer="21" font="vector" ratio="15">9</text>
<text x="-22.86" y="2.032" size="1.016" layer="21" font="vector" ratio="15">8</text>
<text x="-22.86" y="5.842" size="1.016" layer="21" font="vector" ratio="15">7</text>
<text x="-22.86" y="8.382" size="1.016" layer="21" font="vector" ratio="15">6</text>
<text x="-22.86" y="10.922" size="1.016" layer="21" font="vector" ratio="15">5</text>
<text x="-22.86" y="13.462" size="1.016" layer="21" font="vector" ratio="15">4</text>
<text x="-22.86" y="16.002" size="1.016" layer="21" font="vector" ratio="15">3</text>
<text x="-22.86" y="18.542" size="1.016" layer="21" font="vector" ratio="15">2</text>
<text x="-22.86" y="21.082" size="1.016" layer="21" font="vector" ratio="15">TX</text>
<text x="-22.86" y="23.622" size="1.016" layer="21" font="vector" ratio="15">RX</text>
<pad name="SDA" x="-24.13" y="-17.78" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="SCL" x="-24.13" y="-20.32" drill="1.016" diameter="1.8796" rot="R90"/>
<text x="-22.86" y="-18.288" size="1.016" layer="21" font="vector" ratio="15">SDA</text>
<text x="-22.86" y="-20.828" size="1.016" layer="21" font="vector" ratio="15">SCL</text>
<pad name="IOREF" x="24.13" y="-8.89" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="NC" x="24.384" y="-11.43" drill="1.016" diameter="1.8796" rot="R90"/>
<text x="22.7457" y="-8.382" size="1.016" layer="21" font="vector" ratio="15" rot="R180">IOREF</text>
<wire qx="-4.445" y1="22.86" qy="-5.08" y2="23.495" width="0.254" layer="51"/>
<wire qx="-5.08" y1="23.495" qy="-5.08" y2="27.305" width="0.254" layer="51"/>
<wire qx="-5.08" y1="27.305" qy="-4.445" y2="27.94" width="0.254" layer="51"/>
<wire qx="-4.445" y1="27.94" qy="1.905" y2="27.94" width="0.254" layer="51"/>
<wire qx="1.905" y1="27.94" qy="2.54" y2="27.305" width="0.254" layer="51"/>
<wire qx="2.54" y1="27.305" qy="2.54" y2="23.495" width="0.254" layer="51"/>
<wire qx="2.54" y1="23.495" qy="1.905" y2="22.86" width="0.254" layer="51"/>
<wire qx="1.905" y1="22.86" qy="-4.445" y2="22.86" width="0.254" layer="51"/>
<text x="0.635" y="21.971" size="0.508" layer="51" font="vector" ratio="15">RST</text>
<text x="-1.778" y="25.146" size="0.508" layer="51" font="vector" ratio="15">ISP</text>
<wire qx="-3.175" y1="22.352" qy="-4.445" y2="22.352" width="0.2032" layer="51"/>
<text x="-1.27" y="29.464" size="0.6096" layer="25" font="vector" ratio="20" align="bottom-center">&gt;NAME</text>
<text x="0" y="-30.988" size="0.6096" layer="27" font="vector" ratio="20" align="top-center">&gt;VALUE</text>
<text x="-11.43" y="-44.45" size="1.016" layer="51" font="vector" ratio="20" align="top-center">USB</text>
<text x="17.78" y="-39.37" size="1.016" layer="51" font="vector" ratio="20" align="top-center">PWR JACK</text>
</package>
<package name="UNO_R3_SHIELD_NOLABELS_LOCK">
<description>&lt;h3&gt;Arduino Uno-Compatible Footprint&lt;/h3&gt;
No holes, no ICSP connections, no silk labels.
Looking footprint for headers.
&lt;p&gt;Specifications:
&lt;ul&gt;&lt;li&gt;Pin count: 32&lt;/li&gt;
&lt;li&gt;Pin pitch: 0.1"&lt;/li&gt;
&lt;li&gt;Area:2.1x2.35"&lt;/li&gt;
&lt;/ul&gt;&lt;/p&gt;
&lt;p&gt;Example device(s):
&lt;ul&gt;&lt;li&gt;Arduino Uno R3 Shield&lt;/li&gt;
&lt;/ul&gt;&lt;/p&gt;</description>
<wire qx="-24.13" y1="-30.48" qy="-17.17" y2="-30.48" width="0.254" layer="51"/>
<wire qx="-17.17" y1="-30.48" qy="-4.97" y2="-30.48" width="0.254" layer="51"/>
<wire qx="-4.97" y1="-30.48" qy="24.13" y2="-30.48" width="0.254" layer="51"/>
<wire qx="24.13" y1="-30.48" qy="26.67" y2="-27.94" width="0.254" layer="51"/>
<wire qx="26.67" y1="26.67" qy="24.13" y2="26.67" width="0.254" layer="51"/>
<wire qx="24.13" y1="26.67" qy="21.59" y2="29.21" width="0.254" layer="51"/>
<wire qx="-26.67" y1="25.4" qy="-26.67" y2="-27.94" width="0.254" layer="51"/>
<wire qx="-26.67" y1="-27.94" qy="-24.13" y2="-30.48" width="0.254" layer="51"/>
<wire qx="21.59" y1="29.21" qy="-11.43" y2="29.21" width="0.254" layer="51"/>
<wire qx="-11.43" y1="29.21" qy="-13.97" y2="26.67" width="0.254" layer="51"/>
<wire qx="-13.97" y1="26.67" qy="-25.4" y2="26.67" width="0.254" layer="51"/>
<wire qx="-25.4" y1="26.67" qy="-26.67" y2="25.4" width="0.254" layer="51"/>
<wire qx="26.67" y1="-27.94" qy="26.67" y2="26.67" width="0.254" layer="51"/>
<wire qx="-17.17" y1="-45.98" qy="-4.97" y2="-45.98" width="0.254" layer="51"/>
<wire qx="13.53" y1="-40.78" qy="22.53" y2="-40.78" width="0.254" layer="51"/>
<wire qx="-17.17" y1="-45.98" qy="-17.17" y2="-30.48" width="0.254" layer="51"/>
<wire qx="-4.97" y1="-45.98" qy="-4.97" y2="-30.48" width="0.254" layer="51"/>
<wire qx="13.53" y1="-40.78" qy="13.53" y2="-30.58" width="0.254" layer="51"/>
<wire qx="22.53" y1="-40.78" qy="22.53" y2="-30.58" width="0.254" layer="51"/>
<wire qx="-25.4" y1="25.4" qy="-22.86" y2="25.4" width="0.127" layer="51"/>
<wire qx="-22.86" y1="25.4" qy="-22.86" y2="5.08" width="0.127" layer="51"/>
<wire qx="-22.86" y1="5.08" qy="-25.4" y2="5.08" width="0.127" layer="51"/>
<wire qx="-25.4" y1="5.08" qy="-25.4" y2="25.4" width="0.127" layer="51"/>
<wire qx="-25.4" y1="3.81" qy="-22.86" y2="3.81" width="0.127" layer="51"/>
<wire qx="-22.86" y1="3.81" qy="-22.86" y2="-21.59" width="0.127" layer="51"/>
<wire qx="-22.86" y1="-21.59" qy="-25.4" y2="-21.59" width="0.127" layer="51"/>
<wire qx="-25.4" y1="-21.59" qy="-25.4" y2="3.81" width="0.127" layer="51"/>
<wire qx="22.86" y1="-12.7" qy="25.4" y2="-12.7" width="0.127" layer="51"/>
<wire qx="25.4" y1="-12.7" qy="25.4" y2="7.62" width="0.127" layer="51"/>
<wire qx="25.4" y1="7.62" qy="22.86" y2="7.62" width="0.127" layer="51"/>
<wire qx="22.86" y1="7.62" qy="22.86" y2="-12.7" width="0.127" layer="51"/>
<wire qx="25.4" y1="10.16" qy="22.86" y2="10.16" width="0.127" layer="51"/>
<wire qx="22.86" y1="10.16" qy="22.86" y2="25.4" width="0.127" layer="51"/>
<wire qx="22.86" y1="25.4" qy="25.4" y2="25.4" width="0.127" layer="51"/>
<wire qx="25.4" y1="25.4" qy="25.4" y2="10.16" width="0.127" layer="51"/>
<pad name="RES" x="24.384" y="-6.35" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="3.3V" x="24.13" y="-3.81" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="5V" x="24.384" y="-1.27" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="GND@0" x="24.13" y="1.27" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="GND@1" x="24.384" y="3.81" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="VIN" x="24.13" y="6.35" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="A0" x="24.384" y="11.43" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="A1" x="24.13" y="13.97" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="A2" x="24.384" y="16.51" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="A3" x="24.13" y="19.05" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="A4" x="24.384" y="21.59" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="A5" x="24.13" y="24.13" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="RX" x="-24.13" y="24.13" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="TX" x="-24.384" y="21.59" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="D2" x="-24.13" y="19.05" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="D3" x="-24.384" y="16.51" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="D4" x="-24.13" y="13.97" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="D5" x="-24.384" y="11.43" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="D6" x="-24.13" y="8.89" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="D7" x="-24.384" y="6.35" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="D8" x="-24.13" y="2.54" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="D9" x="-24.384" y="0" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="D10" x="-24.13" y="-2.54" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="D11" x="-24.384" y="-5.08" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="D12" x="-24.13" y="-7.62" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="D13" x="-24.384" y="-10.16" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="GND@2" x="-24.13" y="-12.7" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="AREF" x="-24.384" y="-15.24" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="SDA" x="-24.13" y="-17.78" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="SCL" x="-24.13" y="-20.32" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="IOREF" x="24.13" y="-8.89" drill="1.016" diameter="1.8796" rot="R90"/>
<pad name="NC" x="24.384" y="-11.43" drill="1.016" diameter="1.8796" rot="R90"/>
<wire qx="-4.445" y1="22.86" qy="-5.08" y2="23.495" width="0.254" layer="51"/>
<wire qx="-5.08" y1="23.495" qy="-5.08" y2="27.305" width="0.254" layer="51"/>
<wire qx="-5.08" y1="27.305" qy="-4.445" y2="27.94" width="0.254" layer="51"/>
<wire qx="-4.445" y1="27.94" qy="1.905" y2="27.94" width="0.254" layer="51"/>
<wire qx="1.905" y1="27.94" qy="2.54" y2="27.305" width="0.254" layer="51"/>
<wire qx="2.54" y1="27.305" qy="2.54" y2="23.495" width="0.254" layer="51"/>
<wire qx="2.54" y1="23.495" qy="1.905" y2="22.86" width="0.254" layer="51"/>
<wire qx="1.905" y1="22.86" qy="-4.445" y2="22.86" width="0.254" layer="51"/>
<text x="0.635" y="21.971" size="0.508" layer="51" font="vector" ratio="15">RST</text>
<text x="-1.778" y="25.146" size="0.508" layer="51" font="vector" ratio="15">ISP</text>
<wire qx="-3.175" y1="22.352" qy="-4.445" y2="22.352" width="0.2032" layer="51"/>
<text x="-1.27" y="29.464" size="0.6096" layer="25" font="vector" ratio="20" align="bottom-center">&gt;NAME</text>
<text x="0" y="-30.734" size="0.6096" layer="27" font="vector" ratio="20" align="top-center">&gt;VALUE</text>
<text x="-11.43" y="-44.45" size="0.8128" layer="51" font="vector" ratio="20" align="top-center">USB</text>
<text x="17.78" y="-39.37" size="0.8128" layer="51" font="vector" ratio="20" align="top-center">PWR JACK</text>
</package>
</packages>
<symbols>
<symbol name="ARDUINO_R3_SHIELD">
<description>&lt;h3&gt;Arduino Uno R3-No ICSP&lt;/h3&gt;
&lt;p&gt;Symbol showing all standard pins on Arduino Uno R3 footprint (no ICSP pins)&lt;/p&gt;</description>
<wire qx="-10.16" y1="-25.4" qy="-10.16" y2="20.32" width="0.254" layer="94"/>
<wire qx="-10.16" y1="20.32" qy="10.16" y2="20.32" width="0.254" layer="94"/>
<wire qx="10.16" y1="20.32" qy="10.16" y2="-25.4" width="0.254" layer="94"/>
<wire qx="10.16" y1="-25.4" qy="-10.16" y2="-25.4" width="0.254" layer="94"/>
<text x="-9.652" y="20.574" size="1.778" layer="95" font="vector">&gt;Name</text>
<text x="-8.89" y="-25.654" size="1.778" layer="96" font="vector" align="top-left">&gt;Value</text>
<pin name="RX" x="12.7" y="17.78" visible="pin" length="short" rot="R180"/>
<pin name="TX" x="12.7" y="15.24" visible="pin" length="short" rot="R180"/>
<pin name="D2" x="12.7" y="10.16" visible="pin" length="short" rot="R180"/>
<pin name="*D3" x="12.7" y="7.62" visible="pin" length="short" rot="R180"/>
<pin name="D4" x="12.7" y="5.08" visible="pin" length="short" rot="R180"/>
<pin name="*D5" x="12.7" y="2.54" visible="pin" length="short" rot="R180"/>
<pin name="*D6" x="12.7" y="0" visible="pin" length="short" rot="R180"/>
<pin name="D7" x="12.7" y="-2.54" visible="pin" length="short" rot="R180"/>
<pin name="D8" x="12.7" y="-5.08" visible="pin" length="short" rot="R180"/>
<pin name="*D9" x="12.7" y="-7.62" visible="pin" length="short" rot="R180"/>
<pin name="*D10" x="12.7" y="-10.16" visible="pin" length="short" rot="R180"/>
<pin name="*D11" x="12.7" y="-12.7" visible="pin" length="short" rot="R180"/>
<pin name="D12" x="12.7" y="-15.24" visible="pin" length="short" rot="R180"/>
<pin name="D13" x="12.7" y="-17.78" visible="pin" length="short" rot="R180"/>
<pin name="A0" x="-12.7" y="17.78" visible="pin" length="short"/>
<pin name="A1" x="-12.7" y="15.24" visible="pin" length="short"/>
<pin name="A2" x="-12.7" y="12.7" visible="pin" length="short"/>
<pin name="A3" x="-12.7" y="10.16" visible="pin" length="short"/>
<pin name="A4" x="-12.7" y="7.62" visible="pin" length="short"/>
<pin name="A5" x="-12.7" y="5.08" visible="pin" length="short"/>
<pin name="VIN" x="-12.7" y="-7.62" visible="pin" length="short"/>
<pin name="RES" x="-12.7" y="-5.08" visible="pin" length="short"/>
<pin name="5V" x="-12.7" y="-10.16" visible="pin" length="short"/>
<pin name="AREF" x="-12.7" y="-15.24" visible="pin" length="short"/>
<pin name="GND@2" x="-12.7" y="-17.78" visible="pin" length="short"/>
<pin name="GND@1" x="-12.7" y="-20.32" visible="pin" length="short"/>
<pin name="GND@0" x="-12.7" y="-22.86" visible="pin" length="short"/>
<pin name="3.3V" x="-12.7" y="-12.7" visible="pin" length="short"/>
<pin name="IOREF" x="-12.7" y="-2.54" visible="pin" length="short"/>
<pin name="SDA" x="12.7" y="-20.32" visible="pin" length="short" rot="R180"/>
<pin name="SCL" x="12.7" y="-22.86" visible="pin" length="short" rot="R180"/>
</symbol>
</symbols>
<devicesets>
<deviceset name="ARDUINO_UNO_R3_SHIELD" prefix="B">
<description>&lt;h3&gt;Arduino R3 Shield Footprint&lt;/h3&gt;

Shield form compatible with the Arduino Uno R3.

&lt;p&gt;SparkFun Products:
&lt;ul&gt;&lt;li&gt;&lt;a href=https://www.sparkfun.com/products/13120&gt;MG2639 Cellular Shield&lt;/a&gt;&lt;/li&gt;
&lt;li&gt;&lt;a href=https://www.sparkfun.com/products/12898&gt;MIDI Shield&lt;/a&gt;
&lt;li&gt;&lt;a href=https://www.sparkfun.com/products/11417&gt;R3 Stackable Headers Kit&lt;/a&gt;
&lt;/ul&gt;&lt;/p&gt;</description>
<gates>
<gate name="G$1" symbol="ARDUINO_R3_SHIELD" x="0" y="0"/>
</gates>
<devices>
<device name="BASIC" package="UNO_R3_SHIELD">
<connects>
<connect gate="G$1" pin="*D10" pad="D10"/>
<connect gate="G$1" pin="*D11" pad="D11"/>
<connect gate="G$1" pin="*D3" pad="D3"/>
<connect gate="G$1" pin="*D5" pad="D5"/>
<connect gate="G$1" pin="*D6" pad="D6"/>
<connect gate="G$1" pin="*D9" pad="D9"/>
<connect gate="G$1" pin="3.3V" pad="3.3V"/>
<connect gate="G$1" pin="5V" pad="5V"/>
<connect gate="G$1" pin="A0" pad="A0"/>
<connect gate="G$1" pin="A1" pad="A1"/>
<connect gate="G$1" pin="A2" pad="A2"/>
<connect gate="G$1" pin="A3" pad="A3"/>
<connect gate="G$1" pin="A4" pad="A4"/>
<connect gate="G$1" pin="A5" pad="A5"/>
<connect gate="G$1" pin="AREF" pad="AREF"/>
<connect gate="G$1" pin="D12" pad="D12"/>
<connect gate="G$1" pin="D13" pad="D13"/>
<connect gate="G$1" pin="D2" pad="D2"/>
<connect gate="G$1" pin="D4" pad="D4"/>
<connect gate="G$1" pin="D7" pad="D7"/>
<connect gate="G$1" pin="D8" pad="D8"/>
<connect gate="G$1" pin="GND@0" pad="GND@0"/>
<connect gate="G$1" pin="GND@1" pad="GND@1"/>
<connect gate="G$1" pin="GND@2" pad="GND@2"/>
<connect gate="G$1" pin="IOREF" pad="IOREF"/>
<connect gate="G$1" pin="RES" pad="RES"/>
<connect gate="G$1" pin="RX" pad="RX"/>
<connect gate="G$1" pin="SCL" pad="SCL"/>
<connect gate="G$1" pin="SDA" pad="SDA"/>
<connect gate="G$1" pin="TX" pad="TX"/>
<connect gate="G$1" pin="VIN" pad="VIN"/>
</connects>
<technologies>
<technology name=""/>
</technologies>
</device>
<device name="NOLABELS" package="UNO_R3_SHIELD_NOLABELS">
<connects>
<connect gate="G$1" pin="*D10" pad="D10"/>
<connect gate="G$1" pin="*D11" pad="D11"/>
<connect gate="G$1" pin="*D3" pad="D3"/>
<connect gate="G$1" pin="*D5" pad="D5"/>
<connect gate="G$1" pin="*D6" pad="D6"/>
<connect gate="G$1" pin="*D9" pad="D9"/>
<connect gate="G$1" pin="3.3V" pad="3.3V"/>
<connect gate="G$1" pin="5V" pad="5V"/>
<connect gate="G$1" pin="A0" pad="A0"/>
<connect gate="G$1" pin="A1" pad="A1"/>
<connect gate="G$1" pin="A2" pad="A2"/>
<connect gate="G$1" pin="A3" pad="A3"/>
<connect gate="G$1" pin="A4" pad="A4"/>
<connect gate="G$1" pin="A5" pad="A5"/>
<connect gate="G$1" pin="AREF" pad="AREF"/>
<connect gate="G$1" pin="D12" pad="D12"/>
<connect gate="G$1" pin="D13" pad="D13"/>
<connect gate="G$1" pin="D2" pad="D2"/>
<connect gate="G$1" pin="D4" pad="D4"/>
<connect gate="G$1" pin="D7" pad="D7"/>
<connect gate="G$1" pin="D8" pad="D8"/>
<connect gate="G$1" pin="GND@0" pad="GND@0"/>
<connect gate="G$1" pin="GND@1" pad="GND@1"/>
<connect gate="G$1" pin="GND@2" pad="GND@2"/>
<connect gate="G$1" pin="IOREF" pad="IOREF"/>
<connect gate="G$1" pin="RES" pad="RES"/>
<connect gate="G$1" pin="RX" pad="RX"/>
<connect gate="G$1" pin="SCL" pad="SCL"/>
<connect gate="G$1" pin="SDA" pad="SDA"/>
<connect gate="G$1" pin="TX" pad="TX"/>
<connect gate="G$1" pin="VIN" pad="VIN"/>
</connects>
<technologies>
<technology name=""/>
</technologies>
</device>
<device name="UNO_R3_SHIELD_LOCK" package="UNO_R3_SHIELD_LOCK">
<connects>
<connect gate="G$1" pin="*D10" pad="D10"/>
<connect gate="G$1" pin="*D11" pad="D11"/>
<connect gate="G$1" pin="*D3" pad="D3"/>
<connect gate="G$1" pin="*D5" pad="D5"/>
<connect gate="G$1" pin="*D6" pad="D6"/>
<connect gate="G$1" pin="*D9" pad="D9"/>
<connect gate="G$1" pin="3.3V" pad="3.3V"/>
<connect gate="G$1" pin="5V" pad="5V"/>
<connect gate="G$1" pin="A0" pad="A0"/>
<connect gate="G$1" pin="A1" pad="A1"/>
<connect gate="G$1" pin="A2" pad="A2"/>
<connect gate="G$1" pin="A3" pad="A3"/>
<connect gate="G$1" pin="A4" pad="A4"/>
<connect gate="G$1" pin="A5" pad="A5"/>
<connect gate="G$1" pin="AREF" pad="AREF"/>
<connect gate="G$1" pin="D12" pad="D12"/>
<connect gate="G$1" pin="D13" pad="D13"/>
<connect gate="G$1" pin="D2" pad="D2"/>
<connect gate="G$1" pin="D4" pad="D4"/>
<connect gate="G$1" pin="D7" pad="D7"/>
<connect gate="G$1" pin="D8" pad="D8"/>
<connect gate="G$1" pin="GND@0" pad="GND@0"/>
<connect gate="G$1" pin="GND@1" pad="GND@1"/>
<connect gate="G$1" pin="GND@2" pad="GND@2"/>
<connect gate="G$1" pin="IOREF" pad="IOREF"/>
<connect gate="G$1" pin="RES" pad="RES"/>
<connect gate="G$1" pin="RX" pad="RX"/>
<connect gate="G$1" pin="SCL" pad="SCL"/>
<connect gate="G$1" pin="SDA" pad="SDA"/>
<connect gate="G$1" pin="TX" pad="TX"/>
<connect gate="G$1" pin="VIN" pad="VIN"/>
</connects>
<technologies>
<technology name=""/>
</technologies>
</device>
<device name="UNO_R3_SHIELD_NOLABELS_LOCK" package="UNO_R3_SHIELD_NOLABELS_LOCK">
<connects>
<connect gate="G$1" pin="*D10" pad="D10"/>
<connect gate="G$1" pin="*D11" pad="D11"/>
<connect gate="G$1" pin="*D3" pad="D3"/>
<connect gate="G$1" pin="*D5" pad="D5"/>
<connect gate="G$1" pin="*D6" pad="D6"/>
<connect gate="G$1" pin="*D9" pad="D9"/>
<connect gate="G$1" pin="3.3V" pad="3.3V"/>
<connect gate="G$1" pin="5V" pad="5V"/>
<connect gate="G$1" pin="A0" pad="A0"/>
<connect gate="G$1" pin="A1" pad="A1"/>
<connect gate="G$1" pin="A2" pad="A2"/>
<connect gate="G$1" pin="A3" pad="A3"/>
<connect gate="G$1" pin="A4" pad="A4"/>
<connect gate="G$1" pin="A5" pad="A5"/>
<connect gate="G$1" pin="AREF" pad="AREF"/>
<connect gate="G$1" pin="D12" pad="D12"/>
<connect gate="G$1" pin="D13" pad="D13"/>
<connect gate="G$1" pin="D2" pad="D2"/>
<connect gate="G$1" pin="D4" pad="D4"/>
<connect gate="G$1" pin="D7" pad="D7"/>
<connect gate="G$1" pin="D8" pad="D8"/>
<connect gate="G$1" pin="GND@0" pad="GND@0"/>
<connect gate="G$1" pin="GND@1" pad="GND@1"/>
<connect gate="G$1" pin="GND@2" pad="GND@2"/>
<connect gate="G$1" pin="IOREF" pad="IOREF"/>
<connect gate="G$1" pin="RES" pad="RES"/>
<connect gate="G$1" pin="RX" pad="RX"/>
<connect gate="G$1" pin="SCL" pad="SCL"/>
<connect gate="G$1" pin="SDA" pad="SDA"/>
<connect gate="G$1" pin="TX" pad="TX"/>
<connect gate="G$1" pin="VIN" pad="VIN"/>
</connects>
<technologies>
<technology name=""/>
</technologies>
</device>
</devices>
</deviceset>
</devicesets>
</library>
<library name="custom">
<packages>
<package name="LSM9DS1-BREAKOUT">
<pad name="GND" x="-8.89" y="3.81" drill="0.8" shape="long"/>
<pad name="VDD" x="-8.89" y="1.27" drill="0.8" shape="long"/>
<pad name="SDA" x="-8.89" y="-1.27" drill="0.8" shape="long"/>
<pad name="SCL" x="-8.89" y="-3.81" drill="0.8" shape="long"/>
<wire qx="-10.16" y1="5.08" qy="-10.16" y2="-5.08" width="0.127" layer="21"/>
<wire qx="-10.16" y1="-5.08" qy="12.7" y2="-5.08" width="0.127" layer="21"/>
<wire qx="12.7" y1="-5.08" qy="12.7" y2="5.08" width="0.127" layer="21"/>
<wire qx="12.7" y1="5.08" qy="-10.16" y2="5.08" width="0.127" layer="21"/>
<circle x="8.89" y="0" radius="1.79605" width="0.127" layer="21"/>
<wire qx="1.27" y1="2.54" qy="1.27" y2="-2.54" width="0.127" layer="21"/>
<wire qx="1.27" y1="-2.54" qy="-3.81" y2="-2.54" width="0.127" layer="21"/>
<wire qx="-3.81" y1="-2.54" qy="-3.81" y2="2.54" width="0.127" layer="21"/>
<wire qx="-3.81" y1="2.54" qy="1.27" y2="2.54" width="0.127" layer="21"/>
<text x="-5.08" y="2.54" size="1.27" layer="21">LSM9DS1 breakout</text>
</package>
<package name="MPL3115A2-BREAKOUT">
<pad name="SDA" x="-7.62" y="1.27" drill="0.8" shape="long"/>
<pad name="INT1" x="-7.62" y="3.81" drill="0.8" shape="long"/>
<pad name="INT2" x="-7.62" y="6.35" drill="0.8" shape="long"/>
<pad name="SCL" x="-7.62" y="-1.27" drill="0.8" shape="long"/>
<pad name="VCC" x="-7.62" y="-3.81" drill="0.8" shape="long"/>
<pad name="GND" x="-7.62" y="-6.35" drill="0.8" shape="long"/>
<wire qx="-8.89" y1="7.62" qy="-8.89" y2="-7.62" width="0.127" layer="21"/>
<wire qx="-8.89" y1="-7.62" qy="8.89" y2="-7.62" width="0.127" layer="21"/>
<wire qx="8.89" y1="-7.62" qy="8.89" y2="7.62" width="0.127" layer="21"/>
<wire qx="8.89" y1="7.62" qy="-8.89" y2="7.62" width="0.127" layer="21"/>
<circle x="6.35" y="5.08" radius="1.27" width="0.127" layer="21"/>
<circle x="6.35" y="-5.08" radius="1.27" width="0.127" layer="21"/>
<wire qx="6.35" y1="1.27" qy="1.27" y2="1.27" width="0.127" layer="21"/>
<wire qx="1.27" y1="1.27" qy="1.27" y2="-1.27" width="0.127" layer="21"/>
<wire qx="1.27" y1="-1.27" qy="6.35" y2="-1.27" width="0.127" layer="21"/>
<wire qx="6.35" y1="-1.27" qy="6.35" y2="1.27" width="0.127" layer="21"/>
<text x="-5.08" y="2.54" size="1.27" layer="21">MLP3115A2
breakout</text>
</package>
<package name="XBEE-DIL20">
<pad name="VCC" x="-6.35" y="12.7" drill="0.8" shape="long"/>
<pad name="DOUT" x="-6.35" y="10.16" drill="0.8" shape="long"/>
<pad name="DIN" x="-6.35" y="7.62" drill="0.8" shape="long"/>
<pad name="DO8" x="-6.35" y="5.08" drill="0.8" shape="long"/>
<pad name="RST" x="-6.35" y="2.54" drill="0.8" shape="long"/>
<pad name="PWM0" x="-6.35" y="0" drill="0.8" shape="long"/>
<pad name="PWM1" x="-6.35" y="-2.54" drill="0.8" shape="long"/>
<pad name="RSV" x="-6.35" y="-5.08" drill="0.8" shape="long"/>
<pad name="DTR" x="-6.35" y="-7.62" drill="0.8" shape="long"/>
<pad name="GND" x="-6.35" y="-10.16" drill="0.8" shape="long"/>
<pad name="AD0" x="6.35" y="12.7" drill="0.8" shape="long"/>
<pad name="AD1" x="6.35" y="10.16" drill="0.8" shape="long"/>
<pad name="AD2" x="6.35" y="7.62" drill="0.8" shape="long"/>
<pad name="AD3" x="6.35" y="5.08" drill="0.8" shape="long"/>
<pad name="RTS" x="6.35" y="2.54" drill="0.8" shape="long"/>
<pad name="AD5" x="6.35" y="0" drill="0.8" shape="long"/>
<pad name="VREF" x="6.35" y="-2.54" drill="0.8" shape="long"/>
<pad name="ON" x="6.35" y="-5.08" drill="0.8" shape="long"/>
<pad name="CTS" x="6.35" y="-7.62" drill="0.8" shape="long"/>
<pad name="AD4" x="6.35" y="-10.16" drill="0.8" shape="long"/>
<wire qx="-12.7" y1="13.97" qy="-12.7" y2="-11.43" width="0.127" layer="21"/>
<wire qx="-12.7" y1="-11.43" qy="12.7" y2="-11.43" width="0.127" layer="21"/>
<wire qx="12.7" y1="-11.43" qy="12.7" y2="13.97" width="0.127" layer="21"/>
<wire qx="12.7" y1="13.97" qy="6.35" y2="13.97" width="0.127" layer="21"/>
<wire qx="6.35" y1="13.97" qy="-6.35" y2="13.97" width="0.127" layer="21"/>
<wire qx="-6.35" y1="13.97" qy="-12.7" y2="13.97" width="0.127" layer="21"/>
<wire qx="-6.35" y1="13.97" qy="-3.81" y2="16.51" width="0.127" layer="21"/>
<wire qx="-3.81" y1="16.51" qy="3.81" y2="16.51" width="0.127" layer="21"/>
<wire qx="3.81" y1="16.51" qy="6.35" y2="13.97" width="0.127" layer="21"/>
<text x="-2.54" y="1.27" size="1.27" layer="21">XBee
breakout</text>
</package>
<package name="5LN01SP_NMOS">
<description>NMOS</description>
<pad name="SOURCE" x="-1.27" y="0" drill="0.8"/>
<pad name="GATE" x="1.27" y="0" drill="0.8"/>
<pad name="DRAIN" x="0" y="2.54" drill="0.8"/>
<wire qx="-2.54" y1="0" qy="2.54" y2="0" width="0.127" layer="21" curve="-180"/>
<wire qx="-2.54" y1="0" qy="2.54" y2="0" width="0.127" layer="21"/>
<text x="-3.81" y="3.81" size="1.27" layer="21">5LN01SP</text>
</package>
</packages>
<symbols>
<symbol name="LSM9DS1-BREAKOUT">
<wire qx="-7.62" y1="7.62" qy="-7.62" y2="-5.08" width="0.254" layer="94"/>
<wire qx="-7.62" y1="-5.08" qy="17.78" y2="-5.08" width="0.254" layer="94"/>
<wire qx="17.78" y1="-5.08" qy="17.78" y2="7.62" width="0.254" layer="94"/>
<wire qx="17.78" y1="7.62" qy="-7.62" y2="7.62" width="0.254" layer="94"/>
<pin name="GND" x="-12.7" y="5.08" length="middle"/>
<pin name="VDD" x="-12.7" y="2.54" length="middle"/>
<pin name="SDA" x="-12.7" y="0" length="middle"/>
<pin name="SCL" x="-12.7" y="-2.54" length="middle"/>
<text x="5.08" y="0" size="1.778" layer="94">LSM9DS1
breakout</text>
</symbol>
<symbol name="MPL3115A2-BREAKOUT">
<wire qx="-5.08" y1="10.16" qy="7.62" y2="10.16" width="0.254" layer="94"/>
<wire qx="7.62" y1="10.16" qy="7.62" y2="-7.62" width="0.254" layer="94"/>
<wire qx="7.62" y1="-7.62" qy="-5.08" y2="-7.62" width="0.254" layer="94"/>
<wire qx="-5.08" y1="-7.62" qy="-5.08" y2="10.16" width="0.254" layer="94"/>
<pin name="INT2" x="-10.16" y="7.62" length="middle"/>
<pin name="INT1" x="-10.16" y="5.08" length="middle"/>
<pin name="SDA" x="-10.16" y="2.54" length="middle"/>
<pin name="SCL" x="-10.16" y="0" length="middle"/>
<pin name="VCC" x="-10.16" y="-2.54" length="middle"/>
<pin name="GND" x="-10.16" y="-5.08" length="middle"/>
<text x="-10.16" y="12.7" size="1.778" layer="94">MPL3115A2 breakout</text>
</symbol>
<symbol name="XBEE-BREAKOUT">
<wire qx="-10.16" y1="-12.7" qy="-10.16" y2="15.24" width="0.254" layer="94"/>
<wire qx="-10.16" y1="15.24" qy="10.16" y2="15.24" width="0.254" layer="94"/>
<wire qx="10.16" y1="15.24" qy="10.16" y2="-12.7" width="0.254" layer="94"/>
<wire qx="10.16" y1="-12.7" qy="-10.16" y2="-12.7" width="0.254" layer="94"/>
<pin name="VCC" x="-15.24" y="12.7" length="middle"/>
<pin name="DOUT" x="-15.24" y="10.16" length="middle"/>
<pin name="DIN" x="-15.24" y="7.62" length="middle"/>
<pin name="DO8" x="-15.24" y="5.08" length="middle"/>
<pin name="RST" x="-15.24" y="2.54" length="middle"/>
<pin name="PWM0" x="-15.24" y="0" length="middle"/>
<pin name="PWM1" x="-15.24" y="-2.54" length="middle"/>
<pin name="RSV" x="-15.24" y="-5.08" length="middle"/>
<pin name="DTR" x="-15.24" y="-7.62" length="middle"/>
<pin name="GND" x="-15.24" y="-10.16" length="middle"/>
<pin name="AD0" x="15.24" y="12.7" length="middle" rot="R180"/>
<pin name="AD1" x="15.24" y="10.16" length="middle" rot="R180"/>
<pin name="AD2" x="15.24" y="7.62" length="middle" rot="R180"/>
<pin name="AD3" x="15.24" y="5.08" length="middle" rot="R180"/>
<pin name="RTS" x="15.24" y="2.54" length="middle" rot="R180"/>
<pin name="AD5" x="15.24" y="0" length="middle" rot="R180"/>
<pin name="VREF" x="15.24" y="-2.54" length="middle" rot="R180"/>
<pin name="ON" x="15.24" y="-5.08" length="middle" rot="R180"/>
<pin name="CTS" x="15.24" y="-7.62" length="middle" rot="R180"/>
<pin name="AD4" x="15.24" y="-10.16" length="middle" rot="R180"/>
<text x="-7.62" y="17.78" size="1.778" layer="94">XBee breakout</text>
</symbol>
<symbol name="5LN01SP_NMOS">
<description>NMOS</description>
<wire qx="-5.08" y1="1.27" qy="-5.08" y2="-1.27" width="0.254" layer="94"/>
<wire qx="-2.54" y1="-3.81" qy="-2.54" y2="-2.54" width="0.254" layer="94"/>
<wire qx="-2.54" y1="-2.54" qy="-2.54" y2="2.54" width="0.254" layer="94"/>
<wire qx="-2.54" y1="2.54" qy="-2.54" y2="3.81" width="0.254" layer="94"/>
<wire qx="-2.54" y1="-2.54" qy="0" y2="-2.54" width="0.254" layer="94"/>
<wire qx="-2.54" y1="2.54" qy="2.54" y2="2.54" width="0.254" layer="94"/>
<polygon width="0.254" layer="94">
<vertex x="0" y="-1.27"/>
<vertex x="0" y="-3.81"/>
<vertex x="2.54" y="-2.54"/>
</polygon>
<pin name="GATE" x="-10.16" y="0" length="middle"/>
<pin name="DRAIN" x="2.54" y="7.62" length="middle" rot="R270"/>
<pin name="SOURCE" x="2.54" y="-7.62" length="middle" rot="R90"/>
</symbol>
</symbols>
<devicesets>
<deviceset name="LSM9DS1-BREAKOUT">
<gates>
<gate name="G$1" symbol="LSM9DS1-BREAKOUT" x="-5.08" y="-2.54"/>
</gates>
<devices>
<device name="" package="LSM9DS1-BREAKOUT">
<connects>
<connect gate="G$1" pin="GND" pad="GND"/>
<connect gate="G$1" pin="SCL" pad="SCL"/>
<connect gate="G$1" pin="SDA" pad="SDA"/>
<connect gate="G$1" pin="VDD" pad="VDD"/>
</connects>
<technologies>
<technology name=""/>
</technologies>
</device>
</devices>
</deviceset>
<deviceset name="MPL3115A2-BREAKOUT">
<description>Pressure sensor / altimeter</description>
<gates>
<gate name="G$1" symbol="MPL3115A2-BREAKOUT" x="0" y="0"/>
</gates>
<devices>
<device name="" package="MPL3115A2-BREAKOUT">
<connects>
<connect gate="G$1" pin="GND" pad="GND"/>
<connect gate="G$1" pin="INT1" pad="INT1"/>
<connect gate="G$1" pin="INT2" pad="INT2"/>
<connect gate="G$1" pin="SCL" pad="SCL"/>
<connect gate="G$1" pin="SDA" pad="SDA"/>
<connect gate="G$1" pin="VCC" pad="VCC"/>
</connects>
<technologies>
<technology name=""/>
</technologies>
</device>
</devices>
</deviceset>
<deviceset name="XBEE-MODULE">
<gates>
<gate name="G$1" symbol="XBEE-BREAKOUT" x="-10.16" y="5.08"/>
</gates>
<devices>
<device name="" package="XBEE-DIL20">
<connects>
<connect gate="G$1" pin="AD0" pad="AD0"/>
<connect gate="G$1" pin="AD1" pad="AD1"/>
<connect gate="G$1" pin="AD2" pad="AD2"/>
<connect gate="G$1" pin="AD3" pad="AD3"/>
<connect gate="G$1" pin="AD4" pad="AD4"/>
<connect gate="G$1" pin="AD5" pad="AD5"/>
<connect gate="G$1" pin="CTS" pad="CTS"/>
<connect gate="G$1" pin="DIN" pad="DIN"/>
<connect gate="G$1" pin="DO8" pad="DO8"/>
<connect gate="G$1" pin="DOUT" pad="DOUT"/>
<connect gate="G$1" pin="DTR" pad="DTR"/>
<connect gate="G$1" pin="GND" pad="GND"/>
<connect gate="G$1" pin="ON" pad="ON"/>
<connect gate="G$1" pin="PWM0" pad="PWM0"/>
<connect gate="G$1" pin="PWM1" pad="PWM1"/>
<connect gate="G$1" pin="RST" pad="RST"/>
<connect gate="G$1" pin="RSV" pad="RSV"/>
<connect gate="G$1" pin="RTS" pad="RTS"/>
<connect gate="G$1" pin="VCC" pad="VCC"/>
<connect gate="G$1" pin="VREF" pad="VREF"/>
</connects>
<technologies>
<technology name=""/>
</technologies>
</device>
</devices>
</deviceset>
<deviceset name="5LN01SP_NMOS">
<gates>
<gate name="G$1" symbol="5LN01SP_NMOS" x="2.54" y="0"/>
</gates>
<devices>
<device name="" package="5LN01SP_NMOS">
<connects>
<connect gate="G$1" pin="DRAIN" pad="DRAIN"/>
<connect gate="G$1" pin="GATE" pad="GATE"/>
<connect gate="G$1" pin="SOURCE" pad="SOURCE"/>
</connects>
<technologies>
<technology name=""/>
</technologies>
</device>
</devices>
</deviceset>
</devicesets>
</library>
<library name="SparkFun-Resistors">
<description>&lt;h3&gt;SparkFun Resistors&lt;/h3&gt;
This library contains resistors. Reference designator:R. 
&lt;br&gt;
&lt;br&gt;
We've spent an enormous amount of time creating and checking these footprints and parts, but it is &lt;b&gt; the end user's responsibility&lt;/b&gt; to ensure correctness and suitablity for a given componet or application. 
&lt;br&gt;
&lt;br&gt;If you enjoy using this library, please buy one of our products at &lt;a href=" www.sparkfun.com"&gt;SparkFun.com&lt;/a&gt;.
&lt;br&gt;
&lt;br&gt;
&lt;b&gt;Licensing:&lt;/b&gt; Creative Commons ShareAlike 4.0 International - https://creativecommons.org/licenses/by-sa/4.0/ 
&lt;br&gt;
&lt;br&gt;
You are welcome to use this library for commercial purposes. For attribution, we ask that when you begin to sell your device using our footprint, you email us with a link to the product being sold. We want bragging rights that we helped (in a very small part) to create your 8th world wonder. We would like the opportunity to feature your device on our homepage.</description>
<packages>
<package name="AXIAL-0.3">
<description>&lt;h3&gt;AXIAL-0.3&lt;/h3&gt;
&lt;p&gt;Commonly used for 1/4W through-hole resistors. 0.3" pitch between holes.&lt;/p&gt;</description>
<wire qx="-2.54" y1="0.762" qy="2.54" y2="0.762" width="0.2032" layer="21"/>
<wire qx="2.54" y1="0.762" qy="2.54" y2="0" width="0.2032" layer="21"/>
<wire qx="2.54" y1="0" qy="2.54" y2="-0.762" width="0.2032" layer="21"/>
<wire qx="2.54" y1="-0.762" qy="-2.54" y2="-0.762" width="0.2032" layer="21"/>
<wire qx="-2.54" y1="-0.762" qy="-2.54" y2="0" width="0.2032" layer="21"/>
<wire qx="-2.54" y1="0" qy="-2.54" y2="0.762" width="0.2032" layer="21"/>
<wire qx="2.54" y1="0" qy="2.794" y2="0" width="0.2032" layer="21"/>
<wire qx="-2.54" y1="0" qy="-2.794" y2="0" width="0.2032" layer="21"/>
<pad name="P$1" x="-3.81" y="0" drill="0.9" diameter="1.8796"/>
<pad name="P$2" x="3.81" y="0" drill="0.9" diameter="1.8796"/>
<text x="0" y="1.016" size="0.6096" layer="25" font="vector" ratio="20" align="bottom-center">&gt;Name</text>
<text x="0" y="-1.016" size="0.6096" layer="27" font="vector" ratio="20" align="top-center">&gt;Value</text>
</package>
<package name="AXIAL-0.1">
<description>&lt;h3&gt;AXIAL-0.1&lt;/h3&gt;
&lt;p&gt;Commonly used for 1/4W through-hole resistors. 0.1" pitch between holes.&lt;/p&gt;</description>
<wire qx="0" y1="-0.762" qy="0" y2="0" width="0.2032" layer="21"/>
<wire qx="0" y1="0" qy="0" y2="0.762" width="0.2032" layer="21"/>
<wire qx="0.254" y1="0" qy="0" y2="0" width="0.2032" layer="21"/>
<wire qx="0" y1="0" qy="-0.254" y2="0" width="0.2032" layer="21"/>
<pad name="P$1" x="-1.27" y="0" drill="0.9" diameter="1.8796"/>
<pad name="P$2" x="1.27" y="0" drill="0.9" diameter="1.8796"/>
<text x="0" y="1.143" size="0.6096" layer="25" font="vector" ratio="20" align="bottom-center">&gt;Name</text>
<text x="0" y="-1.143" size="0.6096" layer="21" font="vector" ratio="20" align="top-center">&gt;Value</text>
</package>
<package name="AXIAL-0.1-KIT">
<description>&lt;h3&gt;AXIAL-0.1-KIT&lt;/h3&gt;
&lt;p&gt;Commonly used for 1/4W through-hole resistors. 0.1" pitch between holes.&lt;/p&gt;
&lt;p&gt;&lt;b&gt;Warning:&lt;/b&gt; This is the KIT version of the AXIAL-0.1 package. This package has a smaller diameter top stop mask, which doesn't cover the diameter of the pad. This means only the bottom side of the pads' copper will be exposed. You'll only be able to solder to the bottom side.&lt;/p&gt;</description>
<wire qx="0" y1="-0.762" qy="0" y2="0" width="0.2032" layer="21"/>
<wire qx="0" y1="0" qy="0" y2="0.762" width="0.2032" layer="21"/>
<wire qx="0.254" y1="0" qy="0" y2="0" width="0.2032" layer="21"/>
<wire qx="0" y1="0" qy="-0.254" y2="0" width="0.2032" layer="21"/>
<pad name="P$1" x="-1.27" y="0" drill="0.9" diameter="1.8796" stop="no"/>
<pad name="P$2" x="1.27" y="0" drill="0.9" diameter="1.8796" stop="no"/>
<text x="0" y="1.143" size="0.6096" layer="25" font="vector" ratio="20" align="bottom-center">&gt;Name</text>
<text x="0" y="-1.143" size="0.6096" layer="27" font="vector" ratio="20" align="top-center">&gt;Value</text>
<circle x="-1.27" y="0" radius="0.4572" width="0" layer="29"/>
<circle x="-1.27" y="0" radius="1.016" width="0" layer="30"/>
<circle x="1.27" y="0" radius="1.016" width="0" layer="30"/>
<circle x="-1.27" y="0" radius="0.4572" width="0" layer="29"/>
<circle x="1.27" y="0" radius="0.4572" width="0" layer="29"/>
</package>
<package name="AXIAL-0.3-KIT">
<description>&lt;h3&gt;AXIAL-0.3-KIT&lt;/h3&gt;
&lt;p&gt;Commonly used for 1/4W through-hole resistors. 0.3" pitch between holes.&lt;/p&gt;
&lt;p&gt;&lt;b&gt;Warning:&lt;/b&gt; This is the KIT version of the AXIAL-0.3 package. This package has a smaller diameter top stop mask, which doesn't cover the diameter of the pad. This means only the bottom side of the pads' copper will be exposed. You'll only be able to solder to the bottom side.&lt;/p&gt;</description>
<wire qx="-2.54" y1="1.27" qy="2.54" y2="1.27" width="0.254" layer="21"/>
<wire qx="2.54" y1="1.27" qy="2.54" y2="0" width="0.254" layer="21"/>
<wire qx="2.54" y1="0" qy="2.54" y2="-1.27" width="0.254" layer="21"/>
<wire qx="2.54" y1="-1.27" qy="-2.54" y2="-1.27" width="0.254" layer="21"/>
<wire qx="-2.54" y1="-1.27" qy="-2.54" y2="0" width="0.254" layer="21"/>
<wire qx="-2.54" y1="0" qy="-2.54" y2="1.27" width="0.254" layer="21"/>
<wire qx="2.54" y1="0" qy="2.794" y2="0" width="0.254" layer="21"/>
<wire qx="-2.54" y1="0" qy="-2.794" y2="0" width="0.254" layer="21"/>
<pad name="P$1" x="-3.81" y="0" drill="1.016" diameter="2.032" stop="no"/>
<pad name="P$2" x="3.81" y="0" drill="1.016" diameter="2.032" stop="no"/>
<text x="0" y="1.524" size="0.6096" layer="25" font="vector" ratio="20" align="bottom-center">&gt;NAME</text>
<text x="0" y="-1.524" size="0.6096" layer="27" font="vector" ratio="20" align="top-center">&gt;VALUE</text>
<polygon width="0.127" layer="30">
<vertex x="3.8201" y="-0.9449" curve="-90"/>
<vertex x="2.8652" y="-0.0152" curve="-90.011749"/>
<vertex x="3.8176" y="0.9602" curve="-90"/>
<vertex x="4.7676" y="-0.0178" curve="-90.024193"/>
</polygon>
<polygon width="0.127" layer="29">
<vertex x="3.8176" y="-0.4369" curve="-90.012891"/>
<vertex x="3.3731" y="-0.0127" curve="-90"/>
<vertex x="3.8176" y="0.4546" curve="-90"/>
<vertex x="4.2595" y="-0.0025" curve="-90.012967"/>
</polygon>
<polygon width="0.127" layer="30">
<vertex x="-3.8075" y="-0.9525" curve="-90"/>
<vertex x="-4.7624" y="-0.0228" curve="-90.011749"/>
<vertex x="-3.81" y="0.9526" curve="-90"/>
<vertex x="-2.86" y="-0.0254" curve="-90.024193"/>
</polygon>
<polygon width="0.127" layer="29">
<vertex x="-3.81" y="-0.4445" curve="-90.012891"/>
<vertex x="-4.2545" y="-0.0203" curve="-90"/>
<vertex x="-3.81" y="0.447" curve="-90"/>
<vertex x="-3.3681" y="-0.0101" curve="-90.012967"/>
</polygon>
</package>
<package name="0603">
<description>&lt;p&gt;&lt;b&gt;Generic 1608 (0603) package&lt;/b&gt;&lt;/p&gt;
&lt;p&gt;0.2mm courtyard excess rounded to nearest 0.05mm.&lt;/p&gt;</description>
<wire qx="-1.6" y1="0.7" qy="1.6" y2="0.7" width="0.0508" layer="39"/>
<wire qx="1.6" y1="0.7" qy="1.6" y2="-0.7" width="0.0508" layer="39"/>
<wire qx="1.6" y1="-0.7" qy="-1.6" y2="-0.7" width="0.0508" layer="39"/>
<wire qx="-1.6" y1="-0.7" qy="-1.6" y2="0.7" width="0.0508" layer="39"/>
<wire qx="-0.356" y1="0.432" qy="0.356" y2="0.432" width="0.1016" layer="51"/>
<wire qx="-0.356" y1="-0.419" qy="0.356" y2="-0.419" width="0.1016" layer="51"/>
<smd name="1" x="-0.85" y="0" dx="1.1" dy="1" layer="1"/>
<smd name="2" x="0.85" y="0" dx="1.1" dy="1" layer="1"/>
<text x="0" y="0.762" size="0.6096" layer="25" font="vector" ratio="20" align="bottom-center">&gt;NAME</text>
<text x="0" y="-0.762" size="0.6096" layer="27" font="vector" ratio="20" align="top-center">&gt;VALUE</text>
<rectangle qx="-0.8382" y1="-0.4699" qy="-0.3381" y2="0.4801" layer="51"/>
<rectangle qx="0.3302" y1="-0.4699" qy="0.8303" y2="0.4801" layer="51"/>
<rectangle qx="-0.1999" y1="-0.3" qy="0.1999" y2="0.3" layer="35"/>
</package>
</packages>
<symbols>
<symbol name="RESISTOR">
<wire qx="-2.54" y1="0" qy="-2.159" y2="1.016" width="0.1524" layer="94"/>
<wire qx="-2.159" y1="1.016" qy="-1.524" y2="-1.016" width="0.1524" layer="94"/>
<wire qx="-1.524" y1="-1.016" qy="-0.889" y2="1.016" width="0.1524" layer="94"/>
<wire qx="-0.889" y1="1.016" qy="-0.254" y2="-1.016" width="0.1524" layer="94"/>
<wire qx="-0.254" y1="-1.016" qy="0.381" y2="1.016" width="0.1524" layer="94"/>
<wire qx="0.381" y1="1.016" qy="1.016" y2="-1.016" width="0.1524" layer="94"/>
<wire qx="1.016" y1="-1.016" qy="1.651" y2="1.016" width="0.1524" layer="94"/>
<wire qx="1.651" y1="1.016" qy="2.286" y2="-1.016" width="0.1524" layer="94"/>
<wire qx="2.286" y1="-1.016" qy="2.54" y2="0" width="0.1524" layer="94"/>
<text x="0" y="1.524" size="1.778" layer="95" font="vector" align="bottom-center">&gt;NAME</text>
<text x="0" y="-1.524" size="1.778" layer="96" font="vector" align="top-center">&gt;VALUE</text>
<pin name="2" x="5.08" y="0" visible="off" length="short" direction="pas" swaplevel="1" rot="R180"/>
<pin name="1" x="-5.08" y="0" visible="off" length="short" direction="pas" swaplevel="1"/>
</symbol>
</symbols>
<devicesets>
<deviceset name="10KOHM" prefix="R">
<description>&lt;h3&gt;10kΩ resistor&lt;/h3&gt;
&lt;p&gt;A resistor is a passive two-terminal electrical component that implements electrical resistance as a circuit element. Resistors act to reduce current flow, and, at the same time, act to lower voltage levels within circuits. - Wikipedia&lt;/p&gt;</description>
<gates>
<gate name="G$1" symbol="RESISTOR" x="0" y="0"/>
</gates>
<devices>
<device name="-HORIZ-1/4W-1%" package="AXIAL-0.3">
<connects>
<connect gate="G$1" pin="1" pad="P$1"/>
<connect gate="G$1" pin="2" pad="P$2"/>
</connects>
<technologies>
<technology name="">
<attribute name="PROD_ID" value="RES-12183" constant="no"/>
<attribute name="VALUE" value="10k" constant="no"/>
</technology>
</technologies>
</device>
<device name="-VERT-1/4W-1%" package="AXIAL-0.1">
<connects>
<connect gate="G$1" pin="1" pad="P$1"/>
<connect gate="G$1" pin="2" pad="P$2"/>
</connects>
<technologies>
<technology name="">
<attribute name="PROD_ID" value="RES-12183"/>
<attribute name="VALUE" value="10k"/>
</technology>
</technologies>
</device>
<device name="-VERT_KIT-1/4W-1%" package="AXIAL-0.1-KIT">
<connects>
<connect gate="G$1" pin="1" pad="P$1"/>
<connect gate="G$1" pin="2" pad="P$2"/>
</connects>
<technologies>
<technology name="">
<attribute name="PROD_ID" value="RES-12183" constant="no"/>
<attribute name="VALUE" value="10k" constant="no"/>
</technology>
</technologies>
</device>
<device name="-VERT-1/4W-5%" package="AXIAL-0.1">
<connects>
<connect gate="G$1" pin="1" pad="P$1"/>
<connect gate="G$1" pin="2" pad="P$2"/>
</connects>
<technologies>
<technology name="">
<attribute name="PROD_ID" value="RES-09435"/>
<attribute name="VALUE" value="10k"/>
</technology>
</technologies>
</device>
<device name="-VERT_KIT-1/4W-5%" package="AXIAL-0.1-KIT">
<connects>
<connect gate="G$1" pin="1" pad="P$1"/>
<connect gate="G$1" pin="2" pad="P$2"/>
</connects>
<technologies>
<technology name="">
<attribute name="PROD_ID" value="RES-09435"/>
<attribute name="VALUE" value="10k"/>
</technology>
</technologies>
</device>
<device name="-HORIZ-1/4W-5%" package="AXIAL-0.3">
<connects>
<connect gate="G$1" pin="1" pad="P$1"/>
<connect gate="G$1" pin="2" pad="P$2"/>
</connects>
<technologies>
<technology name="">
<attribute name="PROD_ID" value="RES-09435"/>
<attribute name="VALUE" value="10k"/>
</technology>
</technologies>
</device>
<device name="-HORIZ_KIT-1/4W-5%" package="AXIAL-0.3-KIT">
<connects>
<connect gate="G$1" pin="1" pad="P$1"/>
<connect gate="G$1" pin="2" pad="P$2"/>
</connects>
<technologies>
<technology name="">
<attribute name="PROD_ID" value="RES-09435"/>
<attribute name="VALUE" value="10k"/>
</technology>
</technologies>
</device>
<device name="-HORIZ_KIT-1/4W-1%" package="AXIAL-0.3-KIT">
<connects>
<connect gate="G$1" pin="1" pad="P$1"/>
<connect gate="G$1" pin="2" pad="P$2"/>
</connects>
<technologies>
<technology name="">
<attribute name="PROD_ID" value="RES-12183"/>
<attribute name="VALUE" value="10k"/>
</technology>
</technologies>
</device>
<device name="-VERT-1/6W-5%" package="AXIAL-0.1">
<connects>
<connect gate="G$1" pin="1" pad="P$1"/>
<connect gate="G$1" pin="2" pad="P$2"/>
</connects>
<technologies>
<technology name="">
<attribute name="PROD_ID" value="RES-08375"/>
<attribute name="VALUE" value="10k"/>
</technology>
</technologies>
</device>
<device name="-VERT_KIT-1/6W-5%" package="AXIAL-0.1-KIT">
<connects>
<connect gate="G$1" pin="1" pad="P$1"/>
<connect gate="G$1" pin="2" pad="P$2"/>
</connects>
<technologies>
<technology name="">
<attribute name="PROD_ID" value="RES-08375"/>
<attribute name="VALUE" value="10k"/>
</technology>
</technologies>
</device>
<device name="-HORIZ-1/6W-5%" package="AXIAL-0.3">
<connects>
<connect gate="G$1" pin="1" pad="P$1"/>
<connect gate="G$1" pin="2" pad="P$2"/>
</connects>
<technologies>
<technology name="">
<attribute name="PROD_ID" value="RES-08375"/>
<attribute name="VALUE" value="10k"/>
</technology>
</technologies>
</device>
<device name="-HORIZ_KIT-1/6W-5%" package="AXIAL-0.3-KIT">
<connects>
<connect gate="G$1" pin="1" pad="P$1"/>
<connect gate="G$1" pin="2" pad="P$2"/>
</connects>
<technologies>
<technology name="">
<attribute name="PROD_ID" value="RES-08375"/>
<attribute name="VALUE" value="10k"/>
</technology>
</technologies>
</device>
<device name="-0603-1/10W-1%" package="0603">
<connects>
<connect gate="G$1" pin="1" pad="1"/>
<connect gate="G$1" pin="2" pad="2"/>
</connects>
<technologies>
<technology name="">
<attribute name="PROD_ID" value="RES-00824"/>
<attribute name="VALUE" value="10k"/>
</technology>
</technologies>
</device>
</devices>
</deviceset>
</devicesets>
</library>
</libraries>
<attributes>
</attributes>
<variantdefs>
</variantdefs>
<classes>
<class number="0" name="default" width="0" drill="0">
</class>
</classes>
<parts>
<part name="B1" library="SparkFun-Boards" deviceset="ARDUINO_UNO_R3_SHIELD" device="BASIC"/>
<part name="U$1" library="custom" deviceset="LSM9DS1-BREAKOUT" device=""/>
<part name="U$2" library="custom" deviceset="MPL3115A2-BREAKOUT" device=""/>
<part name="U$3" library="custom" deviceset="XBEE-MODULE" device=""/>
<part name="R1" library="SparkFun-Resistors" deviceset="10KOHM" device="-HORIZ-1/4W-5%" value="10k"/>
<part name="R2" library="SparkFun-Resistors" deviceset="10KOHM" device="-HORIZ-1/4W-5%" value="10k"/>
<part name="R3" library="SparkFun-Resistors" deviceset="10KOHM" device="-HORIZ-1/4W-5%" value="10k"/>
<part name="R4" library="SparkFun-Resistors" deviceset="10KOHM" device="-HORIZ-1/4W-5%" value="10k"/>
<part name="R5" library="SparkFun-Resistors" deviceset="10KOHM" device="-HORIZ-1/4W-5%" value="10k"/>
<part name="R6" library="SparkFun-Resistors" deviceset="10KOHM" device="-HORIZ-1/4W-5%" value="10k"/>
<part name="R7" library="SparkFun-Resistors" deviceset="10KOHM" device="-HORIZ-1/4W-5%" value="10k"/>
<part name="R8" library="SparkFun-Resistors" deviceset="10KOHM" device="-HORIZ-1/4W-5%" value="10k"/>
<part name="U$4" library="custom" deviceset="5LN01SP_NMOS" device=""/>
<part name="U$5" library="custom" deviceset="5LN01SP_NMOS" device=""/>
<part name="U$6" library="custom" deviceset="5LN01SP_NMOS" device=""/>
<part name="U$7" library="custom" deviceset="5LN01SP_NMOS" device=""/>
</parts>
<sheets>
<sheet>
<plain>
</plain>
<instances>
<instance part="B1" gate="G$1" x="20.32" y="73.66"/>
<instance part="U$1" gate="G$1" x="139.7" y="86.36"/>
<instance part="U$2" gate="G$1" x="144.78" y="66.04"/>
<instance part="U$3" gate="G$1" x="134.62" y="27.94"/>
<instance part="R1" gate="G$1" x="63.5" y="81.28" rot="R90"/>
<instance part="R2" gate="G$1" x="83.82" y="81.28" rot="R90"/>
<instance part="R3" gate="G$1" x="63.5" y="60.96" rot="R90"/>
<instance part="R4" gate="G$1" x="83.82" y="60.96" rot="R90"/>
<instance part="R5" gate="G$1" x="63.5" y="40.64" rot="R90"/>
<instance part="R6" gate="G$1" x="83.82" y="40.64" rot="R90"/>
<instance part="R7" gate="G$1" x="63.5" y="20.32" rot="R90"/>
<instance part="R8" gate="G$1" x="83.82" y="20.32" rot="R90"/>
<instance part="U$4" gate="G$1" x="73.66" y="86.36" rot="R90"/>
<instance part="U$5" gate="G$1" x="73.66" y="66.04" rot="R90"/>
<instance part="U$6" gate="G$1" x="73.66" y="45.72" rot="R90"/>
<instance part="U$7" gate="G$1" x="73.66" y="25.4" rot="R90"/>
</instances>
<busses>
</busses>
<nets>
<net name="N$3" class="0">
<segment>
<pinref part="B1" gate="G$1" pin="GND@0"/>
<pinref part="B1" gate="G$1" pin="GND@1"/>
<pinref part="B1" gate="G$1" pin="GND@2"/>
<wire qx="7.62" y1="55.88" qy="7.62" y2="53.34" width="0.1524" layer="91"/>
<junction x="7.62" y="53.34"/>
<wire qx="7.62" y1="53.34" qy="7.62" y2="50.8" width="0.1524" layer="91"/>
<junction x="7.62" y="50.8"/>
<wire qx="7.62" y1="50.8" qy="7.62" y2="7.62" width="0.1524" layer="91"/>
<wire qx="109.22" y1="7.62" qy="109.22" y2="17.78" width="0.1524" layer="91"/>
<pinref part="U$1" gate="G$1" pin="GND"/>
<wire qx="109.22" y1="17.78" qy="109.22" y2="60.96" width="0.1524" layer="91"/>
<wire qx="109.22" y1="60.96" qy="109.22" y2="91.44" width="0.1524" layer="91"/>
<wire qx="109.22" y1="91.44" qy="127" y2="91.44" width="0.1524" layer="91"/>
<pinref part="U$2" gate="G$1" pin="GND"/>
<wire qx="134.62" y1="60.96" qy="109.22" y2="60.96" width="0.1524" layer="91"/>
<junction x="109.22" y="60.96"/>
<pinref part="U$3" gate="G$1" pin="GND"/>
<wire qx="119.38" y1="17.78" qy="109.22" y2="17.78" width="0.1524" layer="91"/>
<junction x="109.22" y="17.78"/>
<wire qx="7.62" y1="7.62" qy="109.22" y2="7.62" width="0.1524" layer="91"/>
</segment>
</net>
<net name="N$6" class="0">
<segment>
<pinref part="U$4" gate="G$1" pin="GATE"/>
<pinref part="R2" gate="G$1" pin="1"/>
<wire qx="73.66" y1="76.2" qy="83.82" y2="76.2" width="0.1524" layer="91"/>
<pinref part="U$5" gate="G$1" pin="GATE"/>
<pinref part="R4" gate="G$1" pin="1"/>
<wire qx="73.66" y1="55.88" qy="83.82" y2="55.88" width="0.1524" layer="91"/>
<pinref part="U$6" gate="G$1" pin="GATE"/>
<pinref part="R6" gate="G$1" pin="1"/>
<wire qx="73.66" y1="35.56" qy="83.82" y2="35.56" width="0.1524" layer="91"/>
<pinref part="U$7" gate="G$1" pin="GATE"/>
<pinref part="R8" gate="G$1" pin="1"/>
<wire qx="73.66" y1="15.24" qy="83.82" y2="15.24" width="0.1524" layer="91"/>
<wire qx="83.82" y1="15.24" qy="106.68" y2="15.24" width="0.1524" layer="91"/>
<junction x="83.82" y="15.24"/>
<pinref part="B1" gate="G$1" pin="3.3V"/>
<wire qx="7.62" y1="60.96" qy="5.08" y2="60.96" width="0.1524" layer="91"/>
<wire qx="5.08" y1="60.96" qy="5.08" y2="96.52" width="0.1524" layer="91"/>
<wire qx="5.08" y1="96.52" qy="106.68" y2="96.52" width="0.1524" layer="91"/>
<wire qx="106.68" y1="96.52" qy="106.68" y2="88.9" width="0.1524" layer="91"/>
<pinref part="U$1" gate="G$1" pin="VDD"/>
<wire qx="106.68" y1="88.9" qy="127" y2="88.9" width="0.1524" layer="91"/>
<wire qx="106.68" y1="88.9" qy="106.68" y2="76.2" width="0.1524" layer="91"/>
<junction x="106.68" y="88.9"/>
<pinref part="U$2" gate="G$1" pin="VCC"/>
<wire qx="106.68" y1="76.2" qy="106.68" y2="63.5" width="0.1524" layer="91"/>
<wire qx="106.68" y1="63.5" qy="134.62" y2="63.5" width="0.1524" layer="91"/>
<wire qx="106.68" y1="63.5" qy="106.68" y2="55.88" width="0.1524" layer="91"/>
<junction x="106.68" y="63.5"/>
<pinref part="U$3" gate="G$1" pin="VCC"/>
<wire qx="106.68" y1="55.88" qy="106.68" y2="40.64" width="0.1524" layer="91"/>
<wire qx="106.68" y1="40.64" qy="119.38" y2="40.64" width="0.1524" layer="91"/>
<wire qx="106.68" y1="15.24" qy="106.68" y2="35.56" width="0.1524" layer="91"/>
<junction x="106.68" y="40.64"/>
<wire qx="106.68" y1="35.56" qy="106.68" y2="40.64" width="0.1524" layer="91"/>
<wire qx="83.82" y1="35.56" qy="106.68" y2="35.56" width="0.1524" layer="91"/>
<junction x="83.82" y="35.56"/>
<junction x="106.68" y="35.56"/>
<wire qx="83.82" y1="55.88" qy="106.68" y2="55.88" width="0.1524" layer="91"/>
<junction x="83.82" y="55.88"/>
<junction x="106.68" y="55.88"/>
<wire qx="83.82" y1="76.2" qy="106.68" y2="76.2" width="0.1524" layer="91"/>
<junction x="83.82" y="76.2"/>
<junction x="106.68" y="76.2"/>
</segment>
</net>
<net name="N$7" class="0">
<segment>
<pinref part="R1" gate="G$1" pin="2"/>
<wire qx="63.5" y1="86.36" qy="63.5" y2="88.9" width="0.1524" layer="91"/>
<pinref part="U$4" gate="G$1" pin="DRAIN"/>
<wire qx="63.5" y1="88.9" qy="66.04" y2="88.9" width="0.1524" layer="91"/>
<pinref part="B1" gate="G$1" pin="SDA"/>
<wire qx="33.02" y1="53.34" qy="43.18" y2="53.34" width="0.1524" layer="91"/>
<wire qx="43.18" y1="53.34" qy="43.18" y2="88.9" width="0.1524" layer="91"/>
<wire qx="43.18" y1="88.9" qy="63.5" y2="88.9" width="0.1524" layer="91"/>
<junction x="63.5" y="88.9"/>
</segment>
</net>
<net name="N$8" class="0">
<segment>
<pinref part="U$4" gate="G$1" pin="SOURCE"/>
<pinref part="R2" gate="G$1" pin="2"/>
<wire qx="81.28" y1="88.9" qy="83.82" y2="88.9" width="0.1524" layer="91"/>
<wire qx="83.82" y1="88.9" qy="83.82" y2="86.36" width="0.1524" layer="91"/>
<pinref part="U$1" gate="G$1" pin="SDA"/>
<wire qx="127" y1="86.36" qy="119.38" y2="86.36" width="0.1524" layer="91"/>
<wire qx="119.38" y1="86.36" qy="96.52" y2="86.36" width="0.1524" layer="91"/>
<wire qx="96.52" y1="86.36" qy="96.52" y2="88.9" width="0.1524" layer="91"/>
<wire qx="96.52" y1="88.9" qy="83.82" y2="88.9" width="0.1524" layer="91"/>
<junction x="83.82" y="88.9"/>
<pinref part="U$2" gate="G$1" pin="SDA"/>
<wire qx="134.62" y1="68.58" qy="119.38" y2="68.58" width="0.1524" layer="91"/>
<wire qx="119.38" y1="68.58" qy="119.38" y2="86.36" width="0.1524" layer="91"/>
<junction x="119.38" y="86.36"/>
</segment>
</net>
<net name="N$9" class="0">
<segment>
<pinref part="U$5" gate="G$1" pin="SOURCE"/>
<pinref part="R4" gate="G$1" pin="2"/>
<wire qx="81.28" y1="68.58" qy="83.82" y2="68.58" width="0.1524" layer="91"/>
<wire qx="83.82" y1="68.58" qy="83.82" y2="66.04" width="0.1524" layer="91"/>
<pinref part="U$1" gate="G$1" pin="SCL"/>
<wire qx="127" y1="83.82" qy="116.84" y2="83.82" width="0.1524" layer="91"/>
<wire qx="116.84" y1="83.82" qy="96.52" y2="83.82" width="0.1524" layer="91"/>
<wire qx="96.52" y1="83.82" qy="96.52" y2="68.58" width="0.1524" layer="91"/>
<wire qx="96.52" y1="68.58" qy="83.82" y2="68.58" width="0.1524" layer="91"/>
<junction x="83.82" y="68.58"/>
<pinref part="U$2" gate="G$1" pin="SCL"/>
<wire qx="134.62" y1="66.04" qy="116.84" y2="66.04" width="0.1524" layer="91"/>
<wire qx="116.84" y1="66.04" qy="116.84" y2="83.82" width="0.1524" layer="91"/>
<junction x="116.84" y="83.82"/>
</segment>
</net>
<net name="N$10" class="0">
<segment>
<pinref part="U$5" gate="G$1" pin="DRAIN"/>
<pinref part="R3" gate="G$1" pin="2"/>
<wire qx="66.04" y1="68.58" qy="63.5" y2="68.58" width="0.1524" layer="91"/>
<wire qx="63.5" y1="68.58" qy="63.5" y2="66.04" width="0.1524" layer="91"/>
<pinref part="B1" gate="G$1" pin="SCL"/>
<wire qx="33.02" y1="50.8" qy="45.72" y2="50.8" width="0.1524" layer="91"/>
<wire qx="45.72" y1="50.8" qy="45.72" y2="68.58" width="0.1524" layer="91"/>
<wire qx="45.72" y1="68.58" qy="63.5" y2="68.58" width="0.1524" layer="91"/>
<junction x="63.5" y="68.58"/>
</segment>
</net>
<net name="N$11" class="0">
<segment>
<pinref part="U$6" gate="G$1" pin="SOURCE"/>
<pinref part="R6" gate="G$1" pin="2"/>
<wire qx="81.28" y1="48.26" qy="83.82" y2="48.26" width="0.1524" layer="91"/>
<wire qx="83.82" y1="48.26" qy="83.82" y2="45.72" width="0.1524" layer="91"/>
<pinref part="U$3" gate="G$1" pin="DOUT"/>
<wire qx="119.38" y1="38.1" qy="93.98" y2="38.1" width="0.1524" layer="91"/>
<wire qx="93.98" y1="38.1" qy="93.98" y2="48.26" width="0.1524" layer="91"/>
<wire qx="93.98" y1="48.26" qy="83.82" y2="48.26" width="0.1524" layer="91"/>
<junction x="83.82" y="48.26"/>
</segment>
</net>
<net name="N$12" class="0">
<segment>
<pinref part="U$6" gate="G$1" pin="DRAIN"/>
<pinref part="R5" gate="G$1" pin="2"/>
<wire qx="66.04" y1="48.26" qy="63.5" y2="48.26" width="0.1524" layer="91"/>
<wire qx="63.5" y1="48.26" qy="63.5" y2="45.72" width="0.1524" layer="91"/>
<wire qx="63.5" y1="48.26" qy="53.34" y2="48.26" width="0.1524" layer="91"/>
<junction x="63.5" y="48.26"/>
<wire qx="53.34" y1="48.26" qy="53.34" y2="83.82" width="0.1524" layer="91"/>
<pinref part="B1" gate="G$1" pin="D2"/>
<wire qx="53.34" y1="83.82" qy="33.02" y2="83.82" width="0.1524" layer="91"/>
</segment>
</net>
<net name="N$13" class="0">
<segment>
<pinref part="U$7" gate="G$1" pin="SOURCE"/>
<pinref part="R8" gate="G$1" pin="2"/>
<wire qx="81.28" y1="27.94" qy="83.82" y2="27.94" width="0.1524" layer="91"/>
<wire qx="83.82" y1="27.94" qy="83.82" y2="25.4" width="0.1524" layer="91"/>
<wire qx="83.82" y1="27.94" qy="111.76" y2="27.94" width="0.1524" layer="91"/>
<junction x="83.82" y="27.94"/>
<pinref part="U$3" gate="G$1" pin="DIN"/>
<wire qx="119.38" y1="35.56" qy="111.76" y2="35.56" width="0.1524" layer="91"/>
<wire qx="111.76" y1="27.94" qy="111.76" y2="35.56" width="0.1524" layer="91"/>
</segment>
</net>
<net name="N$14" class="0">
<segment>
<pinref part="U$7" gate="G$1" pin="DRAIN"/>
<pinref part="R7" gate="G$1" pin="2"/>
<wire qx="66.04" y1="27.94" qy="63.5" y2="27.94" width="0.1524" layer="91"/>
<wire qx="63.5" y1="27.94" qy="63.5" y2="25.4" width="0.1524" layer="91"/>
<wire qx="63.5" y1="27.94" qy="50.8" y2="27.94" width="0.1524" layer="91"/>
<junction x="63.5" y="27.94"/>
<wire qx="50.8" y1="27.94" qy="50.8" y2="81.28" width="0.1524" layer="91"/>
<pinref part="B1" gate="G$1" pin="*D3"/>
<wire qx="50.8" y1="81.28" qy="33.02" y2="81.28" width="0.1524" layer="91"/>
</segment>
</net>
<net name="N$15" class="0">
<segment>
<pinref part="B1" gate="G$1" pin="5V"/>
<wire qx="7.62" y1="63.5" qy="55.88" y2="63.5" width="0.1524" layer="91"/>
<wire qx="55.88" y1="63.5" qy="55.88" y2="55.88" width="0.1524" layer="91"/>
<pinref part="R3" gate="G$1" pin="1"/>
<wire qx="55.88" y1="55.88" qy="63.5" y2="55.88" width="0.1524" layer="91"/>
<pinref part="R1" gate="G$1" pin="1"/>
<wire qx="63.5" y1="76.2" qy="55.88" y2="76.2" width="0.1524" layer="91"/>
<wire qx="55.88" y1="76.2" qy="55.88" y2="63.5" width="0.1524" layer="91"/>
<junction x="55.88" y="63.5"/>
<pinref part="R5" gate="G$1" pin="1"/>
<wire qx="63.5" y1="35.56" qy="55.88" y2="35.56" width="0.1524" layer="91"/>
<wire qx="55.88" y1="35.56" qy="55.88" y2="55.88" width="0.1524" layer="91"/>
<junction x="55.88" y="55.88"/>
<pinref part="R7" gate="G$1" pin="1"/>
<wire qx="63.5" y1="15.24" qy="55.88" y2="15.24" width="0.1524" layer="91"/>
<wire qx="55.88" y1="15.24" qy="55.88" y2="35.56" width="0.1524" layer="91"/>
<junction x="55.88" y="35.56"/>
</segment>
</net>
</nets>
</sheet>
</sheets>
</schematic>
</drawing>
<compatibility>
<note version="6.3" minversion="6.2.2" severity="warning">
Since Version 6.2.2 text objects can contain more than one line,
which will not be processed correctly with this version.
</note>
</compatibility>
</eagle>
