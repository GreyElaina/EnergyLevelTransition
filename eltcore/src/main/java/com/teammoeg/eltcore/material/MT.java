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
import com.teammoeg.eltcore.code.HashSetNoNulls;

import static com.teammoeg.eltcore.data.TD.ItemGenerator.*;

/**
 * @author YueSha (GitHub @yuesha-yc)
 */
public class MT {
    public static final HashSetNoNulls<TagMat> ALL_MATERIALS_REGISTERED_HERE = new HashSetNoNulls<>();
    public static final ArrayListNoNulls<TagMat> MAT_ARRAY = new ArrayListNoNulls<>();

    protected static TagMat create (String aNameTag) {
        TagMat rMaterial = TagMat.createMat(aNameTag, aNameTag);
        ALL_MATERIALS_REGISTERED_HERE.add(rMaterial);
        MAT_ARRAY.add(rMaterial);
        return rMaterial;
    }
    public static final TagMat EMPTY = create("Empty");
    public static final TagMat Cu = create("Copper").put(INGOTS, PLATES, DUSTS);
    public static final TagMat H = create("Hydrogen").put(GASSES);
    public static final TagMat He = create("Helium").put(GASSES);
    public static final TagMat Li = create("Lithium").put(INGOTS, PLATES);
    public static final TagMat Be = create("Beryllium").put(INGOTS, PLATES);
}
