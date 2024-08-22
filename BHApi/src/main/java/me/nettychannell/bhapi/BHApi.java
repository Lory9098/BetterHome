package me.nettychannell.bhapi;

import me.nettychannell.bhapi.loader.BHDataLoader;
import me.nettychannell.bhapi.user.BHUser;

public final class BHApi {

    private static BHApi instance;
    private final BHDataLoader<BHUser> dataLoader;

    public BHApi(BHDataLoader<BHUser> dataLoader) {
        instance = this;
        this.dataLoader = dataLoader;
    }

    public static BHApi getInstance() {
        return instance;
    }

    public BHDataLoader<BHUser> getDataLoader() {
        return dataLoader;
    }

}
