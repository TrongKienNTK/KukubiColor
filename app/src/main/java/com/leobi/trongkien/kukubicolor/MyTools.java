package com.leobi.trongkien.kukubicolor;

import java.util.ArrayList;
import java.util.Random;

public class MyTools {
    static int result;
    Random r  = new Random();
    private String colorDark[] = new String[]{
            "#c56cf0",
            "#ffb8b8",
            "#ff3838",
            "#ff9f1a",
            "#fff200",
            "#3ae374",
            "#67e6dc",
            "#17c0eb",
            "#7158e2",
            "#3d3d3d",
            "#f19066",
            "#f5cd79",
            "#574b90",
            "#f78fb3",
            "#3dc1d3",
            "#e15f41",
            "#c44569",
            "#192a56",
            "#40739e",
            "#2f3640"
    };
    private String colorLight[] = new String[]{
            "#cd84f1",
            "#ffcccc",
            "#ff4d4d",
            "#ffaf40",
            "#fffa65",
            "#32ff7e",
            "#7efff5",
            "#18dcff",
            "#7d5fff",
            "#4b4b4b",
            "#f3a683",
            "#f7d794",
            "#786fa6",
            "#f8a5c2",
            "#63cdda",
            "#e77f67",
            "#cf6a87",
            "#273c75",
            "#487eb0",
            "#353b48"
    };

    private String notiMess[] = new String[]{
            "Great!", "Well Done!", "OK!", "Perfect!", "Wow!", "Excellent!", "Good!", "Not Bad!"
    };

    private String notiErr[] = new String[]{
            "Oh no!", "Wrong!", "Bad!", "Miss!", "Try Again!", "OMG!", "Noooo!", ":("
    };

    public String generateMess() {
        int numMess = notiMess.length;
        int iMess = r.nextInt(numMess);
        return notiMess[iMess];
    }

    public String generateErr(){
        int numErr = notiErr.length;
        int iErr = r.nextInt(numErr);
        return notiErr[iErr];
    }

    public ArrayList<String> generateColor(int n){
        int x, y;
        ArrayList<String> a = new ArrayList<>();
        x = r.nextInt(n);
        result = x;

        int numColor = colorLight.length;
        y = r.nextInt(numColor - 1);
        //if(y%2 == 0) y = y + 1;

        //generate Color
        for (int i = 0; i < n; i++){
            if(i == x){
                a.add(colorLight[y]);
            }else{
                a.add(colorDark[y]);
            }
        }
        return  a;

    }
}
