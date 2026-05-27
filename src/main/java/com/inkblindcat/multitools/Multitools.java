package com.inkblindcat.multitools;

import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;

import javax.annotation.Nonnull;

public class Multitools extends JavaPlugin {
    private static Multitools INSTANCE;

    public Multitools(@Nonnull JavaPluginInit init) {
        super(init);
        INSTANCE = this;
    }

    @Override
    protected void setup() {
        super.setup();
    }

    public static Multitools getInstance() {
        return INSTANCE;
    }
}
