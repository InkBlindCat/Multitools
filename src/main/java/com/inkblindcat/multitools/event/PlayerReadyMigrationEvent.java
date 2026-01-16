package com.inkblindcat.multitools.event;

import com.hypixel.hytale.codec.lookup.MapKeyMapCodec;
import com.hypixel.hytale.common.plugin.PluginIdentifier;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.asset.type.item.config.Item;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;
import com.hypixel.hytale.server.core.inventory.Inventory;
import com.hypixel.hytale.server.core.inventory.ItemStack;
import com.hypixel.hytale.server.core.modules.interaction.interaction.config.Interaction;
import com.hypixel.hytale.server.core.plugin.PluginBase;
import com.hypixel.hytale.server.core.plugin.PluginManager;
import com.inkblindcat.multitools.Multitools;
import com.inkblindcat.multitools.util.MultitoolMigrationUtil;

public class PlayerReadyMigrationEvent {

    public static void onPlayerReady(PlayerReadyEvent event) {
        Player player = event.getPlayer();
        Inventory inventory = player.getInventory();
        if (inventory == null) return;

        MultitoolMigrationUtil.migrateContainer(
                inventory.getCombinedBackpackStorageHotbar(),
                "Tool_Multitool_",
                "Tool_Hatchet_Pickaxe_Multitool_"
        );

        PluginBase torchPlugin = PluginManager.get().getPlugin(PluginIdentifier.fromString("Buuz135:ThePickaxesPlaceTorches"));
        if (torchPlugin == null) {
            Multitools.getInstance().getLogger().atInfo().log("Torch plugin not found");
        } else {
            Multitools.getInstance().getLogger().atInfo().log("Torch plugin detected");
        }
    }
}
