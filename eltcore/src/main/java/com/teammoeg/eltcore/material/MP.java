/*
 * Copyright (c) 2020. TeamMoeg
 *
 * This file is part of Energy Level Transition.
 *
 * Energy Level Transition is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * Energy Level Transition is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Energy Level Transition.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.teammoeg.eltcore.material;

import com.teammoeg.eltcore.code.ArrayListNoNulls;

/**
 * @author YueSha (GitHub @yuesha-yc)
 * Material Prefix
 */
public class MP {

    public static final ArrayListNoNulls<TagMatPrefix> MAT_PREFIX_ARRAY = new ArrayListNoNulls<>();

    private static TagMatPrefix create(String aPrefixName) {
        TagMatPrefix tagMatPrefix = new TagMatPrefix(aPrefixName);
        MAT_PREFIX_ARRAY.add(tagMatPrefix);
        return tagMatPrefix;
    }

    public static final TagMatPrefix
            ingot = create("prefix.ingot"),
            plate = create("prefix.plate"),
            dust = create("prefix.dust");
}
