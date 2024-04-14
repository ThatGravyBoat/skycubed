package tech.thatgravyboat.skycubed.features.stats;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.network.chat.Component;
import tech.thatgravyboat.skycubed.api.events.ActionBarCallback;
import tech.thatgravyboat.skycubed.api.items.ItemAttributes;
import tech.thatgravyboat.skycubed.features.misc.SkyBlockModule;
import tech.thatgravyboat.skycubed.utils.RegexMatcher;
import tech.thatgravyboat.skycubed.utils.RepoPattern;

import java.util.regex.Pattern;

public class PlayerStatsModule {

    private static final Pattern HEALTH_PATTERN = RepoPattern.get("actionbar.health", "§.(?<health>[0-9,]+)/(?<maxhealth>[0-9,]+)❤");
    private static final Pattern DEFENSE_PATTERN = RepoPattern.get("actionbar.defense", "§.(?<defense>[0-9,]+)§.❈ Defense");
    private static final Pattern MANA_PATTERN = RepoPattern.get("actionbar.mana", "§.(?<mana>[0-9,]+)/(?<maxmana>[0-9,]+)✎ Mana");

    private static int maxHealth = 20;
    private static int health = 20;
    private static int maxMana = 100;
    private static int mana = 100;
    private static int defense = 0;

    private static int itemMana = 0;

    public static void init() {
        ActionBarCallback.EVENT.register(message -> {
            if (!SkyBlockModule.isSkyBlock()) return message;
            String text = message.getString();
            RegexMatcher health = RegexMatcher.of(HEALTH_PATTERN, text);
            if (health.found()) {
                PlayerStatsModule.maxHealth = health.integer("maxhealth", PlayerStatsModule.maxHealth);
                PlayerStatsModule.health = health.integer("health", PlayerStatsModule.health);
                text = health.replace("");
            }

            RegexMatcher defense = RegexMatcher.of(DEFENSE_PATTERN, text);
            if (defense.found()) {
                PlayerStatsModule.defense = defense.integer("defense", PlayerStatsModule.defense);
                text = defense.replace("");
            }

            RegexMatcher mana = RegexMatcher.of(MANA_PATTERN, text);
            if (mana.found()) {
                PlayerStatsModule.maxMana = mana.integer("maxmana", PlayerStatsModule.maxMana);
                PlayerStatsModule.mana = mana.integer("mana", PlayerStatsModule.mana);
                text = mana.replace("");
            }
            return Component.literal(text.trim());
        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            itemMana = 0;
            if (client.player == null) return;
            var ability = client.player.getMainHandItem().getCubedAttribute(ItemAttributes.RIGHT_CLICK_ABILITY);
            if (ability == null) return;
            itemMana = ability.rightInt();
        });
    }

    public static float defenceMultiplier() {
        return 1f + (float) defense / 100f;
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

    public static float effectiveHealthPercent() {
        float defenseMultiplier = defenceMultiplier();
        float effectiveMaxHealth = maxHealth * defenseMultiplier;
        float effectiveHealth = health * defenseMultiplier;
        return effectiveHealth / effectiveMaxHealth;
    }

    public static float healthPercent(boolean effective) {
        return effective ? effectiveHealthPercent() : healthPercent();
    }

    public static String healthString() {
        return health + "/" + maxHealth;
    }

    public static String effectiveHealthString() {
        float defenseMultiplier = defenceMultiplier();
        return (int) (health * defenseMultiplier) + "/" + (int) (maxHealth * defenseMultiplier);
    }

    public static String healthString(boolean effective) {
        return effective ? effectiveHealthString() : healthString();
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
