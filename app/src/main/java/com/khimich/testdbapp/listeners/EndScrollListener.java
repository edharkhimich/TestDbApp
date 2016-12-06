package com.khimich.testdbapp.listeners;

import android.widget.AbsListView;

public abstract class EndScrollListener implements AbsListView.OnScrollListener {

    private int bufferItemCount = 0;
    private int itemCount = 0;
    private boolean isLoading = true;


    public EndScrollListener(int bufferItemCount) {
        this.bufferItemCount = bufferItemCount;
    }

    public abstract void loadMore(int totalItemsCount, int firstVisibleItem);

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (totalItemCount < itemCount) {
            this.itemCount = totalItemCount;
            if (totalItemCount == 0) {
                this.isLoading = true;
            }
        }

        if (isLoading && (totalItemCount > itemCount)) {
            isLoading = false;
            itemCount = totalItemCount;
        }

        if (!isLoading && (totalItemCount - visibleItemCount) == (firstVisibleItem + bufferItemCount)) {
            loadMore(totalItemCount, firstVisibleItem);
            isLoading = true;
        }
    }
}