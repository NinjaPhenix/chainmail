package ninjaphenix.chainmail.api.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.network.ServerPlayerEntity;

public interface PlayerDisconnectCallback {
    Event<PlayerDisconnectCallback> EVENT = EventFactory.createArrayBacked(PlayerDisconnectCallback.class, listeners ->
            (player) -> {
                for (PlayerDisconnectCallback listener : listeners) {
                    listener.onPlayerDisconnected(player);
                }
            });

    void onPlayerDisconnected(ServerPlayerEntity player);
}
