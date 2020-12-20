package kr.thkim.bbs.database;

import kr.thkim.bbs.model.BserDBModel;

public class LocalCachingManager {
    private static LocalCachingManager instance;
    public static LocalCachingManager getInstance(){
        if(instance==null){
            instance = new LocalCachingManager();
        }
        return instance;
    }

    private BserDBModel cacheDB;

    public void setCacheDB(BserDBModel cacheDB) {
        this.cacheDB = cacheDB;
    }

    public BserDBModel getCacheDB() {
        return cacheDB;
    }
}
