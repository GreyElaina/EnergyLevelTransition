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
import net.minecraft.item.Items;
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
import net.moeg.elt.handlers.Handler_Items;
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
//    final Slot outputSlot2;

    private Runnable contentsChangedListener;
    public final Inventory input;
    private final Inventory output;

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
        this.outputSlot1 = this.addSlot(new Slot(this.output, 3, 98, 16) {
            public boolean canInsert(ItemStack stack) {
                return false;
            }

//            public ItemStack onTakeItem(PlayerEntity player, ItemStack stack) {
//                System.out.println("FUCK");
////                WoodCutterScreenHandler.this.input.setStack(0, ItemStack.EMPTY);
////                WoodCutterScreenHandler.this.outputSlot1.setStack(new ItemStack(Items.OAK_LOG, 1));
//                ItemStack itemStack = WoodCutterScreenHandler.this.outputSlot1.takeStack(1);
//                stack = itemStack;
//                player.inventory.insertStack(itemStack);
////                if (!itemStack.isEmpty()) {
////                    WoodCutterScreenHandler.this.populateResult();
////                }
//
////                stack.getItem().onCraft(stack, player.world, player);
//                WoodCutterScreenHandler.this.sendContentUpdates();
//
//                return super.onTakeItem(player, stack);
//            }

            public ItemStack takeStack(int amount) {
                ItemStack itemStack = super.takeStack(amount);
                ItemStack itemStack2 = (ItemStack)context.run((world, blockPos) -> {
                    if (WoodCutterScreenHandler.this.input.getStack(0).getItem() == Handler_Items.OAK_BRANCH) {
                        ItemStack theStack = new ItemStack(Items.OAK_LOG);
                        if (theStack != null) {
                            theStack.setCount(1);
                            return theStack;
                        }
                    }

                    return itemStack;
                }).orElse(itemStack);
                WoodCutterScreenHandler.this.input.removeStack(0, 1);
//                WoodCutterScreenHandler.this.input.removeStack(1, 1);
                return itemStack2;
            }

            protected void onCrafted(ItemStack stack, int amount) {
                this.takeStack(amount);
                super.onCrafted(stack, amount);
            }

            public ItemStack onTakeItem(PlayerEntity player, ItemStack stack) {
                stack.getItem().onCraft(stack, player.world, player);
                return super.onTakeItem(player, stack);
            }
        });

//        this.outputSlot2 = this.addSlot(new Slot(this.output, 1, 98, 16+18) {
//            public boolean canInsert(ItemStack stack) {
//                return false;
//            }
//        });

        int k;
        for(k = 0; k < 3; ++k) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + k * 9 + 9, 8 + j * 18, 84 + k * 18));
            }
        }

        for(k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
        }

//        this.addProperty(this.selectedRecipe);
    }

    public void onContentChanged(Inventory inventory) {
        ItemStack itemStack = this.inputSlot.getStack();
        if (itemStack.getItem() != this.inputStack.getItem()) {
            this.inputStack = itemStack.copy();
//            this.outputSlot1.setStack(ItemStack.EMPTY);
        }
        this.context.run((world, blockPos) -> {
            this.sendContentUpdates();
        });
    }

    public boolean onButtonClickCutLog(PlayerEntity player) {
        ItemStack result = new ItemStack(Items.OAK_LOG);

        ItemStack inputStack = this.input.getStack(0);
        ItemStack toolStack1 = this.input.getStack(1);
        ItemStack toolStack2 = this.input.getStack(2);
        ItemStack outputStack1 = this.output.getStack(3);
        if (inputStack.isEmpty() || toolStack2.isEmpty() && toolStack1.isEmpty()) {
            return false;
        }
        else {
            if (inputStack.getItem() == Handler_Items.OAK_BRANCH) {
//                populateResult();
                inputStack.decrement(1);
                if (inputStack.isEmpty()) {
                    this.input.setStack(0, ItemStack.EMPTY);
                }
                if (outputStack1.getItem() == Items.AIR)
                    this.outputSlot1.setStack(result.copy());
                else {
                    outputStack1.increment(1);
                    player.inventory.insertStack(result.copy());
                }

                this.sendContentUpdates();
//                this.outputSlot1.onStackChanged();
//                this.input.markDirty();
//                this.output.markDirty();
//                this.onContentChanged(this.input);
//                this.onContentChanged(this.output);
                return true;
            }
            else return false;
        }
    }

    public boolean canUse(PlayerEntity player) {
        return canUse(this.context, player, MANUAL_WOOD_CUTTER);
    }

//    public void onContentChanged(Inventory inventory) {
//        ItemStack itemStack = this.inputSlot.getStack();
//        if (itemStack.getItem() != this.inputStack.getItem()) {
//            this.inputStack = itemStack.copy();
//            this.outputSlot1.setStack(ItemStack.EMPTY);
//        }
//
//    }

    private void populateResult() {
        ItemStack result = new ItemStack(Items.OAK_LOG);

        ItemStack inputStack = this.input.getStack(0);
        ItemStack toolStack1 = this.input.getStack(1);
        ItemStack toolStack2 = this.input.getStack(2);
        ItemStack outputStack1 = this.output.getStack(3);

        inputStack.decrement(1);
        if (inputStack.isEmpty()) {
            this.input.setStack(0, ItemStack.EMPTY);
        }
        if (outputStack1.isEmpty())
            this.outputSlot1.setStack(result.copy());
        else {
            outputStack1.increment(1);
        }

        this.sendContentUpdates();
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
//        this.output.removeStack(1);
        this.context.run((world, blockPos) -> {
            this.dropInventory(player, player.world, this.input);
        });
    }
}
