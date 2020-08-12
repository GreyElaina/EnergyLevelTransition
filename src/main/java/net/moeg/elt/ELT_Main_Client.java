package net.moeg.elt;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.moeg.elt.gui.WoodCutterScreen;
import net.moeg.elt.handlers.ScreenHandlerTypeELT;
import net.moeg.eltcore.render.LeavesColorProvider;

import static net.moeg.elt.handlers.Handler_Blocks.EXAMPLE_BLOCK;

public class ELT_Main_Client implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ColorProviderRegistry.BLOCK.register(LeavesColorProvider.INSTANCE, EXAMPLE_BLOCK);
        ScreenRegistry.register(ScreenHandlerTypeELT.WOOD_CUTTER, WoodCutterScreen::new);
    }
}
