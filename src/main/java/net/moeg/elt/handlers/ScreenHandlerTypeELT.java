/*
 * Copyright (c) 2020. TeamMoeg
 *
 * This file is part of Energy Level Transition.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights to
 *  use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 *   of the Software, and to permit persons to whom the Software is furnished to
 *   do so, subject to the following conditions:
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 *  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 *  WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 *  PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 *  OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 *  OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 *  OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 *  SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 *  You should have received a copy of the MIT License
 *  along with GregTech. If not, see <https://opensource.org/licenses/MIT>.
 */

package net.moeg.elt.handlers;

import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.moeg.elt.gui.WoodCutterScreenHandler;

public class ScreenHandlerTypeELT {

    public static final ScreenHandlerType<WoodCutterScreenHandler> WOOD_CUTTER = ScreenHandlerRegistry.registerExtended(new Identifier("elt", "wood_cutter_screenhandler"), (syncId, inventory, buf) -> {
        BlockPos pos = buf.readBlockPos();
        World world = inventory.player.world;
        return new WoodCutterScreenHandler(syncId, inventory, (Inventory) world.getBlockEntity(pos), ScreenHandlerContext.create(inventory.player.world, pos));
    });

}
