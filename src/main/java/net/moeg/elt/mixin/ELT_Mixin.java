package net.moeg.elt.mixin;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.moeg.elt.ELT_Main;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Mixin(AxeItem.class)
public class ELT_Mixin {

	private static HashSet<Material> AXEABLE = Sets.newHashSet(new Material[]{Material.NETHER_WOOD});

//	@Redirect(method = "<clinit>[]", at = @At(value = "INVOKE", ordinal = 0, target = "Lcom/google/common/collect/Sets;newHashSet([Lnet/minecraft/block/Material[];)Ljava/util/HashSet;"))
//	private static HashSet makeMyNewHashSet(Material[] oldArray) {
//		return AXEABLE;
//	}

//	@Redirect(method = "<clinit>", at = @At(value = "INVOKE", ordinal = 0, target = "Lcom/google/common/collect/Sets;newHashSet([Lnet/minecraft/block/Material;)Ljava/util/HashSet;"))
//	private static HashSet makeMyNewHashSet(Material[] oldArray) {
//		return AXEABLE;
//	}

}
