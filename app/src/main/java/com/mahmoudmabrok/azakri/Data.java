package com.mahmoudmabrok.azakri;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by motamed on 3/26/2018.
 */

public class Data {
    String[] names = new String[]{"masa", "sabah"};
    private ArrayList<String> sabah = new ArrayList<>();
    private ArrayList<String> masa = new ArrayList<>();

    public Data() {
        prepareData();
    }

    private void prepareData() {

        sabah.add("أصبحنا وأصبح الملك لله والحمد لله " +
                "ولا اله الا الله وحده لا شريك له له الملك وله الحمد وهو على كل شئ قدير" +
                " رب أسالك خير ما في هذا اليوم وخير ما بعده وأعوذ بك من شر هذا اليوم وشر ما بعده" +
                " رب أعوذ بك من الكسل وسوء الكبر رب أعوذ يك من عذاب ف النار وعذاب ف القبر ");

        sabah.add("أصبحنا على فطرة الاسلام ,وكلمة الإخلاص  ودين نبينا محمد" +
                " وملة  ابينا ابراهيم حنيفا مسلما  وما كان من المشركين\n");

        masa.add("أمسينا  على فطرة الاسلام ,وكلمة الإخلاص  ودين نبينا محمد " +
                "وملة  ابينا ابراهيم حنيفا مسلما  وما كان من المشركين\n");

        masa.add("أمسينا وأمسي  الملك لله والحمد لله ولا اله الا الله");


        Scanner in;
        /*try {
            for (int i = 0; i <names.length ; i++) {
                 in = new Scanner(new File(getClass().getResource("res/t")));
            }
        }catch (Resources.NotFoundException){

        }*/
    }

    public ArrayList<String> getSabah() {
        return sabah;
    }

    public ArrayList<String> getMasa() {
        return masa;
    }
}
