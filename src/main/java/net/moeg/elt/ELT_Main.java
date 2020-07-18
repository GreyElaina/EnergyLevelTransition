package net.moeg.elt;

import net.devtech.arrp.api.RRPCallback;
import net.devtech.arrp.api.RuntimeResourcePack;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;
import net.moeg.elt.blockentity.DemoBlockEntity;
import net.moeg.elt.items.ItemGroups_ELT;
import net.moeg.elt.loaders.Loader_Blocks;
import net.moeg.elt.loaders.Loader_Items;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static net.moeg.elt.loaders.Loader_Blocks.BLOCK_RESOURCE;
import static net.moeg.elt.loaders.Loader_Blocks.EXAMPLE_BLOCK;
import static net.devtech.arrp.api.RuntimeResourcePack.*;
import static net.devtech.arrp.json.loot.JLootTable.*;
import static net.moeg.elt.loaders.Loader_Items.ITEM_RESOURCE;

public class ELT_Main implements ModInitializer {

	public static final String MOD_ID = "elt";
	public static final RuntimeResourcePack RESOURCE_PACK = RuntimeResourcePack.create("elt:main");
	public static final Logger LOGGER = LogManager.getFormatterLogger("Energy Level Transition");

	/** Load the ItemGroups */
	public static final ItemGroups_ELT ITEM_GROUPS_ELT = new ItemGroups_ELT();

	public static BlockEntityType<DemoBlockEntity> DEMO_BLOCK_ENTITY;

	@Override
	public void onInitialize() {

		LOGGER.info("~~~~~ Thanks for playing Energy Level Transition! ~~~~~");
		LOGGER.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		LOGGER.info("~~~~~~~~~~~~        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		LOGGER.info("~~~~~~~~~~~~  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		LOGGER.info("~~~~~~~~~~~~  ~~~~~~~~~  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		LOGGER.info("~~~~~~~~~~~~        ~~~  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		LOGGER.info("~~~~~~~~~~~~  ~~~~~~~~~  ~~~~~~~~~          ~~~~~~~~~~~");
		LOGGER.info("~~~~~~~~~~~~  ~~~~~~~~~  ~~~~~~~~~~~~~  ~~~~~~~~~~~~~~~");
		LOGGER.info("~~~~~~~~~~~~        ~~~  ~~~~~~~~~~~~~  ~~~~~~~~~~~~~~~");
		LOGGER.info("~~~~~~~~~~~~~~~~~~~~~~~  ~~~~~~~~~~~~~  ~~~~~~~~~~~~~~~");
		LOGGER.info("~~~~~~~~~~~~~~~~~~~~~~~        ~~~~~~~  ~~~~~~~~~~~~~~~");
		LOGGER.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  ~~~~~~~~~~~~~~~");
		LOGGER.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  ~~~~~~~~~~~~~~~");
		LOGGER.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		LOGGER.info("~~~~~~~~~~~~~~~~~ Created by TeamMoeg ~~~~~~~~~~~~~~~~~");
		LOGGER.info("~~ https://github.com/MoegTech/EnergyLevelTransition ~~");
		LOGGER.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		LOGGER.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");


		Loader_Items LOADER_ITEMS = new Loader_Items();
		Loader_Blocks LOADER_BLOCKS = new Loader_Blocks();

		RESOURCE_PACK.addLootTable(id("minecraft:blocks/acacia_fence"),
				loot("minecraft:block")
						.pool(pool()
								.rolls(1)
								.entry(entry()
										.type("minecraft:item")
										.name("minecraft:diamond"))
								.condition(condition("minecraft:survives_explosion")))
		);

		RRPCallback.EVENT.register(a -> a.add(RESOURCE_PACK)); // register arrp resourcepack
		RRPCallback.EVENT.register(a -> a.add(ITEM_RESOURCE));
		RRPCallback.EVENT.register(a -> a.add(BLOCK_RESOURCE));

		DEMO_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "elt:demo", BlockEntityType.Builder.create(DemoBlockEntity::new, EXAMPLE_BLOCK).build(null));

		LOGGER.info("---Energy Level Transition Initialized!---");

	}
}
