package net.moeg.elt.loaders;

import net.devtech.arrp.api.RuntimeResourcePack;
import net.devtech.arrp.json.animation.JAnimation;
import net.devtech.arrp.json.lang.JLang;
import net.devtech.arrp.json.models.JModel;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.moeg.elt.items.ItemBase;
import net.moeg.elt.items.ItemGroups_ELT;
import org.spongepowered.asm.mixin.injection.Redirect;

import static net.moeg.elt.ELT_Main.RESOURCE_PACK;

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

    public static final RuntimeResourcePack ITEM_RESOURCE = RuntimeResourcePack.create("elt:item");


    public static final Item ELT_SYMBOL;
    public static final Item BRANCH;
    public static final Item BRICH_BRANCH;

    public static final Item EXAMPLE_BLOCK, MANUAL_WOOD_CUTTER, EXAMPLE_2;

    /** Methods with Block Item. */

    private static Item register(Block block, ItemGroup group) {
        return register(new BlockItem(block, (new Item.Settings()).group(group)));
    }

    private static Item register(Block block) {
        return register(new BlockItem(block, new Item.Settings()));
    }

    private static Item register(BlockItem item) {
        return register((Block)item.getBlock(), (Item)item);
    }

    protected static Item register(Block block, Item item) {
        return register(Registry.BLOCK.getId(block), item);
    }

    /** Methods with Item. */
    private static Item register(String namespace, String path, Item item) {
        return register(new Identifier(namespace, path), item);
    }

    private static Item register(Identifier id, Item item) {
        if (item instanceof BlockItem) {
            ((BlockItem)item).appendBlocks(Item.BLOCK_ITEMS, item);
        }

        return (Item) Registry.register(Registry.ITEM, (Identifier)id, item);
    }

    /** The true method registering an item with full properties. */
    // Everything is registered
    private static Item register(String path, String enName, String cnName, String enTooltip, String cnTooltip, int frametime, Item item) {

        Identifier itemId = new Identifier("elt", path);
        registerModel(path);
        registerLang(path, "en_us", enName, enTooltip);
        registerLang(path, "zh_cn", cnName, cnTooltip);
        registerAnimation(path, frametime);

        return register(itemId, item);
    }

    private static Item register(String path, String enName, String cnName, int frametime, Item item) {

        Identifier itemId = new Identifier("elt", path);
        registerModel(path);
        registerLang(path, "en_us", enName, "");
        registerLang(path, "zh_cn", cnName, "");
        registerAnimation(path, frametime);

        return register(itemId, item);
    }

    // No animation is registered
    private static Item register(String path, String enName, String cnName, String enTooltip, String cnTooltip, Item item) {

        Identifier itemId = new Identifier("elt", path);
        registerModel(path);
        registerLang(path, "en_us", enName, enTooltip);
        registerLang(path, "zh_cn", cnName, cnTooltip);
        return register(itemId, item);
    }

    private static Item register(String path, String enName, String cnName, Item item) {

        Identifier itemId = new Identifier("elt", path);
        registerModel(path);
        registerLang(path, "en_us", enName, "");
        registerLang(path, "zh_cn", cnName, "");
        return register(itemId, item);
    }

    // No Chinese localization is registered, not recommended to use this.
    private static Item registerNoCN(String path, String en, Item item) {

        Identifier itemId = new Identifier("elt", path);
        registerModel(path);
        registerLang(path, "en_us", en, "");
        registerLang(path, "zh_cn", en, "");

        return register(itemId, item);
    }

    // No English nor Chinese localization is registered
    private static Item registerNoEN(String path, Item item) {

        Identifier itemId = new Identifier("elt", path);
        registerModel(path);

        return register(itemId, item);
    }

    // No model is registered
    private static Item register(String path, Item item) {

        Identifier itemId = new Identifier("elt", path);

        return register(itemId, item);
    }

    /** The Run Time Resources registering methods */
    private static void registerModel(String path, String parent) {
        Identifier resourceId = new Identifier("elt", "item/"+path);
        ITEM_RESOURCE.addModel(JModel.model(parent).textures(JModel.textures().layer0("elt:item/"+path)), resourceId);
    }

    private static void registerModel(String path) {
        Identifier resourceId = new Identifier("elt", "item/"+path);
        ITEM_RESOURCE.addModel(JModel.model("item/generated").textures(JModel.textures().layer0("elt:item/"+path)), resourceId);
    }

    // With specified tooltip
    private static void registerLang(String path, String language, String name, String tooltip) {
        ITEM_RESOURCE.addLang(new Identifier("elt", language), JLang.lang().translate("item.elt."+path, name).translate("item.elt."+path+".tooltip", tooltip));
    }

    private static void registerAnimation(String path, int frametime) {
        Identifier aniID = new Identifier("elt", "item/"+path);
        ITEM_RESOURCE.addAnimation(aniID, JAnimation.animation().frameTime(frametime));
    }

    static {
        ELT_SYMBOL = register("symbol", "Symbol", "能级跃迁", "", "", new ItemBase((new Item.Settings()).group(ItemGroups_ELT.ELT_MISC)));
        BRANCH = register("oak_branch", "Oak Branch", "橡木树枝", "A branch", "一根树枝", new ItemBase((new Item.Settings()).group(ItemGroups_ELT.ELT_MATERIAL)));
        BRICH_BRANCH = register("birch_branch", "Birch Branch", "白桦树枝", "A branch", "一根树枝", new ItemBase((new Item.Settings()).group(ItemGroups_ELT.ELT_MATERIAL)));

        EXAMPLE_BLOCK = register(Loader_Blocks.EXAMPLE_BLOCK, ItemGroups_ELT.ELT_MACHINE);
        MANUAL_WOOD_CUTTER = register(Loader_Blocks.MANUAL_WOOD_CUTTER, ItemGroups_ELT.ELT_MACHINE);
        EXAMPLE_2 = register(Loader_Blocks.EXAMPLE_2, ItemGroups_ELT.ELT_MACHINE);

    }

}
