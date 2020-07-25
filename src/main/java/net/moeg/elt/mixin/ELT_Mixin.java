package net.moeg.elt.mixin;

import com.google.common.collect.Sets;
import net.minecraft.block.Material;
import net.minecraft.item.AxeItem;
import org.spongepowered.asm.mixin.Mixin;

import java.util.HashSet;

@Mixin(AxeItem.class)
public class ELT_Mixin {

    private static final HashSet<Material> AXEABLE = Sets.newHashSet(Material.NETHER_WOOD);

//	@Redirect(method = "<clinit>[]", at = @At(value = "INVOKE", ordinal = 0, target = "Lcom/google/common/collect/Sets;newHashSet([Lnet/minecraft/block/Material[];)Ljava/util/HashSet;"))
//	private static HashSet makeMyNewHashSet(Material[] oldArray) {
//		return AXEABLE;
//	}

//	@Redirect(method = "<clinit>", at = @At(value = "INVOKE", ordinal = 0, target = "Lcom/google/common/collect/Sets;newHashSet([Lnet/minecraft/block/Material;)Ljava/util/HashSet;"))
//	private static HashSet makeMyNewHashSet(Material[] oldArray) {
//		return AXEABLE;
//	}

}
