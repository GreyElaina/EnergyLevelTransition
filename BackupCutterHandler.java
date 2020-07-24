package net.moeg.elt.gui;

import com.google.common.collect.Lists;
import java.util.List;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.FilledMapItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.map.MapState;
import net.minecraft.recipe.StonecuttingRecipe;
import net.minecraft.screen.Property;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.LiteralText;
import net.minecraft.world.World;
import net.moeg.elt.handlers.Handler_Items;
import net.moeg.elt.handlers.ScreenHandlerTypeELT;

import static net.moeg.elt.handlers.Handler_Blocks.*;

public class WoodCutterScreenHandler extends ScreenHandler {
    private final ScreenHandlerContext context;
    private final Property selectedRecipe;
    private final World world;
    private boolean currentlyTakingItem;
    private List<StonecuttingRecipe> availableRecipes;
    private ItemStack inputStack;
    private long lastTakeTime;
    final Slot inputSlot;
    final Slot toolSlot1;
    //    final Slot toolSlot2;
    final Slot outputSlot1;
//    final Slot outputSlot2;

    private Runnable contentsChangedListener;
    public final Inventory inventory;
    private final Inventory resultSlot;

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
        this.inventory = new SimpleInventory(2) {
            public void markDirty() {
                super.markDirty();
                WoodCutterScreenHandler.this.onContentChanged(this);
            }
        };
        this.resultSlot = new SimpleInventory(1) {
            public void markDirty() {
                super.markDirty();
                WoodCutterScreenHandler.this.onContentChanged(this);
            }
        };
        this.context = context;
        this.world = playerInventory.player.world;
        // The inventory slots
        this.inputSlot = this.addSlot(new Slot(this.inventory, 0, 45, 16) {
            public boolean canInsert(ItemStack stack) { return true; }
        });
        this.toolSlot1 = this.addSlot(new Slot(this.inventory, 1, 45, 38){
            public boolean canInsert(ItemStack stack) { return true; }
        });
//        this.toolSlot2 = this.addSlot(new Slot(this.inventory, 2, 45, 38+18){
//            public boolean canInsert(ItemStack stack) { return true; /*stack.getItem() == ELT_Main.TOOL_2;*/ }
//        });

        // The resultSlot slots
        this.outputSlot1 = this.addSlot(new Slot(this.resultSlot, 0, 98, 16) {
            public boolean canInsert(ItemStack stack) {
                return false;
            }

//            public ItemStack onTakeItem(PlayerEntity player, ItemStack stack) {
//                System.out.println("FUCK");
////                WoodCutterScreenHandler.this.inventory.setStack(0, ItemStack.EMPTY);
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

//            public ItemStack takeStack(int amount) {
//                ItemStack itemStack = super.takeStack(amount);
//                ItemStack itemStack2 = (ItemStack)context.run((world, blockPos) -> {
//                    if (WoodCutterScreenHandler.this.inventory.getStack(0).getItem() == Handler_Items.OAK_BRANCH) {
//                        ItemStack theStack = new ItemStack(Items.OAK_LOG);
//                        if (theStack != null) {
//                            theStack.setCount(1);
//                            return theStack;
//                        }
//                    }
//
//                    return itemStack;
//                }).orElse(itemStack);
//                WoodCutterScreenHandler.this.inventory.removeStack(0, 1);
////                WoodCutterScreenHandler.this.inventory.removeStack(1, 1);
//                return itemStack2;
//            }
//
//            protected void onCrafted(ItemStack stack, int amount) {
//                this.takeStack(amount);
//                super.onCrafted(stack, amount);
//            }
//
//            public ItemStack onTakeItem(PlayerEntity player, ItemStack stack) {
//                stack.getItem().onCraft(stack, player.world, player);
//                return super.onTakeItem(player, stack);
//            }
        });

//        this.outputSlot2 = this.addSlot(new Slot(this.resultSlot, 1, 98, 16+18) {
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

    public boolean onButtonClickCutLog(PlayerEntity player) {

        System.out.println("Clicked");
        ItemStack inputStack = this.inventory.getStack(0);
        ItemStack toolStack1 = this.inventory.getStack(1);
        ItemStack outputStack1 = this.resultSlot.getStack(2);
        if (inputStack.isEmpty() || toolStack1.isEmpty()) {
            return false;
        }
        else if (inputStack.getItem() == Handler_Items.OAK_BRANCH) {
//            this.CutAction(inputStack, toolStack1, outputStack1);

            this.context.run((world, blockPos) -> {
                if (!world.isClient) {
                    player.sendMessage(new LiteralText("Hello, world!"), false);
                }
                System.out.println("Called A");

                ItemStack LOG = new ItemStack(Items.OAK_LOG);

                inputStack.decrement(1);
                this.sendContentUpdates();

                if (inputStack.isEmpty()) {
                    System.out.println("Called B");

                    this.inputSlot.setStack(ItemStack.EMPTY);
                    this.sendContentUpdates();
                }
                this.resultSlot.setStack(0, LOG.copy());
                this.resultSlot.getStack(0).increment(1);

//                if (this.resultSlot.getStack(0).getItem() == Items.AIR) {
//                    System.out.println("Called when Empty");
//                    this.resultSlot.setStack(0, LOG.copy());
//                    this.sendContentUpdates();
//                } else {
//                    System.out.println("Called D");
//
//                    this.resultSlot.getStack(0).increment(1);
//                    this.sendContentUpdates();
//                }
                this.inventory.markDirty();
                this.resultSlot.markDirty();
                this.sendContentUpdates();
            });
            return true;
        }
        else return false;

    }

    public void CutAction (ItemStack input, ItemStack tool1, ItemStack output) {
        System.out.println("Called");
        this.context.run((world, blockPos) -> {
            System.out.println("Called A");

            ItemStack LOG = new ItemStack(Items.OAK_LOG);

            input.decrement(1);
            this.sendContentUpdates();

            if (input.isEmpty()) {
                System.out.println("Called B");

                this.inputSlot.setStack(ItemStack.EMPTY);
                this.sendContentUpdates();
            }
            if (output.getItem() == Items.AIR) {
                System.out.println("Called when Empty");
                this.outputSlot1.setStack(LOG.copy());
                this.sendContentUpdates();
            }
            else {
                System.out.println("Called D");

                output.increment(1);
                this.sendContentUpdates();
            }
        });

    }

    public boolean canUse(PlayerEntity player) {
        return canUse(this.context, player, MANUAL_WOOD_CUTTER);

    }

//    public void onContentChanged(Inventory inventory) {
//        ItemStack itemStack = this.inventory.getStack(0);
//        ItemStack itemStack2 = this.inventory.getStack(1);
//        ItemStack itemStack3 = this.resultSlot.getStack(0);
//        if (itemStack3.isEmpty() || !itemStack.isEmpty() && !itemStack2.isEmpty()) {
//            if (!itemStack.isEmpty() && !itemStack2.isEmpty()) {
//                this.updateResult(itemStack, itemStack2, itemStack3);
//            }
//        } else {
//            this.resultSlot.removeStack(0);
//        }
//
//    }

    public void onContentChanged(Inventory inventory) {
        this.sendContentUpdates();
    }

    private void updateResult(ItemStack map, ItemStack item, ItemStack oldResult) {
        this.context.run((world, blockPos) -> {
            Item itemx = item.getItem();
            MapState mapState = FilledMapItem.getMapState(map, world);
            if (mapState != null) {
                ItemStack itemStack6;
                if (itemx == Items.PAPER) {
                    itemStack6 = map.copy();
                    itemStack6.setCount(1);
                    itemStack6.getOrCreateTag().putInt("map_scale_direction", 1);
                    this.sendContentUpdates();
                } else if (itemx == Items.GLASS_PANE && !mapState.locked) {
                    itemStack6 = map.copy();
                    itemStack6.setCount(1);
                    this.sendContentUpdates();
                } else {
                    if (itemx != Items.MAP) {
                        this.resultSlot.removeStack(0);
                        this.sendContentUpdates();
                        return;
                    }

                    itemStack6 = map.copy();
                    itemStack6.setCount(2);
                    this.sendContentUpdates();
                }

                if (!ItemStack.areEqual(itemStack6, oldResult)) {
                    this.resultSlot.setStack(0, itemStack6);
                    this.sendContentUpdates();
                }

            }
        });
    }


    public ItemStack transferSlot(PlayerEntity player, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = (Slot)this.slots.get(index);
        if (slot != null && slot.hasStack()) {
            ItemStack itemStack2 = slot.getStack();
            ItemStack itemStack3 = itemStack2;
            Item item = itemStack2.getItem();
            itemStack = itemStack2.copy();
            if (index == 2) {
                if (this.inventory.getStack(0).getItem() == Handler_Items.OAK_BRANCH) {
                    itemStack3 = (ItemStack)this.context.run((world, blockPos) -> {
                        ItemStack itemStack2x = new ItemStack(Items.OAK_LOG);
                        if (itemStack2x != null) {
                            itemStack2x.setCount(1);
                            return itemStack2x;
                        } else {
                            return itemStack2;
                        }
                    }).orElse(itemStack2);
                }

                item.onCraft(itemStack3, player.world, player);
                if (!this.insertItem(itemStack3, 3, 39, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onStackChanged(itemStack3, itemStack);
            } else if (index != 1 && index != 0) {
                if (item == Items.OAK_LOG) {
                    if (!this.insertItem(itemStack2, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (item != Items.PAPER && item != Items.MAP && item != Items.GLASS_PANE) {
                    if (index >= 3 && index < 30) {
                        if (!this.insertItem(itemStack2, 30, 39, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (index >= 30 && index < 39 && !this.insertItem(itemStack2, 3, 30, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (!this.insertItem(itemStack2, 1, 2, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(itemStack2, 3, 39, false)) {
                return ItemStack.EMPTY;
            }

            if (itemStack3.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            }

            slot.markDirty();
            if (itemStack3.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }

            this.currentlyTakingItem = true;
            slot.onTakeItem(player, itemStack3);
            this.currentlyTakingItem = false;
            this.sendContentUpdates();
        }

        return itemStack;
    }

    public void close(PlayerEntity player) {
        super.close(player);
        this.context.run((world, blockPos) -> {
            this.dropInventory(player, player.world, this.inventory);
        });
    }
}
