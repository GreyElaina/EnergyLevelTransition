package net.moeg.elt.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.screen.StonecutterScreenHandler;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.moeg.elt.blockentity.BlockEntityWoodCutter;
import net.moeg.elt.blockentity.DemoBlockEntity;
import net.moeg.elt.gui.WoodCutterScreenHandler;

import javax.annotation.Nullable;

public class WoodCutterBlock extends BlockWithEntity {

    protected static final VoxelShape SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
    private static final TranslatableText TITLE = new TranslatableText("container.woodcutter");
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }


    public WoodCutterBlock(FabricBlockSettings settings) {
        super(settings.hardness(10.0f).breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
    }

    /** Action on clicking the block.  */
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) {
            return ActionResult.SUCCESS;
        } else {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof BlockEntityWoodCutter) {
                System.out.println(player);
                player.openHandledScreen((BlockEntityWoodCutter)blockEntity);
            }
            return ActionResult.CONSUME;
        }
    }


    /** Register the voxel shape of the block. */
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public BlockEntity createBlockEntity(BlockView blockView) {
        return new BlockEntityWoodCutter();
    }
}
