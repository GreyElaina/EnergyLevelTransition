package net.moeg.elt.gui;

import com.google.common.collect.Lists;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.StonecuttingRecipe;
import net.minecraft.screen.*;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;
import net.moeg.elt.ELT_Main;
import net.moeg.elt.handlers.Handler_Items;
import net.moeg.elt.handlers.ScreenHandlerTypeELT;

import javax.annotation.Nullable;
import java.util.List;

public class WoodCutterScreenHandler extends ScreenHandler implements NamedScreenHandlerFactory {
    final Slot inputSlot;
    final Slot toolSlot1;
    final Slot toolSlot2;
    final Slot outputSlot1;
    final Inventory inv;
    private final ScreenHandlerContext context;
//    private final ScreenHandlerFactory baseFactory;

    @Override
    public Text getDisplayName() {
        return new TranslatableText("elt.wood_cutter.displaytext");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return null;
    }

    public WoodCutterScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(5), ScreenHandlerContext.EMPTY);
    }

    public WoodCutterScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inv, ScreenHandlerContext context) {
        super(ScreenHandlerTypeELT.WOOD_CUTTER, syncId);
        this.context = context;
        this.inv = inv;
        inv.onOpen(playerInventory.player);
        System.out.println(playerInventory.player.currentScreenHandler);
        playerInventory.player.currentScreenHandler = this;
        this.inputSlot = this.addSlot(new Slot(inv, 0, 45, 16) {
            public boolean canInsert(ItemStack stack) {
                return true;
            }
        });
        this.toolSlot1 = this.addSlot(new Slot(inv, 1, 45, 38) {
            public boolean canInsert(ItemStack stack) {
                return true;
            }
        });
        this.toolSlot2 = this.addSlot(new Slot(inv, 2, 45, 38 + 18) {
            public boolean canInsert(ItemStack stack) {
                return true; /*stack.getItem() == ELT_Main.TOOL_2;*/
            }
        });

        this.outputSlot1 = this.addSlot(new Slot(inv, 3, 98, 16) {
            public boolean canInsert(ItemStack stack) {
                return false;
            }

        });

        int k;
        for (k = 0; k < 3; ++k) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + k * 9 + 9, 8 + j * 18, 84 + k * 18));
            }
        }

        for (k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
        }

    }

    @Override
    public boolean onButtonClick(PlayerEntity player, int id) {

        System.out.println("Clicked");
        ItemStack result = new ItemStack(Items.OAK_LOG);
        if (inputSlot.getStack().isEmpty() || (toolSlot1.getStack().isEmpty() && toolSlot2.getStack().isEmpty())) {
            return false;
        } else {
            if (inputSlot.getStack().getItem() == Handler_Items.OAK_BRANCH) {
                System.out.println("has branch");
                this.context.run((world, blockPos) -> {
                    System.out.println("Context run");

                    inputSlot.getStack().decrement(1);
                    if (inputSlot.getStack().isEmpty()) {
                        inputSlot.setStack(ItemStack.EMPTY);
                    }
                    if (outputSlot1.getStack().getItem() == Items.AIR)
                        this.outputSlot1.setStack(result.copy());
                    else {
                        outputSlot1.getStack().increment(1);
                    }
                    outputSlot1.markDirty();
                    this.sendContentUpdates();
                });
                return true;
            }
            else
            {
                System.out.println("no branch");
                return false;
            }
        }
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }
    public ItemStack transferSlot(PlayerEntity player, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        return itemStack;
    }
}
