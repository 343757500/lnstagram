package skinlibrary.attr.base;

import android.view.View;
import android.widget.TextView;

import skinlibrary.utils.SkinResourcesUtils;


/**
 * 文案国际化
 */
public class TextStringAttr extends SkinAttr {

    @Override
    protected void applySkin(View view) {
        if (view instanceof TextView) {
            TextView tv = (TextView) view;
            if (isString()) {
                tv.setText(SkinResourcesUtils.getString(attrValueRefId));
            }
        }
    }

}
