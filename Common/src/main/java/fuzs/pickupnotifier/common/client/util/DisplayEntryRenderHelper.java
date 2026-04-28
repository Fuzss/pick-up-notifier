package fuzs.pickupnotifier.common.client.util;

import com.google.common.collect.ImmutableSortedMap;
import fuzs.pickupnotifier.common.PickUpNotifier;
import fuzs.pickupnotifier.common.config.ClientConfig;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;
import net.minecraft.world.item.ItemStack;

import java.util.Map;
import java.util.NavigableMap;

public class DisplayEntryRenderHelper {
    /**
     * @see net.minecraft.client.gui.screens.inventory.tooltip.TooltipRenderUtil#BACKGROUND_SPRITE
     */
    private static final Identifier BACKGROUND_SPRITE = Identifier.withDefaultNamespace("tooltip/background");
    /**
     * @see net.minecraft.client.gui.screens.inventory.tooltip.TooltipRenderUtil#FRAME_SPRITE
     */
    private static final Identifier FRAME_SPRITE = Identifier.withDefaultNamespace("tooltip/frame");
    private static final NavigableMap<Integer, Character> MAP = ImmutableSortedMap.<Integer, Character>naturalOrder()
            .put(1_000, 'K')
            .put(1_000_000, 'M')
            .put(1_000_000_000, 'B')
            .build();

    private static float itemStackAlpha = 1.0F;

    public static void renderItem(GuiGraphicsExtractor guiGraphics, ItemStack itemStack, int x, int y, float alpha) {
        itemStackAlpha = alpha;
        guiGraphics.item(itemStack, x, y);
        itemStackAlpha = 1.0F;
    }

    public static float getItemStackAlpha() {
        return itemStackAlpha;
    }

    private static MutableComponent shortenValue(int value) {
        Map.Entry<Integer, Character> entry = MAP.floorEntry(value);
        if (entry == null) {
            return Component.literal(String.valueOf(value));
        } else {
            return Component.literal(String.valueOf(value / entry.getKey()) + entry.getValue());
        }
    }

    public static void renderGuiItemDecorations(GuiGraphicsExtractor guiGraphics, Font font, int count, int xPosition, int yPosition, float alpha) {
        if (count <= 1 && !PickUpNotifier.CONFIG.get(ClientConfig.class).display.displaySingleCount) {
            return;
        }

        guiGraphics.pose().pushMatrix();

        Component component = shortenValue(count);
        float scale = Math.min(1.0F, 16.0F / font.width(component));
        guiGraphics.pose().scale(scale, scale);

        float posX = (xPosition + 17) / scale - font.width(component);
        float posY = (yPosition + font.lineHeight * 2) / scale - font.lineHeight;
        guiGraphics.text(font, component, (int) posX, (int) posY, ARGB.color(alpha, -1), true);

        guiGraphics.pose().popMatrix();
    }

    /**
     * Supports an additional color parameter (used for alpha).
     *
     * @see net.minecraft.client.gui.screens.inventory.tooltip.TooltipRenderUtil#extractTooltipBackground(GuiGraphicsExtractor,
     *         int, int, int, int, Identifier)
     */
    public static void extractTooltipBackground(GuiGraphicsExtractor guiGraphics, int x, int y, int width, int height, int color) {
        int x0 = x - 3 - 9;
        int y0 = y - 3 - 9;
        int paddedWidth = width + 3 + 3 + 18;
        int paddedHeight = height + 3 + 3 + 18;
        guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED,
                BACKGROUND_SPRITE,
                x0,
                y0,
                paddedWidth,
                paddedHeight,
                color);
        guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, FRAME_SPRITE, x0, y0, paddedWidth, paddedHeight, color);
    }
}
