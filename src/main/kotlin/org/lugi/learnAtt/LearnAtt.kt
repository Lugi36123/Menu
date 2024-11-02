package org.lugi.learnAtt

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.player.PlayerSwapHandItemsEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin
import org.lugi.learnAtt.InvControll.Window

class LearnAtt : JavaPlugin() {

    var serverInstance: JavaPlugin = this

    override fun onEnable() {
        // Plugin startup logic

        server.pluginManager.apply {
            registerEvents(
                object : Listener {
                    @EventHandler
                    fun onFKey(e: PlayerSwapHandItemsEvent) {
                        e.player.apply {
                            if (this.world.name == "world") {
                                if (this.isSneaking == false) return

                                e.isCancelled = true

                                Window.createInventory(e.player, 5, Component.text("메뉴"))

                                Window.setSlot(e.player as Player, 1, 2,
                                    ItemStack(Material.PAPER))

                                Window.openInventory(e.player)
                            }
                        }
                    }
                }, serverInstance)

            registerEvents(
                object : Listener {
                    @EventHandler
                    fun onClose(e: InventoryCloseEvent) {
                        if (Window.getInventory(e.player as Player) == e.inventory){
                            Window.deleteInventory(e.player as Player)
                        }
                    }
                }, serverInstance)

            registerEvents(
                object : Listener {
                    @EventHandler
                    fun onClick(e: InventoryClickEvent){
                        if (Window.getInventory(e.whoClicked as Player) == e.inventory){

                        }
                    }
                }
                ,serverInstance
            )
        }
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }


}
