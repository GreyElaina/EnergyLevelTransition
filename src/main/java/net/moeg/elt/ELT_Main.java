package net.moeg.elt;

import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.moeg.elt.api.items.ItemBase;
import net.moeg.elt.blocks.ExampleBlock;
import net.moeg.elt.items.ItemGroups_ELT;

public class ELT_Main implements ModInitializer {

	public static final ItemBase ELT_SYMBOL = new ItemBase(new Item.Settings());

	public static final ItemGroups_ELT ITEM_GROUPS_ELT = new ItemGroups_ELT();

	public static final ExampleBlock EXAMPLE_BLOCK = new ExampleBlock(Block.Settings.of(Material.STONE));


	@Override
	public void onInitialize() {

		Registry.register(Registry.ITEM, new Identifier("elt", "symbol"), ELT_SYMBOL);


		Registry.register(Registry.BLOCK, new Identifier("elt", "example_block"), EXAMPLE_BLOCK);
		Registry.register(Registry.ITEM, new Identifier("elt", "example_block"), new BlockItem(EXAMPLE_BLOCK, new Item.Settings()));


		System.out.println("===ELT Loaded===");

	}
}
