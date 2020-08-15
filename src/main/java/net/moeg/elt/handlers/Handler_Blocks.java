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

import net.devtech.arrp.json.animation.JAnimation;
import net.devtech.arrp.json.blockstate.JState;
import net.devtech.arrp.json.models.JModel;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.moeg.elt.blocks.ExampleBlock;
import net.moeg.elt.blocks.WoodCutterBlock;

import static net.moeg.elt.ELT_Main.*;
import static net.moeg.eltcore.data.CS.F;
import static net.moeg.eltcore.data.CS.T;

/**
 * 这是一个用来注册方块的类
 * 先定义一个方块的静态字段
 * 然后在静态域注册
 * <p>
 * 例子
 * public static final Block EXAMPLE_BLOCK;
 * <p>
 * static {
 * <p>
 * EXAMPLE_BLOCK = register("example_block", new ExampleBlock(FabricBlockSettings.of(Material.STONE)));*
 * }
 */

public class Handler_Blocks {

    public static final Block EXAMPLE_BLOCK;
    public static final Block MANUAL_WOOD_CUTTER;
    public static final Block EXAMPLE_2;

    static {

        EXAMPLE_BLOCK = register(T, T, "example_block", "Example Block", "一个例子方块", new ExampleBlock(FabricBlockSettings.of(Material.STONE)));
        MANUAL_WOOD_CUTTER = register(T, F, "wood_cutter", "Manual Wood Cutter", "原木切割案", new WoodCutterBlock(FabricBlockSettings.of(Material.WOOD)));
        EXAMPLE_2 = register(F, F, "example2", "An Example", "第二个例子方块", new Block(FabricBlockSettings.of(Material.WOOD)));
    }

    // No Model is registered
    private static Block register(String path, Block block) {
        return Registry.register(Registry.BLOCK, new Identifier("elt", path), block);
    }

    @Deprecated
    private static Block registerNoEN(boolean isCustomModel, boolean isCustomBlockState, String path, Block block) {
        registerModel(isCustomModel, path);
        registerBlockItemModel(path);
        registerBlockState(isCustomBlockState, path);
        return Registry.register(Registry.BLOCK, new Identifier("elt", path), block);
    }

    @Deprecated
    private static Block registerNoCN(boolean isCustomModel, boolean isCustomBlockState, String path, String en, Block block) {
        registerModel(isCustomModel, path);
        registerBlockItemModel(path);
        registerBlockState(isCustomBlockState, path);
        registerLang(path, "en_us", en);
        return Registry.register(Registry.BLOCK, new Identifier("elt", path), block);
    }

    // no custom model nor blockstate
    private static Block register(String path, String en, String cn, Block block) {
        return register(F, path, en, cn, block);
    }

    // no custom blockstate
    private static Block register(boolean isCustomModel, String path, String en, String cn, Block block) {
        return register(isCustomModel, F, path, en, cn, block);
    }

    /**
     * THe recommended method to register a block.
     */
    private static Block register(boolean isCustomModel, boolean isCustomBlockState, String path, String en, String cn, Block block) {
        registerModel(isCustomModel, path);
        registerBlockItemModel(path);
        registerBlockState(isCustomBlockState, path);
        registerLang(path, "en_us", en);
        registerLang(path, "zh_cn", cn);
        return Registry.register(Registry.BLOCK, new Identifier("elt", path), block);
    }

    /**
     * The Run Time Resources registering methods.
     */
    private static void registerBlockState(boolean isCustomBlockState, String path) {
        if (!isCustomBlockState) {
            Identifier resourceId = new Identifier("elt", path);
            RESOURCE_PACK.addBlockState(JState.state(JState.variant(JState.model("elt:block/" + path))), resourceId);
        }
    }

    private static void registerModel(boolean isCustomModel, String path, String parent) {
        if (!isCustomModel) {
            Identifier resourceId = new Identifier("elt", "block/" + path);
            RESOURCE_PACK.addModel(JModel.model(parent).textures(JModel.textures().var("all", "elt:block/" + path).particle("#all")), resourceId);
        }
    }

    private static void registerBlockItemModel(String path) {
        Identifier resourceId = new Identifier("elt", "item/" + path);
        RESOURCE_PACK.addModel(JModel.model("elt:block/" + path), resourceId);
    }

    private static void registerModel(boolean isCustomModel, String path) {
        if (!isCustomModel) {
            Identifier resourceId = new Identifier("elt", "block/" + path);
            RESOURCE_PACK.addModel(JModel.model("block/cube_all").textures(JModel.textures().var("all", "elt:block/" + path).particle("#all")), resourceId);
        }
    }

    private static void registerLang(String path, String language, String name) {
        if (language.equalsIgnoreCase("en_us"))
            RESOURCE_PACK.addLang(new Identifier("elt", language), EN_US.translate("block.elt." + path, name));
        if (language.equalsIgnoreCase("zh_cn"))
            RESOURCE_PACK.addLang(new Identifier("elt", language), ZH_CN.translate("block.elt." + path, name));
    }

    private static void registerAnimation(String path, int frametime) {
        Identifier aniID = new Identifier("elt", "block/" + path);
        RESOURCE_PACK.addAnimation(aniID, JAnimation.animation().frameTime(frametime));
    }

}
