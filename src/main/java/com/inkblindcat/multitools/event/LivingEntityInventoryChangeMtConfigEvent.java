package com.inkblindcat.multitools.event;

import com.hypixel.hytale.server.core.entity.Entity;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.event.events.entity.LivingEntityInventoryChangeEvent;
import com.hypixel.hytale.server.core.inventory.container.ItemContainer;
import com.inkblindcat.multitools.util.MultitoolDurabilityUtil;


public class LivingEntityInventoryChangeMtConfigEvent {

    public static void onLivingEntityInventoryChangeEvent(LivingEntityInventoryChangeEvent event) {
        Entity entity = event.getEntity();
        if (!(entity instanceof Player)) return;

        ItemContainer container = event.getItemContainer();
        MultitoolDurabilityUtil.migrateContainerDurability(container);
    }
}
