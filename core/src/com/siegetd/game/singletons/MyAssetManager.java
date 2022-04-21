package com.siegetd.game.singletons;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.utils.Array;

import java.util.List;

/**queueAsset() adds asset to assMans queue
 * Use finishLoading() to load all assets to memory / This is a blocking method
 * Use update to load assets in non blocking way
 * Use assman.get to get assets from memory
 * Path is automaticly set to resources, set path out from that directory
 * When adding assets to load use <Class>.class for type
 * **/

public class MyAssetManager{

    public static MyAssetManager assetManagerInstance = null;
    private static AssetManager assMan;

    private MyAssetManager(){
        assMan = new AssetManager();
    }

    public static MyAssetManager getInstance(){
        if(assetManagerInstance == null){
            assetManagerInstance = new MyAssetManager();
        }
        return assetManagerInstance;
    }

    public void queueAssetsFromList(List<String> paths,Class<?> type ){
        for (String path:paths) {
            assMan.load(path, type);
        }
    }

    public void queueAsset(String path, Class<?> type){
        assMan.load(path,type);
    }

    public AssetManager getAssMan(){
        return assMan;
    }
}
