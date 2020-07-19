package net.moeg.elt.items.tools;

import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.moeg.elt.items.ItemGroups_ELT;

public class ToolSpear extends SwordItem {
    public ToolSpear(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings.group(ItemGroups_ELT.ELT_TOOLS));
    }
}
