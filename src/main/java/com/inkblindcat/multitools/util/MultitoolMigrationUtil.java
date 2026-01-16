package com.inkblindcat.multitools.util;

import com.hypixel.hytale.server.core.inventory.ItemStack;
import com.hypixel.hytale.server.core.inventory.container.ItemContainer;

public class MultitoolMigrationUtil {

    public static void migrateContainer(
            ItemContainer container,
            String oldPrefix,
            String newPrefix
    ) {
        if (container == null) return;

        short size = container.getCapacity();
        for (short slot = 0; slot < size; slot++) {
            migrateSlot(container, slot, oldPrefix, newPrefix);
        }
    }

    public static void migrateSlot(
            ItemContainer container,
            short slot,
            String oldPrefix,
            String newPrefix
    ) {
        if (container == null) return;

        ItemStack stack = container.getItemStack(slot);
        ItemStack migrated = createMigratedItem(stack, oldPrefix, newPrefix);

        if (migrated != stack) {
            container.setItemStackForSlot(slot, migrated);
        }
    }

    public static ItemStack createMigratedItem(
            ItemStack stack,
            String oldPrefix,
            String newPrefix
    ) {
        if (stack == null || stack.isEmpty()) return stack;

        String itemId = stack.getItemId();
        if (itemId == null || !itemId.startsWith(oldPrefix)) return stack;

        String suffix = itemId.substring(oldPrefix.length());
        String newId = newPrefix + suffix;

        return new ItemStack(newId, stack.getQuantity())
                .withDurability(stack.getDurability());
    }
}
