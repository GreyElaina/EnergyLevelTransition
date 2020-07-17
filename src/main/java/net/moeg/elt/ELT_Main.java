package net.moeg.elt;

import net.fabricmc.api.ModInitializer;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;
import net.moeg.elt.blockentity.DemoBlockEntity;
import net.moeg.elt.items.ItemGroups_ELT;
import net.moeg.elt.loaders.Loader_Blocks;
import net.moeg.elt.loaders.Loader_Items;

import static net.moeg.elt.loaders.Loader_Blocks.EXAMPLE_BLOCK;

public class ELT_Main implements ModInitializer {

	public static final String MOD_ID = "elt";


	/** Load the ItemGroups */
	public static final ItemGroups_ELT ITEM_GROUPS_ELT = new ItemGroups_ELT();

	public static BlockEntityType<DemoBlockEntity> DEMO_BLOCK_ENTITY;

	@Override
	public void onInitialize() {

		Loader_Items LOADER_ITEMS = new Loader_Items();
		Loader_Blocks LOADER_BLOCKS = new Loader_Blocks();

		DEMO_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "elt:demo", BlockEntityType.Builder.create(DemoBlockEntity::new, EXAMPLE_BLOCK).build(null));

		System.out.println("===ELT Loaded===");

	}
}
