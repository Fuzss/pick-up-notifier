package fuzs.pickupnotifier.fabric.client;

import fuzs.pickupnotifier.common.PickUpNotifier;
import fuzs.pickupnotifier.common.client.PickUpNotifierClient;
import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import net.fabricmc.api.ClientModInitializer;

public class PickUpNotifierFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientModConstructor.construct(PickUpNotifier.MOD_ID, PickUpNotifierClient::new);
    }
}
