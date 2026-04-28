package fuzs.pickupnotifier.common;

import fuzs.pickupnotifier.common.config.ClientConfig;
import fuzs.pickupnotifier.common.config.ServerConfig;
import fuzs.pickupnotifier.common.network.ClientboundTakeItemMessage;
import fuzs.pickupnotifier.common.network.ClientboundTakeItemStackMessage;
import fuzs.puzzleslib.common.api.config.v3.ConfigHolder;
import fuzs.puzzleslib.common.api.core.v1.ModConstructor;
import fuzs.puzzleslib.common.api.core.v1.context.PayloadTypesContext;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PickUpNotifier implements ModConstructor {
    public static final String MOD_ID = "pickupnotifier";
    public static final String MOD_NAME = "Pick Up Notifier";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public static final ConfigHolder CONFIG = ConfigHolder.builder(MOD_ID)
            .client(ClientConfig.class)
            .server(ServerConfig.class);

    @Override
    public void onRegisterPayloadTypes(PayloadTypesContext context) {
        context.optional();
        context.playToClient(ClientboundTakeItemMessage.class, ClientboundTakeItemMessage.STREAM_CODEC);
        context.playToClient(ClientboundTakeItemStackMessage.class, ClientboundTakeItemStackMessage.STREAM_CODEC);
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}
