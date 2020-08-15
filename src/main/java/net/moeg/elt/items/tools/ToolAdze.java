package net.moeg.elt.items.tools;

import net.minecraft.item.AxeItem;
import net.minecraft.item.ToolMaterial;
import net.moeg.eltcore.handlers.Handler_ItemGroups;

public class ToolAdze extends AxeItem {
    public ToolAdze(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings.group(Handler_ItemGroups.ELT_TOOLS));
    }
}
