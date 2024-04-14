package tech.thatgravyboat.skycubed.features.misc;

import tech.thatgravyboat.skycubed.utils.RepoPattern;

import java.util.regex.Pattern;

public class NotificationModule {

    private static final Pattern ORINGO = RepoPattern.get("notification.oringo", "Oringo has brought his Traveling Zoo shop in town!");

    public static void init() {

    }
}
