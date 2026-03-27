package com.inkblindcat.multitools.event;

import com.hypixel.hytale.server.core.inventory.InventoryChangeEvent;
import com.hypixel.hytale.server.core.inventory.container.ItemContainer;
import com.inkblindcat.multitools.util.MultitoolDurabilityUtil;


public class InventoryChangeMtConfigEvent {
    public static void onInventoryChangeEvent(InventoryChangeEvent event) {
        ItemContainer container = event.getItemContainer();
        MultitoolDurabilityUtil.migrateContainerDurability(container);
    }
}
