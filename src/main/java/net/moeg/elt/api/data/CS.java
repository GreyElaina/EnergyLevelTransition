package net.moeg.elt.api.data;


import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;

import java.util.Set;

public class CS {

    /** Because "true" and "false" are too long. Some Programmers might wanna kill me for that, but this looks much better than true and false, and also it is better to have something that is not 4 and 5 Characters long, because of symmetry */
    public static final boolean T = true, F = false;

    /** A few Default Values for Light Opacity. */
    public static final int LIGHT_OPACITY_NONE = 0, LIGHT_OPACITY_LEAVES = 1, LIGHT_OPACITY_WATER = 3, LIGHT_OPACITY_MAX = 255;

    /** Colors in hex */
    public static int
    RED = 0xf91313,
    BLUE = 0x3495eb,
    WHITE = 0xffffff;

    public static final int[] RAINBOW_ARRAY = {
            0xff0000,
            0xff4000,
            0xff8000,
            0xffc000,
            0xffff00,
            0xc0ff00,
            0x80ff00,
            0x40ff00,
            0x00ff00,
            0x00ff40,
            0x00ff80,
            0x00ffc0,
            0x00ffff,
            0x00c0ff,
            0x0080ff,
            0x0040ff,
            0x0000ff,
            0x4000ff,
            0x8000ff,
            0xc000ff,
            0xff00ff,
            0xff00c0,
            0xff0080,
            0xff0040,
    };

}
