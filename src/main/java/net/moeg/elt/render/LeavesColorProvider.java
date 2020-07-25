package net.moeg.elt.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;

import javax.annotation.Nullable;

import static net.moeg.elt.api.data.CS.WHITE;

@Environment(EnvType.CLIENT)
public class LeavesColorProvider implements BlockColorProvider {

    public static final LeavesColorProvider INSTANCE = new LeavesColorProvider();

    @Override
    public int getColor(BlockState state, @Nullable BlockRenderView world, @Nullable BlockPos pos, int tintIndex) {

        int color = WHITE;
        int biomecolor;

        if (world != null) {

            biomecolor = BiomeColors.getFoliageColor(world, pos);
            return biomecolor;

        } else return color;

//        if (pos != null && pos.getY() == 60) {
//            color = RED;
//        }
//        else if (pos != null && pos.getX() == 101) {
//            color = BLUE;
//        }
//        else {
//            color = WHITE;
//        }

    }
}
