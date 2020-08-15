package net.moeg.elt.items.tools;

import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.moeg.eltcore.handlers.Handler_ItemGroups;

//刮刀
public class ToolHammer extends ToolItem {
    public ToolHammer(ToolMaterial material, Settings settings) {
        super(material, settings.group(Handler_ItemGroups.ELT_TOOLS));
    }
}
