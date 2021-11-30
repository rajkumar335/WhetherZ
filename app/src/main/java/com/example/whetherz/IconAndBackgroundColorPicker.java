package com.example.whetherz;

public class IconAndBackgroundColorPicker {
    public static int getBackgroudWarmthColor(int maxTemp, int minTemp){
        double avgTemp =((float)maxTemp+(float)minTemp)/2;
        if(avgTemp>45){
            return R.color.red;
        }
        else if(avgTemp>=35 && avgTemp<45){
            return R.color.orange;
        }
        else if(avgTemp>=26 && avgTemp<35){
            return R.color.warm;
        }
        else
            return R.color.dark_aqua_blue;
    }

    public static int getCurrentWhetherIconLarge(String abbr){
        switch(abbr){
            case "sn" : return R.drawable.snow128;
            case "sl" : return R.drawable.sleet128;
            case "h"  : return R.drawable.hail128;
            case "t"  : return R.drawable.thunderstrom128;
            case "hr" : return R.drawable.heavyrain128;
            case "lr" : return R.drawable.lightrain128;
            case "s"  : return R.drawable.shower128;
            case "hc" : return R.drawable.heavycloud128;
            case "lc" : return R.drawable.lightcloud128;
            case "c"  : return R.drawable.clear128;
            default: return 0;
        }
    }

    public static  int getCurrentWhetherIconSmall(String abbr){
        switch(abbr){
            case "sn" : return R.drawable.snow64;
            case "sl" : return R.drawable.sleet64;
            case "h"  : return R.drawable.hail64;
            case "t"  : return R.drawable.thunderstrom64;
            case "hr" : return R.drawable.heavyrain64;
            case "lr" : return R.drawable.lightrain64;
            case "s"  : return R.drawable.showers64;
            case "hc" : return R.drawable.heavycloud64;
            case "lc" : return R.drawable.lightcloud64;
            case "c"  : return R.drawable.clear64;
            default: return 0;
        }
    }
}
