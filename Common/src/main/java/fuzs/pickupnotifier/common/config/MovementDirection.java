package fuzs.pickupnotifier.common.config;

import fuzs.pickupnotifier.common.PickUpNotifier;
import fuzs.puzzleslib.common.api.client.gui.v2.AnchorPoint;
import net.minecraft.client.gui.navigation.ScreenAxis;

public enum MovementDirection {
    NONE {
        @Override
        public boolean move(ScreenAxis screenAxis) {
            return false;
        }
    },
    HORIZONTAL {
        @Override
        public boolean move(ScreenAxis screenAxis) {
            return screenAxis == ScreenAxis.HORIZONTAL
                    && !PickUpNotifier.CONFIG.get(ClientConfig.class).display.position.isHorizontalCenter();
        }
    },
    VERTICAL {
        @Override
        public boolean move(ScreenAxis screenAxis) {
            return screenAxis == ScreenAxis.VERTICAL
                    && !PickUpNotifier.CONFIG.get(ClientConfig.class).display.position.isVerticalCenter();
        }
    },
    ALL {
        @Override
        public boolean move(ScreenAxis screenAxis) {
            return PickUpNotifier.CONFIG.get(ClientConfig.class).display.position != AnchorPoint.CENTER;
        }
    };

    public abstract boolean move(ScreenAxis screenAxis);
}
