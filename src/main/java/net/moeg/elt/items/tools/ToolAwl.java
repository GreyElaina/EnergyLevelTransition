package net.moeg.elt.items.tools;

import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.moeg.elt.items.ItemGroups_ELT;

//锥
public class ToolAwl extends ToolItem {
    public ToolAwl(ToolMaterial material, Settings settings) {
        super(material, settings.group(ItemGroups_ELT.ELT_TOOLS));
    }
}
