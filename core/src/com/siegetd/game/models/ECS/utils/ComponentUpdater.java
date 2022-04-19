package com.siegetd.game.models.ECS.utils;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.siegetd.game.Globals;
import com.siegetd.game.api.SocketConnection;
import com.siegetd.game.models.ECS.components.CurrencyComponent;
import com.siegetd.game.models.ECS.components.HitpointComponent;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import io.socket.client.Socket;

public class ComponentUpdater {

    private SocketConnection connection;
    private Socket socket;

    private ComponentMapper<HitpointComponent> hitpointMapper;
    private ComponentMapper<CurrencyComponent> currencyMapper;

    public ComponentUpdater() {
        try {
            connection = SocketConnection.getInstance();
            this.socket = connection.getSocket();
        } catch (URISyntaxException error) {
            System.out.println(error);
        }

        hitpointMapper = ComponentMapper.getFor(HitpointComponent.class);
        currencyMapper = ComponentMapper.getFor(CurrencyComponent.class);
    }

    public void updateHitpointComponent(
            Entity entity,
            int newHitpoints
    ) throws JSONException {
        entity.remove(HitpointComponent.class);
        entity.add(new HitpointComponent(newHitpoints));

        // Create JSON object
        JSONObject object = new JSONObject();
        object.put("pin", Globals.pin);
        object.put("playerName", "pelton");
        object.put("hitpoints", hitpointMapper.get(entity).hitpoints);
        object.put("currency", currencyMapper.get(entity).currency);

        // Update server
        socket.emit("update_game_data", object);
    }

    public void updateCurrencyComponent(
            Entity entity,
            int newCurrency
    ) throws JSONException {
        entity.remove(CurrencyComponent.class);
        entity.add(new CurrencyComponent(newCurrency));

        // Create JSON object
        JSONObject object = new JSONObject();
        object.put("pin", 1);
        object.put("playerName", "pelton");
        object.put("hitpoints", hitpointMapper.get(entity).hitpoints);
        object.put("currency", currencyMapper.get(entity).currency);

        socket.emit("update_game_data", object);
    }
}
