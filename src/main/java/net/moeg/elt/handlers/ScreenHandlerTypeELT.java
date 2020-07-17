package net.moeg.elt.handlers;

import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.moeg.elt.gui.WoodCutterScreenHandler;

public class ScreenHandlerTypeELT {

     public static final ScreenHandlerType<WoodCutterScreenHandler> WOOD_CUTTER = ScreenHandlerRegistry.registerSimple(new Identifier("elt", "wood_cutter"), WoodCutterScreenHandler::new);

}
