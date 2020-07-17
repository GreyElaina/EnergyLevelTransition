package net.moeg.elt.loaders;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.moeg.elt.items.ItemBase;
import net.moeg.elt.items.ItemGroups_ELT;

/**
 * 这是一个用来注册物品和方块的物品形态的类
 * 先定义一个物品的静态字段
 * 然后在静态域注册
 *
 * 例子
 *     public static final Item ELT_SYMBOL;
 *
 * static {
 *
 *         ELT_SYMBOL = register("symbol", new ItemBase((new Item.Settings()).group(ItemGroups_ELT.ELT_MISC)));
 *     }
 *
 * 如果要注册方块的物品形态
 * static {
 *
 *         EXAMPLE_BLOCK = register(Loader_Blocks.EXAMPLE_BLOCK, ItemGroups_ELT.ELT_MACHINE);
 *
 *     }
 *
 * */
public class Loader_Items {

    public static final Item ELT_SYMBOL;
    public static final Item BRANCH;
    public static final Item EXAMPLE_BLOCK;
    public static final Item MANUAL_WOOD_CUTTER;



    private static Item register(Block block) {
        return register(new BlockItem(block, new Item.Settings()));
    }

    private static Item register(Block block, ItemGroup group) {
        return register(new BlockItem(block, (new Item.Settings()).group(group)));
    }

    private static Item register(BlockItem item) {
        return register((Block)item.getBlock(), (Item)item);
    }

    protected static Item register(Block block, Item item) {
        return register(Registry.BLOCK.getId(block), item);
    }

    private static Item register(String namespace, String path, Item item) {
        return register(new Identifier(namespace, path), item);
    }

    private static Item register(String path, Item item) {
        return register(new Identifier("elt", path), item);
    }

    private static Item register(Identifier id, Item item) {
        if (item instanceof BlockItem) {
            ((BlockItem)item).appendBlocks(Item.BLOCK_ITEMS, item);
        }

        return (Item) Registry.register(Registry.ITEM, (Identifier)id, item);
    }

    static {
        ELT_SYMBOL = register("symbol", new ItemBase((new Item.Settings()).group(ItemGroups_ELT.ELT_MISC)));
        BRANCH = register("branch", new ItemBase((new Item.Settings()).group(ItemGroups_ELT.ELT_MATERIAL)));
        EXAMPLE_BLOCK = register(Loader_Blocks.EXAMPLE_BLOCK, ItemGroups_ELT.ELT_MACHINE);
        MANUAL_WOOD_CUTTER = register(Loader_Blocks.MANUAL_WOOD_CUTTER, ItemGroups_ELT.ELT_MACHINE);

    }

}
