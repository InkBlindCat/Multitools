package com.inkblindcat.multitools.event;

import com.hypixel.hytale.component.*;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.EntityEventSystem;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.event.events.ecs.InteractivelyPickupItemEvent;
import com.hypixel.hytale.server.core.inventory.ItemStack;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.inkblindcat.multitools.util.MultitoolMigrationUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PickupItemMigrationSystem extends EntityEventSystem<EntityStore, InteractivelyPickupItemEvent> {
    public PickupItemMigrationSystem() {
        super(InteractivelyPickupItemEvent.class);
    }

    @Override
    public void handle(int index, @Nonnull ArchetypeChunk<EntityStore> archetypeChunk, @Nonnull Store<EntityStore> store,
                       @Nonnull CommandBuffer<EntityStore> commandBuffer, @Nonnull InteractivelyPickupItemEvent interactivelyPickupItemEvent) {

        Ref<EntityStore> ref = archetypeChunk.getReferenceTo(index);
        Player player = store.getComponent(ref, Player.getComponentType());
        player.sendMessage(Message.raw("Test " + player.getDisplayName()));

        ItemStack itemStack = interactivelyPickupItemEvent.getItemStack();
        ItemStack NewItemStack =  MultitoolMigrationUtil.createMigratedItem(
                itemStack,
                "Tool_Multitool_",
                "Tool_Hatchet_Pickaxe_Multitool_"
        );

        interactivelyPickupItemEvent.setItemStack(NewItemStack);
    }


    @Nullable
    @Override
    public Query<EntityStore> getQuery() {
        return PlayerRef.getComponentType();
    }
}
