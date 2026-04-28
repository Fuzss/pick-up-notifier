package fuzs.pickupnotifier.client;

import fuzs.pickupnotifier.PickUpNotifier;
import fuzs.pickupnotifier.client.handler.DrawEntriesHandler;
import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.common.api.client.core.v1.context.GuiLayersContext;
import fuzs.puzzleslib.common.api.client.event.v1.ClientTickEvents;
import fuzs.puzzleslib.common.api.client.event.v1.entity.player.ClientPlayerCopyCallback;
import fuzs.puzzleslib.common.api.client.event.v1.entity.player.ClientPlayerNetworkEvents;

public class PickUpNotifierClient implements ClientModConstructor {

    @Override
    public void onClientSetup() {
        registerEventHandlers();
    }

    private static void registerEventHandlers() {
        ClientTickEvents.END.register(DrawEntriesHandler.INSTANCE::onClientTick);
        ClientPlayerNetworkEvents.LEAVE.register(DrawEntriesHandler.INSTANCE::onPlayerLeave);
        ClientPlayerCopyCallback.EVENT.register(DrawEntriesHandler.INSTANCE::onCopy);
    }

    @Override
    public void onRegisterGuiLayers(GuiLayersContext context) {
        context.registerGuiLayer(GuiLayersContext.STATUS_EFFECTS,
                PickUpNotifier.id("pick_up_entries"),
                DrawEntriesHandler.INSTANCE::renderPickUpEntries);
    }
}
