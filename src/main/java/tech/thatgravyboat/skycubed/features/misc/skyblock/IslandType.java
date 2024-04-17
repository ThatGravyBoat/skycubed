package tech.thatgravyboat.skycubed.features.misc.skyblock;

import it.unimi.dsi.fastutil.objects.Object2ReferenceMap;
import it.unimi.dsi.fastutil.objects.Object2ReferenceOpenHashMap;
import net.minecraft.Util;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

public enum IslandType {
    UNKNOWN,
    RIFT,
    ;

    private static final Object2ReferenceMap<String, IslandType> BY_ID = Util.make(new Object2ReferenceOpenHashMap<>(), map -> {
        for (IslandType type : values()) {
            map.put(type.name().toLowerCase(Locale.ROOT), type);
        }
    });

    public static IslandType fromId(@Nullable String string) {
        if (string == null) return UNKNOWN;
        return BY_ID.getOrDefault(string.toLowerCase(Locale.ROOT), UNKNOWN);
    }
}
