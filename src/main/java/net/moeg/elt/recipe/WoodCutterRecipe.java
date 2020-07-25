package net.moeg.elt.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.moeg.elt.ELT_Main;
import net.moeg.elt.blockentity.WoodCutterBlockEntity;
import net.moeg.elt.util.ItemStackUtil;

public class WoodCutterRecipe implements Recipe<WoodCutterBlockEntity> {
    private final ItemStack output;
    private final ItemStack output2;
    private final Identifier id;
    private final Ingredient input;
    private final DefaultedList<Ingredient> tools;

    public WoodCutterRecipe(Identifier id,ItemStack output, ItemStack output2, Ingredient input, DefaultedList<Ingredient> tools) {
        this.output = output;
        this.id = id;
        this.output2 = output2;
        this.input = input;
        this.tools = tools;
    }

    @Override
    public boolean matches(WoodCutterBlockEntity inv, World world) {
        if (input.test(inv.getStack(0))) {
            if (tools.size() == 1) {
                return tools.get(0).test(inv.getStack(1)) || tools.get(0).test(inv.getStack(2));
            } else if (tools.size() == 2) {
                return tools.get(0).test(inv.getStack(1)) && tools.get(0).test(inv.getStack(2));
            }
        }
        return false;
    }

    @Override
    public ItemStack craft(WoodCutterBlockEntity inv) {
        ItemStack inv1 = inv.getStack(3);
        ItemStack inv2 = inv.getStack(4);
        if (ItemStack.areItemsEqual(inv1, output) && inv1.getCount() < inv1.getMaxCount() + output.getCount()) {
            inv1.increment(output.getCount());
        }
        else if (inv1.isEmpty()){
            inv.setStack(3,output.copy());
        }
        if (ItemStack.areItemsEqual(inv2, output2) && inv2.getCount() < inv2.getMaxCount() + output2.getCount()) {
            inv2.increment(output2.getCount());
        }
        else if (inv2.isEmpty()){
            inv.setStack(4,output.copy());
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getOutput() {
        return ItemStack.EMPTY;
    }

    @Override
    public Identifier getId() {
        return id;
    }
    public static RecipeType<WoodCutterRecipe> WOOD_CUTTER = Registry.register(Registry.RECIPE_TYPE,new Identifier("elt:woofcutter"), new RecipeType<WoodCutterRecipe>() {
        public String toString() {
            return "woofcutter";
        }
    });

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ELT_Main.WOOD_CUTTER_RECIPE;
    }

    @Override
    public RecipeType<?> getType() {
        return WOOD_CUTTER;
    }


    public static class Serializer implements RecipeSerializer<WoodCutterRecipe> {
        public WoodCutterRecipe read(Identifier identifier, JsonObject jsonObject) {
            return new WoodCutterRecipe(identifier,
                    ItemStackUtil.loadStackFromJson(jsonObject.get("output").getAsJsonObject()),
                    ItemStackUtil.loadStackFromJson(jsonObject.get("output2").getAsJsonObject()),
                    Ingredient.fromJson(jsonObject.get("input")),
                    getIngredients(jsonObject.get("tools").getAsJsonArray())
                    );
        }

        private static DefaultedList<Ingredient> getIngredients(JsonArray json) {
            DefaultedList<Ingredient> defaultedList = DefaultedList.of();

            for (int i = 0; i < json.size(); ++i) {
                Ingredient ingredient = Ingredient.fromJson(json.get(i));
                if (!ingredient.isEmpty()) {
                    defaultedList.add(ingredient);
                }
            }

            return defaultedList;
        }

        public WoodCutterRecipe read(Identifier identifier, PacketByteBuf packetByteBuf) {
            return null;
        }

        public void write(PacketByteBuf packetByteBuf, WoodCutterRecipe shapelessRecipe) {
        }
    }
}
