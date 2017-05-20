package com.example.win81.managethem;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;


public class ReflectResource {
    private Resources res;// Resource acquisition of APK inside res
    private String apkPackageName;// Resource APK inside the package name

    private static final int NO_COLOR = 0x00000001;
    private static final int TRANSPARENT_COLOR = 0x00000000;

    public ReflectResource(Resources res, String apkPackageName) {
        this.res = res;
        this.apkPackageName = apkPackageName;
    }


    public int getResApkLayoutId(String layoutName) {
        return res.getIdentifier(layoutName, "layout", apkPackageName);
    }


    public View getResApkLayoutView(Context context, String layoutName) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return inflater.inflate(res.getLayout(getResApkLayoutId(layoutName)), null);
    }


    public int getResApkWidgetViewID(String widgetName) {
        return res.getIdentifier(widgetName, "id", apkPackageName);
    }

    public View getResApkWidgetView(View layout, String widgetName) {
        return layout.findViewById(getResApkWidgetViewID(widgetName));
    }

    /*
      The drawable file ID

      @param DrawableName
     *            Picture name
     */
    public int getDrawableId(String imgName) {
        return res.getIdentifier(imgName, "drawable", apkPackageName);
    }

    public Drawable getResApkDrawable(String imgName) {
        int id = getDrawableId(imgName);
        if(id > 0){
            return res.getDrawable(id);
        }
        Log.i("getResApkDrawable", imgName+" Not found in the skin of the plugin");
        return null;
    }

    public int getResApkStringId(String stringName) {
        return res.getIdentifier(stringName, "string", apkPackageName);
    }

    public String getResApkString(String stringName) {
        return res.getString(getResApkStringId(stringName));
    }

    public int getResApkAnimId(String animationName) {
        return res.getIdentifier(animationName, "anim", apkPackageName);
    }

    public XmlPullParser getResApkAnimXml(String animationName) {
        return res.getAnimation(getResApkAnimId(animationName));
    }

    public Animation getResApkAnim(Context context, String animationName) {
        Animation animation = null;
        XmlPullParser parser = getResApkAnimXml(animationName);
        AttributeSet attrs = Xml.asAttributeSet(parser);
        try {
            animation = createAnimationFromXml(context, parser, null, attrs);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return animation;
    }

    private Animation createAnimationFromXml(Context c, XmlPullParser parser,
                                             AnimationSet parent, AttributeSet attrs)
            throws XmlPullParserException, IOException {

        Animation anim = null;
        int type;
        int depth = parser.getDepth();
        while (((type = parser.next()) != XmlPullParser.END_TAG || parser.getDepth() > depth) && type != XmlPullParser.END_DOCUMENT) {

            if (type != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals("set")) {
                anim = new AnimationSet(c, attrs);
                createAnimationFromXml(c, parser, (AnimationSet) anim, attrs);
            } else if (name.equals("alpha")) {
                anim = new AlphaAnimation(c, attrs);
            } else if (name.equals("scale")) {
                anim = new ScaleAnimation(c, attrs);
            } else if (name.equals("rotate")) {
                anim = new RotateAnimation(c, attrs);
            } else if (name.equals("translate")) {
                anim = new TranslateAnimation(c, attrs);
            } else {
                throw new RuntimeException("Unknown animation name: "+ parser.getName());
            }
            if (parent != null) {
                parent.addAnimation(anim);
            }
        }
        return anim;
    }

    public int getResApkColorId(String colorName) {
        return res.getIdentifier(colorName, "color", apkPackageName);
    }

    public int getResApkColor(String colorName) {
        return res.getColor(getResApkColorId(colorName));
    }

    public int getResApkDimensId(String dimenName) {
        return res.getIdentifier(dimenName, "dimen", apkPackageName);
    }

    public float getResApkDimens(String dimenName) {
        return res.getDimension(getResApkDimensId(dimenName));
    }

    public InputStream getResApkRaw(String string) {
        return res.openRawResource(getResApkRawId(string));
    }

    private int getResApkRawId(String string) {
        return res.getIdentifier(string, "raw", apkPackageName);
    }

}
