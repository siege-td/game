package com.siegetd.game.singletons;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.utils.Array;

import java.util.List;

public class MyAssetManager{

    public static MyAssetManager assetManagerInstance = null;
    private AssetManager assMan;

    private MyAssetManager(){
        assMan = new AssetManager();
    }

    public static MyAssetManager getInstance(){
        if(assetManagerInstance == null){
            assetManagerInstance = new MyAssetManager();
        }
        return assetManagerInstance;
    }

    private void queueAssetsFromList(List<String> paths,Class<?> type ){
        for (String path:paths) {
            assMan.load(path, type);
        }
    }

    private void queueAsset(String path, Class<?> type){
        assMan.load(path,type);
    }
}
