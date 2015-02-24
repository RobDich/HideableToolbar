package com.robdich.hideabletoolbarsample;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.robdich.hideabletoolbar.HideableToolbarActivity;
import com.robdich.hideabletoolbar.view.ObserveableRecyclerView;

/**
 * Created by Robert on 2/24/2015.
 */
public class SimpleListActivity extends HideableToolbarActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private static final int ITEM_COUNT = 30;
    private static final int[] listItems = new int[ITEM_COUNT];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_list);

        for(int i = 0; i < ITEM_COUNT; i++){
            listItems[i] = i;
        }

        recyclerView = (RecyclerView) findViewById(R.id.recylerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new SimpleListAdapter());
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        observeScrollable((ObserveableRecyclerView)recyclerView);
    }

    @Override
    protected int getToolbarResId() {
        return R.id.toolbar_actionbar;
    }

    @Override
    protected View getHideableToolbar() {
        return findViewById(R.id.toolbar_actionbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hideable_toolbar_sample, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class SimpleListAdapter extends RecyclerView.Adapter<SimpleListAdapter.ViewHolder> {

        public class ViewHolder extends RecyclerView.ViewHolder {

            public TextView textView;

            public ViewHolder(View v) {
                super(v);
                textView = (TextView) v.findViewById(R.id.text_item);
            }

        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.text_item_layout, parent, false);
            ViewHolder vh = new ViewHolder(v);

            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            int n = listItems[position];
            holder.textView.setText("Item " + n);
        }

        @Override
        public int getItemCount() {
            return listItems.length;
        }
    }

}