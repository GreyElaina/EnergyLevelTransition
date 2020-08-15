package net.moeg.elt.items.tools;

import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.moeg.eltcore.handlers.Handler_ItemGroups;

public class ToolHarpoon extends ToolItem {
    public ToolHarpoon(ToolMaterial material, Settings settings) {
        super(material, settings.group(Handler_ItemGroups.ELT_TOOLS));
    }
}
