package com.lhh.lnstagram.mvvm.view.lazyviewpager;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.lhh.lnstagram.R;

import java.lang.reflect.Field;


/**
 * ViewPager that add items lazily in the two following situation:
 * <ul>
 *     <li>its adapter inherits from {@link LazyViewPagerAdapter}</li>
 *     <li>its adapter inherits from {@link LazyFragmentPagerAdapter} and Fragment implements {@link LazyFragmentPagerAdapter.Laziable} </li>
 * </ul>
 */
public class LazyViewPager extends ViewPager {

	// 默认值
	private static final float DEFAULT_OFFSET = 0.5f;
	private static final boolean DEFAULT_IS_LAZY_LOAD = true;
	private static final boolean DEFAULT_IS_NO_SCROLL = true;
	private static final boolean DEFAULT_IS_NO_ANIM = true;
	// 参数
	private float mInitLazyItemOffset = DEFAULT_OFFSET;
	private boolean init_lazy_is_lazy_load = DEFAULT_IS_LAZY_LOAD;
	private boolean init_lazy_is_no_scroll = DEFAULT_IS_NO_SCROLL;
	private boolean init_lazy_is_no_anim = DEFAULT_IS_NO_ANIM;
	// 懒加载适配器
	private LazyPagerAdapter mLazyPagerAdapter;


	/**
	 * change the initLazyItemOffset 默认0.5f
	 * @param initLazyItemOffset put mInitLazyItemOffset if {@code 0 < initLazyItemOffset <= 1}
	 */
	public void setInitLazyItemOffset(float initLazyItemOffset) {
		if (initLazyItemOffset > 0 && initLazyItemOffset <= 1) {
			mInitLazyItemOffset = initLazyItemOffset;
		}
	}

	/**
	 * 设置是否启动懒加载
 	 * @param is_lazy_load true启动懒加载(默认)
     */
	public void setLazyLoad(boolean is_lazy_load) {
		this.init_lazy_is_lazy_load = is_lazy_load;
	}

	/**
	 * 设置是否允许滑动
	 * @param is_no_scroll true不可滑动(默认)
	 */
	public void setNoScroll(boolean is_no_scroll) {
		this.init_lazy_is_no_scroll = is_no_scroll;
	}

	/**
	 * 设置是否允许切换动画
	 * @param is_no_anim true没有动画(默认)
	 */
	public void setNoAnim(boolean is_no_anim) {
		this.init_lazy_is_no_anim = is_no_anim;
	}


	public LazyViewPager(Context context) {
		super(context);
	}

	public LazyViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);

		// 读取参数,并设置保存
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LazyViewPager);
		setInitLazyItemOffset(a.getFloat(R.styleable.LazyViewPager_init_lazy_item_offset, DEFAULT_OFFSET));
		setLazyLoad(a.getBoolean(R.styleable.LazyViewPager_init_lazy_is_lazyload, DEFAULT_IS_LAZY_LOAD));
		setNoScroll(a.getBoolean(R.styleable.LazyViewPager_init_lazy_is_noscroll, DEFAULT_IS_NO_SCROLL));
		a.recycle();
	}


	@Override
	public void setAdapter(PagerAdapter adapter) {
		super.setAdapter(adapter);
        mLazyPagerAdapter = adapter != null && adapter instanceof LazyPagerAdapter ? (LazyPagerAdapter) adapter : null;
	}

	@Override
	protected void onPageScrolled(int position, float offset, int offsetPixels) {
		// 启动的情况下才去执行这个差异化操作
		if (init_lazy_is_lazy_load) {
			if (mLazyPagerAdapter != null) {
				if (getCurrentItem() == position) {
					int lazyPosition = position + 1;
					if (offset >= mInitLazyItemOffset && mLazyPagerAdapter.isLazyItem(lazyPosition)) {
						mLazyPagerAdapter.startUpdate(this);
						mLazyPagerAdapter.addLazyItem(this, lazyPosition);
						mLazyPagerAdapter.finishUpdate(this);
					}
				} else if (getCurrentItem() > position) {
					int lazyPosition = position;
					if (1 - offset >= mInitLazyItemOffset && mLazyPagerAdapter.isLazyItem(lazyPosition)) {
						mLazyPagerAdapter.startUpdate(this);
						mLazyPagerAdapter.addLazyItem(this, lazyPosition);
						mLazyPagerAdapter.finishUpdate(this);
					}
				}
			}
		}
		super.onPageScrolled(position, offset, offsetPixels);
	}


	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// 启动的情况下才去执行这个差异化操作
		if (init_lazy_is_no_scroll) {
			return false;
		}  else {
			return super.onTouchEvent(ev);
		}
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		// 启动的情况下才去执行这个差异化操作
		if (init_lazy_is_no_scroll) {
			return false;
		} else {
			return super.onInterceptTouchEvent(ev);
		}
	}

	@Override
	public void setCurrentItem(int item) {
		//去除页面切换时的滑动翻页效果
		super.setCurrentItem(item, !init_lazy_is_no_anim);
	}

	// 【【注意设置位置】】设置玩adapter后就去改。如果adapter数据是后来绑定的。你也可以在数据绑定后再设置一次。
	public void setDefaultItem(ViewPager viewPager, PagerAdapter pagerAdapter, int position){
		try {
			Class c = Class.forName("androidx.viewpager.widget.ViewPager");
			Field field =c.getDeclaredField("mCurItem");
			field.setAccessible(true);
			field.setInt(viewPager, position);
		} catch (Exception e) {
			e.printStackTrace();
		}
		pagerAdapter.notifyDataSetChanged();
		viewPager.setCurrentItem(position);
	}

}
