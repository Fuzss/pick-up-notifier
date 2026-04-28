package fuzs.pickupnotifier.common.config;

import fuzs.pickupnotifier.common.PickUpNotifier;
import net.minecraft.client.gui.navigation.ScreenAxis;

public enum MoveOut {
    DISABLED {
        @Override
        public boolean move(ScreenAxis screenAxis) {
            return false;
        }
    },
    HORIZONTALLY_ONLY {
        @Override
        public boolean move(ScreenAxis screenAxis) {
            return screenAxis == ScreenAxis.HORIZONTAL
                    && !PickUpNotifier.CONFIG.get(ClientConfig.class).display.position.isHorizontalCenter();
        }
    },
    VERTICALLY_ONLY {
        @Override
        public boolean move(ScreenAxis screenAxis) {
            return screenAxis == ScreenAxis.VERTICAL
                    && !PickUpNotifier.CONFIG.get(ClientConfig.class).display.position.isVerticalCenter();
        }
    },
    ENABLED {
        @Override
        public boolean move(ScreenAxis screenAxis) {
            return PickUpNotifier.CONFIG.get(ClientConfig.class).display.position != AnchorPoint.CENTER;
        }
    };

    public abstract boolean move(ScreenAxis screenAxis);
}
