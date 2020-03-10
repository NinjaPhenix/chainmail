package ninjaphenix.chainmail.api.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.network.ServerPlayerEntity;

public interface PlayerConnectCallback {
    Event<PlayerConnectCallback> EVENT = EventFactory.createArrayBacked(PlayerConnectCallback.class, listeners ->
            (player) -> {
                for (PlayerConnectCallback listener : listeners) {
                    listener.onPlayerConnected(player);
                }
            });

    void onPlayerConnected(ServerPlayerEntity player);
}
