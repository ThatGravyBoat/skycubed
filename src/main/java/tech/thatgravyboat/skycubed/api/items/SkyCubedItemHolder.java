package tech.thatgravyboat.skycubed.api.items;

import com.teamresourceful.resourcefullib.common.exceptions.NotImplementedException;
import org.jetbrains.annotations.Nullable;

public interface SkyCubedItemHolder {

    @Nullable
    default <T> T getCubedAttribute(SkyCubedItemAttribute<T> attribute) {
        throw new NotImplementedException();
    }

    default boolean hasCubedAttribute(SkyCubedItemAttribute<Boolean> attribute) {
        Boolean value = getCubedAttribute(attribute);
        return value != null && value;
    }
}
