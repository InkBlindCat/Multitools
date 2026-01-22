package com.inkblindcat.multitools.event;

import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;
import com.hypixel.hytale.server.core.inventory.Inventory;
import com.hypixel.hytale.server.core.inventory.container.CombinedItemContainer;
import com.inkblindcat.multitools.util.MultitoolDurabilityUtil;


public class PlayerReadyMtConfigEvent {

    public static void onPlayerReady(PlayerReadyEvent event) {
        Player player = event.getPlayer();
        Inventory inventory = player.getInventory();
        if (inventory == null) return;

        CombinedItemContainer container = inventory.getCombinedBackpackStorageHotbar();
        MultitoolDurabilityUtil.migrateContainerDurability(container);
    }
}
