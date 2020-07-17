package net.moeg.elt.gui;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.List;
import java.util.Map;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.gui.screen.ingame.ScreenHandlerProvider;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.recipe.StonecuttingRecipe;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.StonecutterScreenHandler;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;

@Environment(EnvType.CLIENT)
public class WoodCutterScreen extends HandledScreen<WoodCutterScreenHandler> {
    private static final Identifier TEXTURE = new Identifier("elt:textures/gui/wood_cutter.png");
    private float scrollAmount;
    private boolean mouseClicked;
    private int scrollOffset;
    private boolean canCraft;

    public WoodCutterScreen(WoodCutterScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        handler.setContentsChangedListener(this::onInventoryChange);
        --this.titleY;
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        super.render(matrices, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(matrices, mouseX, mouseY);
    }

    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        this.renderBackground(matrices);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.client.getTextureManager().bindTexture(TEXTURE);
        int i = this.x;
        int j = this.y;
//        System.out.println(i);
//        System.out.println(j);
        // Draw the entire background from a 256x256 texture
        this.drawTexture(matrices, i, j, 0, 0, this.backgroundWidth, this.backgroundHeight);
        int k = (int)(41.0F * this.scrollAmount);
        // Draw the scroll button
        this.drawTexture(matrices, i + 119, j + 15 + k, 176 + 12, 0, 12, 15);
        int l = this.x + 52;
        int m = this.y + 14;
        int n = this.scrollOffset + 12;
//        this.renderRecipeBackground(matrices, mouseX, mouseY, l, m, n);
//        this.renderRecipeIcons(l, m, n);
    }

    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int i = (this.width - this.backgroundWidth) / 2;
        int j = (this.height - this.backgroundHeight) / 2;

        for(int k = 0; k < 3; ++k) {
            double d = mouseX - (double)(i + 60);
            double e = mouseY - (double)(j + 14 + 19 * k);
            if (d >= 0.0D && e >= 0.0D && d < 108.0D && e < 19.0D && ((WoodCutterScreenHandler)this.handler).onButtonClickCutLog(this.client.player, k)) {
                this.client.interactionManager.clickButton(((WoodCutterScreenHandler)this.handler).syncId, k);
                return true;
            }
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }



//    protected void drawMouseoverTooltip(MatrixStack matrices, int x, int y) {
//        super.drawMouseoverTooltip(matrices, x, y);
//        if (this.canCraft) {
//            int i = this.x + 52;
//            int j = this.y + 14;
//            int k = this.scrollOffset + 12;
//            List<StonecuttingRecipe> list = ((WoodCutterScreenHandler)this.handler).getAvailableRecipes();
//
//            for(int l = this.scrollOffset; l < k && l < ((WoodCutterScreenHandler)this.handler).getAvailableRecipeCount(); ++l) {
//                int m = l - this.scrollOffset;
//                int n = i + m % 4 * 16;
//                int o = j + m / 4 * 18 + 2;
//                if (x >= n && x < n + 16 && y >= o && y < o + 18) {
//                    this.renderTooltip(matrices, ((StonecuttingRecipe)list.get(l)).getOutput(), x, y);
//                }
//            }
//        }
//
//    }
//
//    private void renderRecipeBackground(MatrixStack matrixStack, int i, int j, int k, int l, int m) {
//        for(int n = this.scrollOffset; n < m && n < ((WoodCutterScreenHandler)this.handler).getAvailableRecipeCount(); ++n) {
//            int o = n - this.scrollOffset;
//            int p = k + o % 4 * 16;
//            int q = o / 4;
//            int r = l + q * 18 + 2;
//            int s = this.backgroundHeight;
//            if (n == ((WoodCutterScreenHandler)this.handler).getSelectedRecipe()) {
//                s += 18;
//            } else if (i >= p && j >= r && i < p + 16 && j < r + 18) {
//                s += 36;
//            }
//
//            this.drawTexture(matrixStack, p, r - 1, 0, s, 16, 18);
//        }
//
//    }
//
//    private void renderRecipeIcons(int x, int y, int scrollOffset) {
//        List<StonecuttingRecipe> list = ((WoodCutterScreenHandler)this.handler).getAvailableRecipes();
//
//        for(int i = this.scrollOffset; i < scrollOffset && i < ((WoodCutterScreenHandler)this.handler).getAvailableRecipeCount(); ++i) {
//            int j = i - this.scrollOffset;
//            int k = x + j % 4 * 16;
//            int l = j / 4;
//            int m = y + l * 18 + 2;
//            this.client.getItemRenderer().renderInGuiWithOverrides(((StonecuttingRecipe)list.get(i)).getOutput(), k, m);
//        }
//
//    }
//
//    public boolean mouseClicked(double mouseX, double mouseY, int button) {
//        this.mouseClicked = false;
//        if (this.canCraft) {
//            int i = this.x + 52;
//            int j = this.y + 14;
//            int k = this.scrollOffset + 12;
//
//            for(int l = this.scrollOffset; l < k; ++l) {
//                int m = l - this.scrollOffset;
//                double d = mouseX - (double)(i + m % 4 * 16);
//                double e = mouseY - (double)(j + m / 4 * 18);
//                if (d >= 0.0D && e >= 0.0D && d < 16.0D && e < 18.0D && ((WoodCutterScreenHandler)this.handler).onButtonClick(this.client.player, l)) {
//                    MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(SoundEvents.UI_STONECUTTER_SELECT_RECIPE, 1.0F));
//                    this.client.interactionManager.clickButton(((WoodCutterScreenHandler)this.handler).syncId, l);
//                    return true;
//                }
//            }
//
//            i = this.x + 119;
//            j = this.y + 9;
//            if (mouseX >= (double)i && mouseX < (double)(i + 12) && mouseY >= (double)j && mouseY < (double)(j + 54)) {
//                this.mouseClicked = true;
//            }
//        }
//
//        return super.mouseClicked(mouseX, mouseY, button);
//    }

//    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
//        if (this.mouseClicked && this.shouldScroll()) {
//            int i = this.y + 14;
//            int j = i + 54;
//            this.scrollAmount = ((float)mouseY - (float)i - 7.5F) / ((float)(j - i) - 15.0F);
//            this.scrollAmount = MathHelper.clamp(this.scrollAmount, 0.0F, 1.0F);
//            this.scrollOffset = (int)((double)(this.scrollAmount * (float)this.getMaxScroll()) + 0.5D) * 4;
//            return true;
//        } else {
//            return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
//        }
//    }

//    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
//        if (this.shouldScroll()) {
//            int i = this.getMaxScroll();
//            this.scrollAmount = (float)((double)this.scrollAmount - amount / (double)i);
//            this.scrollAmount = MathHelper.clamp(this.scrollAmount, 0.0F, 1.0F);
//            this.scrollOffset = (int)((double)(this.scrollAmount * (float)i) + 0.5D) * 4;
//        }
//
//        return true;
//    }

//    private boolean shouldScroll() {
//        return this.canCraft && ((WoodCutterScreenHandler)this.handler).getAvailableRecipeCount() > 12;
//    }
//
//    protected int getMaxScroll() {
//        return (((WoodCutterScreenHandler)this.handler).getAvailableRecipeCount() + 4 - 1) / 4 - 3;
//    }

    private void onInventoryChange() {
        this.canCraft = ((WoodCutterScreenHandler)this.handler).canCraft();
        if (!this.canCraft) {
            this.scrollAmount = 0.0F;
            this.scrollOffset = 0;
        }

    }

}
