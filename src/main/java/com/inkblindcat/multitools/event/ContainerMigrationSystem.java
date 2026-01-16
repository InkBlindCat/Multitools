package com.inkblindcat.multitools.event;

import com.hypixel.hytale.component.ArchetypeChunk;
import com.hypixel.hytale.component.CommandBuffer;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.EntityEventSystem;
import com.hypixel.hytale.math.vector.Vector3i;
import com.hypixel.hytale.protocol.BlockPosition;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.event.events.ecs.UseBlockEvent;
import com.hypixel.hytale.server.core.modules.entity.component.TransformComponent;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.chunk.WorldChunk;
import com.hypixel.hytale.server.core.universe.world.meta.BlockState;
import com.hypixel.hytale.server.core.universe.world.meta.state.ItemContainerState;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.core.util.TargetUtil;
import com.inkblindcat.multitools.util.MultitoolMigrationUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ContainerMigrationSystem extends EntityEventSystem<EntityStore, UseBlockEvent.Pre> {

    public ContainerMigrationSystem() {
        super(UseBlockEvent.Pre.class);
    }

    @Override
    public void handle(int index, @Nonnull ArchetypeChunk<EntityStore> archetypeChunk, @Nonnull Store<EntityStore> store,
                       @Nonnull CommandBuffer<EntityStore> commandBuffer, @Nonnull UseBlockEvent.Pre pre) {

        Ref<EntityStore> ref = archetypeChunk.getReferenceTo(index);
        Player player = store.getComponent(ref, Player.getComponentType());

        final TransformComponent transform = archetypeChunk.getComponent(index, TransformComponent.getComponentType());
        if (transform != null) {
            final WorldChunk chunk = transform.getChunk();
            if (chunk != null) {
                final Vector3i targetBlockPos = TargetUtil.getTargetBlock(archetypeChunk.getReferenceTo(index), 5, commandBuffer);
                if (targetBlockPos != null) {
                    final BlockPosition pos = player.getWorld().getBaseBlock(new BlockPosition(targetBlockPos.x, targetBlockPos.y, targetBlockPos.z));
                    final BlockState state = chunk.getState(pos.x, pos.y, pos.z);

                    if (state instanceof ItemContainerState containerState) {
                        MultitoolMigrationUtil.migrateContainer(
                                containerState.getItemContainer(),
                                "Tool_Multitool_",
                                "Tool_Hatchet_Pickaxe_Multitool_"
                        );
                    }
                }
            }
        }
    }

    @Nullable
    @Override
    public Query<EntityStore> getQuery() {
        return PlayerRef.getComponentType();
    }
}
