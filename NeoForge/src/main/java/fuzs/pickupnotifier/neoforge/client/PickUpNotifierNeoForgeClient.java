package fuzs.pickupnotifier.neoforge.client;

import fuzs.pickupnotifier.common.PickUpNotifier;
import fuzs.pickupnotifier.common.client.PickUpNotifierClient;
import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

@Mod(value = PickUpNotifier.MOD_ID, dist = Dist.CLIENT)
public class PickUpNotifierNeoForgeClient {

    public PickUpNotifierNeoForgeClient() {
        ClientModConstructor.construct(PickUpNotifier.MOD_ID, PickUpNotifierClient::new);
    }
}
