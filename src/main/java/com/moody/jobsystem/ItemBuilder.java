package com.moody.jobsystem;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ItemBuilder {

    private ItemMeta itemMeta;
    private ItemStack itemStack;

    public ItemBuilder(Material material) {

        this.itemStack = new ItemStack(material);
        this.itemMeta = itemStack.getItemMeta();

    }

    public ItemBuilder setDisplayName(String s) {
        this.itemMeta.setDisplayName(s);
        return this;
    }

    public ItemBuilder setLocalizedName(String s) {
        this.itemMeta.setLocalizedName(s);
        return this;
    }

    public ItemBuilder setLore(String... s) {
        this.itemMeta.setLore(Arrays.asList(s));
        return this;
    }

    public ItemBuilder setUnbreable(boolean s) {
        this.itemMeta.setUnbreakable(s);
        return this;
    }

    public ItemBuilder addItemFlags(ItemFlag... itemFlags) {
        this.itemMeta.addItemFlags(itemFlags);
        return this;
    }

    public ItemStack build() {
        this.itemStack.setItemMeta(this.itemMeta);
        return itemStack;
    }

}

