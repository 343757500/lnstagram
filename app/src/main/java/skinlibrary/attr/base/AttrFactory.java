package skinlibrary.attr.base;

import java.util.HashMap;



/**
 * 属性工厂
 */
public class AttrFactory {

    // 支持的属性
    private static HashMap<String, SkinAttr> sSupportAttr = new HashMap<>();

    static {
        // 默认支持的属性
//        sSupportAttr.put("background", new BackgroundAttr());
//        sSupportAttr.put("textColor", new TextColorAttr());
//        sSupportAttr.put("src", new ImageViewSrcAttr());
        sSupportAttr.put("text", new TextStringAttr());
        sSupportAttr.put("hint", new TextHintAttr());
    }

    /**
     * 获取属性对应的皮肤属性扩展类
     * @param attrName
     * @param attrValueRefId
     * @param attrValueRefName
     * @param typeName
     * @return
     */
    public static SkinAttr get(String attrName, int attrValueRefId, String attrValueRefName, String typeName) {
        SkinAttr mSkinAttr = sSupportAttr.get(attrName).clone();
        if (mSkinAttr == null) return null;
        mSkinAttr.attrName = attrName;
        mSkinAttr.attrValueRefId = attrValueRefId;
        mSkinAttr.attrValueRefName = attrValueRefName;
        mSkinAttr.attrValueTypeName = typeName;
        return mSkinAttr;
    }

    /**
     * 检查当前属性是否可以被支持
     * @param attrName 属性名
     */
    public static boolean isSupportedAttr(String attrName) {
        return sSupportAttr.containsKey(attrName);
    }

    /**
     * 添加支持的属性
     * @param attrName 属性名
     * @param skinAttr 皮肤扩展属性类
     */
    public static void addSupportAttr(String attrName, SkinAttr skinAttr) {
        sSupportAttr.put(attrName, skinAttr);
    }

}
