package net.moeg.elt;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.moeg.elt.gui.WoodCutterScreen;
import net.moeg.elt.handlers.ScreenHandlerTypeELT;
import net.moeg.elt.render.LeavesColorProvider;

import static net.moeg.elt.handlers.Handler_Blocks.EXAMPLE_BLOCK;

public class ELT_Main_Client implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ColorProviderRegistry.BLOCK.register(LeavesColorProvider.INSTANCE, EXAMPLE_BLOCK);
        //ScreenProviderRegistry.INSTANCE.<WoodCutterScreenHandler>registerFactory(ELT_Main.WOOD_CUTTER, (container) -> new WoodCutterScreen(container, MinecraftClient.getInstance().player.inventory, new TranslatableText(ELT_Main.WOOD_CUTTER_TRANSLATION_KEY)));
        ScreenRegistry.register(ScreenHandlerTypeELT.WOOD_CUTTER, WoodCutterScreen::new);
    }
}
