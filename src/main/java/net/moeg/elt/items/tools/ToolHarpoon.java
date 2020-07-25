package net.moeg.elt.items.tools;

import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.moeg.elt.items.ItemGroups_ELT;

public class ToolHarpoon extends ToolItem {
    public ToolHarpoon(ToolMaterial material, Settings settings) {
        super(material, settings.group(ItemGroups_ELT.ELT_TOOLS));
    }
}
