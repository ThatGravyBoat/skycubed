package tech.thatgravyboat.skycubed.features.stats;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import tech.thatgravyboat.skycubed.api.events.ActionBarCallback;
import tech.thatgravyboat.skycubed.api.items.ItemAttributes;
import tech.thatgravyboat.skycubed.features.misc.SkyBlockModule;
import tech.thatgravyboat.skycubed.utils.RegexMatcher;
import tech.thatgravyboat.skycubed.utils.RepoPattern;
import tech.thatgravyboat.skycubed.utils.TextUtils;

import java.util.regex.Pattern;

public class PlayerStatsModule {

    private static final Pattern HEALTH_PATTERN = RepoPattern.get("actionbar.health", "(?<health>[0-9,]+)/(?<maxhealth>[0-9,]+)❤");
    private static final Pattern DEFENSE_PATTERN = RepoPattern.get("actionbar.defense", "(?<defense>[0-9,]+)❈ defense");
    private static final Pattern MANA_PATTERN = RepoPattern.get("actionbar.mana", "(?<mana>[0-9,]+)/(?<maxmana>[0-9,]+)✎ mana");

    private static int maxHealth = 20;
    private static int health = 20;
    private static int maxMana = 100;
    private static int mana = 100;
    private static int defense = 0;

    private static int itemMana = 0;

    public static void init() {
        ActionBarCallback.EVENT.register(message -> {
            if (!SkyBlockModule.isSkyBlock()) return;
            String text = TextUtils.toSimpleText(message);
            RegexMatcher health = RegexMatcher.of(HEALTH_PATTERN, text);
            if (health.found()) {
                PlayerStatsModule.maxHealth = health.integer("maxhealth", PlayerStatsModule.maxHealth);
                PlayerStatsModule.health = health.integer("health", PlayerStatsModule.health);
            }

            RegexMatcher defense = RegexMatcher.of(DEFENSE_PATTERN, text);
            if (defense.found()) {
                PlayerStatsModule.defense = defense.integer("defense", PlayerStatsModule.defense);
            }

            RegexMatcher mana = RegexMatcher.of(MANA_PATTERN, text);
            if (mana.found()) {
                PlayerStatsModule.maxMana = mana.integer("maxmana", PlayerStatsModule.maxMana);
                PlayerStatsModule.mana = mana.integer("mana", PlayerStatsModule.mana);
            }
        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            itemMana = 0;
            if (client.player == null) return;
            var ability = client.player.getMainHandItem().getCubedAttribute(ItemAttributes.RIGHT_CLICK_ABILITY);
            if (ability == null) return;
            itemMana = ability.rightInt();
        });
    }

    public static int maxHealth() {
        return maxHealth;
    }

    public static int health() {
        return health;
    }

    public static float healthPercent() {
        return (float) health / (float) maxHealth;
    }

    public static String healthString() {
        return health + "/" + maxHealth;
    }

    public static int maxMana() {
        return maxMana;
    }

    public static int mana() {
        return mana;
    }

    public static float manaPercent() {
        return (float) mana / (float) maxMana;
    }

    public static String manaString() {
        return mana + "/" + maxMana;
    }

    public static int itemMana() {
        return itemMana;
    }

    public static float itemManaPercent() {
        return (float) itemMana / (float) maxMana;
    }

    public static int defense() {
        return defense;
    }
}
