package net.moeg.elt.blockentity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.screen.HopperScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Direction;
import net.moeg.elt.ELT_Main;
import net.moeg.elt.gui.WoodCutterScreenHandler;

public class BlockEntityWoodCutter extends LootableContainerBlockEntity {

    private DefaultedList<ItemStack> items = DefaultedList.ofSize(5, ItemStack.EMPTY);

    // Serialize the BlockEntity
    public CompoundTag toTag(CompoundTag tag) {
        Inventories.toTag(tag,items);
        return super.toTag(tag);
    }

    @Override
    protected Text getContainerName() {
        return new TranslatableText("test");
    }

    // Deserialize the BlockEntity
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
        Inventories.fromTag(tag,items);

    }



    public BlockEntityWoodCutter() {
        super(ELT_Main.BlockEntityWoodCutter);

    }
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new WoodCutterScreenHandler(syncId, playerInventory, this);
    }

    @Override
    protected DefaultedList<ItemStack> getInvStackList() {
        return items;
    }

    @Override
    protected void setInvStackList(DefaultedList<ItemStack> list) {
        this.items = list;
    }

    @Override
    public void markDirty() {
        super.markDirty();
    }

    @Override
    public int size() {
        return 5;
    }
}
