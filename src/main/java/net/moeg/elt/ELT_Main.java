package net.moeg.elt;

import net.devtech.arrp.api.RRPCallback;
import net.devtech.arrp.api.RuntimeResourcePack;
import net.devtech.arrp.json.lang.JLang;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;
import net.moeg.elt.blockentity.DemoBlockEntity;
import net.moeg.elt.items.ItemGroups_ELT;
import net.moeg.elt.handlers.Handler_Blocks;
import net.moeg.elt.handlers.Handler_Items;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static net.moeg.elt.handlers.Handler_Blocks.EXAMPLE_BLOCK;
import static net.devtech.arrp.api.RuntimeResourcePack.*;
import static net.devtech.arrp.json.loot.JLootTable.*;

public class ELT_Main implements ModInitializer {

	public static final String MOD_ID = "elt";
	public static final RuntimeResourcePack RESOURCE_PACK = RuntimeResourcePack.create("elt:main");
	public static final Logger LOGGER = LogManager.getFormatterLogger("Energy Level Transition");

	public static JLang EN_US = JLang.lang();
	public static JLang ZH_CN = JLang.lang();

	/** Load the ItemGroups */
	public static final ItemGroups_ELT ITEM_GROUPS_ELT = new ItemGroups_ELT();

	public static BlockEntityType<DemoBlockEntity> DEMO_BLOCK_ENTITY;
	public static BlockEntityType<DemoBlockEntity> BlockEntityWoodCutter;

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


		Handler_Items Handler_ITEMS = new Handler_Items();
		Handler_Blocks Handler_BLOCKS = new Handler_Blocks();

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

		DEMO_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "elt:demo", BlockEntityType.Builder.create(DemoBlockEntity::new, EXAMPLE_BLOCK).build(null));
		BlockEntityWoodCutter = Registry.register(Registry.BLOCK_ENTITY_TYPE, "elt:wood_cutter", BlockEntityType.Builder.create(DemoBlockEntity::new, EXAMPLE_BLOCK).build(null));

		LOGGER.info("---Energy Level Transition Initialized!---");

	}
}
