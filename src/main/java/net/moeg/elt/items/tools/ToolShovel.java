package net.moeg.elt.items.tools;

import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolMaterial;
import net.moeg.eltcore.handlers.Handler_ItemGroups;

public class ToolShovel extends ShovelItem {
    public ToolShovel(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings.group(Handler_ItemGroups.ELT_TOOLS));
    }
}
