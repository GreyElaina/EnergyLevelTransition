package net.moeg.elt.items.tools;

import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.moeg.elt.items.ItemGroups_ELT;

//刮刀
public class ToolHammer extends ToolItem {
    public ToolHammer(ToolMaterial material, Settings settings) {
        super(material, settings.group(ItemGroups_ELT.ELT_TOOLS));
    }
}
