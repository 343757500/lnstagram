package skinlibrary;

import android.view.View;
import android.widget.TextView;

import java.util.List;

import skinlibrary.attr.base.DynamicAttr;

public interface IDynamicNewView {

    void dynamicAddView(View view, List<DynamicAttr> pDAttrs);

    void dynamicAddView(View view, String attrName, int attrValueResId);

    /**
     * add the TextView for font switch
     * @param textView textView
     */
    void dynamicAddFontView(TextView textView);

}
