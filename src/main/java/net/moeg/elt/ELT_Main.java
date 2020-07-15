package net.moeg.elt;

import com.google.common.collect.Sets;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.moeg.elt.blockentity.DemoBlockEntity;
import net.moeg.elt.items.ItemBase;
import net.moeg.elt.blocks.ExampleBlock;
import net.moeg.elt.items.ItemGroups_ELT;

import java.util.Set;

public class ELT_Main implements ModInitializer {

	public static final ItemBase ELT_SYMBOL = new ItemBase(new Item.Settings());

	public static final ItemGroups_ELT ITEM_GROUPS_ELT = new ItemGroups_ELT();

	public static final ExampleBlock EXAMPLE_BLOCK = new ExampleBlock(FabricBlockSettings.of(Material.STONE));

	public static BlockEntityType<DemoBlockEntity> DEMO_BLOCK_ENTITY;

	private static Set<Material> AXEABLE = Sets.newHashSet(new Material[]{Material.NETHER_WOOD});

	@Override
	public void onInitialize() {

		Registry.register(Registry.ITEM, new Identifier("elt", "symbol"), ELT_SYMBOL);

		Registry.register(Registry.BLOCK, new Identifier("elt", "example_block"), EXAMPLE_BLOCK);
		Registry.register(Registry.ITEM, new Identifier("elt", "example_block"), new BlockItem(EXAMPLE_BLOCK, new Item.Settings()));

		DEMO_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "elt:demo", BlockEntityType.Builder.create(DemoBlockEntity::new, EXAMPLE_BLOCK).build(null));

		ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> 0x3495eb, EXAMPLE_BLOCK);

		System.out.println("===ELT Loaded===");

	}
}
