package net.moeg.elt.items;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import static net.moeg.elt.handlers.Handler_Items.*;

public class ItemGroups_ELT {

    public static ItemGroup ELT_MATERIAL = FabricItemGroupBuilder.create(
            new Identifier("elt", "material"))
            .icon(() -> new ItemStack(ELT_SYMBOL))
            .build();

    public static ItemGroup ELT_MACHINE = FabricItemGroupBuilder.create(
            new Identifier("elt", "machine"))
            .icon(() -> new ItemStack(ELT_SYMBOL))
            .build();

    public static ItemGroup ELT_TOOLS = FabricItemGroupBuilder.create(
            new Identifier("elt", "tools"))
            .icon(() -> new ItemStack(ELT_SYMBOL))
            .build();

    public static ItemGroup ELT_FOOD = FabricItemGroupBuilder.create(
            new Identifier("elt", "food"))
            .icon(() -> new ItemStack(ELT_SYMBOL))
            .build();

    public static ItemGroup ELT_FLUID = FabricItemGroupBuilder.create(
            new Identifier("elt", "fluid"))
            .icon(() -> new ItemStack(ELT_SYMBOL))
            .build();

    public static ItemGroup ELT_MISC = FabricItemGroupBuilder.create(
            new Identifier("elt", "misc"))
            .icon(() -> new ItemStack(ELT_SYMBOL))
            .build();

}
