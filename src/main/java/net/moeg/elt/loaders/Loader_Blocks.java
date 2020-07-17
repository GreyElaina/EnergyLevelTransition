package net.moeg.elt.loaders;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.moeg.elt.blocks.ExampleBlock;
import net.moeg.elt.blocks.WoodCutterBlock;

/**
 * 这是一个用来注册方块的类
 * 先定义一个方块的静态字段
 * 然后在静态域注册
 *
 * 例子
 * public static final Block EXAMPLE_BLOCK;
 *
 * static {
 *
 *         EXAMPLE_BLOCK = register("example_block", new ExampleBlock(FabricBlockSettings.of(Material.STONE)));*
 *     }
 *
 * */

public class Loader_Blocks {

    public static final Block EXAMPLE_BLOCK;
    public static final Block MANUAL_WOOD_CUTTER;


    private static Block register(String path, Block block) {

        return (Block) Registry.register(Registry.BLOCK, new Identifier("elt", path), block);
    }

    static {

        EXAMPLE_BLOCK = register("example_block", new ExampleBlock(FabricBlockSettings.of(Material.STONE)));
        MANUAL_WOOD_CUTTER = register("wood_cutter", new WoodCutterBlock(FabricBlockSettings.of(Material.WOOD)));

    }

}
