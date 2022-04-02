package com.gabrielbog.openstream;

public class AdapterManager {
    private static AdapterManager inst = null;

    private RecyclerAdapter recyclerAdapter;
    private RecyclerFavoriteAdapter recyclerFavoriteAdapter;

    private AdapterManager()
    {
        recyclerAdapter = new RecyclerAdapter();
        recyclerFavoriteAdapter = new RecyclerFavoriteAdapter();
    }

    public static AdapterManager getInstance()
    {
        if(inst == null)
        {
            inst = new AdapterManager();
        }
        return inst;
    }

    public RecyclerAdapter getRecyclerAdapter() {
        return recyclerAdapter;
    }

    public void setRecyclerAdapter(RecyclerAdapter recyclerAdapter) {
        this.recyclerAdapter = recyclerAdapter;
    }

    public RecyclerFavoriteAdapter getRecyclerFavoriteAdapter() {
        return recyclerFavoriteAdapter;
    }

    public void setRecyclerFavoriteAdapter(RecyclerFavoriteAdapter recyclerFavoriteAdapter) {
        this.recyclerFavoriteAdapter = recyclerFavoriteAdapter;
    }
}
