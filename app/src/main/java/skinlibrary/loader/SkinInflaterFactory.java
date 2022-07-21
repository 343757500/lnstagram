package skinlibrary.loader;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import skinlibrary.SkinConfig;
import skinlibrary.SkinItem;
import skinlibrary.attr.base.AttrFactory;
import skinlibrary.attr.base.DynamicAttr;
import skinlibrary.attr.base.SkinAttr;
import skinlibrary.utils.SkinListUtils;
import skinlibrary.utils.SkinLog;

/**
 * 自定义的InflaterFactory，用来代替默认的LayoutInflaterFactory
 * Ref: <a href="http://willowtreeapps.com/blog/app-development-how-to-get-the-right-layoutinflater/"> How to Get the Right LayoutInflater</a>
 */
public class SkinInflaterFactory implements LayoutInflater.Factory2 {

    /**
     * 存储那些有皮肤更改需求的View及其对应的属性的集合
     */
    private Map<View, SkinItem> mSkinItemMap = new HashMap<>();
    private AppCompatActivity mAppCompatActivity;

    public SkinInflaterFactory(AppCompatActivity appCompatActivity) {
        this.mAppCompatActivity = appCompatActivity;
    }

    @Override
    public View onCreateView(String s, Context context, AttributeSet attributeSet) {
        return null;
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        View view = mAppCompatActivity.getDelegate().createView(parent, name, context, attrs);
        if (SkinConfig.isCanChangeFont() && view instanceof TextView) {
            TextViewRepository.add(mAppCompatActivity, (TextView) view);
        }

        // 开启了全局，或有自定义属性
        if (SkinConfig.isGlobalSkinApply()
                || attrs.getAttributeBooleanValue(SkinConfig.NAMESPACE, SkinConfig.ATTR_SKIN_ENABLE, false)) {
            if (view == null) {
                view = ViewProducer.createViewFromTag(context, name, attrs);
            }
            if (view == null) {
                return null;
            }
            parseSkinAttr(context, attrs, view);
        }
        return view;
    }

    /**
     * collect skin view
     */
    private void parseSkinAttr(Context context, AttributeSet attrs, View view) {
        List<SkinAttr> viewAttrs = new ArrayList<>();

        // SkinLog.d("--viewName:" + view.getClass().getSimpleName());
        for (int i = 0; i < attrs.getAttributeCount(); i++) {
            String attrName = attrs.getAttributeName(i);
            String attrValue = attrs.getAttributeValue(i);
            // SkinLog.d("  |--AttributeName:" + attrName + "|attrValue:" + attrValue);

            // region style
            // style theme
            // Xwj 暂时不需要支持其他皮肤
            // if ("style".equals(attrName)) {
            if ("style".equals(attrName) && false) {
                int[] skinAttrs = new int[]{android.R.attr.textColor, android.R.attr.background};
                TypedArray a = context.getTheme().obtainStyledAttributes(attrs, skinAttrs, 0, 0);
                int textColorId = a.getResourceId(0, -1);
                int backgroundId = a.getResourceId(1, -1);
                if (textColorId != -1) {
                    String entryName = context.getResources().getResourceEntryName(textColorId);
                    String typeName = context.getResources().getResourceTypeName(textColorId);
                    SkinAttr skinAttr = AttrFactory.get("textColor", textColorId, entryName, typeName);
                    SkinLog.d("    textColor in style is supported:" + "\n" +
                            "    resource id:" + textColorId + "\n" +
                            "    attrName:" + attrName + "\n" +
                            "    attrValue:" + attrValue + "\n" +
                            "    entryName:" + entryName + "\n" +
                            "    typeName:" + typeName);
                    if (skinAttr != null) {
                        viewAttrs.add(skinAttr);
                    }
                }
                if (backgroundId != -1) {
                    String entryName = context.getResources().getResourceEntryName(backgroundId);
                    String typeName = context.getResources().getResourceTypeName(backgroundId);
                    SkinAttr skinAttr = AttrFactory.get("background", backgroundId, entryName, typeName);
                    SkinLog.d("    background in style is supported:" + "\n" +
                            "    resource id:" + backgroundId + "\n" +
                            "    attrName:" + attrName + "\n" +
                            "    attrValue:" + attrValue + "\n" +
                            "    entryName:" + entryName + "\n" +
                            "    typeName:" + typeName);
                    if (skinAttr != null) {
                        viewAttrs.add(skinAttr);
                    }

                }
                a.recycle();
                continue;
            }
            //end region


            // if attrValue is reference，eg:@color/red
            if (AttrFactory.isSupportedAttr(attrName) && attrValue.startsWith("@")) {
                try {
                    //resource id
                    int id = Integer.parseInt(attrValue.substring(1));
                    if (id == 0) {
                        continue;
                    }
                    //entryName，eg:text_color_selector
                    String entryName = context.getResources().getResourceEntryName(id);
                    //typeName，eg:color、drawable
                    String typeName = context.getResources().getResourceTypeName(id);
                    SkinAttr mSkinAttr = AttrFactory.get(attrName, id, entryName, typeName);
//                    SkinLog.d("    " + attrName + " is supported:" + "\n" +
//                            "    resource id:" + id + "\n" +
//                            "    attrName:" + attrName + "\n" +
//                            "    attrValue:" + attrValue + "\n" +
//                            "    entryName:" + entryName + "\n" +
//                            "    typeName:" + typeName
//                    );
                    if (mSkinAttr != null) {
                        viewAttrs.add(mSkinAttr);
                    }
                } catch (NumberFormatException e) {
                    SkinLog.e(e.toString());
                    e.printStackTrace();
                }
            }

        }

        // 应用皮肤
//        try {
            if (!SkinListUtils.isEmpty(viewAttrs)) {
                SkinItem skinItem = new SkinItem();
                skinItem.view = view;
                skinItem.attrs = viewAttrs;
                mSkinItemMap.put(skinItem.view, skinItem);
                // Xwj 新全部打开先
                if (SkinManager.getInstance().isExternalSkin()
                        || SkinManager.getInstance().isNightMode()) {
                    // 如果当前皮肤来自于外部或者是处于夜间模式
                    skinItem.apply();
                }
                skinItem.apply();
            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            Log.i("mzh", ThrowableUtils.getFullStackTrace(e));
//        }
    }



    public void applySkin() {
        if (mSkinItemMap.isEmpty()) {
            return;
        }
        for (View view : mSkinItemMap.keySet()) {
            if (view == null) {
                continue;
            }
            mSkinItemMap.get(view).apply();
        }
    }

    /**
     * clear skin view
     */
    public void clean() {
        for (View view : mSkinItemMap.keySet()) {
            if (view == null) {
                continue;
            }
            mSkinItemMap.get(view).clean();
        }
        TextViewRepository.remove(mAppCompatActivity);
        mSkinItemMap.clear();
        mSkinItemMap = null;
    }

    private void addSkinView(SkinItem item) {
        if (mSkinItemMap.get(item.view) != null) {
            mSkinItemMap.get(item.view).attrs.addAll(item.attrs);
        } else {
            mSkinItemMap.put(item.view, item);
        }
    }

    public void removeSkinView(View view) {
        SkinItem skinItem = mSkinItemMap.remove(view);
        if (skinItem != null) {
            SkinLog.d("removeSkinView from mSkinItemMap:" + skinItem.view);
        }
        if (SkinConfig.isCanChangeFont() && view instanceof TextView) {
            SkinLog.d("removeSkinView from TextViewRepository:" + view);
            TextViewRepository.remove(mAppCompatActivity, (TextView) view);
        }
    }



    /**
     * Dynamically add skin view
     *
     * @param context        context
     * @param view           added view
     * @param attrName       attribute name
     * @param attrValueResId resource id
     */
    public void dynamicAddSkinEnableView(Context context, View view, String attrName, int attrValueResId) {
        String entryName = context.getResources().getResourceEntryName(attrValueResId);
        String typeName = context.getResources().getResourceTypeName(attrValueResId);
        SkinAttr mSkinAttr = AttrFactory.get(attrName, attrValueResId, entryName, typeName);
        SkinItem skinItem = new SkinItem();
        skinItem.view = view;
        List<SkinAttr> viewAttrs = new ArrayList<>();
        viewAttrs.add(mSkinAttr);
        skinItem.attrs = viewAttrs;
        skinItem.apply();
        addSkinView(skinItem);
    }

    /**
     * dynamic add skin view and it's attrs
     *
     * @param context context
     * @param view    view
     * @param attrs   attrs
     */
    public void dynamicAddSkinEnableView(Context context, View view, List<DynamicAttr> attrs) {
        List<SkinAttr> viewAttrs = new ArrayList<>();
        SkinItem skinItem = new SkinItem();
        skinItem.view = view;

        for (DynamicAttr dAttr : attrs) {
            int id = dAttr.refResId;
            String entryName = context.getResources().getResourceEntryName(id);
            String typeName = context.getResources().getResourceTypeName(id);
            SkinAttr mSkinAttr = AttrFactory.get(dAttr.attrName, id, entryName, typeName);
            viewAttrs.add(mSkinAttr);
        }

        skinItem.attrs = viewAttrs;
        skinItem.apply();
        addSkinView(skinItem);
    }

    public void dynamicAddFontEnableView(Activity activity, TextView textView) {
        TextViewRepository.add(activity, textView);
    }

}
