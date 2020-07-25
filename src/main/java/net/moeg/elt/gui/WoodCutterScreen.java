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
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.recipe.StonecuttingRecipe;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.StonecutterScreenHandler;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;

@Environment(EnvType.CLIENT)
public class WoodCutterScreen extends HandledScreen<WoodCutterScreenHandler> {
    private static final Identifier TEXTURE = new Identifier("elt:textures/gui/wood_cutter.png");
    private float scrollAmount;


    public WoodCutterScreen(WoodCutterScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        --this.titleY;
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        super.render(matrices, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(matrices, mouseX, mouseY);
    }

    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        this.renderBackground(matrices);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        assert this.client != null;
        this.client.getTextureManager().bindTexture(TEXTURE);
        int i = this.x;
        int j = this.y;
        this.drawTexture(matrices, i, j, 0, 0, this.backgroundWidth, this.backgroundHeight);
        int k = (int)(41.0F * this.scrollAmount);
        this.drawTexture(matrices, i + 119, j + 15 + k, 176 + 12, 0, 12, 15);
    }

    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int i = (this.width - this.backgroundWidth) / 2;
        int j = (this.height - this.backgroundHeight) / 2;

        float buttonWidth = 18;
        float buttonHeight = 18;

        for(int k = 0; k < 3; ++k) {
            double d = mouseX - (double)(i + 79 + 18 * k);
            double e = mouseY - (double)(j + 61);
            if (d >= 0.0D && e >= 0.0D && d < buttonWidth && e < buttonHeight ) {
                assert this.client != null;
                assert this.client.interactionManager != null;
                this.client.interactionManager.clickButton((this.handler).syncId, k);
                return true;
            }
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }


}
