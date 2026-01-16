package com.inkblindcat.multitools;

import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;
import com.hypixel.hytale.server.core.modules.interaction.interaction.config.Interaction;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.inkblindcat.multitools.event.PlayerReadyMigrationEvent;
import com.inkblindcat.multitools.event.ContainerMigrationSystem;
import com.inkblindcat.multitools.interaction.MultitoolAltInteraction;

import javax.annotation.Nonnull;

public class Multitools extends JavaPlugin {
    private static Multitools INSTANCE;

    public Multitools(@Nonnull JavaPluginInit init) {
        super(init);
        INSTANCE = this;
    }

    @Override
    protected void setup() {
        this.getEventRegistry().registerGlobal(PlayerReadyEvent.class, PlayerReadyMigrationEvent::onPlayerReady);
        this.getEntityStoreRegistry().registerSystem(new ContainerMigrationSystem());
//        this.getEntityStoreRegistry().registerSystem(new InteractivelyPickupItemMigrationSystem());

        this.getCodecRegistry(Interaction.CODEC).register("IBC_Multitools_MultitoolAltInteraction", MultitoolAltInteraction.class, MultitoolAltInteraction.CODEC);
    }

    public static Multitools getInstance() {
        return INSTANCE;
    }
}
