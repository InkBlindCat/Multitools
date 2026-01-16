package com.inkblindcat.multitools.interaction;

import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.common.plugin.PluginIdentifier;
import com.hypixel.hytale.component.CommandBuffer;
import com.hypixel.hytale.math.vector.Vector3i;
import com.hypixel.hytale.protocol.InteractionState;
import com.hypixel.hytale.protocol.InteractionType;
import com.hypixel.hytale.server.core.entity.InteractionContext;
import com.hypixel.hytale.server.core.inventory.ItemStack;
import com.hypixel.hytale.server.core.modules.interaction.interaction.CooldownHandler;
import com.hypixel.hytale.server.core.modules.interaction.interaction.config.client.SimpleBlockInteraction;
import com.hypixel.hytale.server.core.plugin.PluginBase;
import com.hypixel.hytale.server.core.plugin.PluginManager;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MultitoolAltInteraction extends SimpleBlockInteraction {
    public static final BuilderCodec<MultitoolAltInteraction> CODEC =
            BuilderCodec.builder(
                    MultitoolAltInteraction.class,
                    MultitoolAltInteraction::new,
                    SimpleBlockInteraction.CODEC
            ).build();


    @Override
    protected void interactWithBlock(@Nonnull World world, @Nonnull CommandBuffer<EntityStore> commandBuffer, @Nonnull InteractionType interactionType, @Nonnull InteractionContext interactionContext, @Nullable ItemStack itemStack, @Nonnull Vector3i vector3i, @Nonnull CooldownHandler cooldownHandler) {
        if (torchPluginMissing()) {
            interactionContext.getState().state = InteractionState.Failed;
        } else {
            interactionContext.getState().state = InteractionState.Finished;
        }
    }

    @Override
    protected void simulateInteractWithBlock(@Nonnull InteractionType interactionType, @Nonnull InteractionContext interactionContext, @Nullable ItemStack itemStack, @Nonnull World world, @Nonnull Vector3i vector3i) {

    }

    private boolean torchPluginMissing() {
        PluginBase torchPlugin = PluginManager.get().getPlugin(PluginIdentifier.fromString("Buuz135:ThePickaxesPlaceTorches"));
        return torchPlugin == null;
    }
}
