/*
 * Copyright (c) 2020. TeamMoeg
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
