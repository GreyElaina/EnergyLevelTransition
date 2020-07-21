package net.moeg.elt.gui;

import com.google.common.collect.Lists;
import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.StonecuttingRecipe;
import net.minecraft.screen.Property;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import net.moeg.elt.handlers.ScreenHandlerTypeELT;

import static net.moeg.elt.handlers.Handler_Blocks.*;

public class WoodCutterScreenHandler extends ScreenHandler {
    private final ScreenHandlerContext context;
    private final Property selectedRecipe;
    private final World world;
    private List<StonecuttingRecipe> availableRecipes;
    private ItemStack inputStack;
    private long lastTakeTime;
    final Slot inputSlot;
    final Slot toolSlot1;
    final Slot toolSlot2;
    final Slot outputSlot1;
    final Slot outputSlot2;

    private Runnable contentsChangedListener;
    public final Inventory input;
    private final CraftingResultInventory output;

    public WoodCutterScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, ScreenHandlerContext.EMPTY);
    }

    public WoodCutterScreenHandler(int syncId, PlayerInventory playerInventory, final ScreenHandlerContext context) {
        super(ScreenHandlerTypeELT.WOOD_CUTTER, syncId);
        this.selectedRecipe = Property.create();
        this.availableRecipes = Lists.newArrayList();
        this.inputStack = ItemStack.EMPTY;
        this.contentsChangedListener = () -> {
        };
        this.input = new SimpleInventory(3) {
            public void markDirty() {
                super.markDirty();
                WoodCutterScreenHandler.this.onContentChanged(this);
                WoodCutterScreenHandler.this.contentsChangedListener.run();
            }
        };
        this.output = new CraftingResultInventory();
        this.context = context;
        this.world = playerInventory.player.world;
        // The input slots
        this.inputSlot = this.addSlot(new Slot(this.input, 0, 45, 16) {
            public boolean canInsert(ItemStack stack) { return true; }
        });
        this.toolSlot1 = this.addSlot(new Slot(this.input, 1, 45, 38){
            public boolean canInsert(ItemStack stack) { return true; }
        });
        this.toolSlot2 = this.addSlot(new Slot(this.input, 2, 45, 38+18){
            public boolean canInsert(ItemStack stack) { return true; /*stack.getItem() == ELT_Main.TOOL_2;*/ }
        });

        // The output slots
        this.outputSlot1 = this.addSlot(new Slot(this.output, 3, 98, 16)
        {
            public boolean canInsert(ItemStack stack) {
                return false;
            }

            public ItemStack onTakeItem(PlayerEntity player, ItemStack stack) {
                ItemStack itemStack = WoodCutterScreenHandler.this.inputSlot.takeStack(1);
                if (!itemStack.isEmpty()) {
                    WoodCutterScreenHandler.this.populateResult();
                }

                stack.getItem().onCraft
                        (stack, player.world, player);
                context.run((world, blockPos) -> {
                    long l = world.getTime();
                    if (WoodCutterScreenHandler.this.lastTakeTime != l) {
                        world.playSound((PlayerEntity)null, blockPos, SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundCategory.BLOCKS, 1.0F, 1.0F);
                        WoodCutterScreenHandler.this.lastTakeTime = l;
                    }

                });
                return super.onTakeItem(player, stack);
            }
        });


        this.outputSlot2 = this.addSlot(new Slot(this.output, 4, 98, 16+18) {
            public boolean canInsert(ItemStack stack) {
                return false;
            }

            public ItemStack onTakeItem(PlayerEntity player, ItemStack stack) {
                ItemStack itemStack = WoodCutterScreenHandler.this.inputSlot.takeStack(1);
                if (!itemStack.isEmpty()) {
                    WoodCutterScreenHandler.this.populateResult();
                }

                stack.getItem().onCraft(stack, player.world, player);
                context.run((world, blockPos) -> {
                    long l = world.getTime();
                    if (WoodCutterScreenHandler.this.lastTakeTime != l) {
                        world.playSound((PlayerEntity)null, blockPos, SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundCategory.BLOCKS, 1.0F, 1.0F);
                        WoodCutterScreenHandler.this.lastTakeTime = l;
                    }

                });
                return super.onTakeItem(player, stack);
            }
        });



        int k;
        for(k = 0; k < 3; ++k) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + k * 9 + 9, 8 + j * 18, 84 + k * 18));
            }
        }

        for(k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
        }

        this.addProperty(this.selectedRecipe);
    }

    public boolean onButtonClickCutLog(PlayerEntity player, int id) {
        ItemStack inputStack = this.input.getStack(0);
        ItemStack toolStack1 = this.input.getStack(1);
        ItemStack toolStack2 = this.input.getStack(2);
        int i = id + 1;
        if (inputStack.isEmpty() || toolStack1.isEmpty()) {
            return false;
        }
//        else {
//                ItemStack itemStack3 = itemStack;
//                if (!list.isEmpty()) {
//                    player.applyEnchantmentCosts(itemStack, i);
//                    boolean bl = itemStack.getItem() == Items.BOOK;
//                    if (bl) {
//                        itemStack3 = new ItemStack(Items.ENCHANTED_BOOK);
//                        CompoundTag compoundTag = itemStack.getTag();
//                        if (compoundTag != null) {
//                            itemStack3.setTag(compoundTag.copy());
//                        }
//
//                        this.input.setStack(0, itemStack3);
//                    }
//
//                    for(int k = 0; k < list.size(); ++k) {
//                        EnchantmentLevelEntry enchantmentLevelEntry = (EnchantmentLevelEntry)list.get(k);
//                        if (bl) {
//                            EnchantedBookItem.addEnchantment(itemStack3, enchantmentLevelEntry);
//                        } else {
//                            itemStack3.addEnchantment(enchantmentLevelEntry.enchantment, enchantmentLevelEntry.level);
//                        }
//                    }
//
//                    if (!player.abilities.creativeMode) {
//                        itemStack2.decrement(i);
//                        if (itemStack2.isEmpty()) {
//                            this.input.setStack(1, ItemStack.EMPTY);
//                        }
//                    }
//
//                    this.input.markDirty();
//                    this.onContentChanged(this.input);
//
//            }
        return true;
    }


    @Environment(EnvType.CLIENT)
    public int getSelectedRecipe() {
        return this.selectedRecipe.get();
    }

    @Environment(EnvType.CLIENT)
    public List<StonecuttingRecipe> getAvailableRecipes() {
        return this.availableRecipes;
    }

    @Environment(EnvType.CLIENT)
    public int getAvailableRecipeCount() {
        return this.availableRecipes.size();
    }

    @Environment(EnvType.CLIENT)
    public boolean canCraft() {
        return this.inputSlot.hasStack() && this.toolSlot1.hasStack() && this.toolSlot2.hasStack() && !this.availableRecipes.isEmpty();
    }

    public boolean canUse(PlayerEntity player) {
        return canUse(this.context, player, MANUAL_WOOD_CUTTER);
    }

    private boolean method_30160(int i) {
        return i >= 0 && i < this.availableRecipes.size();
    }

    public void onContentChanged(Inventory inventory) {
        ItemStack itemStack = this.inputSlot.getStack();
        if (itemStack.getItem() != this.inputStack.getItem()) {
            this.inputStack = itemStack.copy();
            this.updateInput(inventory, itemStack);
        }

    }

    private void updateInput(Inventory input, ItemStack stack) {
        this.availableRecipes.clear();
        this.selectedRecipe.set(-1);
        this.outputSlot1.setStack(ItemStack.EMPTY);
        if (!stack.isEmpty()) {
            this.availableRecipes = this.world.getRecipeManager().getAllMatches(RecipeType.STONECUTTING, input, this.world);
        }

    }

    private void populateResult() {
        if (!this.availableRecipes.isEmpty() && this.method_30160(this.selectedRecipe.get())) {
            StonecuttingRecipe stonecuttingRecipe = (StonecuttingRecipe)this.availableRecipes.get(this.selectedRecipe.get());
            this.outputSlot1.setStack(stonecuttingRecipe.craft(this.input));
        } else {
            this.outputSlot1.setStack(ItemStack.EMPTY);
        }

        this.sendContentUpdates();
    }

    public ScreenHandlerType<?> getType() {
        return ScreenHandlerTypeELT.WOOD_CUTTER;
    }

    @Environment(EnvType.CLIENT)
    public void setContentsChangedListener(Runnable runnable) {
        this.contentsChangedListener = runnable;
    }

    public boolean canInsertIntoSlot(ItemStack stack, Slot slot) {
        return slot.inventory != this.output && super.canInsertIntoSlot(stack, slot);
    }

    public ItemStack transferSlot(PlayerEntity player, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = (Slot)this.slots.get(index);
        if (slot != null && slot.hasStack()) {
            ItemStack itemStack2 = slot.getStack();
            Item item = itemStack2.getItem();
            itemStack = itemStack2.copy();
            if (index == 1) {
                item.onCraft(itemStack2, player.world, player);
                if (!this.insertItem(itemStack2, 2, 38, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onStackChanged(itemStack2, itemStack);
            } else if (index == 0) {
                if (!this.insertItem(itemStack2, 2, 38, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (this.world.getRecipeManager().getFirstMatch(RecipeType.STONECUTTING, new SimpleInventory(new ItemStack[]{itemStack2}), this.world).isPresent()) {
                if (!this.insertItem(itemStack2, 0, 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (index >= 2 && index < 29) {
                if (!this.insertItem(itemStack2, 29, 38, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (index >= 29 && index < 38 && !this.insertItem(itemStack2, 2, 29, false)) {
                return ItemStack.EMPTY;
            }

            if (itemStack2.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            }

            slot.markDirty();
            if (itemStack2.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTakeItem(player, itemStack2);
            this.sendContentUpdates();
        }

        return itemStack;
    }

    public void close(PlayerEntity player) {
        super.close(player);
        this.output.removeStack(1);
        this.context.run((world, blockPos) -> {
            this.dropInventory(player, player.world, this.input);
        });
    }
}
