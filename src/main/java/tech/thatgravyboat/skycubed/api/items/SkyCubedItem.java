package tech.thatgravyboat.skycubed.api.items;

import com.teamresourceful.resourcefullib.common.exceptions.NotImplementedException;

public interface SkyCubedItem {

    default <T> T getCubedAttribute(SkyCubedItemAttribute<T> attribute) {
        throw new NotImplementedException();
    }
}
