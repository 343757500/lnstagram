package skinlibrary.attr.base;

import android.view.View;

import skinlibrary.utils.SkinResourcesUtils;


/**
 * 皮肤属性类（抽象类）
 */
public abstract class SkinAttr implements Cloneable {

    protected static final String RES_TYPE_NAME_STRING = "string";
    protected static final String RES_TYPE_NAME_COLOR = "color";
    protected static final String RES_TYPE_NAME_DRAWABLE = "drawable";
    protected static final String RES_TYPE_NAME_MIPMAP = "mipmap";

    /**
     * attribute reference id
     */
    protected int attrValueRefId;

    /**
     * attribute name, eg: background、textColor
     */
    protected String attrName;

    /**
     * resources name, eg:app_exit_btn_background
     */
    protected String attrValueRefName;

    /**
     * type of the value, such as color or drawable
     */
    protected String attrValueTypeName;

    /**
     * Use to apply view with new TypedValue
     * @param view view
     */
    public void apply(View view) {
        if (!SkinResourcesUtils.isNightMode()) {
            applySkin(view);
        } else {
            applyNightMode(view);
        }
    }

    /**
     * 替换皮肤的核心思想方法
     * @param view
     */
    protected abstract void applySkin(View view);

    /**
     * 夜间模式
     * @param view
     */
    protected void applyNightMode(View view) {

    }

    protected boolean isDrawable() {
        return RES_TYPE_NAME_DRAWABLE.equals(attrValueTypeName)
                || RES_TYPE_NAME_MIPMAP.equals(attrValueTypeName);
    }

    protected boolean isColor() {
        return RES_TYPE_NAME_COLOR.equals(attrValueTypeName);
    }

    protected boolean isString() {
        return RES_TYPE_NAME_STRING.equals(attrValueTypeName);
    }

    @Override
    public String toString() {
        return "SkinAttr{" +
                "attrName='" + attrName + '\'' +
                ", attrValueRefId=" + attrValueRefId +
                ", attrValueRefName='" + attrValueRefName + '\'' +
                ", attrValueTypeName='" + attrValueTypeName + '\'' +
                '}';
    }

    @Override
    public SkinAttr clone() {
        SkinAttr o = null;
        try {
            o = (SkinAttr) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return o;
    }

}
